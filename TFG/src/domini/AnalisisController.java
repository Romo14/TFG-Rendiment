/*
 * 
 */
package domini;

import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

/**
 * Controlador de l'anàlisis dels components. Classe que s'encarrega de definir els objectes que analitzaran l'ordinador. També gestiona el pass d'informació entre aquests objectes i la capa de domini
 */
/**
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisController {

    /** Les opcions de l'anàlisis */
    private static OpcionsController opcionsController;

    /** Objecte que analitzarà la CPU */
    private AnalisisCPU cpu;

    /** Objecte que analitzarà la GPU */
    private AnalisisGPU gpu;

    /** Objecte que analitzarà el disc dur */
    private AnalisisHDD hdd;

    /** Objecte que analitzarà la targeta de xarxa */
    private AnalisisNET net;

    /** Objecte que analitzarà la memòria RAM */
    private AnalisisRAM ram;

    /**
     * Creadora per defecte del controlador de l'anàlisis. Crea un objecte per
     * cada component a analitzar
     * 
     * @param oc
     *            El controlador de les opcions
     */
    public AnalisisController(OpcionsController oc) {
	cpu = new AnalisisCPU();
	gpu = new AnalisisGPU();
	hdd = new AnalisisHDD();
	net = new AnalisisNET();
	ram = new AnalisisRAM();
	opcionsController = oc;
    }

    /**
     * Funció encarregada de actualitzar la informació de l'ús dels components
     * del sistema depenen de si aquests formen part de l'anàlisis o no.
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
     * Obté la informació del màxim, mínim i mitjana de l'ús de la CPU. Aquesta
     * funció es crida un cop acabat l'anàlisis.
     * 
     * @return the cpu info
     */
    public Float[] getCpuInfo() {
	Float[] res = new Float[3];
	res[0] = cpu.getAvgPercentatge();
	res[1] = cpu.getMaxPercentatge();
	res[2] = cpu.getMinPercentatge();
	return res;
    }

    /**
     * Obté, per cada component analitzat, el llistat de valors comptabilitzats
     * al llarg del temps per crear la gràfica.
     * 
     * @param string
     *            String que indica de quin component volem obtenir el llistat
     *            de valors
     * @return El llistat amb els valors i el temps en que s'han obtingut
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
     * Obté la informació del màxim, mínim i mitjana de l'ús del disc dur.
     * Aquesta funció es crida un cop acabat l'anàlisis.
     * 
     * @return the hdd info
     */
    public float[] getHddInfo() {
	float[] res = new float[3];
	res[0] = (float) (((hdd.getAvgTotal() - hdd.getInicial()) / 1024 / 1024) / hdd
		.getTemps().size());
	res[1] = hdd.getMaxTotal();
	res[2] = hdd.getMinTotal();
	return res;
    }

    /**
     * Obté la informació del màxim, mínim i mitjana de l'ús de la targeta de
     * xarxa. Aquesta funció es crida un cop acabat l'anàlisis.
     * 
     * @return the net info
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
     * Obté la informació del màxim, mínim i mitjana de l'ús de la memòria RAM.
     * Aquesta funció es crida un cop acabat l'anàlisis.
     * 
     * @return the ram info
     */
    public Float[] getRamInfo() {
	Float[] res = new Float[6];
	res[0] = ram.getAvgPercentatge();
	res[1] = (float) (ram.getAvgTotal() / ram.getComptador());
	res[2] = ram.getMaxPercentatge();
	res[3] = (float) ram.getMaxTotal();
	res[4] = ram.getMinPercentatge();
	res[5] = (float) ram.getMinTotal();
	return res;
    }

    /**
     * Obté la informació de la GPU. Aquesta funció es crida un cop acabat
     * l'anàlisis.
     * 
     * @return the gpu info
     */
    public String getGpuInfo() {
	return gpu.getInfo();
    }

    /**
     * Obté detalls del component que passem com a paràmetre
     * 
     * @param string
     *            Identificador del component
     * @return detalls del component seleccionat
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
}
