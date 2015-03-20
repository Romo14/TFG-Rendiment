package domini;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

/**
 * The Class AnalisisNET.
 */
public class AnalisisNET {

    /** The la targeta de xarxa sigar. */
    static Map<String, List<Long>> rxChangeMap = new HashMap<String, List<Long>>();

    /** The rx current map. */
    static Map<String, Long> rxCurrentMap = new HashMap<String, Long>();

    /** The tx change map. */
    static Map<String, List<Long>> txChangeMap = new HashMap<String, List<Long>>();

    /** The tx current map. */
    static Map<String, Long> txCurrentMap = new HashMap<String, Long>();

    /** La mitjana d'�s de la targeta de xarxa en percentatge. */
    private float avgPercentatge;

    /** La mitjana d'�s de la targeta de xarxa en valors totals. */
    private long avgTotal;

    /** Comptador de les vegades que s'actualitza la informaci� de la la targeta de xarxa. */
    private long comptador;

    /** Element que ens permet accedir a la informaci� de la la targeta de xarxa. */
    private Sigar netSigar;

    /** Llista de valors obtinguts en el temps */
    private ArrayList<Float> graf;

    /** M�xim �s de la la targeta de xarxa en percentatge. */
    private float maxPercentatge;

    /** M�xim �s de la la targeta de xarxa en total. */
    private long maxTotal;

    /** M�nim �s de la la targeta de xarxa en percentatge. */
    private float minPercentatge;

    /** M�nim �s de la la targeta de xarxa en total. */
    private long minTotal;

    /** Hora del dia en qu� es realitza l'obtenci� de les dades. */
    private Second segon;

    /** Llistat de les hores en que s'obt� les dades. */
    private ArrayList<Second> temps;
/** The speed. */
    private long speed;
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
     * Gets the net sigar.
     *
     * @return the net sigar
     */
    public Sigar getNetSigar() {
	return netSigar;
    }

    /**
     * Gets the segon.
     *
     * @return the segon
     */
    public Second getSegon() {
	return segon;
    }

    /**
     * Gets the speed.
     *
     * @return the min total
     */
    public long getSpeed() {
	return speed;
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
     * Sets the avg percentatge.
     *
     * @param avgPercentatge the new avg percentatge
     */
    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    /**
     * Sets the avg total.
     *
     * @param avgTotal the new avg total
     */
    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    /**
     * Sets the comptador.
     *
     * @param comptador the new comptador
     */
    public void setcomptador(long comptador) {
	this.comptador = comptador;
    }

    /**
     * Sets the graf.
     *
     * @param graf the new graf
     */
    public void setGraf(ArrayList<Float> graf) {
	this.graf = graf;
    }

    /**
     * Sets the max percentatge.
     *
     * @param maxPercentatge the new max percentatge
     */
    public void setMaxPercentatge(float maxPercentatge) {
	this.maxPercentatge = maxPercentatge;
    }

    /**
     * Sets the max total.
     *
     * @param maxTotal the new max total
     */
    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    /**
     * Sets the min percentatge.
     *
     * @param minPercentatge the new min percentatge
     */
    public void setMinPercentatge(float minPercentatge) {
	this.minPercentatge = minPercentatge;
    }

    /**
     * Sets the min total.
     *
     * @param minTotal the new min total
     */
    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    /**
     * Sets the net sigar.
     *
     * @param netSigar the new net sigar
     */
    public void setNetSigar(Sigar netSigar) {
	this.netSigar = netSigar;
    }

    /**
     * Sets the segon.
     *
     * @param segon the new segon
     */
    public void setSegon(Second segon) {
	this.segon = segon;
    }

    /**
     * Sets the speed.
     *
     * @param speed the new speed
     */
    public void setSpeed(long speed) {
	this.speed = speed;
    }

    /**
     * Sets the temps.
     *
     * @param temps the new temps
     */
    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

    /**
     * Update net.
     */
    public void updateNET() {
	netSigar = new Sigar();
	float total = getMetric();
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
