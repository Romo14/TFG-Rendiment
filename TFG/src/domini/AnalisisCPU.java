package domini;

import java.util.ArrayList;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada de realitzar l'anàlisi del processador
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 * 
 */
public class AnalisisCPU extends Analisi {


    /**
     * Creadora per defecte de l'objecte AnalisisCPU
     */
    public AnalisisCPU() {
	super();
    }
    /**
     * Obté el cpu info.
     * 
     * @return cpu info
     */
    public String getInfoComponent() {
	try {
	    CpuInfo info = sigar.getCpuInfoList()[0];
	    return "Fabricant: " + info.getVendor() + " Model: "
		    + info.getModel() + ", Nuclis: " + info.getTotalCores()
		    + ", Freqüència màxima: " + info.getMhz()
		    + " MHz, Sockets:" + info.getTotalSockets()
		    + ", Nuclis per socket: " + info.getCoresPerSocket();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return "";
    }

    /**
     * Obté el tot.
     * 
     * @return tot
     */
    public Object[] getTot() {
	Object[] tot = new Object[6];
	tot[0] = avgPercentatge;
	tot[1] = maxPercentatge;
	tot[2] = minPercentatge;
	tot[3] = graf;
	tot[4] = temps;
	tot[5] = info;
	return tot;
    }

    /**
     * Restaura les dades de l'anàlisi de la cpu. S'utilitza quan carreguem un
     * anàlisi ja realitzat.
     * 
     * @param dadesCpu
     *            Dades de la cpu
     */
    @SuppressWarnings("unchecked")
    public void setTot(Object[] dadesCpu) {
	avgPercentatge = (float) dadesCpu[0];
	maxPercentatge = (float) dadesCpu[1];
	minPercentatge = (float) dadesCpu[2];
	graf = (ArrayList<Float>) dadesCpu[3];
	temps = (ArrayList<Second>) dadesCpu[4];
	info = (String) dadesCpu[5];
    }

    /**
     * Actualitza les dades de l'ús de la cpu. Obté els valors actuals i
     * comprova actualitza els paràmetres adients.
     */
    public void update() {
	CpuPerc cpuPerc = null;
	try {
	    cpuPerc = sigar.getCpuPerc();
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	long used = (long) (cpuPerc.getCombined() * 100);
	graf.add((float) (cpuPerc.getCombined() * 100));
	temps.add(segon);
	segon = (Second) segon.next();
	if (used > maxTotal) {
	    maxPercentatge = (float) (cpuPerc.getCombined() * 100);
	    maxTotal = used;
	}
	if (used < minTotal) {
	    minPercentatge = (float) (cpuPerc.getCombined() * 100);
	    minTotal = used;
	}
	++comptador;
	avgTotal += used;
	avgPercentatge = (float) (avgTotal / comptador);
    }
}
