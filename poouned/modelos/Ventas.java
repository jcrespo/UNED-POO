package poouned.modelos;

import java.util.ArrayList;
import java.util.Date;

import poouned.util.PersistidorArchivo;

/**
 * Esta clase se utiliza para agrupar los tickets generados por las ventas desde caja.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category modelos
 */
@SuppressWarnings("serial")
public class Ventas extends AbstractListModel {

	private Inventario inventario;
	private ArrayList<Ticket> tickets;
	private String archivo = "ventas.dat";
	
	/**
	 * Constructor de la clase.
	 */
	public Ventas(Inventario inventario) {
		this.inventario = inventario;
		this.tickets = new ArrayList<Ticket>();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Ventas(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	

	/**
	 * A�adir un ticket a las ventas
	 * @param ticket el ticket a a�adir.
	 * @return el resultado de la operaci�n de a�adir el ticket.
	 * @throws Exception se lanza cuando ya existe el ticket que se pretende introducir.
	 */
	public boolean add(Ticket ticket) throws Exception {
		if (findByCodigo(ticket.getCodigo()) == null) {
			setChanged();
			return this.tickets.add(ticket);
		}else {
			throw new Exception("El ticket con c�digo " + ticket.getCodigo() + " ya existe.");
		}
	}
	
	/**
	 * A�ade una lista de tickets a las ventas.
	 * @param tickets la lista de tickets a a�adir.
	 * @return el resultado de la operaci�n de a�adir la lista de tickets.
	 * @throws Exception si el m�todo add lanza una excepci�n de re-lanza para informar que
	 * la operaci�n no tuvo �xito.
	 */
	public boolean addAll(ArrayList<Ticket> tickets) throws Exception {
		for (Ticket t : tickets) {
			try { add(t); }
			catch (Exception ex) {
				throw new Exception(ex.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * Busca en la lista de ventas, un ticket por su c�digo.
	 * @param codigo el c�digo a buscar.
	 * @return si ha sido encontrado el ticket, se devuelve.
	 */
	public Ticket findByCodigo(String codigo) {
		for (Ticket item: tickets) {
			if (item.getCodigo().equals(codigo)) return item;
		}
		return null;
	}
	
	/**
	 * Obtiene un ticket por su �ndice en la lista de ventas.
	 * @param index el �ndice a buscar.
	 * @return si es encontrado el ticket, se devuelve.
	 */
	public Ticket get(int index) {
		return this.tickets.get(index);
	}
	
	/**
	 * Obtiene la lista de tickets completa.
	 * @return la lista de tickets completa.
	 */
	public ArrayList<Ticket> getAll() {
		return tickets;
	}

	/**
	 * Obtiene una lista de tickets que no han sido facturados previamente.
	 * @param cliente el cliente al que se pretenden facturar sus ventas.
	 * @param periodo el per�odo sobre el cual se van a facturar sus ventas.
	 * @return la lista de tickets para un cliente y un per�odo de tiempo facturables.
	 */
	public ArrayList<Ticket> getFacturables(Cliente cliente, String periodo) {
		ArrayList<Ticket> ticketsFact = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.getCliente().getCodigo().equals(cliente.getCodigo())
			 && (ticket.getPeriodoFiscal().equals(periodo))
			 && (ticket.getFacturado() == false)) {
				ticketsFact.add(ticket);
			}
		}
		return ticketsFact;
	}
	
	/**
	 * Obtiene la lista de clientes que han realizado compras y por tanto tiene tickets en la lista de ventas.
	 * @return la lista de clietnes.
	 */
	public ArrayList<Cliente> getClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for (Ticket ticket : tickets) {
			Cliente cliente = ticket.getCliente();
				if (findById(clientes, cliente.getId()) == null) {
					clientes.add(cliente);
				}
			}
		return clientes;
	}
	
	/**
	 * Obtiene un cliente en la lista de clientes.
	 * @param clientes la lista de clientes.
	 * @param id el id a buscar.
	 * @return si es encontrado, el cliente que se buscaba.
	 */
	private Cliente findById(ArrayList<Cliente> clientes, String id) {
		for (Cliente item: clientes) {
			if (item.getId().equals(id)) return item;
		}
		return null;
	}
	
	
	/**
	 * Obtiene los tickets entre un intervalo de tiempo.
	 * Este m�todo sirve de apoyo para los m�todos que
	 * generan listados basados en peri�dos de tiempo.
	 * @param fechaInicial Fecha inicial de b�squeda.
	 * @param fechaFinal Fecha final de b�squeda.
	 * @return
	 */
	public ArrayList<Ticket> getIntervalo(Date fechaInicial, Date fechaFinal) {
		ArrayList<Ticket> ticketsInterval = new ArrayList<Ticket>();		
		for (Ticket ticket : tickets) {
			if (ticket.getFecha().after(fechaInicial) &&
				ticket.getFecha().before(fechaFinal))
				ticketsInterval.add(ticket);
		}
		return ticketsInterval;
	}
	
	/**
	 * Obtiene las ventas en un intervalo de tiempo para un cliente concreto.
	 * @param fechaInicial la fecha inicial del intervalo de tiempo.
	 * @param fechaFinal la fecha final del intervalo de tiempo.
	 * @param cliente el cliente sobre el que se buscan sus compras.
	 * @return la lista con las ventas realizas en un intervalo de tiempo a un cliente.
	 */
	public ArrayList<Ticket> getIntervaloCliente(Date fechaInicial, Date fechaFinal, Cliente cliente) {
		ArrayList<Ticket> ticketAux = new ArrayList<Ticket>();
		ArrayList<Ticket> ticketsIntervalo = getIntervalo(fechaInicial, fechaFinal);
		for (Ticket ticket : ticketsIntervalo) {
			if (ticket.getCliente().getCodigo().equals(cliente.getCodigo())) {
				ticketAux.add(ticket);
			}
		}
		return ticketAux;
	}
	
	/**
	 * Se elimina un ticket de la lista de ventas.		
	 * @param el ticket a eliminar.
	 * @return el resultado de la operaci�n de eliminar un ticket.
	 */
	public boolean remove(Ticket ticket) {
		boolean result = this.tickets.remove(ticket);
		setChanged();
		return result;
	}
	
	/**
	 * Se elimina un ticket de la lista de ventas por su n�mero de �ndice.
	 * @param el �ndice a buscar.
	 * @return el resultado la operaci�n de eliminar el ticket de la lista de ventas.
	 */
	public Ticket remove(int index) {
		Ticket item = this.tickets.remove(index);
		setChanged();
		return item;
	}
	
	/**
	 * Realiza una devoluci�n de una venta que consiste en eliminar el ticket de la lista de ventas
	 * y reincorporar los art�culos al stock. La condici�n es que dicha venta no haya sido facturada previamente.
	 * @param index el �ndice del ticket para realizar la devoluci�n
	 * @throws IllegalArgumentException
	 */
	public void devolucion(int index) throws IllegalArgumentException {
		Ticket ticket = this.tickets.get(index);
		if (ticket.getFacturado() == false) {
			for (ProductoVendido pv : ticket.getProductos()) {
				ProductoInventariado pi = (ProductoInventariado) inventario.findByCodigo(pv.getCodigo());
				pi.setStock(pi.getStock() + pv.getUnidades());
			}
			this.tickets.remove(index);
			setChanged();
		} else {
			throw new IllegalArgumentException("No se puede devolver una venta facturada.");
		}
	}
	
	/**
	 * Devuelve el tama�o de la lista de ventas.
	 * @return el tama�o de la lista de ventas.
	 */
	public int size() {
		return this.tickets.size();
	}
	
	/**
	 * Realiza la importaci�n de la lista de ventas desde archivo.
	 * @return el resultado de la importaci�n.
	 * @throws Exception si ha tenido �xito la operaci�n de importar.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean importar() throws Exception {
		PersistidorArchivo p = new PersistidorArchivo();
		try {
			addAll((ArrayList) p.importar(this.archivo));
			setChanged();
			return true;
		} catch (Exception e) {
			throw new Exception("Error en la importaci�n.");
		}
	}
	
	/**
	 * Realiza la exportaci�n de la lista de ventas hacia archivo.
	 * @return el resultado de la exportaci�n.
	 */
	public boolean exportar() {
		return super.exportar(tickets, archivo);
	}

	/**
	 * Representaci�n como string de la lista de ventas.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		for (Ticket item: tickets) bfr.append(bfr + " " + item.toString());
		return bfr.toString();
	}
	
	
}
