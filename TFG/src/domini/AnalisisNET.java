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

public class AnalisisNET {

    static Map<String, List<Long>> rxChangeMap = new HashMap<String, List<Long>>();

    static Map<String, Long> rxCurrentMap = new HashMap<String, Long>();

    static Map<String, List<Long>> txChangeMap = new HashMap<String, List<Long>>();

    static Map<String, Long> txCurrentMap = new HashMap<String, Long>();

    private float avgPercentatge;

    private long avgTotal;

    private long contador;

    private ArrayList<Float> graf;

    private float maxPercentatge;

    private long maxTotal;

    private float minPercentatge;

    private long minTotal;

    private Sigar netSigar;

    private Second segon;

    private long speed;

    private ArrayList<Second> temps;

    /**
     * @throws InterruptedException
     * @throws SigarException
     * 
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
	this.contador = 0;
	this.temps = new ArrayList<Second>();
	this.segon = new Second();
    }

    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    public long getAvgTotal() {
	return avgTotal;
    }

    public long getContador() {
	return contador;
    }

    public ArrayList<Float> getGraf() {
	return graf;
    }

    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    public long getMaxTotal() {
	return maxTotal;
    }

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

    public float getMinPercentatge() {
	return minPercentatge;
    }

    public long getMinTotal() {
	return minTotal;
    }

    public Sigar getNetSigar() {
	return netSigar;
    }

    public Second getSegon() {
	return segon;
    }

    public long getSpeed() {
	return speed;
    }

    public ArrayList<Second> getTemps() {
	return temps;
    }

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

    public void setAvgPercentatge(float avgPercentatge) {
	this.avgPercentatge = avgPercentatge;
    }

    public void setAvgTotal(long avgTotal) {
	this.avgTotal = avgTotal;
    }

    public void setContador(long contador) {
	this.contador = contador;
    }

    public void setGraf(ArrayList<Float> graf) {
	this.graf = graf;
    }

    public void setMaxPercentatge(float maxPercentatge) {
	this.maxPercentatge = maxPercentatge;
    }

    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    public void setMinPercentatge(float minPercentatge) {
	this.minPercentatge = minPercentatge;
    }

    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    public void setNetSigar(Sigar netSigar) {
	this.netSigar = netSigar;
    }

    public void setSegon(Second segon) {
	this.segon = segon;
    }

    public void setSpeed(long speed) {
	this.speed = speed;
    }

    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

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
	++contador;
	avgTotal += total;
	avgPercentatge = (float) (avgTotal * 100 / contador) / speed;

    }
}
