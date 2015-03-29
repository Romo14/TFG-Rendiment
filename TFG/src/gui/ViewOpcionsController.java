/*
 * 
 */
package gui;

import domini.OpcionsController;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewOpcionsController.
 */
public class ViewOpcionsController {

    /** The opcions. */
    static OpcionsController opcions;

    /**
     * Instantiates a new view opcions controller.
     */
    public ViewOpcionsController() {
	opcions = new OpcionsController();
    }

    /**
     * Gets the dies.
     * 
     * @return the dies
     */
    public static int getDies() {
	return opcions.getDies();
    }

    /**
     * Gets the hores.
     * 
     * @return the hores
     */
    public static int getHores() {
	return opcions.getHores();
    }

    /**
     * Gets the minuts.
     * 
     * @return the minuts
     */
    public static int getMinuts() {
	return opcions.getMinuts();
    }

    /**
     * Checks if is cpu.
     * 
     * @return true, if is cpu
     */
    public static boolean isCpu() {
	return opcions.isCpu();
    }

    /**
     * Checks if is hdd.
     * 
     * @return true, if is hdd
     */
    public static boolean isHdd() {
	return opcions.isHdd();
    }

    /**
     * Checks if is net.
     * 
     * @return true, if is net
     */
    public static boolean isNet() {
	return opcions.isNet();
    }

    /**
     * Checks if is ram.
     * 
     * @return true, if is ram
     */
    public static boolean isRam() {
	return opcions.isRam();
    }

    /**
     * Sets the cpu.
     * 
     * @param cpu
     *            the new cpu
     */
    public static void setCpu(boolean cpu) {
	opcions.setCpu(cpu);
    }

    /**
     * Sets the dies.
     * 
     * @param dies
     *            the new dies
     */
    public static void setDies(int dies) {
	opcions.setDies(dies);
    }

    /**
     * Sets the hdd.
     * 
     * @param hdd
     *            the new hdd
     */
    public static void setHdd(boolean hdd) {
	opcions.setHdd(hdd);
    }

    /**
     * Sets the hores.
     * 
     * @param hores
     *            the new hores
     */
    public static void setHores(int hores) {
	opcions.setHores(hores);
    }

    /**
     * Sets the minuts.
     * 
     * @param minuts
     *            the new minuts
     */
    public static void setMinuts(int minuts) {
	opcions.setMinuts(minuts);
    }

    /**
     * Sets the net.
     * 
     * @param net
     *            the new net
     */
    public static void setNet(boolean net) {
	opcions.setNet(net);
    }

    /**
     * Sets the ram.
     * 
     * @param ram
     *            the new ram
     */
    public static void setRam(boolean ram) {
	opcions.setRam(ram);
    }

    /**
     * Gets the opcions.
     * 
     * @return the opcions
     */
    public OpcionsController getOpcions() {
	return opcions;
    }

    /**
     * Gets the temps.
     * 
     * @return the temps
     */
    public int getTemps() {
	return opcions.getDies() * 86400 + opcions.getHores() * 3600
		+ opcions.getMinuts() * 60;
    }

    /**
     * Sets the all true.
     */
    public static void setAllTrue() {
	opcions.setAllTrue();
    }

}
