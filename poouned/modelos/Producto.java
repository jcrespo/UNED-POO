package poouned.modelos;

import java.io.Serializable;

/**
 * Esta clase abstracta representa los productos del sistema TPV Es la clase
 * padre de la que deben heredar bien los productos que se van a inventariar
 * para formar parte del stock o bien los articulos que van a formar parte de
 * una venta.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 */
public abstract class Producto extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 3259352728271118932L;
	
	public static final int IVA_GENERAL = 		21;
	public static final int IVA_REDUCIDO = 	10;
	public static final int IVA_SUPERREDUCIDO = 4;
	
	protected String codigo;
	protected String descripcion;
	protected double precioO;
	protected int 	 iva;

	/**
	 * Asigna un código al producto
	 * 
	 * @param codigo Código descriptivo del producto. Un número entero que puede ser un código de barras.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
		setChanged();
	}

	/**
	 * Obtiene el código del producto
	 * 
	 * @return Código descriptivo del producto. Un número entero que puede ser un código de barras.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Asigna una descripción al producto
	 * 
	 * @param descripcion Descripción detallada del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		setChanged();
	}

	/**
	 * Obtiene la descripción del producto
	 * 
	 * @return Descripción detallada del producto.
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Asigna un precio al producto
	 * 
	 * @param precio
	 *            Precio del producto sin IVA
	 */
	public void setPrecioO(double precio) {
		this.precioO = precio;
		setChanged();
	}

	/**
	 * Obtiene el precio del producto sin IVA
	 * 
	 * @return Precio del producto sin IVA
	 */
	public double getPrecioO() {
		return this.precioO;
	}
	
	/**
	 * Asigna un porcentaje en concepto de IVA
	 * 
	 * @param iva
	 *            Impuesto sobre el Valor Añadido
	 */
	public void setIva(int iva) {
		this.iva = iva;
		setChanged();
	}

	/**
	 * Obtiene el porcentaje de IVA
	 * 
	 * @return Porcentaje de IVA aplicable
	 */
	public int getIva() {
		return this.iva;
	}

	/**
	 * Obtiene el precio final del producto IVA incluido
	 * 
	 * @return Precio final del producto
	 */
	public double getPrecio() {
		return (this.precioO + (this.precioO / 100 * this.iva));
	}
	
}
