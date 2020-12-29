package poouned.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import poouned.modelos.Cliente;
import poouned.modelos.Directorio;
import poouned.modelos.Inventario;
import poouned.modelos.ProductoInventariado;
import poouned.modelos.Ticket;
import poouned.modelos.Ventas;
import poouned.modelos.tablemodel.ProductosROTM;
import poouned.modelos.tablemodel.ProductosVendidosTM;
import poouned.util.QuantityWrongException;

/**
 * Clase para el panel de caja del TPV
 * Desde esta ventana se produce la venta de los productos.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class CajaPanel extends JPanel implements Observer {

	private JTable tabla, tablaTicket;
	private JComboBox comboCliente;
	private DefaultComboBoxModel comboModel;
	private ProductosROTM modelo;
	private ProductosVendidosTM modeloVentas;
	private JTextField txtCantidad;
	private Inventario inventario;
	private Directorio directorio;
	private Ventas ventas;
	private Ticket ticket;

	/**
	 * Constructor de la clase
	 * 
	 * @param inventario gestor de productos
	 * @param ventas gestor de tickets
	 * @param directorio gestor de clientes
	 */
	public CajaPanel(Inventario inventario, Ventas ventas, Directorio directorio) {
		this.inventario = inventario;
		this.ventas = ventas;
		this.directorio = directorio;
		this.directorio.addObserver(this);
		this.ventas.addObserver(this);
		this.inventario.addObserver(this);
		initComponents();
		inicioVenta();
		setVisible(true);
	}

	/**
	 * Método privado auxiliar para agrupar la inicialización de los componentes gráficos.
	 * 
	 */
	private void initComponents() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout());

		JPanel panelSuperior, panelInventario, panelTicket, panelTabla1, panelTabla2, panelBotones1, panelBotones2;
		panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

		panelInventario = new JPanel(new BorderLayout());
		panelTabla1 = new JPanel(new BorderLayout());
		panelBotones1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		panelTicket = new JPanel(new BorderLayout());
		panelTabla2 = new JPanel(new BorderLayout());
		panelBotones2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		add(panelSuperior, BorderLayout.NORTH);

		panelInventario.add(panelTabla1, BorderLayout.CENTER);
		panelInventario.add(panelBotones1, BorderLayout.SOUTH);
		add(panelInventario, BorderLayout.WEST);

		panelTicket.add(panelTabla2, BorderLayout.CENTER);
		panelTicket.add(panelBotones2, BorderLayout.SOUTH);
		add(panelTicket, BorderLayout.EAST);

		// Parte Superior
		comboCliente = new JComboBox();
		comboModel = new DefaultComboBoxModel(directorio.getAll().toArray());
		comboCliente.setModel(comboModel);
		comboCliente.setVisible(true);
		panelSuperior.add(comboCliente);

		// Panel Inferior

		// Inventario
		JScrollPane scrollPane = new JScrollPane();
		panelTabla1.add(scrollPane);
		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabla);
		modelo = new ProductosROTM(inventario);
		tabla.setModel(modelo);

		JButton btnFind = new JButton("Buscar");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarProductoPanel buscarProductoPanel = new BuscarProductoPanel(
						inventario, ticket);
				JDialog buscarProductoDialog = new JDialog();
				buscarProductoDialog.setModal(true);
				buscarProductoDialog.setResizable(false);
				buscarProductoDialog.setTitle("Buscar producto.");
				buscarProductoDialog.setBounds(100, 100, 450, 300);
				buscarProductoDialog.setContentPane(buscarProductoPanel);
				buscarProductoDialog.setVisible(true);
			}
		});
		panelBotones1.add(btnFind);

		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		panelBotones1.add(txtCantidad);
		txtCantidad.setColumns(10);

		JLabel lblUnidades = new JLabel("Unidades");
		panelBotones1.add(lblUnidades);

		JButton btnAdd = new JButton("Añadir ->");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (tabla.getSelectedRowCount() > 0
							&& Integer.parseInt(txtCantidad.getText()
									.toString()) > 0) {
						ProductoInventariado pi = (ProductoInventariado) inventario
								.get(tabla.getSelectedRow());
						ticket.addProducto(pi, Integer.parseInt(txtCantidad
								.getText().toString()));
					} else {
						JOptionPane.showMessageDialog(null,
								"Formato de cantidad incorrecto.",
								"Error en la venta.", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,
							"Formato de cantidad incorrecto.",
							"Error en la venta.", JOptionPane.ERROR_MESSAGE);
				} catch (QuantityWrongException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),
							"Error en la venta.", JOptionPane.ERROR_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(null,
							"No se ha seleccionado un producto.",
							"Error en la venta.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelBotones1.add(btnAdd);

		// Ticket
		JScrollPane scrollPane2 = new JScrollPane();
		tablaTicket = new JTable();
		scrollPane2.setViewportView(tablaTicket);
		panelTicket.add(scrollPane2);
		
		JButton btnEnd = new JButton("Finalizar");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ticket.getProductos().size() > 0) {
					ticket.setCliente((Cliente) directorio.get(comboCliente
							.getSelectedIndex()));
					ticket.setFacturado(false);
					try {
						ventas.add(ticket);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
					}
					inicioVenta();

				} else {
					JOptionPane.showMessageDialog(null,
							"No se puede finalizar un ticket sin productos.");
				}
			}
		});
		panelBotones2.add(btnEnd);
	}

	/**
	 * Método privado auxiliar que se ejecuta al inicio del panel y cuando finaliza una venta.
	 * De este modo, se crea un nuevo ticket donde ir insertando artículos.
	 */
	private void inicioVenta() {
		ticket = new Ticket();
		ticket.addObserver(this);
		modeloVentas = new ProductosVendidosTM(ticket.getProductos());
		tablaTicket.setModel(modeloVentas);
	}

	/**
	 * Método que implementa el método update interfaz Observable
	 * Cuando se produce un cambio en los modelos, se actualiza
	 * el interfaz de usuario para reflejar los cambios en la GUI
	 */
	@Override
	public void update(Observable o, Object arg) {
		tablaTicket.updateUI();
		tabla.updateUI();
	}

}
