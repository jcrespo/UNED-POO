package poouned.modelos;

import java.io.Serializable;

/**
 * Esta clase abstracta representa los productos del sistema TPV Es la clase
 * padre de la que deben heredar bien los productos que se van a inventariar
 * para formar parte del stock o bien los articulos que van a formar parte de
 * una venta.
 * 
 * @author Francisco Javier Crespo Jim�nez
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
	 * Asigna un c�digo al producto
	 * 
	 * @param codigo C�digo descriptivo del producto. Un n�mero entero que puede ser un c�digo de barras.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
		setChanged();
	}

	/**
	 * Obtiene el c�digo del producto
	 * 
	 * @return C�digo descriptivo del producto. Un n�mero entero que puede ser un c�digo de barras.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Asigna una descripci�n al producto
	 * 
	 * @param descripcion Descripci�n detallada del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		setChanged();
	}

	/**
	 * Obtiene la descripci�n del producto
	 * 
	 * @return Descripci�n detallada del producto.
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
	 *            Impuesto sobre el Valor A�adido
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
