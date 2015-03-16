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

	public Float[] getHddInfo() {
		return analisisController.getHddInfo();
	}

	public Float[] getRamInfo() {
		return analisisController.getRamInfo();
	}

}
