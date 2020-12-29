package poouned.modelos.tablemodel;

import javax.swing.table.DefaultTableModel;

import poouned.modelos.Factura;
import poouned.modelos.Facturas;
import poouned.util.Numericas;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que mantienen los datos de las facturas.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos.tablemodel
 */
@SuppressWarnings("serial")
public class FacturasTM extends DefaultTableModel {

	private Facturas facturas;

	/**
	 * Constructor de clase
	 * @param facturas
	 */
	public FacturasTM(Facturas facturas) {
		this.facturas = facturas;
	}

	String[] columnas = { "Factura Nº", "Cliente", "Importe" };

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

		if (facturas == null) {
			return 0;
		}
		return facturas.size();
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
		Factura factura = (Factura) facturas.get(fila);
		switch (columna) {
		case 0: return factura.getNumeroFactura();
		case 1: return factura.getCliente().toString();
		case 2: return Numericas.redondeo(factura.getImporte());
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
    public Class<?> getColumnClass(int columnIndex) {
       switch (columnIndex) {
       		case 2: return Double.class;
       		default: return String.class;
       }
    }

}