package poouned.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import poouned.modelos.Directorio;
import poouned.modelos.Empresa;

/**
 * Clase del panel para introducir nueva empresa.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({ "serial", "unused" })
public class EmpresaPanel extends JPanel {

	private JTextField txtCif;
	private JTextField txtRazonSocial;
	private JTextField txtDomicilio;
	private Directorio directorio;

	/**
	 * Constructor de la clase.
	 * @param directorio gestor de clientes.
	 */
	public EmpresaPanel(Directorio directorio) {
		this.directorio = directorio;
		//setBounds(100, 100, 450, 200);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblCif = new JLabel("CIF");
		lblCif.setBounds(10, 27, 83, 14);
		add(lblCif);

		txtCif = new JTextField();
		txtCif.setBounds(115, 24, 86, 20);
		txtCif.setColumns(13);
		txtCif.requestFocus();
		add(txtCif);

		JLabel lblRazonSocial = new JLabel("Razón Social");
		lblRazonSocial.setBounds(10, 62, 83, 14);
		add(lblRazonSocial);

		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(115, 59, 310, 20);
		txtRazonSocial.setColumns(10);
		add(txtRazonSocial);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 97, 83, 14);
		add(lblDomicilio);

		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(115, 95, 310, 20);
		txtDomicilio.setColumns(10);
		add(txtDomicilio);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(330, 130, 90, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarFormulario()) {
					try {
						Empresa empresa = new Empresa();
						empresa.setCif(txtCif.getText().toString());
						empresa.setRazonSocial(txtRazonSocial.getText());
						empresa.setDomicilio(txtDomicilio.getText());
						directorio.add(empresa); 
						txtCif.setText("");
						txtRazonSocial.setText("");
						txtDomicilio.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
					} finally {
						txtCif.requestFocus();
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
		if (txtCif.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo CIF no puede estar vacio.");
			txtCif.requestFocus();
			return false;
		} else if (txtCif.getText().trim().length() > 9) {
			JOptionPane.showMessageDialog(this,
					"El campo CIF no puede tener más de 9 caracteres.");
			txtCif.requestFocus();
			return false;
		} else if (txtRazonSocial.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Razón Social no puede estar vacio.");
			txtRazonSocial.requestFocus();
			return false;
		} else if (txtDomicilio.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Domicilio no puede estar vacio.");
			txtDomicilio.requestFocus();
			return false;
		} else {
			return true;
		}

	}

}
