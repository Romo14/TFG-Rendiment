/*
 * 
 */
package gui;

import domini.OpcionsController;

/**
 * Controlador de les opcions de la capa de presentació. S'encarrega
 * d'instanciar el controlador de la classe de domini i passar-li la informació
 * provinent de la interfície.
 */
public class ViewOpcionsController {

	/** Controlador d'opcions de domini. */
	static OpcionsController opcions;

	/**
	 * Creadora per defecte de la classe.
	 */
	public ViewOpcionsController() {
		opcions = new OpcionsController();
	}

	/**
	 * Obté els dies.
	 * 
	 * @return the dies
	 */
	public static int getDies() {
		return opcions.getDies();
	}

	/**
	 * Obté els hores.
	 * 
	 * @return the hores
	 */
	public static int getHores() {
		return opcions.getHores();
	}

	/**
	 * Obté els minuts.
	 * 
	 * @return the minuts
	 */
	public static int getMinuts() {
		return opcions.getMinuts();
	}

	/**
	 * Comprova si s'ha d'analitzar el processador.
	 * 
	 * @return true, si s'ha d'analitar la cpu
	 */
	public static boolean isCpu() {
		return opcions.isCpu();
	}

	/**
	 * Comprova si s'ha d'analitzar el disc dur.
	 * 
	 * @return true, si s'ha d'analitar el disc dur
	 */
	public static boolean isHdd() {
		return opcions.isHdd();
	}

	/**
	 * Comprova si s'ha d'analitzar la targeta de xarxa.
	 * 
	 * @return true, si s'ha d'analitzar
	 */
	public static boolean isNet() {
		return opcions.isNet();
	}

	/**
	 * Comprova si s'ha d'analitzar el ram.
	 * 
	 * @return true, if is ram
	 */
	public static boolean isRam() {
		return opcions.isRam();
	}

	/**
	 * Defineix el cpu.
	 * 
	 * @param cpu
	 *            the new cpu
	 */
	public static void setCpu(boolean cpu) {
		opcions.setCpu(cpu);
	}

	/**
	 * Defineix el dies.
	 * 
	 * @param dies
	 *            the new dies
	 */
	public static void setDies(int dies) {
		opcions.setDies(dies);
	}

	/**
	 * Defineix el hdd.
	 * 
	 * @param hdd
	 *            the new hdd
	 */
	public static void setHdd(boolean hdd) {
		opcions.setHdd(hdd);
	}

	/**
	 * Defineix el hores.
	 * 
	 * @param hores
	 *            the new hores
	 */
	public static void setHores(int hores) {
		opcions.setHores(hores);
	}

	/**
	 * Defineix el minuts.
	 * 
	 * @param minuts
	 *            the new minuts
	 */
	public static void setMinuts(int minuts) {
		opcions.setMinuts(minuts);
	}

	/**
	 * Defineix el net.
	 * 
	 * @param net
	 *            the new net
	 */
	public static void setNet(boolean net) {
		opcions.setNet(net);
	}

	/**
	 * Defineix el ram.
	 * 
	 * @param ram
	 *            the new ram
	 */
	public static void setRam(boolean ram) {
		opcions.setRam(ram);
	}

	/**
	 * Obté les opcions.
	 * 
	 * @return opcions
	 */
	public OpcionsController getOpcions() {
		return opcions;
	}

	/**
	 * Obté la duració en segons de l'anàlisi.
	 * 
	 * @return temps
	 */
	public int getTemps() {
		return opcions.getDies() * 86400 + opcions.getHores() * 3600
				+ opcions.getMinuts() * 60;
	}

	/**
	 * Defineix a les opcions que es realitza un anàlisi de tots els components.
	 */
	public static void setAllTrue() {
		opcions.setAllTrue();
	}

}
