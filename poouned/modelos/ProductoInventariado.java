package poouned.modelos;

import java.io.Serializable;

/**
 * Esta clase extiende a la clase Producto y representa los artículos que van a 
 * permanecer en el inventario del establecimiento.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 * @see Producto
 */
public class ProductoInventariado extends Producto implements Serializable {

	private int stock;
	private static final long serialVersionUID = -8815403396771636987L;


	
	public ProductoInventariado() {
	}
	
	
	/**
     * Constructor de la clase ProductoInventariado
     * Inicializa el IVA con el 21%
     * Inicializa el stock a 0
     */
	public ProductoInventariado(String codigo, String descripcion, double precioO) {
		this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioO = precioO;
        this.iva = IVA_GENERAL;
        this.stock = 0;
	}
	
	/**
     * Constructor de la clase ProductoInventariado
     * Se inicializa con el iva al 21%
     */
	public ProductoInventariado(String codigo, String descripcion, double precioO,
			int stock) {
		this(codigo, descripcion, precioO); 
        this.stock = stock;
	}	
	
	/**
     * Constructor de la clase ProductoInventariado
     */
	public ProductoInventariado(String codigo, String descripcion, double precioO,
			int stock, int iva) {
		this(codigo, descripcion, precioO, stock);
        this.iva = iva;
	}
	
	

	
	/**
     * Asigna una cantidad de productos
     * 
     * @param stock Cantidad de productos existentes
     */
    public void setStock(int stock) {
        this.stock = stock;
        setChanged();
    }
    
    /**
     * Obtiene el stock existente de productos
     * 
     * @return Stock de productos
     */
    public int getStock() {
        return this.stock;
    }
    
    /**
     * Representación en string del un producto inventariado.
     */
    public String toString() {
    	
    	StringBuffer bfr = new StringBuffer();
    	
    	bfr.append( this.codigo + " - " + 
    				this.descripcion + " - " + 
    				this.precioO + "€ - " +
    				this.stock + " unidades");
    	
    	return bfr.toString();
    }

	

	

}
