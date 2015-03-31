/*
 * 
 */
package gui;

import java.io.File;

import org.jfree.data.time.TimeSeries;

import domini.AnalisisController;

/**
 * Controlador de l'anàlisi de la capa de presentació, s'encarrega de comunicar-se amb la classe de
 * domini a través del controlador d'anàlisi.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class ViewAnalisisController {

    /** El controlador d'analisis de domini. */
    private static AnalisisController analisisController;

    /**
     * Creadora per defecte de la classe. Inicialitza la comunicació amb la capa
     * de domini
     */
    public ViewAnalisisController() {
	analisisController = new AnalisisController(
		MainController.opcionsController.getOpcions());
    }

    /**
     * Actualitza les dades dels diferents components que s'analitzen.
     */
    public static void updateSystemData() {
	analisisController.analitzar();
    }

    /**
     * Carregar l'analisi del fitxer que passem com a paràmetre.
     * 
     * @param selectedFile
     *            Fitxer que conté un anàlisi
     */
    public void carregarAnalisi(File selectedFile) {
	analisisController = new AnalisisController(
		MainController.opcionsController.getOpcions());
	analisisController.carregarAnalisi(selectedFile);
	ResultatAnalisisView res = new ResultatAnalisisView();
	res.setPdfCreat(true);
	MainController.view.dispose();
    }

    /**
     * Obté la informació de la CPU.
     * 
     * @return cpu info
     */
    public Float[] getCpuInfo() {
	return analisisController.getCpuInfo();
    }

    /**
     * Obté la duracio.
     * 
     * @return la duracio
     */
    public String getDuracio() {
	return analisisController.getDuracio();
    }

    /**
     * Obté la duracio parcial.
     * 
     * @return la duracio parcial
     */
    public String getDuracioParcial() {
	return AnalisisView.getTempsParcial();
    }

    /**
     * Obté el temps restant de l'anàlisi.
     * 
     * @return la duracio restant
     */
    public long getDuracioRestant() {
	return AnalisisView.getDuracioRestant();
    }

    /**
     * Obté el evol.
     * 
     * @param string
     *            la string
     * @return la evol
     */
    public TimeSeries getEvol(String string) {
	return analisisController.getEvol(string);
    }

    /**
     * Obté la informació del disc dur.
     * 
     * @return hdd info
     */
    public float[] getHddInfo() {
	return analisisController.getHddInfo();
    }

    /**
     * Obté el identificador del PC.
     * 
     * @return Id del PC
     */
    public String getIdPC() {
	return analisisController.getIdPC();
    }

    /**
     * Obté detalls del component que passem com a paràmetre.
     * 
     * @param s
     *            Identificador del component
     * @return Informació del component
     */
    public String getInfoComponent(String s) {
	return analisisController.getInfoComponents(s);
    }

    /**
     * Obté la informació de la targeta de xarxa.
     * 
     * @return net info
     */
    public Float[] getNetInfo() {
	return analisisController.getNetInfo();
    }

    /**
     * Obté la informació de la memòria RAM.
     * 
     * @return Informació de la RAM
     */
    public Float[] getRamInfo() {
	return analisisController.getRamInfo();
    }

    /**
     * Guardar les dades de l'anàlisi.
     * 
     * @param fileName
     *            el nom del fitxer
     * @param duracio
     *            la duracio
     */
    public void guardarAnalisi(String fileName, String duracio) {
	analisisController.guardarAnalisi(fileName, duracio);

    }

    /**
     * Defineix la duracio en format String.
     * 
     * @param s
     *            La nova duracio
     */
    public void setDuracio(String s) {
	analisisController.setDuracio(s);
    }

}
