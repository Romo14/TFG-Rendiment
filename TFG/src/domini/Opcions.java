package domini;

public class Opcions {

	private boolean cpu;
	private int dies;
	private boolean gpu;
	private boolean hdd;
	private int hores;
	private int minuts;
	private boolean net;
	private boolean ram;

	public Opcions() {
		minuts = 0;
		hores = 1;
		dies = 0;
		cpu = hdd = gpu = net = ram = false;
	}

	public int getDies() {
		return dies;
	}

	public int getHores() {
		return hores;
	}

	public int getMinuts() {
		return minuts;
	}

	public boolean isCpu() {
		return cpu;
	}

	public boolean isGpu() {
		return gpu;
	}

	public boolean isHdd() {
		return hdd;
	}

	public boolean isNet() {
		return net;
	}

	public boolean isRam() {
		return ram;
	}

	public void setCpu(boolean cpu) {
		this.cpu = cpu;
	}

	public void setDies(int dies) {
		this.dies = dies;
	}

	public void setGpu(boolean gpu) {
		this.gpu = gpu;
	}

	public void setHdd(boolean hdd) {
		this.hdd = hdd;
	}

	public void setHores(int hores) {
		this.hores = hores;
	}

	public void setMinuts(int minuts) {
		this.minuts = minuts;
	}

	public void setNet(boolean net) {
		this.net = net;
	}

	public void setRam(boolean ram) {
		this.ram = ram;
	}

}
