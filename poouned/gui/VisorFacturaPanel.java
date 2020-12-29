package poouned.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import poouned.modelos.Factura;
import poouned.modelos.ProductoVendido;
import poouned.util.Texto;

/**
 * Clase con el panel para visualizar facturas.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class VisorFacturaPanel extends JPanel {

	private Factura factura;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param factura factura para la que van a mostrar sus datos.
	 */
	public VisorFacturaPanel(Factura factura) {
		this.factura = factura;
		setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel("Factura");
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		add(titulo, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea txtFactura = new JTextArea(25, 55);
		txtFactura.setText(cuerpoFactura());
		txtFactura.setEditable(false);
		scrollPane.add(txtFactura);
		scrollPane.setViewportView(txtFactura);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Método auxiliar para componer el string del cuerpo de la factura.
	 * 
	 * @return cuerpo String con el cuerpo de la factura.
	 */
	private String cuerpoFactura() {
		
		StringBuffer bfr = new StringBuffer();

		bfr.append("Vendedor: CIF " + factura.getCif() + " - " + factura.getRazonSocial() + "\n");
		bfr.append("Cliente: " + factura.getCliente() + "\n");
		bfr.append("Factura nº " + factura.getNumeroFactura() + "Fecha: " + factura.getFecha()+ "\n");
		bfr.append("Código\tTicket\t\tDescripción\t\tUnidades\tPrecio\tImporte\n");
		
		for (ProductoVendido pv : factura.getProductos()) {
			bfr.append(pv.getCodigo() + "\t" +
					  pv.getTicket().getCodigo() + "\t" + 
					  Texto.truncar(pv.getDescripcion()) + "\t" + 
					  pv.getUnidades() + "\t" + 
					  Texto.FORMATO_DECIMAL.format(pv.getPrecio()) + "\t" +
					  Texto.FORMATO_DECIMAL.format((pv.getUnidades() * pv.getPrecio())) + "\n");
		}
		
		bfr.append("\n\nTotal Factura: " + Texto.FORMATO_DECIMAL.format(factura.getImporte()));
		
		return bfr.toString();	
	}
}
