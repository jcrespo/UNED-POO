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

import poouned.modelos.Directorio;
import poouned.modelos.Factura;
import poouned.modelos.Facturas;
import poouned.modelos.Ventas;
import poouned.modelos.tablemodel.FacturasTM;

/**
 * Clase con el panel de mantenimiento de facturas.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class FacturasPanel extends JPanel implements Observer {
	
	private JTable tabla;
	private Facturas facturas;
	private FacturasTM modelo;
	private Directorio directorio;
	private Ventas ventas;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param directorio gestor de clientes.
	 * @param ventas gestor de ventas.
	 * @param facturas gestor de facturas.
	 */
	public FacturasPanel(Directorio directorio, Ventas ventas, Facturas facturas) {
		this.ventas = ventas;
		this.facturas = facturas;
		this.directorio = directorio;
		this.ventas.addObserver(this);
		this.facturas.addObserver(this);
		this.directorio.addObserver(this);
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
		
		
		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabla);
        modelo = new FacturasTM(facturas);
        tabla.setModel(modelo);
        
		
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
		
		JButton btnNuevaEmpresa = new JButton("Nueva Factura");
		botonesPanel.add(btnNuevaEmpresa);
		btnNuevaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacturaPanel facturaPanel = new FacturaPanel(facturas, directorio, ventas);
				JDialog facturaDialog = new JDialog();
				facturaDialog.setModal(true);
				facturaDialog.setResizable(false);
				facturaDialog.setTitle("Añadir factura.");
				facturaDialog.setBounds(100, 100, 450, 300);
				facturaDialog.setContentPane(facturaPanel);
				facturaDialog.setVisible(true);
			}
		});
		
		
		JButton btnDetalle = new JButton("Detalle");
		botonesPanel.add(btnDetalle);
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Factura factura = facturas.get(tabla.getSelectedRow());
					JPanel vf = new VisorFacturaPanel(factura);
					JDialog v = new JDialog();
					v.setModal(true);
					v.setTitle("Detalle factura " + factura.getNumeroFactura());
					v.setBounds(100, 100, 800, 600);
					v.setContentPane(vf);
					v.setVisible(true);
					vf.setVisible(true);	
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(
							null, "Error en mostrar el detalle de la factura.",
							"Error.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JButton btnEliminar = new JButton("Eliminar");
		botonesPanel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Confirma la eliminación de la factura?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						facturas.remove(tabla.getSelectedRow());
					}  catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane
						.showMessageDialog(
								null, "No se ha seleccionado una factura.",
								"Error en la eliminación.", JOptionPane.ERROR_MESSAGE);
					} catch (IndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(
								null, "No se ha podido eliminar la factura.",
								"Error en la eliminación.", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		add(botonesPanel, BorderLayout.SOUTH);
	}

	
	/**
	 * Método que implementa el método update interfaz Observable
	 * Cuando se produce un cambio en los modelos, se actualiza
	 * el interfaz de usuario para reflejar los cambios en la GUI
	 */
	@Override
	public void update(Observable o, Object arg) {
		tabla.updateUI();
	}


}
