package poouned.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import poouned.modelos.Inventario;
import poouned.modelos.tablemodel.ProductosTM;

/**
 * Clase con el panel para el mantenimiento de productos.
 * 
 * @author Francisco Javier Crespo Jim�nez
 */
@SuppressWarnings("serial")
public class ProductosPanel extends JPanel implements Observer {
	
	private JTable tabla;
	private ProductosTM modelo;
	private Inventario inventario;
	
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param inventario gestor de productos
	 */
	public ProductosPanel(Inventario inventario) {
		this.inventario = inventario;
		this.inventario.addObserver(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);

		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabla);
        modelo = new ProductosTM(inventario);
        tabla.setModel(modelo);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnNuevo = new JButton("Nuevo");
		botonesPanel.add(btnNuevo);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoPanel productoPanel = new ProductoPanel(inventario);
				JDialog productoDialog = new JDialog();
				productoDialog.setModal(true);
				productoDialog.setResizable(false);
				productoDialog.setTitle("A�adir producto.");
				productoDialog.setBounds(100, 100, 450, 300);
				productoDialog.setContentPane(productoPanel);
				productoDialog.setVisible(true);
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		botonesPanel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "�Confirma la eliminaci�n del producto?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						inventario.remove(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
					} catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane
						.showMessageDialog(
								null, "No se ha seleccionado un producto.",
								"Error en la eliminaci�n.", JOptionPane.ERROR_MESSAGE);
					} catch (IndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(
								null, "No se ha podido eliminar el producto.",
								"Error en la eliminaci�n.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		add(botonesPanel, BorderLayout.SOUTH);
		
	}

	/**
	 * M�todo que implementa el m�todo update interfaz Observable
	 * Cuando se produce un cambio en los modelos, se actualiza
	 * el interfaz de usuario para reflejar los cambios en la GUI
	 */
	@Override
	public void update(Observable o, Object arg) {
		tabla.updateUI();
	}
}