/*
 * 
 */
package domini;

// TODO: Auto-generated Javadoc
/**
 * The Class Opcions.
 */
public class Opcions {

    /** The cpu. */
    private boolean cpu;
    
    /** The dies. */
    private int dies;
    
    /** The hdd. */
    private boolean hdd;
    
    /** The hores. */
    private int hores;
    
    /** The minuts. */
    private int minuts;
    
    /** The net. */
    private boolean net;
    
    /** The ram. */
    private boolean ram;

    /**
     * Instantiates a new opcions.
     */
    public Opcions() {
	minuts = 0;
	hores = 1;
	dies = 0;
	cpu = hdd = net = ram = false;
    }

    /**
     * Gets the dies.
     *
     * @return the dies
     */
    public int getDies() {
	return dies;
    }

    /**
     * Gets the hores.
     *
     * @return the hores
     */
    public int getHores() {
	return hores;
    }

    /**
     * Gets the minuts.
     *
     * @return the minuts
     */
    public int getMinuts() {
	return minuts;
    }

    /**
     * Checks if is cpu.
     *
     * @return true, if is cpu
     */
    public boolean isCpu() {
	return cpu;
    }

    /**
     * Checks if is hdd.
     *
     * @return true, if is hdd
     */
    public boolean isHdd() {
	return hdd;
    }

    /**
     * Checks if is net.
     *
     * @return true, if is net
     */
    public boolean isNet() {
	return net;
    }

    /**
     * Checks if is ram.
     *
     * @return true, if is ram
     */
    public boolean isRam() {
	return ram;
    }

    /**
     * Sets the cpu.
     *
     * @param cpu the new cpu
     */
    public void setCpu(boolean cpu) {
	this.cpu = cpu;
    }

    /**
     * Sets the dies.
     *
     * @param dies the new dies
     */
    public void setDies(int dies) {
	this.dies = dies;
    }

    /**
     * Sets the hdd.
     *
     * @param hdd the new hdd
     */
    public void setHdd(boolean hdd) {
	this.hdd = hdd;
    }

    /**
     * Sets the hores.
     *
     * @param hores the new hores
     */
    public void setHores(int hores) {
	this.hores = hores;
    }

    /**
     * Sets the minuts.
     *
     * @param minuts the new minuts
     */
    public void setMinuts(int minuts) {
	this.minuts = minuts;
    }

    /**
     * Sets the net.
     *
     * @param net the new net
     */
    public void setNet(boolean net) {
	this.net = net;
    }

    /**
     * Sets the ram.
     *
     * @param ram the new ram
     */
    public void setRam(boolean ram) {
	this.ram = ram;
    }

}
