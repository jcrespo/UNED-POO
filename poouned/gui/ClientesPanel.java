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
import poouned.modelos.tablemodel.ClientesTM;


/**
 * Clase para el panel de mantenimiento de clientes.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class ClientesPanel extends JPanel implements Observer {
	
	private JTable 		tabla;
	private ClientesTM 	modelo;
	private Directorio 	directorio;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param directorio gestor de clientes
	 */
	public ClientesPanel(Directorio directorio) {
		this.directorio = directorio;
		this.directorio.addObserver(this);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
		
		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabla);
        modelo = new ClientesTM(directorio);
        tabla.setModel(modelo);

        
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnNuevoParticular = new JButton("Nuevo Particular");
		botonesPanel.add(btnNuevoParticular);
		btnNuevoParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = makeDialog(new ParticularPanel(directorio));
				d.setTitle("Añadir cliente particular.");
				d.setVisible(true);
			}
		});
		
		
		JButton btnNuevaEmpresa = new JButton("Nueva Empresa");
		botonesPanel.add(btnNuevaEmpresa);
		btnNuevaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = makeDialog(new EmpresaPanel(directorio));
				d.setTitle("Añadir cliente empresa.");
				d.setVisible(true);
			}
		});
		
		
		JButton btnEliminar = new JButton("Eliminar");
		botonesPanel.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Confirma la eliminación del cliente?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					try {
						directorio.remove(tabla.convertRowIndexToModel(tabla.getSelectedRow()));
					} catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane
						.showMessageDialog(
								null, "No se ha seleccionado un cliente.",
								"Error en la eliminación.", JOptionPane.ERROR_MESSAGE);
					} catch (IndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(
								null, "No se ha podido eliminar el cliente.",
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
	
	/**
	 * Método auxiliar para crear el cuadro de diálogo modal basado
	 * en un panel
	 * 
	 * @param p interfaz JPanel
	 * @return ventana JDialog con las dimensiones especificadas y modal
	 */
	private JDialog makeDialog(JPanel p) {
		JDialog d = new JDialog();
		d.setModal(true);
		d.setResizable(false);
		d.setBounds(100, 100, 450, 300);
		d.setContentPane(p);

		return d;
	}


}
