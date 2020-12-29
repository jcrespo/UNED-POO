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

import poouned.modelos.Ticket;
import poouned.modelos.Ventas;
import poouned.modelos.tablemodel.TicketTM;

/**
 * Clase con el panel de ventas del TPV
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class VentasPanel extends JPanel implements Observer {

	private Ventas ventas;
	protected JTable tabla;
	private TicketTM modelo;

	/**
	 * Constructor de la clase.
	 * 
	 * @param ventas gestor de ventas.
	 */
	public VentasPanel(Ventas ventas) {
		this.ventas = ventas;
		this.ventas.addObserver(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);

		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabla);
		modelo = new TicketTM(ventas);
		tabla.setModel(modelo);

		JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnDetalle = new JButton("Detalle");
		botonesPanel.add(btnDetalle);
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Ticket ticket = ventas.get(tabla.getSelectedRow());
					JPanel vt = new VisorTicketPanel(ticket);
					JDialog v = new JDialog();
					v.setModal(true);
					v.setTitle("Detalle ticket " + ticket.getCodigo());
					v.setBounds(100, 100, 800, 600);
					v.setContentPane(vt);
					v.setVisible(true);
					vt.setVisible(true);	
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(
							null, "Error en mostrar el detalle del ticket.",
							"Error.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		botonesPanel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane
						.showConfirmDialog(
								null,
								"La eliminación de una venta implica una devolución\n"
								+ "de los productos al stock.\n"
								+ "¿Confirma la eliminación de la venta?",
								"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						ventas.devolucion(tabla.getSelectedRow());
					}
					 catch (ArrayIndexOutOfBoundsException ex) {
							JOptionPane
							.showMessageDialog(
									null, "No se ha seleccionado una venta.",
									"Error en la eliminación de la venta.", JOptionPane.ERROR_MESSAGE);
						} catch (IndexOutOfBoundsException ex) {
							JOptionPane.showMessageDialog(
									null, "No se ha podido eliminar la venta.",
									"Error en la eliminación de la venta.", JOptionPane.ERROR_MESSAGE);
						}
					catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(),
								"Error en la eliminación de la venta.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		add(botonesPanel, BorderLayout.SOUTH);

	}

	/**
	 * Método que implementa el método update interfaz Observable Cuando se
	 * produce un cambio en los modelos, se actualiza el interfaz de usuario
	 * para reflejar los cambios en la GUI.
	 */
	@Override
	public void update(Observable o, Object arg) {
		tabla.updateUI();
	}
}