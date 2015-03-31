package domini;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
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

import domini.netInfo.ParseRoute;

/**
 * Classe encarregada de realitzar l'anàlisi de la targeta de xarxa.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisNET {

    /** rx change map. */
    private static Map<String, List<Long>> rxChangeMap = new HashMap<String, List<Long>>();

    /** rx current map. */
    private static Map<String, Long> rxCurrentMap = new HashMap<String, Long>();

    /** tx change map. */
    private static Map<String, List<Long>> txChangeMap = new HashMap<String, List<Long>>();

    /** tx current map. */
    private static Map<String, Long> txCurrentMap = new HashMap<String, Long>();

    /** Mitjana d'ús de la targeta de xarxa en percentatge. */
    private float avgPercentatge;

    /** Mitjana d'ús de la targeta de xarxa total. */
    private long avgTotal;

    /** Comptador. */
    private long comptador;

    /** Llistat d'ús de la targeta de xarxa. */
    private ArrayList<Float> graf;

    /** Màxim ús de la targeta de xarxa en percentatge. */
    private float maxPercentatge;

    /** Màxim ús de la targeta de xarxa total. */
    private long maxTotal;

    /** Mínim ús de la targeta de xarxa en percentatge. */
    private float minPercentatge;

    /** Mínim ús de la targeta de xarxa total. */
    private long minTotal;

    /** Element que accedeix a la informació de la targeta de xarxa */
    private Sigar netSigar;

    /** Segon. */
    private Second segon;

    /** speed. */
    private long speed;

    /** temps. */
    private ArrayList<Second> temps;

    /**
     * Creadora per defecte de la classe. Inicialitza els atributs amb valors
     * predeterminats
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
     * Obté el avg percentatge.
     * 
     * @return avg percentatge
     */
    public float getAvgPercentatge() {
	return avgPercentatge;
    }

    /**
     * Obté el avg total.
     * 
     * @return avg total
     */
    public long getAvgTotal() {
	return avgTotal;
    }

    /**
     * Obté el comptador.
     * 
     * @return comptador
     */
    public long getComptador() {
	return comptador;
    }

    /**
     * Obté el graf.
     * 
     * @return graf
     */
    public ArrayList<Float> getGraf() {
	return graf;
    }

    /**
     * Obté el max percentatge.
     * 
     * @return max percentatge
     */
    public float getMaxPercentatge() {
	return maxPercentatge;
    }

    /**
     * Obté el max total.
     * 
     * @return max total
     */
    public long getMaxTotal() {
	return maxTotal;
    }

    /**
     * Obté l'ús de la xarxa.
     * 
     * @return metric
     */
    public Long getMetric() {
	try {
	    for (String ni : netSigar.getNetInterfaceList()) {
		NetInterfaceStat netStat = netSigar.getNetInterfaceStat(ni);
		NetInterfaceConfig ifConfig = netSigar
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
     * Obté dades de la xarxa.
     * 
     * @param rxChangeMap
     *            rx change map
     * @return metric data
     */
    long getMetricData(Map<String, List<Long>> rxChangeMap) {
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
     * Obté el min percentatge.
     * 
     * @return min percentatge
     */
    public float getMinPercentatge() {
	return minPercentatge;
    }

    /**
     * Obté el min total.
     * 
     * @return min total
     */
    public long getMinTotal() {
	return minTotal;
    }

    /**
     * Obté informació de la xarxa.
     * 
     * @return net info
     */
    public String getNetInfo() {
	String info = "";
	try {
	    ParseRoute pr = ParseRoute.getInstance();
	    InetAddress ip = InetAddress.getByName(pr.getLocalIPAddress());
	    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
	    info += "Nom: " + network.getName() + " Descripció: "
		    + network.getDisplayName() + " MTU: " + network.getMTU();
	    Enumeration<InetAddress> en = network.getInetAddresses();
	    info += " Adreça IPv4: " + en.nextElement().getHostAddress()
		    + " Adreça IPv6: " + en.nextElement().getHostAddress();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	return info;
    }

    /**
     * Obté el temps.
     * 
     * @return temps
     */
    public ArrayList<Second> getTemps() {
	return temps;
    }

    /**
     * Obté totes les dades de la classe.
     * 
     * @return tot
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
     * Funció interna que forma part del procés d'obtenció de dades de la
     * targeta de xarxa.
     * 
     * @param currentMap
     *            current map
     * @param changeMap
     *            change map
     * @param hwaddr
     *            hwaddr
     * @param current
     *            current
     * @param ni
     *            ni
     */
    void saveChange(Map<String, Long> currentMap,
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
     * Defineix l'estat final de l'anàlisi. Aquesta funció s'utilitza quan es
     * carrega un anàlisi ja realitzat
     * 
     * @param dadesNet
     *            Dades de l'anàlisi carregat
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
