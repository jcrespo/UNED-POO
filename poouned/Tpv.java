package poouned;


/**
 * Sistema Terminal Punto de Venta Pr�ctica de la asignatura Programaci�n
 * Orientada a Objetos Curso 2014-2015 Clase requerida para el inicio de la
 * aplicaci�n por el enunciado.
 * 
 * @author Francisco Javier Crespo Jim�nez
 */
public class Tpv {

	/**
	 * M�todo principal de la aplicaci�n
	 * 
	 * @param args No se utilizan par�metros de entrada.

	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Controlador();
            }
        });
	}
}