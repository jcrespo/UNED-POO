package poouned;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import poouned.gui.CajaPanel;
import poouned.gui.ClientesPanel;
import poouned.gui.FacturasPanel;
import poouned.gui.ProductosPanel;
import poouned.gui.VentasPanel;
import poouned.gui.VisorListadosPanel;
import poouned.modelos.Cliente;
import poouned.modelos.Directorio;
import poouned.modelos.Facturas;
import poouned.modelos.Inventario;
import poouned.modelos.Ventas;
import poouned.util.Listados;

/**
 * Clase Controlador de la aplicación. Ejecuta la ventana principal, los menús e
 * inicializa las estructuras de datos necesarias pasándolas como parámetros a
 * los paneles.
 * 
 */
@SuppressWarnings("serial")
public class Controlador extends JFrame {

	private Inventario inventario;
	private Directorio directorio;
	private Ventas ventas;
	private Facturas facturas;
	private JPanel panelProductos, panelClientes, panelCaja, panelVentas,
			panelFacturas;

	/**
	 * Constructor de la clase. Inicializa las estructuras de datos y las
	 * caaracterísticas de la GUI
	 */
	public Controlador() {

		inventario = new Inventario();
		directorio = new Directorio();
		ventas = new Ventas(inventario);
		facturas = new Facturas();

		setPreferredSize(new Dimension(950, 600));
		setMinimumSize(new Dimension(950, 400));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Práctica TPV - Programación Orientada a Objetos - Uned Curso 2014/2015");
		initMenu();
		setVisible(true);
		importacion();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent ev) {
				cierre();
			}
		});
	}

	/**
	 * Método auxiliar privado que crea el menú de la aplicación. Es llamado por
	 * el constructor al inicio de la aplicación. Contiene los listener en forma
	 * de clases anónimas con la comprobación de parámetros previa a mostrar el
	 * panel correspondiente.
	 */
	private void initMenu() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setMnemonic(KeyEvent.VK_A);

		menuBar.add(menuArchivo);
		
		JMenuItem menuImportar = new JMenuItem("Importar", KeyEvent.VK_I);
		menuImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importacion();
			}
		});
		menuArchivo.add(menuImportar);
		
		
		JMenuItem menuExportar = new JMenuItem("Exportar", KeyEvent.VK_E);
		menuExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportacion();
			}
		});
		menuArchivo.add(menuExportar);
		
		
		JMenuItem menuSalir = new JMenuItem("Salir", KeyEvent.VK_S);
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cierre();
			}
		});
		menuArchivo.add(menuSalir);
		
		
		JMenu menuProductos = new JMenu("Productos");
		menuProductos.setMnemonic(KeyEvent.VK_P);
		menuBar.add(menuProductos);

		JMenuItem menuProductoInventario = new JMenuItem("Inventario", KeyEvent.VK_V);
		menuProductoInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProductos = new ProductosPanel(inventario);
				setContentPane(panelProductos);
				setVisible(true);
			}
		});
		menuProductos.add(menuProductoInventario);

		JMenu menuClientes = new JMenu("Clientes");
		menuClientes.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menuClientes);
		JMenuItem menuDirectorioClientes = new JMenuItem("Directorio", KeyEvent.VK_D);
		menuDirectorioClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelClientes = new ClientesPanel(directorio);
				setContentPane(panelClientes);
				setVisible(true);
			}
		});
		menuClientes.add(menuDirectorioClientes);

		JMenu menuCaja = new JMenu("Caja");
		menuCaja.setMnemonic(KeyEvent.VK_J);
		menuBar.add(menuCaja);

		JMenuItem menuCajaTpv = new JMenuItem("TPV", KeyEvent.VK_T);
		menuCajaTpv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((inventario.size() == 0 || directorio.size() == 0)) {
					JOptionPane
							.showMessageDialog(
									null,
									"No se puede hacer ventas sin productos y clientes.",
									"Error en la venta.",
									JOptionPane.ERROR_MESSAGE);
				} else {
					panelCaja = new CajaPanel(inventario, ventas, directorio);
					setContentPane(panelCaja);
					setVisible(true);
				}
			}
		});
		menuCaja.add(menuCajaTpv);

		JMenuItem menuVentas = new JMenuItem("Ventas", KeyEvent.VK_V);
		menuVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelVentas = new VentasPanel(ventas);
				setContentPane(panelVentas);
				setVisible(true);
			}
		});
		menuCaja.add(menuVentas);

		JMenuItem menuFacturas = new JMenuItem("Facturas", KeyEvent.VK_F);
		menuFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFacturas = new FacturasPanel(directorio, ventas, facturas);
				setContentPane(panelFacturas);
				setVisible(true);
			}
		});
		menuCaja.add(menuFacturas);

		JMenu menuListados = new JMenu("Listados");
		menuListados.setMnemonic(KeyEvent.VK_L);
		menuBar.add(menuListados);

		JMenuItem menuListado1 = new JMenuItem(
				"1.- Ventas agrupadas por clientes.");
		menuListado1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = JOptionPane
						.showInputDialog("Fecha Inicial (mm/dd/yyyy)");
				String s2 = JOptionPane
						.showInputDialog("Fecha Final (mm/dd/yyyy)");
				try {
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					Date fechaInicial = new Date(s1);
					Date fechaFinal = new Date(s2);
					Listados listados = new Listados(ventas);
					VisorListadosPanel panelListado1 = new VisorListadosPanel(
							"Listado 1: Ventas realizadas agrupadas por cliente",
							listados.getIntervaloGroupByClientes(fechaInicial,
									fechaFinal));
					setContentPane(panelListado1);
					setVisible(true);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null,
							"No se ha podido crear intervalo de fechas.",
							"Error.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuListados.add(menuListado1);

		JMenuItem menuListado2 = new JMenuItem("2.- Ventas a un cliente.");
		menuListado2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = JOptionPane
						.showInputDialog("Fecha Inicial (mm/dd/yyyy)");
				String s2 = JOptionPane
						.showInputDialog("Fecha Final (mm/dd/yyyy)");
				String c = JOptionPane.showInputDialog("Nif-Cif del cliente");
				try {
					Date fechaInicial = new Date(s1);
					Date fechaFinal = new Date(s2);
					Cliente cliente = directorio.findById(c);
					Listados listados = new Listados(ventas);
					VisorListadosPanel panelListado2 = new VisorListadosPanel(
							"Listado 2: Ventas realizadas a un cliente",
							listados.getIntervaloCliente(fechaInicial,
									fechaFinal, cliente));
					setContentPane(panelListado2);
					setVisible(true);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null,
							"Error en los parámetros.", "Error.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuListados.add(menuListado2);

		JMenuItem menuListado3 = new JMenuItem("3.- Ranking de productos.");
		menuListado3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = JOptionPane
						.showInputDialog("Fecha Inicial (mm/dd/yyyy)");
				String s2 = JOptionPane
						.showInputDialog("Fecha Final (mm/dd/yyyy)");
				try {
					Date fechaInicial = new Date(s1);
					Date fechaFinal = new Date(s2);
					Listados listados = new Listados(ventas);
					VisorListadosPanel panelListado3 = new VisorListadosPanel(
							"Listado 3: Ranking de productos.", listados
									.rankingProductosIntervalo(fechaInicial,
											fechaFinal));
					setContentPane(panelListado3);
					setVisible(true);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null,
							"No se ha podido crear intervalo de fechas.",
							"Error.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuListados.add(menuListado3);

	}

	/**
	 * Método auxiliar privado que importa los datos de la aplicación desde los
	 * archivos. Es llamado por el constructor al inicio de la aplicación.
	 */
	private void importacion() {
		if (JOptionPane.showConfirmDialog(null,
				"¿Desea realizar importación desde los archivos?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				inventario.importar();
				directorio.importar();
				ventas.importar();
				facturas.importar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Método auxiliar privado que exporta los datos de la aplicación desde los
	 * archivos.
	 */
	private void exportacion() {
		try {
			inventario.exportar();
			directorio.exportar();
			ventas.exportar();
			facturas.exportar();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,
						ex.getMessage(), "Error.", JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/**
	 * Método auxiliar privado que realiza un cierre ordenado de la aplicación.
	 * Llama al método para exportar los datos antes de salir.
	 */
	private void cierre() {
		if (JOptionPane.showConfirmDialog(null,
				"¿Seguro que quiere salir?", "WARNING",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			exportacion();
			System.exit(0);
		}
	}
}