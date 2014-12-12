package gui;

import domini.OpcionsController;

public class ViewOpcionsController {

    static OpcionsController opcions;

    public ViewOpcionsController() {
	opcions = new OpcionsController();
    }

    public static int getDies() {
	return opcions.getDies();
    }

    public static int getHores() {
	return opcions.getHores();
    }

    public static int getMinuts() {
	return opcions.getMinuts();
    }

    public static boolean isCpu() {
	return opcions.isCpu();
    }

    public static boolean isGpu() {
	return opcions.isGpu();
    }

    public static boolean isHdd() {
	return opcions.isHdd();
    }

    public static boolean isNet() {
	return opcions.isNet();
    }

    public static boolean isRam() {
	return opcions.isRam();
    }

    public static void setCpu(boolean cpu) {
	opcions.setCpu(cpu);
    }

    public static void setDies(int dies) {
	opcions.setDies(dies);
    }

    public static void setGpu(boolean gpu) {
	opcions.setGpu(gpu);
    }

    public static void setHdd(boolean hdd) {
	opcions.setHdd(hdd);
    }

    public static void setHores(int hores) {
	opcions.setHores(hores);
    }

    public static void setMinuts(int minuts) {
	opcions.setMinuts(minuts);
    }

    public static void setNet(boolean net) {
	opcions.setNet(net);
    }

    public static void setRam(boolean ram) {
	opcions.setRam(ram);
    }

    public OpcionsController getOpcions() {
	return opcions;
    }

}
