package domini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe encarregada de guardar i carregar els anàlisis realitzats de disc.
 * 
 * @author Oriol Gasset Romo <oriol.gasset@est.fib.upc.edu>
 */
public class GestioDades {

    /**
     * Guardar les dades a disc.
     * 
     * @param resultat
     *            resultat de l'anàlisi
     * @param name
     *            nom del fitxer on es guardarà l'anàlisi
     */
    public static void guardar(List<Object> resultat, String name) {
	try {
	    File f;
	    name = name.replace(".pdf", ".ren");
	    f = new File(name);
	    FileOutputStream saveFile = new FileOutputStream(f);
	    ObjectOutputStream save = new ObjectOutputStream(saveFile);
	    save.writeObject(resultat);
	    save.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Carrega les dades de disc.
     * 
     * @param file
     *            Fitxer d'anàlisi
     * @return llistat amb les dades de l'anàlisi
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Object> carregar(File file) {
	FileInputStream saveFile;
	try {
	    saveFile = new FileInputStream(file);
	    ObjectInputStream load = new ObjectInputStream(saveFile);
	    ArrayList<Object> l = null;
	    l = (ArrayList<Object>) load.readObject();
	    load.close();
	    return l;
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
