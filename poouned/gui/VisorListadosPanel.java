package poouned.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * Clase con el panel para visualizar los listados requeridos.
 * 
 * @author Francisco Javier Crespo Jiménez
 */
@SuppressWarnings("serial")
public class VisorListadosPanel extends JPanel {
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param titulo String con el título a mostrar en el JLabel superior.
	 * @param cuerpo String con el cuerpo del listado a ser incluido en el JTextArea.
	 */
	public VisorListadosPanel(String titulo, String cuerpo) {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10) );
		
		JLabel lTitulo = new JLabel(titulo);
		lTitulo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		add(lTitulo, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea txtArea = new JTextArea(25, 55);
		txtArea.setText(cuerpo);
		txtArea.setEditable(false);
		scrollPane.add(txtArea);
		scrollPane.setViewportView(txtArea);
		add(scrollPane, BorderLayout.CENTER);
	}
}