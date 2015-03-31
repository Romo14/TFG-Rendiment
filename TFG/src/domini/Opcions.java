/*
 * 
 */
package domini;

/**
 * Classe encarregada de guardar la configuraci� de l'an�lisi que s'executa:
 * quins components s'analitzen i la duraci� de l'an�lisi.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class Opcions {

    /** Boolean que indica si s'analitza la CPU. */
    private boolean cpu;

    /** Dies que durar� l'an�lisi. */
    private int dies;

    /** Boolean que indica si s'analitza el disc dur . */
    private boolean hdd;

    /** Hores que durar� l'an�lisi. */
    private int hores;

    /** Minuts que durar� l'an�lisi. */
    private int minuts;

    /** Boolean que indica si s'analitza la xarxa. */
    private boolean net;

    /** Boolean que indica si s'analitza la mem�ria RAM. */
    private boolean ram;

    /**
     * Creadora per defecte de la classe. Inicialitza els valors de forma
     * predeterminada
     */
    public Opcions() {
	minuts = 0;
	hores = 1;
	dies = 0;
	cpu = hdd = net = ram = false;
    }

    /**
     * Obt� els dies que durar� l'an�lisi.
     * 
     * @return dies
     */
    public int getDies() {
	return dies;
    }

    /**
     * Obt� les hores que durar� l'an�lisi.
     * 
     * @return hores
     */
    public int getHores() {
	return hores;
    }

    /**
     * Obt� els minuts que durar� l'an�lisi.
     * 
     * @return minuts
     */
    public int getMinuts() {
	return minuts;
    }

    /**
     * Comprova si s'ha d'analitzar la CPU.
     * 
     * @return Cert, si s'ha d'analitzar la CPY
     */
    public boolean isCpu() {
	return cpu;
    }

    /**
     * Comprova si s'ha d'analitzar el disc dur.
     * 
     * @return Cert, si s'ha d'analitzar el disc dur
     */
    public boolean isHdd() {
	return hdd;
    }

    /**
     * Comprova si s'ha d'analitzar el net.
     * 
     * @return Cert, si s'ha d'analitzar la xarxa
     */
    public boolean isNet() {
	return net;
    }

    /**
     * Comprova si s'ha d'analitzar el ram.
     * 
     * @return Cert, si s'ha d'analitzar la mem�ria RAM
     */
    public boolean isRam() {
	return ram;
    }

    /**
     * Defineix si s'ha d'analitzar la CPU.
     * 
     * @param cpu
     *            cert/fals
     */
    public void setCpu(boolean cpu) {
	this.cpu = cpu;
    }

    /**
     * Defineix el dies.
     * 
     * @param dies
     *            Dies que durar� l'an�lisi
     */
    public void setDies(int dies) {
	this.dies = dies;
    }

    /**
     * Defineix si s'ha d'analitzar el dis dur.
     * 
     * @param hdd
     *            cert/fals
     */
    public void setHdd(boolean hdd) {
	this.hdd = hdd;
    }

    /**
     * Defineix el hores.
     * 
     * @param hores
     *            Hores que durar� l'an�lisi
     */
    public void setHores(int hores) {
	this.hores = hores;
    }

    /**
     * Defineix el minuts.
     * 
     * @param minuts
     *            Minuts que durar� l'an�lisi
     */
    public void setMinuts(int minuts) {
	this.minuts = minuts;
    }

    /**
     * Defineix si s'ha d'analitzar la xarxa.
     * 
     * @param net
     *            cert/fals
     */
    public void setNet(boolean net) {
	this.net = net;
    }

    /**
     * Defineix si s'ha d'analitzar la mem�ria RAM.
     * 
     * @param ram
     *            cert/fals
     */
    public void setRam(boolean ram) {
	this.ram = ram;
    }

}
