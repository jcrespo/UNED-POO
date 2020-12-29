package poouned.modelos.tablemodel;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

import poouned.modelos.Ticket;
import poouned.modelos.Ventas;
import poouned.util.Numericas;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que mantienen los datos de los tickets.
 * 
 * @author Francisco Javier Crespo Jiménez.
 */
@SuppressWarnings("serial")
public class TicketTM extends DefaultTableModel {

	private Ventas ventas;

	/**
	 * Constructor de la clase.
	 * @param lista de ventas.
	 */
	public TicketTM(Ventas ventas) {
		this.ventas = ventas;
	}

	String[] columnas = { "Código", "Fecha", "Cliente", "Importe", "Facturado" };

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
	 * Obtiene el valor de la combinación fila y columna.
	 */
	@Override
	public Object getValueAt(int fila, int columna) {
		Ticket venta = (Ticket) ventas.get(fila);
		switch (columna) {
		case 0: return venta.getCodigo();
		case 1: return venta.getFecha();
		case 2: return venta.getCliente();
		case 3: return Numericas.redondeo(venta.getImporte());
		case 4: return venta.getFacturado();
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
	 * Ajusta el valor del objeto en la fila y columna indicada.
	 */
	@Override
    public Class<?> getColumnClass(int columnIndex) {
       switch (columnIndex) {
       		case 1: return Date.class;
       		case 3: return Double.class;
       		case 4: return Boolean.class;
       		default: return String.class;
       }
    }
	
}