/*
 * 
 */
package gui;

import org.jfree.data.time.TimeSeries;

import domini.AnalisisController;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewAnalisisController.
 */
public class ViewAnalisisController {

	/** The analisis controller. */
	private static AnalisisController analisisController;

	/**
	 * Update system data.
	 */
	public static void updateSystemData() {
		analisisController.analitzar();
	}

	/**
	 * Instantiates a new view analisis controller.
	 */
	public ViewAnalisisController() {
		analisisController = new AnalisisController(
				MainController.opcionsController.getOpcions());
	}

	/**
	 * Gets the cpu info.
	 *
	 * @return the cpu info
	 */
	public Float[] getCpuInfo() {
		return analisisController.getCpuInfo();
	}

	/**
	 * Gets the evol.
	 *
	 * @param string the string
	 * @return the evol
	 */
	public TimeSeries getEvol(String string) {
		return analisisController.getEvol(string);
	}

	/**
	 * Gets the hdd info.
	 *
	 * @return the hdd info
	 */
	public float[] getHddInfo() {
		return analisisController.getHddInfo();
	}

	/**
	 * Gets the ram info.
	 *
	 * @return the ram info
	 */
	public Float[] getRamInfo() {
		return analisisController.getRamInfo();
	}

	/**
	 * Gets the net info.
	 *
	 * @return the net info
	 */
	public Float[] getNetInfo() {
		return analisisController.getNetInfo();
	}

	/**
	 * Gets the gpu info.
	 *
	 * @return the gpu info
	 */
	public String getGpuInfo() {
		return analisisController.getGpuInfo();
	}

}
