package domini;

public class AnalisisController {

    private static OpcionsController opcionsController;
    private static int segons;
    private AnalisisCPU cpu;
    private AnalisisGPU gpu;
    private AnalisisHDD hdd;
    private AnalisisNET net;
    private AnalisisRAM ram;

    public AnalisisController(OpcionsController oc) {
	cpu = new AnalisisCPU();
	gpu = new AnalisisGPU();
	hdd = new AnalisisHDD();
	net = new AnalisisNET();
	ram = new AnalisisRAM();
	opcionsController = oc;
    }

    private static int convertTemps() {
	segons = 0;
	segons += opcionsController.getMinuts() * 60;
	segons += opcionsController.getHores() * 3600;
	segons += opcionsController.getDies() * 86400;
	return segons;
    }

    public void analisisComplet() {
	segons = convertTemps();
	while (segons > 0) {
	    if (segons % 10 == 0) {

	    }
	    --segons;
	}
	guardaResultats();
    }

    public void analisisPersonalitzat() {
	segons = convertTemps();
	while (segons > 0) {
	    if (segons % 10 == 0) {
		if (opcionsController.isCpu()) {
		}
		if (opcionsController.isGpu()) {
		}
		if (opcionsController.isHdd()) {
		}
		if (opcionsController.isNet()) {
		}
		if (opcionsController.isRam()) {
		}
	    }
	    --segons;
	}
	guardaResultats();
    }

    private void guardaResultats() {

    }

}
