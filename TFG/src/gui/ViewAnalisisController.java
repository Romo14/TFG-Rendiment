/*
 * 
 */
package gui;

import java.io.File;

import org.jfree.data.time.TimeSeries;

import domini.AnalisisController;

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
     * @param string
     *            the string
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
     * Obté detalls del component que passem com a paràmetre
     * 
     * @param s
     *            Identificador del component
     * @return Informació del component
     */
    public String getInfoComponent(String s) {
	return analisisController.getInfoComponents(s);
    }

    /**
     * Obté el temps restant de l'anàlisi.
     * 
     * @return
     */
    public int getDuracioRestant() {
	return AnalisisView.getDuracioRestant();
    }

    public String getDuracioParcial() {
	return AnalisisView.getTempsParcial();
    }

    public void guardarAnalisi(String fileName, String duracio) {
	analisisController.guardarAnalisi(fileName, duracio);

    }

    public void carregarAnalisi(File selectedFile) {
	analisisController = new AnalisisController(MainController.opcionsController.getOpcions());
	analisisController.carregarAnalisi(selectedFile);
	ResultatAnalisisView res = new ResultatAnalisisView();
	res.setPdfCreat(true);
	MainController.view.dispose();
    }

    public String getIdPC() {
	return analisisController.getIdPC();
    }

}
