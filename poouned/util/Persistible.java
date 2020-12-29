package poouned.util;

import java.util.ArrayList;

/**
 * Interfaz que debe implementar la utilidad que haga la persistencia de los
 * datos indicados en el enunciado.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category util
 */
public interface Persistible {

	/**
	 * Método para realizar la exportación de un Object genérico a un destino
	 * 
	 * @param objeto de la clase Object a exportar
	 * @param destino destino de la exportación
	 * @throws Exception Lanza una excepción genérica en caso de problema en la exportación
	 */
	public void exportar(Object objeto, String destino) throws Exception;
	
	/**
	 * Método para realizar la importación desde un origen a un ArrayList de objetos
	 * 
	 * @param origen
	 * @return ArrayList<Object> ArrayList de objetos importados
	 * @throws Exception Lanza una excepción genérica si falla la importación.
	 */
	public ArrayList<Object> importar(String origen) throws Exception;
	
}
