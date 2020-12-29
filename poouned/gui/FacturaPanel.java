package poouned.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import poouned.modelos.Cliente;
import poouned.modelos.Directorio;
import poouned.modelos.Factura;
import poouned.modelos.Facturas;
import poouned.modelos.ProductoInventariado;
import poouned.modelos.Ticket;
import poouned.modelos.Ventas;

import com.aeat.valida.Validador;

/**
 * Clase para generar las facturas.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
public class FacturaPanel extends JPanel {
	private JTextField txtCifVendedor, txtRSVendedor, txtPeriodo;
	private JComboBox comboCliente;
	private Facturas facturas;
	private Ventas ventas;
	private Directorio directorio;

	/**
	 * Constructor de la clase.
	 * 
	 * @param facturas gestor de facturas.
	 * @param directorio gestor de clientes.
	 * @param ventas gestor de tickets (ventas).
	 */
	public FacturaPanel(Facturas facturas, Directorio directorio, Ventas ventas) {
		this.facturas = facturas;
		this.directorio = directorio;
		this.ventas = ventas;
		setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 49, 113, 14);
		add(lblCliente);
		
		comboCliente = new JComboBox();
		comboCliente.setModel(new DefaultComboBoxModel(directorio.getAll().toArray()));
		comboCliente.setBounds(179, 46, 234, 20);
		add(comboCliente);

		JLabel lblCifVendedor = new JLabel("Cif Vendedor");
		lblCifVendedor.setBounds(10, 97, 133, 14);
		add(lblCifVendedor);

		txtCifVendedor = new JTextField();
		txtCifVendedor.setBounds(179, 94, 234, 20);
		add(txtCifVendedor);
		txtCifVendedor.setColumns(10);

		JLabel lblRazonSocialVendedor = new JLabel("Razón Social Vendedor");
		lblRazonSocialVendedor.setBounds(10, 145, 159, 14);
		add(lblRazonSocialVendedor);

		txtRSVendedor = new JTextField();
		txtRSVendedor.setBounds(179, 142, 234, 20);
		add(txtRSVendedor);
		txtRSVendedor.setColumns(10);
		
		JLabel lblPeriodoFiscal = new JLabel("Período Fiscal");
		lblPeriodoFiscal.setBounds(10, 193, 133, 14);
		add(lblPeriodoFiscal);
		
		txtPeriodo = new JTextField();
		txtPeriodo.setBounds(180, 190, 86, 20);
		add(txtPeriodo);
		txtPeriodo.setColumns(10);
				
		JButton btnGenerarFactura = new JButton("Generar Factura");
		btnGenerarFactura.setBounds(276, 189, 137, 23);
		add(btnGenerarFactura);
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarFormulario()) {
					try {
						Cliente cliente = (Cliente) directorio.get(comboCliente.getSelectedIndex());
						String periodo = txtPeriodo.getText().toString();
						ArrayList<Ticket> ticketsFacturables = ventas.getFacturables(cliente, periodo);
						if (ticketsFacturables != null && ticketsFacturables.size() > 0) {
							Factura factura = new Factura();
							factura.setCliente(cliente);
							factura.setCif(txtCifVendedor.getText().toString());
							factura.setRazonSocial(txtRSVendedor.getText().toString());
							factura.setPeriodoFiscal(periodo);
							for (Ticket ticket : ticketsFacturables) ticket.facturar();
							factura.setTickets(ticketsFacturables);
							facturas.add(factura);
							txtCifVendedor.setText("");
							txtRSVendedor.setText("");
							txtPeriodo.setText("");
						} else {
							JOptionPane.showMessageDialog(null, 
								"No existen ventas para facturar para este cliente en el período " + periodo,
								"Error.", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
					} finally {
						comboCliente.requestFocus();
					}
				}
			}
		});
	}
	
	/**
	 * Método auxiliar para realizar comprobaciones sobre los datos introducidos en el formulario
	 * 
	 * @return boolean con el resultado de la validación
	 */
	private boolean validarFormulario() {
		if (txtCifVendedor.getText().trim().length() == 9) {
			Validador validador = new Validador();
			int i = validador.checkNif(txtCifVendedor.getText());
			if (!(i > 0)) {
				JOptionPane.showMessageDialog(this,
					"El campo CIF no tiene un formato válido.");
				txtCifVendedor.requestFocus();
				return false;
			}
		}
		if (txtCifVendedor.getText().trim().length() != 9) {
			JOptionPane.showMessageDialog(this,
					"El campo CIF del vendedor debe tener 9 caracteres.");
			txtCifVendedor.requestFocus();
			return false;
		}
		else if (txtRSVendedor.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Razón Social no puede estar vacio.");
			txtRSVendedor.requestFocus();
			return false;
		} else if (txtPeriodo.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Período Fiscal no puede estar vacio.");
			txtPeriodo.requestFocus();
			return false;
		} else {
			return true;
		}
	}

}
