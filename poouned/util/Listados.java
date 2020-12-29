package poouned.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import poouned.modelos.Cliente;
import poouned.modelos.ProductoVendido;
import poouned.modelos.Ticket;
import poouned.modelos.Ventas;

/**
 * Clase con métodos para implementar los listados requeridos en el enunciado.
 * 
 * @author Francisco Javier Crespo Jiménez.
 * @category util
 */
public class Listados {
	
	private Ventas 		ventas;
	
	/**
	 * Constructor de la clase
	 * @param gestor de ventas

	 */
	public Listados(Ventas ventas) {
		this.ventas 	= ventas;
	}


	/**
	 * Listado 1
	 * 
	 * Ventas realizadas en un intervalo de tiempo determinado agrupadas estas
	 * por clientes.
	 * 
	 * @param fechaInicial la fecha inicial de búsqueda.
	 * @param fechaFinal la fecha final de búsqueda.
	 * @return el String con el cuerpo del listado.
	 */
	public String getIntervaloGroupByClientes(Date fechaInicial, Date fechaFinal) {
		StringBuffer bfr = new StringBuffer();
		for (Cliente cliente : ventas.getClientes()) {
			bfr.append("Cliente: " + cliente + "\n");
			for (Ticket ticket : ventas.getIntervaloCliente(fechaInicial,
					fechaFinal, cliente)) {
				for (ProductoVendido pv : ticket.getProductos()) {
					bfr.append(pv.getCodigo());
					bfr.append(" - " + pv.getDescripcion());
					bfr.append(" - " + pv.getUnidades() + " unidades");
					bfr.append(" - "
							+ Texto.FORMATO_DECIMAL.format(pv.getPrecio())
							+ "\n");
				}
			}
			bfr.append("\n");
		}
		return bfr.toString();
	}
	
	

	/**
	 * Listado 2
	 * 
	 * @param fechaInicial la fecha inicial de búsqueda.
	 * @param fechaFinal la fecha final de búsqueda.
	 * @param cliente el cliente del cual se buscan las ventas realizadas.
	 * @return el String con el cuerpo del listado.
	 */
	public String getIntervaloCliente(Date fechaInicial, Date fechaFinal, Cliente cliente) {
		StringBuffer bfr = new StringBuffer();
		ArrayList<Ticket> tickets = ventas.getIntervaloCliente(fechaInicial,
				fechaFinal, cliente);
		bfr.append("Cliente: " + cliente + "\n");
			for (Ticket ticket : tickets) {
				for (ProductoVendido pv : ticket.getProductos()) {
					bfr.append(pv.getCodigo());
					bfr.append(" - " + pv.getDescripcion());
					bfr.append(" - " + pv.getUnidades() + " unidades");
					bfr.append(" - "
							+ Texto.FORMATO_DECIMAL.format(pv.getPrecio())
							+ "\n");
				}
			}
		return bfr.toString();
	}
	
	
	
	/**
	 * Listado 3
	 * 
	 * @param fechaInicial la fecha inicial de búsqueda.
	 * @param fechaFinal la fecha final de búsqueda.
	 * @return el String con el cuerpo del listado.
	 */
	public String rankingProductosIntervalo(Date fechaInicial, Date fechaFinal) {
		ArrayList<ProductoVendido> ranking = new ArrayList<ProductoVendido>();
		ArrayList<Ticket> ventasIntervalo = ventas.getIntervalo(fechaInicial, fechaFinal);
		for (Ticket ticket : ventasIntervalo) {
			for (ProductoVendido pv : ticket.getProductos()) {
				for (ProductoVendido pr : ranking) {
					if (pr.getCodigo().equals(pv.getCodigo())) {
						pr.setUnidades(pr.getUnidades() + pv.getUnidades());
					}
				}
				ranking.add(pv);
			}
		}
		
		Collections.sort(ranking, new Comparator<ProductoVendido>() {
			@Override
			public int compare(ProductoVendido p1, ProductoVendido p2) {
				return (new Integer(p2.getUnidades()).compareTo(new Integer(p1.getUnidades())));
			}
		});
		
		StringBuffer bfr = new StringBuffer();
		for (ProductoVendido pv : ranking) {
			bfr.append(pv.getCodigo());
			bfr.append(" - " + pv.getDescripcion());
			bfr.append(" - " + pv.getUnidades() + " unidades");
			bfr.append(" - " + Texto.FORMATO_DECIMAL.format(pv.getPrecio()) + "\n");
		}
		return bfr.toString();
	}	
	
}
