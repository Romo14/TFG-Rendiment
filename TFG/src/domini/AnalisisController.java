/*
 * 
 */
package domini;

import java.util.ArrayList;
import java.util.Iterator;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

/**
 * Controlador de l'an�lisis dels components. Classe que s'encarrega de definir els objectes que analitzaran l'ordinador. Tamb� gestiona el pass d'informaci� entre aquests objectes i la capa de domini
 */
/**
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisController {

    /** Les opcions de l'an�lisis */
    private static OpcionsController opcionsController;

    /** Objecte que analitzar� la CPU */
    private AnalisisCPU cpu;

    /** Objecte que analitzar� la GPU */
    private AnalisisGPU gpu;

    /** Objecte que analitzar� el disc dur */
    private AnalisisHDD hdd;

    /** Objecte que analitzar� la targeta de xarxa */
    private AnalisisNET net;

    /** Objecte que analitzar� la mem�ria RAM */
    private AnalisisRAM ram;

    /**
     * Creadora per defecte del controlador de l'an�lisis. Crea un objecte per
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
     * Funci� encarregada de actualitzar la informaci� de l'�s dels components
     * del sistema depenen de si aquests formen part de l'an�lisis o no.
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
     * Obt� la informaci� del m�xim, m�nim i mitjana de l'�s de la CPU. Aquesta
     * funci� es crida un cop acabat l'an�lisis.
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
     * Obt�, per cada component analitzat, el llistat de valors comptabilitzats
     * al llarg del temps per crear la gr�fica.
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
     * Obt� la informaci� del m�xim, m�nim i mitjana de l'�s del disc dur.
     * Aquesta funci� es crida un cop acabat l'an�lisis.
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
     * Obt� la informaci� del m�xim, m�nim i mitjana de l'�s de la targeta de
     * xarxa. Aquesta funci� es crida un cop acabat l'an�lisis.
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
     * Obt� la informaci� del m�xim, m�nim i mitjana de l'�s de la mem�ria RAM.
     * Aquesta funci� es crida un cop acabat l'an�lisis.
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
     * Obt� la informaci� de la GPU. Aquesta funci� es crida un cop acabat
     * l'an�lisis.
     * 
     * @return the gpu info
     */
    public String getGpuInfo() {
	return gpu.getInfo();
    }

    /**
     * Obt� detalls del component que passem com a par�metre
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
