/*
 * 
 */
package domini;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import domini.netInfo.ParseRoute;

/**
 * Controlador de la l'anàlisi, és l'encarregat de crear l'anàlisi i actualitzar
 * les dades d'aquest i través d'aquest controlador també guardem i carreguem
 * els anàlisis realitzats.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisController {

    /** Objecte encarregar d'analitzar la cpu. */
    private AnalisisCPU cpu;

    /** Duració de l'anàlisi. */
    private String duracio;

    /** Objecte encarregar d'analitzar el disc dur. */
    private AnalisisHDD hdd;

    /** Identificador de l'ordinador. */
    private String idPC;

    /** Objecte encarregar d'analitzar la targeta de xarxa. */
    private AnalisisNET net;

    /** El controlador d'opcions. */
    private OpcionsController opcionsController;

    /** Objecte encarregar d'analitzar la memòria RAM. */
    private AnalisisRAM ram;

    /**
     * Creadora per defecte de l'anàlisi controller. Inicia un nou objecte
     * d'anàlisi per a cada component i les opcions de l'anàlisi.
     * 
     * @param oc
     *            Controlador d'opcions del procés
     */
    public AnalisisController(OpcionsController oc) {
	cpu = new AnalisisCPU();
	hdd = new AnalisisHDD();
	net = new AnalisisNET();
	ram = new AnalisisRAM();
	duracio = "-1";
	opcionsController = oc;
	try {
	    ParseRoute pr = ParseRoute.getInstance();
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
	    InetAddress ip = InetAddress.getByName(pr.getLocalIPAddress());
	    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	    byte[] mac = network.getHardwareAddress();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < mac.length; i++) {
		sb.append(String.format("%02X%s", mac[i],
			(i < mac.length - 1) ? "-" : ""));
	    }
	    idPC = "Hostname: "
		    + java.net.InetAddress.getLocalHost().getHostName()
		    + "  // MAC: " + sb + "  // " + "Data: "
		    + dateFormat.format(cal.getTime());
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (SocketException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Acció que es realitza cada segon i s'encarrega de, per cada component que
     * formi part del procés, actualitzar les seves dades.
     */
    public void analitzar() {
	if (opcionsController.isCpu()) {
	    cpu.updateCPU();
	}
	if (opcionsController.isHdd()) {
	    hdd.updateHDD();
	}
	if (opcionsController.isNet()) {
	    net.updateNET();
	}
	if (opcionsController.isRam()) {
	    ram.updateRAM();
	}
    }

    /**
     * Carregar analisi.
     * 
     * @param selectedFile
     *            URL del fitxer a carregar.
     */
    public void carregarAnalisi(File selectedFile) {
	ArrayList<Object> dades = GestioDades.carregar(selectedFile);
	this.opcionsController.setCpu((boolean) dades.get(6));
	this.opcionsController.setRam((boolean) dades.get(7));
	this.opcionsController.setHdd((boolean) dades.get(8));
	this.opcionsController.setNet((boolean) dades.get(9));
	if (opcionsController.isCpu())
	    cpu.setTot((Object[]) dades.get(0));
	if (opcionsController.isRam())
	    ram.setTot((Object[]) dades.get(1));
	if (opcionsController.isHdd())
	    hdd.setTot((Object[]) dades.get(2));
	if (opcionsController.isNet())
	    net.setTot((Object[]) dades.get(3));
	this.idPC = (String) dades.get(4);
	this.duracio = (String) dades.get(5);
    }

    /**
     * Obté la informació del resultat de l'anàlisi del processador.
     * 
     * @return Informació de la CPU.
     */
    public Float[] getCpuInfo() {
	Float[] res = new Float[3];
	res[0] = cpu.getAvgPercentatge();
	res[1] = cpu.getMaxPercentatge();
	res[2] = cpu.getMinPercentatge();
	return res;
    }

    public String getDuracio() {
	return duracio;

    }

    /**
     * Obté la informació de la variació de l'ús del component passat per
     * paràmetre al llarg de l'anàlisi. A la capa de presentació es converteix
     * en les dades de la gràfica.
     * 
     * @param string
     *            Identificador del component
     * @return Evolució de l'ús del component en el temps
     */
    public TimeSeries getEvol(String string) {
	TimeSeries res = new TimeSeries(string);
	ArrayList<Float> aux = new ArrayList<Float>();
	ArrayList<Second> temps = new ArrayList<Second>();
	Iterator<Float> itAux;
	Iterator<Second> itTemps;
	switch (string) {
	case "CPU":
	    aux = cpu.getGraf();
	    temps = cpu.getTemps();
	    break;
	case "HDD":
	    aux = hdd.getGraf();
	    temps = hdd.getTemps();
	    break;
	case "RAM":
	    aux = ram.getGraf();
	    temps = ram.getTemps();
	    break;
	case "NET":
	    aux = net.getGraf();
	    temps = net.getTemps();
	    break;
	}
	itAux = aux.iterator();
	itTemps = temps.iterator();
	while (itAux.hasNext() && itTemps.hasNext()) {
	    res.add(itTemps.next(), itAux.next());
	}
	return res;
    }

    /**
     * Obté la informació del resultat de l'anàlisi del disc dur.
     * 
     * @return Informació del disc dur
     */
    public float[] getHddInfo() {
	float[] res = new float[5];
	res[0] = (float) (((hdd.getAvgTotal() - hdd.getInicial()) / 1024 / 1024) / hdd
		.getTemps().size());
	res[1] = hdd.getMaxTotal();
	res[2] = hdd.getMinTotal();
	res[3] = hdd.getNumEscriptures() - hdd.getNumEscripturesInicial();
	res[4] = hdd.getNumLectures() - hdd.getNumLecturesInicial();
	return res;
    }

    /**
     * Obté l'identificador del PC on s'ha executat l'anàlisi.
     * 
     * @return Identificador del PC
     */
    public String getIdPC() {
	return idPC;
    }

    /**
     * Obté la informació del component que se li passa per paràmetre.
     * 
     * @param string
     *            Identificador del component.
     * @return Informació del component.
     */
    public String getInfoComponents(String string) {
	String res = "";
	switch (string) {
	case "CPU":
	    res = cpu.getCpuInfo();
	    break;
	case "HDD":
	    res = hdd.getHddInfo();
	    break;
	case "RAM":
	    res = ram.getRamInfo();
	    break;
	case "NET":
	    res = net.getNetInfo();
	    break;
	}
	return res;
    }

    /**
     * Obté la informació del resultat de l'anàlisi de la targeta de xarxa.
     * 
     * @return Informació de la targeta de xarxa
     */
    public Float[] getNetInfo() {
	Float[] res = new Float[6];
	res[0] = net.getAvgPercentatge();
	res[1] = (float) (net.getAvgTotal() / net.getComptador());
	res[2] = net.getMaxPercentatge();
	res[3] = (float) net.getMaxTotal();
	res[4] = net.getMinPercentatge();
	res[5] = (float) net.getMinTotal();
	return res;
    }

    /**
     * Obté la informació del resultat de l'anàlisi de la memòria RAM.
     * 
     * @return Informació de la memòria RAM
     */
    public Float[] getRamInfo() {
	Float[] res = new Float[7];
	res[0] = ram.getAvgPercentatge();
	res[1] = (float) (ram.getAvgTotal() / ram.getComptador());
	res[2] = ram.getMaxPercentatge();
	res[3] = (float) ram.getMaxTotal();
	res[4] = ram.getMinPercentatge();
	res[5] = (float) ram.getMinTotal();
	res[6] = (float) ram.getMaxRam();
	return res;
    }

    /**
     * Guarda tota la informació relacionada amb l'anàlisi.
     * 
     * @param fileName
     *            nom del fitxer on es guardarà la informació
     * @param duracio
     *            Duració de l'anàlisi realitzat
     */
    public void guardarAnalisi(String fileName, String duracio) {
	ArrayList<Object> resultat = new ArrayList<Object>();
	resultat.add(cpu.getTot());
	resultat.add(ram.getTot());
	resultat.add(hdd.getTot());
	resultat.add(net.getTot());
	resultat.add(idPC);
	resultat.add(duracio);
	resultat.add(opcionsController.isCpu());
	resultat.add(opcionsController.isRam());
	resultat.add(opcionsController.isHdd());
	resultat.add(opcionsController.isNet());
	GestioDades.guardar(resultat, fileName);
    }

    /**
     * Defineix la duració de l'anàlisi en format String
     * 
     * @param s
     *            Duració de l'anàlisi
     */
    public void setDuracio(String s) {
	this.duracio = s;

    }

}
