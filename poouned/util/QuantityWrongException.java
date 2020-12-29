package poouned.util;

/**
 * Clase que define una excepción personalizada en caso de incluir una cantidad
 * de artículos en una venta superior a lo existente en el stock. Se implementa
 * a modo de demostración de esta técnica ya que se podría haber hecho uso de
 * algunas excepciones ya existentes en Java como IllegalArgumentException o
 * InvalidParameterException.
 * 
 * @author Francisco Javier Crespo Jiménez.
 * @category util
 *
 */
@SuppressWarnings("serial")
public class QuantityWrongException extends Exception {
	/**
	 * Excepción lanzada cuando la cantidad de productos es superior a la existente
	 * en el stock de la tienda.
	 * @param msg el mensaje de respuesta a devolver por la excepción.
	 */
	public QuantityWrongException(String msg) {
		super(msg);
	}
}
