/*
 * 
 */
package gui;

import java.io.File;

import org.jfree.data.time.TimeSeries;

import domini.AnalisisController;

/**
 * Controlador de l'an�lisi de la capa de presentaci�, s'encarrega de comunicar-se amb la classe de
 * domini a trav�s del controlador d'an�lisi.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class ViewAnalisisController {

    /** El controlador d'analisis de domini. */
    private static AnalisisController analisisController;

    /**
     * Creadora per defecte de la classe. Inicialitza la comunicaci� amb la capa
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
     * Carregar l'analisi del fitxer que passem com a par�metre.
     * 
     * @param selectedFile
     *            Fitxer que cont� un an�lisi
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
     * Obt� la informaci� de la CPU.
     * 
     * @return cpu info
     */
    public Float[] getCpuInfo() {
	return analisisController.getCpuInfo();
    }

    /**
     * Obt� la duracio.
     * 
     * @return la duracio
     */
    public String getDuracio() {
	return analisisController.getDuracio();
    }

    /**
     * Obt� la duracio parcial.
     * 
     * @return la duracio parcial
     */
    public String getDuracioParcial() {
	return AnalisisView.getTempsParcial();
    }

    /**
     * Obt� el temps restant de l'an�lisi.
     * 
     * @return la duracio restant
     */
    public long getDuracioRestant() {
	return AnalisisView.getDuracioRestant();
    }

    /**
     * Obt� el evol.
     * 
     * @param string
     *            la string
     * @return la evol
     */
    public TimeSeries getEvol(String string) {
	return analisisController.getEvol(string);
    }

    /**
     * Obt� la informaci� del disc dur.
     * 
     * @return hdd info
     */
    public float[] getHddInfo() {
	return analisisController.getHddInfo();
    }

    /**
     * Obt� el identificador del PC.
     * 
     * @return Id del PC
     */
    public String getIdPC() {
	return analisisController.getIdPC();
    }

    /**
     * Obt� detalls del component que passem com a par�metre.
     * 
     * @param s
     *            Identificador del component
     * @return Informaci� del component
     */
    public String getInfoComponent(String s) {
	return analisisController.getInfoComponents(s);
    }

    /**
     * Obt� la informaci� de la targeta de xarxa.
     * 
     * @return net info
     */
    public Float[] getNetInfo() {
	return analisisController.getNetInfo();
    }

    /**
     * Obt� la informaci� de la mem�ria RAM.
     * 
     * @return Informaci� de la RAM
     */
    public Float[] getRamInfo() {
	return analisisController.getRamInfo();
    }

    /**
     * Guardar les dades de l'an�lisi.
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
