package poouned;


/**
 * Sistema Terminal Punto de Venta Práctica de la asignatura Programación
 * Orientada a Objetos Curso 2014-2015 Clase requerida para el inicio de la
 * aplicación por el enunciado.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
public class Tpv {

	/**
	 * Método principal de la aplicación
	 * 
	 * @param args No se utilizan parámetros de entrada.

	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Controlador();
            }
        });
	}
}