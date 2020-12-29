package poouned.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import poouned.modelos.Ticket;

/**
 * Clase con el panel para visualizar tickets.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class VisorTicketPanel extends JPanel {

	private Ticket ticket;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param factura factura para la que van a mostrar sus datos.
	 */
	public VisorTicketPanel(Ticket ticket) {
		this.ticket = ticket;
		setLayout(new BorderLayout());
		
		JLabel titulo = new JLabel("Ticket");
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		add(titulo, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea txtFactura = new JTextArea(25, 55);
		txtFactura.setText(cuerpoTicket());
		txtFactura.setEditable(false);
		scrollPane.add(txtFactura);
		scrollPane.setViewportView(txtFactura);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Método auxiliar para componer el string del cuerpo del ticket.
	 * 
	 * @return cuerpo String con el cuerpo de la factura.
	 */
	private String cuerpoTicket() {
		
		StringBuffer bfr = new StringBuffer();

		bfr.append(ticket);
		
		return bfr.toString();	
	}
}
