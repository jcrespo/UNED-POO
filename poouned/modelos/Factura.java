package poouned.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Esta clase representa las facturas.
 * Una factura se compone de los tickets de un cliente para un mismo periodo fiscal.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 */
public class Factura extends AbstractModel implements Serializable {

	private UUID 	numeroFactura;
	private String 	cif;
	private String 	razonSocial;
	private Date 	fecha;
	private Cliente cliente;
	private String 	periodoFiscal;
	private ArrayList<Ticket> tickets;
	private static final long serialVersionUID = 3161759639856319292L;
	
	/**
	 * Constructor de la clase.
	 */
	public Factura() {
		setNumeroFactura();
		this.fecha = new Date();
		this.tickets = new ArrayList<Ticket>();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Factura (UUID numeroFactura, String cif, String razonSocial, Date fecha, Cliente cliente, String periodo) {
		this.numeroFactura 	= numeroFactura;
		this.cif 			= cif;
		this.razonSocial 	= razonSocial;
		this.fecha			= fecha;
		this.cliente 		= cliente;
		this.periodoFiscal	= periodo;
		
	}

	/**
	 * Obtiene el número de factura
	 * @return el número de factura
	 */
	public UUID getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Ajusta el número de factura
	 */
	protected void setNumeroFactura() {
		this.numeroFactura = UUID.randomUUID();
		setChanged();
	}

	/**
	 * Obtiene el cif de la factura
	 * @return el cif de la factura
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * Ajusta el cif de la factura
	 * @param cif el cif de la factura
	 */
	public void setCif(String cif) {
		this.cif = cif;
		setChanged();
	}

	/**
	 * Obtiene la razón social de la factura
	 * @return la razón social de la factura
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Ajusta la razón social de la factura
	 * @param la razón social de la factura
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
		setChanged();
	}

	/**
	 * Obtiene la fecha de la factura
	 * @return la fecha de la factura
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Ajusta la fecha de la factura
	 * @param fecha a ajustar
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
		setChanged();
	}

	/**
	 * Obtiene el cliente de la factura
	 * @return el cliente de la factura
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Ajusta el cliente de la factura
	 * @param cliente de la factura
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		setChanged();
	}
	
	/**
	 * Añade un ticket a la factura
	 * @param ticket a añadir
	 * @return el resultado de añadir el ticket a la lista
	 */
	public boolean addTicket(Ticket ticket) {
		boolean result = this.tickets.add(ticket);
		setChanged();
		return result;
	}
	
	/**
	 * Añade una lista de tickets
	 * @param tickets a añadir
	 */
	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
		setChanged();
	}
	
	/**
	 * Elimina un ticket de la lista
	 * @param ticket a eliminar
	 * @return el resultado de la eliminación
	 */
	public boolean removeTicket(Ticket ticket) {
		boolean result = this.tickets.remove(ticket);
		setChanged();
		return result;
	}
	
	/**
	 * Resetear la lista de tickets
	 * 
	 */
	public void clearTickets() {
		this.tickets.clear();
		setChanged();
	}
	
	/**
	 * Obtiene una lista con los productos vendidos de la factura
	 * @return productos incluidos en la factura
	 */
	public ArrayList<ProductoVendido> getProductos() {
		ArrayList<ProductoVendido> productosFactura = new ArrayList<ProductoVendido>();
		for (Ticket ticket : tickets) {
			for (ProductoVendido producto : ticket.getProductos()) {
				productosFactura.add(producto);
			}
		}	
		return productosFactura;
	}

	/**
	 * Obtiene el período fiscal de la factura
	 * @return el período fiscal de la factura
	 */
	public String getPeriodoFiscal() {
		return periodoFiscal;
	}

	/**
	 * Ajusta el período fiscal de la factura
	 * @param periodo fiscal de la factura
	 */
	public void setPeriodoFiscal(String periodo) {
		this.periodoFiscal = periodo;
		setChanged();
	}
	
	/**
	 * Obtiene el importe total de la factura
	 * @return el importe total de la factura
	 */
	public double getImporte() {
		double importe = 0;
		for (ProductoVendido pv : getProductos()) importe += (pv.getUnidades() * pv.getPrecio());
		return importe;
	}
	
	/**
	 * Representación de un string con los datos de la factura
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		
		bfr.append("FACTURA\n");
		
		for (Ticket ticket: tickets) {
			bfr.append(bfr + ticket.toString());
		}
		
		return bfr.toString();
	}



}
