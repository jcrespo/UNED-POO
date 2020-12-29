package poouned.modelos.tablemodel;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import poouned.modelos.ProductoVendido;
import poouned.util.Numericas;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que mantienen los datos de los productos vendidos.
 * 
 * @author Francisco Javier Crespo Jiménez.
 * @category modelos.tablemodel
 */
@SuppressWarnings("serial")
public class ProductosVendidosTM extends DefaultTableModel {

	private ArrayList<ProductoVendido> ventas;

	/**
	 * Constructor de la clase.
	 * @param lista de ventas.
	 */
	public ProductosVendidosTM(ArrayList<ProductoVendido> ventas) {
		this.ventas = ventas;
	}

	String[] columnas = { "Código", "Descripción", "unidades", "Precio Unitario", "IVA", "Importe" };

	/**
	 * Obtiene el número de columnas.
	 */
	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	
	/**
	 * Obtiene el número de filas.
	 */
	@Override
	public int getRowCount() {

		if (ventas == null) {
			return 0;
		}
		return ventas.size();
	}

	/**
	 * Obtiene el nombre de las columnas.
	 */
	@Override
	public String getColumnName(int col) {
		return columnas[col];
	}

	/**
	 * Ajusta el valor del objeto en la fila y columna indicada.
	 */
	@Override
	public Object getValueAt(int fila, int columna) {
		ProductoVendido producto = (ProductoVendido) ventas.get(fila);
		switch (columna) {
		case 0: return producto.getCodigo();
		case 1: return producto.getDescripcion();
		case 2: return producto.getUnidades();
		case 3: return Numericas.redondeo(producto.getPrecio());
		case 4: return producto.getIva();
		case 5: return Numericas.redondeo(producto.getPrecio() * producto.getUnidades());
		default: return null;
		}
	}

	/**
	 * Devuelve si la combinación de fila y columna es editable.
	 */
	@Override
	public boolean isCellEditable(int fila, int columna) {
		return false;
	}
	
	/**
	 * Devuelve la clase de la columna indicada.
	 */
	@Override
    public Class<?> getColumnClass(int columnIndex) 
    {
       switch (columnIndex) 
       {
       		case 2: return Integer.class;
       		case 3: return Double.class;
       		case 4: return Integer.class;
       		case 5: return Double.class;
       		default: return String.class;
       }
    }

}