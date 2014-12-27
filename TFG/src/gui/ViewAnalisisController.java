package gui;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;

import domini.AnalisisController;

public class ViewAnalisisController {

	private static AnalisisController analisisController;

	public ViewAnalisisController() {
		analisisController = new AnalisisController(
				MainController.opcionsController.getOpcions());
	}

	public static void updateSystemData() {
		analisisController.analitzar();
	}

	public String[] getRamInfo() {
		return analisisController.getRamInfo();
	}

	public String[] getCpuInfo() {
		return analisisController.getCpuInfo();
	}

	public TimeSeries getEvol(String string) {
		return analisisController.getEvol(string);
	}

}
