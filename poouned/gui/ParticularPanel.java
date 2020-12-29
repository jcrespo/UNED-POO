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
import poouned.modelos.Particular;

/**
 * Clase del panel para introducir nuevo cliente particular.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({ "serial", "unused" })
public class ParticularPanel extends JPanel {

	private JTextField txtNif;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDomicilio;
	private Directorio directorio;

	/**
	 * Constructor de la clase
	 * 
	 * @param directorio gestor de clientes
	 */
	public ParticularPanel(Directorio directorio) {
		this.directorio = directorio;
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblNif = new JLabel("NIF");
		lblNif.setBounds(10, 27, 83, 14);
		add(lblNif);

		txtNif = new JTextField();
		txtNif.setBounds(115, 24, 100, 20);
		txtNif.setColumns(13);
		txtNif.requestFocus();
		add(txtNif);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 62, 83, 14);
		add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(115, 59, 300, 20);
		txtNombre.setColumns(10);
		add(txtNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 97, 83, 14);
		add(lblApellidos);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(115, 94, 300, 20);
		txtApellidos.setColumns(10);
		add(txtApellidos);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 132, 83, 14);
		add(lblDomicilio);

		txtDomicilio = new JTextField();
		txtDomicilio.setBounds(115, 130, 300, 20);
		txtDomicilio.setColumns(10);
		add(txtDomicilio);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(320, 170, 89, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarFormulario()) {
					try {
						Particular particular = new Particular();
						particular.setNif(txtNif.getText().toString()); 
						particular.setNombre(txtNombre.getText());
						particular.setApellidos(txtApellidos.getText());
						particular.setDomicilio(txtDomicilio.getText());
						directorio.add(particular);
						txtNif.setText("");
						txtNombre.setText("");
						txtApellidos.setText("");
						txtDomicilio.setText("");
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
					} finally {
						txtNif.requestFocus();
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
		if (txtNif.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo NIF no puede estar vacio.");
			txtNif.requestFocus();
			return false;
		} else if (txtNif.getText().trim().length() > 9) {
			JOptionPane.showMessageDialog(this,
					"El campo NIF no puede tener más de 9 caracteres.");
			txtNif.requestFocus();
			return false;
		} else if (txtNombre.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Nombre no puede estar vacio.");
			txtNombre.requestFocus();
			return false;
		} else if (txtApellidos.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo Apellidos no puede estar vacio.");
			txtApellidos.requestFocus();
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
