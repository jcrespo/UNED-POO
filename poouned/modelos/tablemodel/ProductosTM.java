package poouned.modelos.tablemodel;

import javax.swing.table.DefaultTableModel;

import poouned.modelos.Inventario;
import poouned.modelos.ProductoInventariado;
import poouned.util.Numericas;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que mantienen los datos de los productos.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos.tablemodel
 */
@SuppressWarnings("serial")
public class ProductosTM extends DefaultTableModel {

	private Inventario productos;

	/**
	 * Constructor de la clase
	 * @param productos
	 */
	public ProductosTM(Inventario productos) {
		this.productos = productos;
	}

	String[] columnas = { "Código", "Descripción", "Precio Bruto", "IVA",
			"Precio IVA", "Stock" };

	/**
	 * Obtiene el número de columnas
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
		
		if (productos == null) {
			return 0;
		}
		
		return productos.size();
	}

	/**
	 * Obtiene el nombre de las columnas.
	 */
	@Override
	public String getColumnName(int col) {
		return columnas[col];
	}

	/**
	 * Obtiene el valor de la combinación fila y columna.
	 */
	@Override
	public Object getValueAt(int fila, int columna) {
		ProductoInventariado producto = (ProductoInventariado) productos.get(fila);
		switch (columna) {
		case 0: return producto.getCodigo();
		case 1: return producto.getDescripcion();
		case 2: return Numericas.redondeo(producto.getPrecioO());
		case 3: return producto.getIva();
		case 4: return Numericas.redondeo(producto.getPrecio());
		case 5: return producto.getStock();
		default: return null;
		}
	}

	/**
	 * Devuelve si la combinación de fila y columna es editable.
	 */
	@Override
	public boolean isCellEditable(int fila, int columna) {
		if (columna == 0 || columna == 4) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Ajusta el valor del objeto en la fila y columna indicada.
	 */
	@Override
	public void setValueAt(Object valor, int fila, int columna) {
		ProductoInventariado producto = (ProductoInventariado) productos.get(fila);
		switch (columna) {
		case 0: producto.setCodigo(valor.toString()); break;
		case 1: producto.setDescripcion(valor.toString()); break;
		case 2: producto.setPrecioO(Double.parseDouble(valor.toString())); break;
		case 3: producto.setIva(Integer.parseInt(valor.toString())); break;
		case 5: producto.setStock(Integer.parseInt(valor.toString())); break;
		}
	}
	
	/**
	 * Devuelve la clase de la columna indicada.
	 */
	@Override
    public Class<?> getColumnClass(int columnIndex) {
       switch (columnIndex) {
       		case 2: return Double.class;
       		case 3: return Integer.class;
       		case 4: return Double.class;
       		case 5: return Integer.class;
       		default: return String.class;
       }
    }
	
}