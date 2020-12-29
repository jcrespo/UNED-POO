package poouned.util;

/**
 * Clase que define una excepci�n personalizada en caso de incluir una cantidad
 * de art�culos en una venta superior a lo existente en el stock. Se implementa
 * a modo de demostraci�n de esta t�cnica ya que se podr�a haber hecho uso de
 * algunas excepciones ya existentes en Java como IllegalArgumentException o
 * InvalidParameterException.
 * 
 * @author Francisco Javier Crespo Jim�nez.
 * @category util
 *
 */
@SuppressWarnings("serial")
public class QuantityWrongException extends Exception {
	/**
	 * Excepci�n lanzada cuando la cantidad de productos es superior a la existente
	 * en el stock de la tienda.
	 * @param msg el mensaje de respuesta a devolver por la excepci�n.
	 */
	public QuantityWrongException(String msg) {
		super(msg);
	}
}
