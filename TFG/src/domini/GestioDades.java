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
 * The Class GestioDadesController.
 */
public class GestioDades {

    /**
     * Guardar.
     *
     * @param resultat the resultat
     * @param name the name
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
     * Carregar.
     *
     * @param file the file
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
	} catch (IOException | ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
