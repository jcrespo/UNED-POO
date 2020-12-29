package poouned.modelos;

import java.io.Serializable;
import java.util.ArrayList;

import poouned.util.PersistidorArchivo;

/**
 * Esta clase abstracta se utiliza como clase padre de las clases que mantienen
 * ArrayList y deben tener la funcionalidad de importar y exportar datos.
 * A su vez, se hereda de AbstractModel que implementa el método setChanged
 * para informar a los observadores que el modelo ha cambiado sus datos.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 * @see AbstractModel
 */
public abstract class AbstractListModel extends AbstractModel implements Serializable {

	
	private static final long serialVersionUID = -5393390236406617476L;

	/**
	 * Método que exporta un ArrayList a un archivo utilizando
	 * el PersistidorArchivo
	 * 
	 * @param list lista a exportar
	 * @param file archivo en el cual se exportará la lista
	 * @return boolean el resultado de la exportación
	 */
	@SuppressWarnings("rawtypes")
	public boolean exportar(ArrayList list, String file) {
		PersistidorArchivo p = new PersistidorArchivo();
        try {
			p.exportar(list, file);
			return true;
		} catch (Exception e) {
			System.err.println(e.toString());
			return false;
		}
	}
	
	
}
