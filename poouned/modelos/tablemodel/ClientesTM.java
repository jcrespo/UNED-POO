package poouned.modelos.tablemodel;

import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import poouned.modelos.Directorio;
import poouned.modelos.Cliente;
import poouned.modelos.Particular;

/**
 * Clase que implementa y concreta DefaultTableModel para ser utilizado como
 * modelo por los objetos JTable que mantienen los datos de los clientes.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos.tablemodel
 */
@SuppressWarnings("serial")
public class ClientesTM extends DefaultTableModel {

	private Directorio clientes;

	/**
	 * Constructor de la clase
	 * @param clientes el directorio de clientes
	 */
	public ClientesTM(Directorio clientes) {
		this.clientes = clientes;
	}

	String[] columnas = { "Código", "Identificación", "Denominación", "Domicilio", "Fecha Alta" };

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
		if (clientes == null) {
			return 0;
		}
		
		return clientes.size();
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
		Cliente cliente = (Cliente) clientes.get(fila);
		switch (columna) {
		case 0: return cliente.getCodigo();
		case 1: return cliente.getId();
		case 2: return cliente.getDenominacion();
		case 3: return cliente.getDomicilio();
		case 4: return cliente.getFechaAlta();
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
		Cliente cliente = (Cliente) clientes.get(fila);
		switch (columna) {
		case 1: cliente.setId(valor.toString()); break;
		case 2: {
			if (cliente.getClass() == poouned.modelos.Particular.class) {
				String nombreNuevo = JOptionPane.showInputDialog("Introduce el nombre");
				String apellidosNuevos = JOptionPane.showInputDialog("Introduce los apellidos.");
				((Particular) cliente).setNombre(nombreNuevo);
				((Particular) cliente).setApellidos(apellidosNuevos);
				break;
			} else {
				cliente.setDenominacion(valor.toString()); break;
			}
		}
		case 3: cliente.setDomicilio(valor.toString()); break;
		}
	}
	
	/**
	 * Devuelve la clase de la columna indicada.
	 */
	@Override
    public Class<?> getColumnClass(int columnIndex) {
       switch (columnIndex) {
       		case 4: return Date.class;
       		default: return String.class;
       }
    }

}