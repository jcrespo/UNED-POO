package poouned.modelos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import poouned.util.QuantityWrongException;
import poouned.util.Texto;

/**
 * Esta clase representa un ticket de venta. Internamente utiliza
 * ArrayList para agrupar objetos de tipo ProductoVendido.
 * 
 * @author Francisco Javier Crespo Jiménez.
 * @category modelos
 */
public class Ticket extends AbstractModel implements Serializable {

	private String codigo;
	private Date fecha;
	private Cliente cliente;
	private ArrayList<ProductoVendido> productos;
	private static final long serialVersionUID = 1435185431313421872L;
	private boolean facturado;
	
	/**
	 * Constructor de la clase.
	 */
	public Ticket() {
		SimpleDateFormat formatoCodigo = new SimpleDateFormat("yyyyMMddHHmmss");
		this.fecha = new Date();
		this.codigo = formatoCodigo.format(fecha);
		this.productos = new ArrayList<ProductoVendido>();
		this.facturado = false;
	}
	
	/**
	 * Obtiene el código del ticket.
	 * @return el código del ticket.
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Obtiene la fecha del ticket.
	 * @return la fecha del ticket.
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Ajusta la fecha del ticket.
	 * @param la fecha del ticket.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
		setChanged();
	}
	
	/**
	 * Ajusta el cliente del ticket.
	 * @param el cliente del ticket.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		setChanged();
	}
	
	/**
	 * Obtiene el cliente del ticket.
	 * @return el cliente del ticket.
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * Obtiene el importe del ticket.
	 * @return el importe del ticket.
	 */
	public double getImporte() {
		double importe = 0;
		for (ProductoVendido producto: productos) 
			importe = importe + (producto.getPrecio() * producto.getUnidades()) ;
		return importe;
	}
	
	/**
	 * Obtiene el período fiscal del ticket.
	 * @return el período fiscal del ticket.
	 */
	public String getPeriodoFiscal() {
		SimpleDateFormat formatoPeriodoFiscal = new SimpleDateFormat("yyyy");
		return formatoPeriodoFiscal.format(fecha);
	}
	
	/**
	 * Obtiene si el ticket ha sido facturado.
	 * @return si el ticket ha sido facturado.
	 */
	public boolean getFacturado() {
		return this.facturado;
	}
	
	/**
	 * Ajusta la facturación del ticket.
	 * @param la facturación del ticket.
	 */
	public void setFacturado(boolean valor) {
		this.facturado = valor;
	}
	
	/**
	 * Ajusta el ticket como facturado.
	 */
	public void facturar() {
		this.facturado = true;
	}
	
	/**
	 * Obtiene la lista de productos del ticket.
	 * @return la lista de productos del ticket.
	 */
	public ArrayList<ProductoVendido> getProductos() {
		return productos;
	}
	
	/**
	 * Añade un producto del inventario para ser incluido en el ticket como producto vendido.
	 * @param producto el producto del inventario a incluir en el ticket.
	 * @param unidades del producto a vender.
	 * @throws QuantityWrongException en caso de que las unidades a vender sea superior a las existencias.
	 */
	public void addProducto(ProductoInventariado producto, int unidades) throws QuantityWrongException {
		
		if (producto.getStock() >= unidades) {
			ProductoVendido productoV = new ProductoVendido(
					producto.getCodigo(), producto.getDescripcion(), producto.getPrecio(),
					producto.getIva(), unidades
					);
			productoV.setTicket(this);
			productoV.setChanged();
			this.productos.add(productoV);
			setChanged();
			producto.setStock(producto.getStock() - unidades);
			producto.setChanged();
			
		} else {
			throw new QuantityWrongException("Cantidad superior al stock existente.");
		}
	}
	
	/**
	 * Representación en string del ticket.
	 */
	public String toString() {

		StringBuffer bfr = new StringBuffer();
		
		bfr.append("ticket nº " + codigo + " fecha: " + fecha + "\n");
		bfr.append("Código\tDescripción\t\tUnidades\tPrecio\tIVA\tImporte\n");
		for(ProductoVendido articulo: this.productos) {
			bfr.append(	articulo.getCodigo() + "\t" +
					  	Texto.truncar(articulo.getDescripcion()) +"\t" +
					  	articulo.getUnidades() + "\t" +
					  	Texto.FORMATO_DECIMAL.format(articulo.getPrecio()) + "\t" + 
					  	articulo.getIva() + "%\t" +
					  	Texto.FORMATO_DECIMAL.format((articulo.getUnidades() * articulo.getPrecio())) + "\n");
		}
		
		return bfr.toString();
	}
	
}
