/*
 * 
 */
package domini;

/**
 * Controlador de les opcions de l'anàlisi.
 */
public class OpcionsController {

    /** Les opcions. */
    private Opcions opcions;

    /**
     * Creadora per defecte de la classe. Inicialitza un objecte opcions.
     */
    public OpcionsController() {
	opcions = new Opcions();
    }

    /**
     * Obté el dies.
     * 
     * @return els dies
     */
    public int getDies() {
	return opcions.getDies();
    }

    /**
     * Obté el hores.
     * 
     * @return les hores
     */
    public int getHores() {
	return opcions.getHores();
    }

    /**
     * Obté el minuts.
     * 
     * @return els minuts
     */
    public int getMinuts() {
	return opcions.getMinuts();
    }

    /**
     * Comprova si s'analitza el CPU.
     * 
     * @return cert, si s'ha d'analitzar la CPU
     */
    public boolean isCpu() {
	return opcions.isCpu();
    }

    /**
     * Comprova si s'analitza el disc dur.
     * 
     * @return cert, si s'ha d'analitzar el disc dur
     */
    public boolean isHdd() {
	return opcions.isHdd();
    }

    /**
     * Comprova si s'analitza la xarxa.
     * 
     * @return cert, si s'ha d'analitzar la xarxa
     */
    public boolean isNet() {
	return opcions.isNet();
    }

    /**
     * Comprova si s'analitza la RAM.
     * 
     * @return cert, si s'ha d'analitzar la RAM
     */
    public boolean isRam() {
	return opcions.isRam();
    }

    /**
     * Defineix si s'ha d'analitzar la CPU.
     * 
     * @param cpu
     *            els new cpu
     */
    public void setCpu(boolean cpu) {
	opcions.setCpu(cpu);
    }

    /**
     * Defineix la duració en dies.
     * 
     * @param dies
     *            duració en dies
     */
    public void setDies(int dies) {
	opcions.setDies(dies);
    }

    /**
     * Defineix si s'ha d'analitzar el disc dur.
     * 
     * @param hdd
     *            cert, si s'ha d'analitzar
     */
    public void setHdd(boolean hdd) {
	opcions.setHdd(hdd);
    }

    /**
     * Defineix la duració en hores.
     * 
     * @param hores
     *            duració en hores
     */
    public void setHores(int hores) {
	opcions.setHores(hores);
    }

    /**
     * Defineix la duració en minuts.
     * 
     * @param minuts
     *            duració en minuts
     */
    public void setMinuts(int minuts) {
	opcions.setMinuts(minuts);
    }

    /**
     * Defineix si s'ha d'analitzar el net.
     * 
     * @param net
     *            cert, si s'ha d'analitzar
     */
    public void setNet(boolean net) {
	opcions.setNet(net);
    }

    /**
     * Defineix si s'ha d'analitzar el ram.
     * 
     * @param ram
     *            cert, si s'ha d'analitzar
     */
    public void setRam(boolean ram) {
	opcions.setRam(ram);
    }

    /**
     * Defineix tots els booleans a true.
     */
    public void setAllTrue() {
	opcions.setCpu(true);
	opcions.setHdd(true);
	opcions.setNet(true);
	opcions.setRam(true);
    }

}
