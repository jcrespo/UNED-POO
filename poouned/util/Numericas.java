package poouned.util;

/**
 * Clase con utilidad para el redondeo de los precios de los productos 
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category util
 */
public class Numericas {
	
	/**
	 * Efectúa el redondeo de un double con 2 cifras decimales
	 * 
	 * @param val cifra a redondear
	 * @return la cifra redondeada con 2 cifras decimales
	 */
	public static double redondeo(double val) {
		return Math.round(val * 100.0) / 100.0;
	}

}
