/*
 * 
 */
package domini;

// TODO: Auto-generated Javadoc
/**
 * The Class OpcionsController.
 */
public class OpcionsController {

    /** The opcions. */
    private Opcions opcions;

    /**
     * Instantiates a new opcions controller.
     */
    public OpcionsController() {
	opcions = new Opcions();
    }

    /**
     * Gets the dies.
     *
     * @return the dies
     */
    public int getDies() {
	return opcions.getDies();
    }

    /**
     * Gets the hores.
     *
     * @return the hores
     */
    public int getHores() {
	return opcions.getHores();
    }

    /**
     * Gets the minuts.
     *
     * @return the minuts
     */
    public int getMinuts() {
	return opcions.getMinuts();
    }

    /**
     * Checks if is cpu.
     *
     * @return true, if is cpu
     */
    public boolean isCpu() {
	return opcions.isCpu();
    }

    /**
     * Checks if is gpu.
     *
     * @return true, if is gpu
     */
    public boolean isGpu() {
	return opcions.isGpu();
    }

    /**
     * Checks if is hdd.
     *
     * @return true, if is hdd
     */
    public boolean isHdd() {
	return opcions.isHdd();
    }

    /**
     * Checks if is net.
     *
     * @return true, if is net
     */
    public boolean isNet() {
	return opcions.isNet();
    }

    /**
     * Checks if is ram.
     *
     * @return true, if is ram
     */
    public boolean isRam() {
	return opcions.isRam();
    }

    /**
     * Sets the cpu.
     *
     * @param cpu the new cpu
     */
    public void setCpu(boolean cpu) {
	opcions.setCpu(cpu);
    }

    /**
     * Sets the dies.
     *
     * @param dies the new dies
     */
    public void setDies(int dies) {
	opcions.setDies(dies);
    }

    /**
     * Sets the gpu.
     *
     * @param gpu the new gpu
     */
    public void setGpu(boolean gpu) {
	opcions.setGpu(gpu);
    }

    /**
     * Sets the hdd.
     *
     * @param hdd the new hdd
     */
    public void setHdd(boolean hdd) {
	opcions.setHdd(hdd);
    }

    /**
     * Sets the hores.
     *
     * @param hores the new hores
     */
    public void setHores(int hores) {
	opcions.setHores(hores);
    }

    /**
     * Sets the minuts.
     *
     * @param minuts the new minuts
     */
    public void setMinuts(int minuts) {
	opcions.setMinuts(minuts);
    }

    /**
     * Sets the net.
     *
     * @param net the new net
     */
    public void setNet(boolean net) {
	opcions.setNet(net);
    }

    /**
     * Sets the ram.
     *
     * @param ram the new ram
     */
    public void setRam(boolean ram) {
	opcions.setRam(ram);
    }

    /**
     * Define el valor de all true.
     */
    public void setAllTrue() {
	opcions.setCpu(true);
	opcions.setGpu(true);
	opcions.setHdd(true);
	opcions.setNet(true);
	opcions.setRam(true);
    }
}
