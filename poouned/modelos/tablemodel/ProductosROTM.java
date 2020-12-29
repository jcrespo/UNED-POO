package poouned.modelos.tablemodel;

import poouned.modelos.Inventario;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que muestran los datos de los productos pero
 * no pueden editarlos.
 * Esta clase hereda de ProductosTM para hacer no editables las celdas de la tabla. 
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos.tablemodel
 * @see ProductosTM
 */
@SuppressWarnings("serial")
public class ProductosROTM extends ProductosTM {

	/**
	 * Constructor de la clase
	 * @param el inventario de productos
	 */
	public ProductosROTM(Inventario productos) {
		super(productos);
	}

	/**
	 * Devuelve si la combinación de fila y columna es editable.
	 */
	@Override
	public boolean isCellEditable(int fila, int columna) {
			return false;
	}

}