package domini;

import java.net.Inet4Address;
import java.net.Inet6Address;
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

/**
 * Classe encarregada de realitzar l'anàlisi de la targeta de xarxa.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class AnalisisNET extends Analisi {

    /** rx change map. */
    private static Map<String, List<Long>> rxChangeMap = new HashMap<String, List<Long>>();

    /** rx current map. */
    private static Map<String, Long> rxCurrentMap = new HashMap<String, Long>();

    /** tx change map. */
    private static Map<String, List<Long>> txChangeMap = new HashMap<String, List<Long>>();

    /** tx current map. */
    private static Map<String, Long> txCurrentMap = new HashMap<String, Long>();

    /** ip de l'ordinador */
    private String ip;

    /** speed. */
    private long speed;

    /**
     * Creadora per defecte de la classe. Inicialitza els atributs amb valors
     * predeterminats
     */
    public AnalisisNET() {
	super();
    }

    /**
     * Obté l'ús de la xarxa.
     * 
     * @return metric
     */
    public Long getMetric() {
	try {
	    for (String ni : sigar.getNetInterfaceList()) {
		NetInterfaceStat netStat = sigar.getNetInterfaceStat(ni);
		NetInterfaceConfig ifConfig = sigar.getNetInterfaceConfig(ni);
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
     * Obté informació de la xarxa.
     * 
     * @return net info
     */
    public String getNetInfo() {
	String info = "";
	try {
	    InetAddress ipAdress = InetAddress.getByName(ip);
	    NetworkInterface network = NetworkInterface
		    .getByInetAddress(ipAdress);
	    if (network == null) {
		ipAdress = InetAddress.getLocalHost();
		network = NetworkInterface.getByInetAddress(ipAdress);
	    }
	    info += "Nom: " + network.getName() + " Descripció: "
		    + network.getDisplayName() + " MTU: " + network.getMTU();
	    Enumeration<InetAddress> en = network.getInetAddresses();
	    while (en.hasMoreElements()) {
		InetAddress aux = en.nextElement();
		if (aux instanceof Inet4Address) {
		    info += " Adreça IPv4: " + aux.getHostAddress();
		} else if (aux instanceof Inet6Address) {
		    info += " Adreça IPv6: " + aux.getHostAddress();
		}
	    }

	} catch (UnknownHostException e) {
	} catch (SocketException e) {
	}
	return info;
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
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
	this.ip = ip;
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
	sigar = new Sigar();
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
