package poouned.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import poouned.modelos.Inventario;
import poouned.modelos.Producto;
import poouned.modelos.ProductoInventariado;

/**
 * Clase para el panel de introducir nuevo producto en el inventario.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({ "serial", "unused" })
public class ProductoPanel extends JPanel {

	private JTextField txtCodigo, txtDescripcion, txtPrecio;
	private JFormattedTextField txtPrecioO, txtStock;

	private Inventario inventario;

	/**
	 * Constructor de la clase.
	 * 
	 * @param inventario gestor de productos
	 */
	public ProductoPanel(Inventario inventario) {
		this.inventario = inventario;
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(10, 27, 83, 14);
		add(lblCodigo);

		txtCodigo = new JTextField(13);
		txtCodigo.setBounds(115, 24, 86, 20);
		add(txtCodigo);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 62, 83, 14);
		add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(115, 59, 309, 20);
		add(txtDescripcion);

		JLabel lblPrecioO = new JLabel("Precio Bruto");
		lblPrecioO.setBounds(10, 97, 83, 14);
		add(lblPrecioO);

		try {
			MaskFormatter mascara = new MaskFormatter("###.##");
			txtPrecioO = new JFormattedTextField(mascara);
			txtPrecioO.setValue("000.00");
			txtPrecioO.setBounds(115, 94, 86, 20);
			add(txtPrecioO);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "Precio incorrecto.", "Error.",
					JOptionPane.ERROR_MESSAGE);
		}

		JLabel lblIvaAplicable = new JLabel("IVA Aplicable");
		lblIvaAplicable.setBounds(10, 136, 83, 14);
		add(lblIvaAplicable);

		String[] tipoIva = { String.valueOf(Producto.IVA_SUPERREDUCIDO),
				String.valueOf(Producto.IVA_REDUCIDO),
				String.valueOf(Producto.IVA_GENERAL) };
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cmbIva = new JComboBox(tipoIva);
		cmbIva.setSelectedIndex(2);
		cmbIva.setBounds(115, 133, 86, 20);
		add(cmbIva);

		JLabel label = new JLabel("%");
		label.setBounds(211, 136, 21, 14);
		add(label);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 176, 83, 14);
		add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setFocusable(false);
		txtPrecio.setBounds(115, 173, 86, 20);
		add(txtPrecio);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(10, 211, 83, 14);
		add(lblStock);

		txtStock = new JFormattedTextField(new Integer(1));

		txtStock.setBounds(115, 208, 86, 20);
		add(txtStock);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(293, 207, 89, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarFormulario()) {
					ProductoInventariado producto = new ProductoInventariado();
					producto.setCodigo(txtCodigo.getText().toString());
					producto.setDescripcion(txtDescripcion.getText());
					try {
						producto.setPrecioO(Double.parseDouble(txtPrecioO
								.getText()));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Formato del campo precio incorrecto.",
								"Error.", JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException ex) {
						JOptionPane.showMessageDialog(null,
								"El campo precio no puede estar en blanco.",
								"Error.", JOptionPane.ERROR_MESSAGE);
					}

					try {
						producto.setIva(Integer.parseInt(tipoIva[cmbIva
								.getSelectedIndex()]));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Formato del campo precio incorrecto.",
								"Error.", JOptionPane.ERROR_MESSAGE);
					}

					try {
						producto.setStock(Integer.parseInt(txtStock.getText()));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Formato del campo stock incorrecto.",
								"Error.", JOptionPane.ERROR_MESSAGE);
					}

					try {
						inventario.add(producto);
						txtCodigo.setText("");
						txtDescripcion.setText("");
						txtPrecioO.setValue("000.00");
						txtStock.setText("1");
						txtCodigo.requestFocus();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(),
								"Error.", JOptionPane.ERROR_MESSAGE);
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

		if (txtCodigo.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo código no puede estar vacio.");
			txtCodigo.requestFocus();
			return false;
		} else if (txtCodigo.getText().trim().length() > 13) {
			JOptionPane.showMessageDialog(this,
					"El campo código no puede tener más de 13 caracteres.");
			txtCodigo.requestFocus();
			return false;
		} else if (txtDescripcion.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo descripción no puede estar vacio.");
			txtDescripcion.requestFocus();
			return false;
		} else if (txtPrecioO.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo precio bruto no puede estar vacio.");
			txtPrecioO.requestFocus();
			return false;
		} else if (txtStock.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(this,
					"El campo stock no puede estar vacio.");
			txtStock.requestFocus();
			return false;
		} else {
			return true;
		}

	}

}
