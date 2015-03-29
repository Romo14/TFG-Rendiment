package domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * Classe encarregada de realitzar l'anàlisi de la targeta de xarxa.
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisNET {

    /**  rx change map. */
    private static Map<String, List<Long>> rxChangeMap = new HashMap<String, List<Long>>();

    /**  rx current map. */
    private static Map<String, Long> rxCurrentMap = new HashMap<String, Long>();

    /**  tx change map. */
    private static Map<String, List<Long>> txChangeMap = new HashMap<String, List<Long>>();

    /**  tx current map. */
    private static Map<String, Long> txCurrentMap = new HashMap<String, Long>();

    /**  Mitjana d'ús de la targeta de xarxa en percentatge. */
    private float avgPercentatge;

    /**   Mitjana d'ús de la targeta de xarxa total. */
    private long avgTotal;

    /**  Comptador. */
    private long comptador;

    /**  Llistat d'ús de la targeta de xarxa. */
    private ArrayList<Float> graf;

    /**  Màxim ús de la targeta de xarxa en percentatge. */
    private float maxPercentatge;

    /**  Màxim ús de la targeta de xarxa total. */
    private long maxTotal;

    /**  Mínim ús de la targeta de xarxa en percentatge. */
    private float minPercentatge;

    /**  Mínim ús de la targeta de xarxa total. */
    private long minTotal;

    /**  Element que accedeix a la informació de la targeta de xarxa */
    private Sigar netSigar;

    /**  Segon. */
    private Second segon;

    /**  speed. */
    private long speed;
    
    /**  temps. */
    private ArrayList<Second> temps;

    /**
     * Instantiates a new analisis net.
     */
    public AnalisisNET() {
	this.avgPercentatge = 0;
	this.avgTotal = 0;
	this.graf = new ArrayList<Float>();
	this.maxPercentatge = 0;
	this.maxTotal = 0;
	this.minPercentatge = 100;
	this.minTotal = Long.MAX_VALUE;
	this.netSigar = new Sigar();
	this.comptador = 0;
	this.temps = new ArrayList<Second>();
	this.segon = new Second();
    }

    /**
     * Gets the avg percentatge.
     *
     * @return the avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Gets the avg total.
     *
     * @return the avg total
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Gets the comptador.
     *
     * @return the comptador
     */
    public long getComptador() {
	return comptador;
    }

    /**
     * Gets the graf.
     * 
     * @return the graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Gets the max percentatge.
     *
     * @return the max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Gets the max total.
     *
     * @return the max total
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Gets the metric.
     *
     * @return the metric
     */
    public Long getMetric() {
	try {
	    for (String ni : netSigar.getNetInterfaceList()) {
		NetInterfaceStat netStat = getNetSigar()
			.getNetInterfaceStat(ni);
		NetInterfaceConfig ifConfig = getNetSigar()
			.getNetInterfaceConfig(ni);
		String hwaddr = null;
		if (!NetFlags.NULL_HWADDR.equals(ifConfig.getHwaddr())) {
		    hwaddr = ifConfig.getHwaddr();
		}
		if (hwaddr != null) {
		    long rxCurrenttmp = netStat.getRxBytes();
		    if (rxCurrenttmp != 0) {
			speed = netStat.getSpeed() / 8;
		    }
		    saveChange(rxCurrentMap, rxChangeMap, hwaddr, rxCurrenttmp,
			    ni);
		    long txCurrenttmp = netStat.getTxBytes();
		    saveChange(txCurrentMap, txChangeMap, hwaddr, txCurrenttmp,
			    ni);
		}
	    }
	    long totalrxDown = getMetricData(rxChangeMap);
	    long totaltxUp = getMetricData(txChangeMap);
	    for (List<Long> l : rxChangeMap.values())
		l.clear();
	    for (List<Long> l : txChangeMap.values())
		l.clear();
	    return totalrxDown + totaltxUp;

	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return null;

    }

    /**
     * Gets the metric data.
     *
     * @param rxChangeMap the rx change map
     * @return the metric data
     */
    private long getMetricData(Map<String, List<Long>> rxChangeMap) {
	long total = 0;
	for (Entry<String, List<Long>> entry : rxChangeMap.entrySet()) {
	    int average = 0;
	    for (Long l : entry.getValue()) {
		average += l;
	    }
	    if (entry.getValue().size() != 0)
		total += average / entry.getValue().size();
	}
	return total;
    }

    /**
     * Gets the min percentatge.
     *
     * @return the min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Gets the min total.
     * 
     * @return the min total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Gets the net info.
     *
     * @return the net info
     */
    public String getNetInfo() {
	netSigar = new Sigar();
	String info = "";
	try {
	    NetInfo netInfo = netSigar.getNetInfo();
	    NetInterfaceConfig netInterface = netSigar.getNetInterfaceConfig();
	    if (!netInfo.getDomainName().equals(""))
		info += "Domini: " + netInfo.getDomainName();
	    info += "Nom: " + netInterface.getName() + " Tipus: "
		    + netInterface.getType() + " Gateway: "
		    + netInfo.getDefaultGateway() + " Adreça: "
		    + netInterface.getAddress() + " Descripció: "
		    + netInterface.getDescription();
	} catch (SigarException e) {
	    e.printStackTrace();
	}
	return info;
    }

    /**
     * Gets the net sigar.
     *
     * @return the net sigar
     */
    public Sigar getNetSigar() {
	return netSigar;
    }


    /**
     * Gets the temps.
     *
     * @return the temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Gets the tot.
     *
     * @return the tot
     */
    public Object getTot() {
	Object[] tot = new Object[9];
	tot[0] = avgPercentatge;
	tot[1] = avgTotal;
	tot[2] = comptador;
	tot[3] = maxPercentatge;
	tot[4] = maxTotal;
	tot[5] = minPercentatge;
	tot[6] = minTotal;
	tot[7] = graf;
	tot[8] = temps;
	return tot;
    }

    /**
     * Save change.
     *
     * @param currentMap the current map
     * @param changeMap the change map
     * @param hwaddr the hwaddr
     * @param current the current
     * @param ni the ni
     */
    private void saveChange(Map<String, Long> currentMap,
	    Map<String, List<Long>> changeMap, String hwaddr, long current,
	    String ni) {
	Long oldCurrent = currentMap.get(ni);
	if (oldCurrent != null) {
	    List<Long> list = changeMap.get(hwaddr);
	    if (list == null) {
		list = new LinkedList<Long>();
		changeMap.put(hwaddr, list);
	    }
	    list.add((current - oldCurrent));
	}
	currentMap.put(ni, current);
    }

    /**
     * Defineix l'estat final de l'anàlisi. Aquesta funció s'utilitza quan es carrega
     * un anàlisi ja realitzat
     *
     * @param dadesNet Dades de l'anàlisi carregat
     */
    @SuppressWarnings("unchecked")
    public void setTot(Object[] dadesNet) {
	avgPercentatge = (float) dadesNet[0];
	avgTotal = (long) dadesNet[1];
	comptador = (long) dadesNet[2];
	maxPercentatge = (float) dadesNet[3];
	maxTotal = (long) dadesNet[4];
	minPercentatge = (float) dadesNet[5];
	minTotal = (long) dadesNet[6];
	graf = (ArrayList<Float>) dadesNet[7];
	temps = (ArrayList<Second>) dadesNet[8];
    }
    
    /**
     * Actualitza la informació de l'anàlisi de la targeta de xarxa.
     */
    public void updateNET() {
	netSigar = new Sigar();
	float total = getMetric();
	if (((total * 100) / speed) > 100) {
	    total = 0;
	}
	if (((total * 100) / speed) < 0) {
	    total = 0;
	}
	graf.add((float) ((total * 100) / speed));
	temps.add(segon);
	segon = (Second) segon.next();
	if (total > maxTotal) {
	    maxPercentatge = (float) ((total * 100) / speed);
	    maxTotal = (long) total;
	}
	if (total < minTotal) {
	    minPercentatge = (float) ((total * 100) / speed);
	    minTotal = (long) total;
	}
	++comptador;
	avgTotal += total;
	avgPercentatge = (float) (avgTotal * 100 / comptador) / speed;

    }
}
