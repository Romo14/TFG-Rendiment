package domini;

import java.io.File;
import java.util.ArrayList;

import org.hyperic.sigar.FileInfo;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.jfree.data.time.Second;

public class AnalisisHDD {

    private long avgTotal;

    private long contador;

    private ArrayList<Float> graf;

    private Sigar hddSigar;

    private float inicial;

    private float maxTotal;

    private float minTotal;

    private Second segon;

    private ArrayList<Second> temps;

    public AnalisisHDD() {
	this.avgTotal = 0;
	this.graf = new ArrayList<Float>();
	this.maxTotal = 0;
	this.minTotal = Long.MAX_VALUE;
	this.hddSigar = new Sigar();
	this.contador = 0;
	this.temps = new ArrayList<Second>();
	this.segon = new Second();
	this.inicial = 0;
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

    public String getHddInfo() {
	String s = "";

	long diskSize = (new File("/").getTotalSpace()) / 1024 / 1024 / 1024;
	long diskFree = (new File("/").getFreeSpace()) / 1024 / 1024 / 1024;
	s = "Tamany del disc: " + diskSize + "GB Espai disponible: " + diskFree
		+ "GB";

	return s;
    }

    public float getInicial() {
	return inicial;
    }

    public float getMaxTotal() {
	return maxTotal;
    }

    public float getMinTotal() {
	return minTotal;
    }

    public Sigar getRamSigar() {
	return hddSigar;
    }

    public Second getSegon() {
	return segon;
    }

    public ArrayList<Second> getTemps() {
	return temps;
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

    public void setInicial(float inicial) {
	this.inicial = inicial;
    }

    public void setMaxTotal(float maxTotal) {
	this.maxTotal = maxTotal;
    }

    public void setMaxTotal(long maxTotal) {
	this.maxTotal = maxTotal;
    }

    public void setMinTotal(float minTotal) {
	this.minTotal = minTotal;
    }

    public void setMinTotal(long minTotal) {
	this.minTotal = minTotal;
    }

    public void setRamSigar(Sigar hddSigar) {
	this.hddSigar = hddSigar;
    }

    public void setSegon(Second segon) {
	this.segon = segon;
    }

    public void setTemps(ArrayList<Second> temps) {
	this.temps = temps;
    }

    public void updateHDD() {
	FileSystemUsage hdd = null;
	try {
	    hdd = hddSigar.getFileSystemUsage("C:");
	} catch (SigarException se) {
	    se.printStackTrace();
	}
	float used = 0;
	if (avgTotal != 0) {
	    used = (float) ((hdd.getDiskReadBytes() + hdd.getDiskWriteBytes() - avgTotal) / 1024.00 / 1024.00);
	} else {
	    avgTotal = (hdd.getDiskReadBytes() + hdd.getDiskWriteBytes());
	    inicial = avgTotal;
	}
	graf.add(used);
	temps.add(segon);
	segon = (Second) segon.next();
	if (used > maxTotal)
	    maxTotal = used;
	if (used < minTotal)
	    minTotal = used;
	++contador;
	avgTotal = hdd.getDiskReadBytes() + hdd.getDiskWriteBytes();
    }
}
