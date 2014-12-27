package domini;

public class OpcionsController {

	private Opcions opcions;

	public OpcionsController() {
		opcions = new Opcions();
	}

	public int getDies() {
		return opcions.getDies();
	}

	public int getHores() {
		return opcions.getHores();
	}

	public int getMinuts() {
		return opcions.getMinuts();
	}

	public boolean isCpu() {
		return opcions.isCpu();
	}

	public boolean isGpu() {
		return opcions.isGpu();
	}

	public boolean isHdd() {
		return opcions.isHdd();
	}

	public boolean isNet() {
		return opcions.isNet();
	}

	public boolean isRam() {
		return opcions.isRam();
	}

	public void setCpu(boolean cpu) {
		opcions.setCpu(cpu);
	}

	public void setDies(int dies) {
		opcions.setDies(dies);
	}

	public void setGpu(boolean gpu) {
		opcions.setGpu(gpu);
	}

	public void setHdd(boolean hdd) {
		opcions.setHdd(hdd);
	}

	public void setHores(int hores) {
		opcions.setHores(hores);
	}

	public void setMinuts(int minuts) {
		opcions.setMinuts(minuts);
	}

	public void setNet(boolean net) {
		opcions.setNet(net);
	}

	public void setRam(boolean ram) {
		opcions.setRam(ram);
	}

	public void setAllTrue() {
		opcions.setCpu(true);
		opcions.setGpu(true);
		opcions.setHdd(true);
		opcions.setNet(true);
		opcions.setRam(true);
	}
}
