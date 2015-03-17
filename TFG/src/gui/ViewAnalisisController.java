package gui;

import org.jfree.data.time.TimeSeries;

import domini.AnalisisController;

public class ViewAnalisisController {

    private static AnalisisController analisisController;

    public static void updateSystemData() {
	analisisController.analitzar();
    }

    public ViewAnalisisController() {
	analisisController = new AnalisisController(
		MainController.opcionsController.getOpcions());
    }

    public Float[] getCpuInfo() {
	return analisisController.getCpuInfo();
    }

    public TimeSeries getEvol(String string) {
	return analisisController.getEvol(string);
    }

    public float[] getHddInfo() {
	return analisisController.getHddInfo();
    }

    public Float[] getRamInfo() {
	return analisisController.getRamInfo();
    }

    public Float[] getNetInfo() {
	return analisisController.getNetInfo();
    }

    public String getGpuInfo() {
	return analisisController.getGpuInfo();
    }

}
