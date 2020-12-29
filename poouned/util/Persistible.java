package poouned.util;

import java.util.ArrayList;

/**
 * Interfaz que debe implementar la utilidad que haga la persistencia de los
 * datos indicados en el enunciado.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category util
 */
public interface Persistible {

	/**
	 * M�todo para realizar la exportaci�n de un Object gen�rico a un destino
	 * 
	 * @param objeto de la clase Object a exportar
	 * @param destino destino de la exportaci�n
	 * @throws Exception Lanza una excepci�n gen�rica en caso de problema en la exportaci�n
	 */
	public void exportar(Object objeto, String destino) throws Exception;
	
	/**
	 * M�todo para realizar la importaci�n desde un origen a un ArrayList de objetos
	 * 
	 * @param origen
	 * @return ArrayList<Object> ArrayList de objetos importados
	 * @throws Exception Lanza una excepci�n gen�rica si falla la importaci�n.
	 */
	public ArrayList<Object> importar(String origen) throws Exception;
	
}
