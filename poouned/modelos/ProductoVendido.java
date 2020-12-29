package poouned.modelos;

import java.io.Serializable;

/**
 * Esta clase extiende a la clase Producto y representa los artículos que van a 
 * formar parte de una venta.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 * @see Producto
 */
public class ProductoVendido extends Producto implements Serializable {
	
	private int unidades;
	private Ticket ticket;
	private static final long serialVersionUID = -7445063567731297627L;

	/**
     * Constructor de la clase ProductoInventariado
     * 
     */
	public ProductoVendido(String codigo, String descripcion, double precioO, int iva, int unidades) {
		this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioO = precioO;
        this.iva = iva;
        this.unidades = unidades;
	}
	
	/**
     * Asigna una cantidad de productos
     * 
     * @param unidades Cantidad de productos en un concepto de venta
     */
    public void setUnidades(int unidades) {
        this.unidades = unidades;
        setChanged();
    }
    
    /**
     * Obtiene la cantidad de unidades vendidas
     * 
     * @return Unidades en un concepto de venta
     */
    public int getUnidades() {
        return this.unidades;
    }

    /**
     * Obtiene el ticket en el que se produjo la venta
     * 
     * @return ticket de la venta
     */
	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * Asigna el ticket en el que se produjo la venta
	 * 
	 * @param ticket de venta
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
		setChanged();
	}
	
}
