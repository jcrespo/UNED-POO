package poouned.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import poouned.modelos.Inventario;
import poouned.modelos.Ticket;
import poouned.modelos.ProductoInventariado;
import poouned.util.QuantityWrongException;

/**
 * Clase del panel de búsqueda de los artículos
 * para ser incluidos en la venta.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({ "serial", "unused" })
public class BuscarProductoPanel extends JPanel {

	private JTextField txtBusqueda, txtCantidad;
	private JLabel lblProducto;
	private JButton btnInsertar;
	private Inventario inventario;
	private ProductoInventariado producto;
	private Ticket ticket;

	/**
	 * Constructor de la clase
	 * @param inventario gestor de productos
	 * @param ticket ticket en curso
	 */
	public BuscarProductoPanel(Inventario inventario, Ticket ticket) {

		this.inventario = inventario;
		this.ticket = ticket;

		setBounds(100, 100, 450, 200);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JRadioButton rdbtnCodigo = new JRadioButton("Código");
		rdbtnCodigo.setSelected(true);
		rdbtnCodigo.setBounds(10, 10, 112, 23);
		add(rdbtnCodigo);

		JRadioButton rdbtnDescripcion = new JRadioButton("Descripción");
		rdbtnDescripcion.setBounds(10, 30, 112, 23);
		add(rdbtnDescripcion);

		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(rdbtnCodigo);
		btnGrp.add(rdbtnDescripcion);

		txtBusqueda = new JTextField();
		txtBusqueda.setBounds(130, 20, 250, 20);
		add(txtBusqueda);
		txtBusqueda.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(130, 50, 89, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCodigo.isSelected()) {
					producto = (ProductoInventariado) inventario
							.findByCodigo(txtBusqueda.getText());
				} else {
					producto = (ProductoInventariado) inventario
							.findByDescripcion(txtBusqueda.getText());
				}
				if (producto != null) {
					lblProducto.setText(producto.toString());
					btnInsertar.setEnabled(true);
				} else {
					lblProducto.setText("Sin producto encontrado.");
					btnInsertar.setEnabled(false);
				}
			}
		});

		lblProducto = new JLabel("Sin producto encontrado.");
		lblProducto.setBounds(10, 90, 400, 20);
		lblProducto.setVisible(true);
		add(lblProducto);

		txtCantidad = new JTextField(10);
		txtCantidad.setBounds(80, 150, 89, 23);
		txtCantidad.setText("1");
		add(txtCantidad);

		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(180, 150, 89, 23);
		add(lblUnidades);

		btnInsertar = new JButton("Añadir");
		btnInsertar.setBounds(280, 150, 89, 23);
		btnInsertar.setEnabled(false);
		add(btnInsertar);
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ticket.addProducto(producto,
							Integer.parseInt(txtCantidad.getText().toString()));
				} catch (NumberFormatException | QuantityWrongException ex) {
					JOptionPane.showMessageDialog(null,
							"Formato o cantidad incorrectos.",
							"Error en la venta.", JOptionPane.ERROR_MESSAGE);
				}
				
				lblProducto.setText(producto.toString());
			}
		});

	}

}
