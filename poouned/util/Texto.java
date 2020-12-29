package poouned.util;

import java.text.DecimalFormat;

/**
 * Clase con utilidad para dar formato a algunas salidas de texto
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category util
 */
public class Texto {
	
	public static final DecimalFormat FORMATO_DECIMAL = new DecimalFormat("####0.##");
	
	private static int TAMANIO_MAX = 24;
	
	/**
	 * M�todo para truncar los textos superiores a TAMANIO_MAX truncando el String y
	 * a�adiendo los puntos suspensivos que indican que el texto ha sido truncado.
	 * 
	 * @param str el string a truncar.
	 * @return es string truncado a TAMANIO_MAX n�mero de caracteres.
	 */
	public static String truncar(String str) {
		if (str.length() > TAMANIO_MAX)
			return str.substring(0, TAMANIO_MAX - 3) + "...";
		else {
			return String.format("%1$-"+ TAMANIO_MAX + "s", str);
		}
	}

}