package poouned.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Esta clase es una utilidad que puede ser usada por los objetos que vayan a ser 
 * persistidos a archivos en la aplicación.
 * 
 * 
 * @author Francisco Javier Crespo Jiménez.
 */
public class PersistidorArchivo implements Persistible {
	
	/**
	 * Realiza la exportación a archivo del objeto pasado como parámetro.
	 * @param objeto El objeto a exportar.
	 * @parem archivo El archivo de destino.
	 * @throws Exception lanzada si el proceso falla.
	 */
	public void exportar(Object objeto, String archivo) throws Exception{
		try {
            FileOutputStream fos= new FileOutputStream(archivo);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(objeto);
            oos.close();
            fos.close();
        	}catch(IOException e){
        	  throw new Exception("No se pudo exportar.");
        }
	}

	/**
	 * Realiza la importación hacia archivo de un array de objetos.
	 * @param archivo el archivo a importar.
	 * @throws Exception lanzada si el proceso de importación no fuese exitoso.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> importar(String archivo) throws Exception {
		ArrayList<Object> tmplist = new ArrayList<Object>();
		try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tmplist = (ArrayList<Object>) ois.readObject();
            ois.close();
            fis.close();
        	} catch(IOException | ClassNotFoundException e) {
        	 throw new Exception("No se pudo importar. " + e.getMessage() + "\n" + e.getStackTrace());
        }	
		return tmplist;
	}

}
