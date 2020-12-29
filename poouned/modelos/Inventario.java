package poouned.modelos;

import java.util.ArrayList;

import poouned.util.PersistidorArchivo;


/**
 * Esta clase representa el conjunto de productos dados de alta en el inventario
 * de la aplicaci�n. Internamente trabaja con ArrayList pero hace ciertas 
 * comprobaciones adicionales (como comprobar si existe el c�digo del producto
 * antes de ser insertado o implementar m�todos de b�squeda por c�digo o descripci�n).
 * Los m�todos p�blicos se llaman del mismo modo que los de ArrayList.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category modelos
 * @see ArrayList
 */
@SuppressWarnings("serial")
public class Inventario extends AbstractListModel {

	private ArrayList<Producto> productos;
	private String archivo = "inventario.dat";
	
	/**
	 * Constructor de la clase.
	 */
	public Inventario() {
		this.productos = new ArrayList<Producto>();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Inventario(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	
	/**
	 * A�ade un producto al inventario
	 * @param producto a a�adir
	 * @return el resultado de a�adir el producto en la lista
	 * @throws Exception
	 */
	public boolean add(Producto producto) throws Exception {
		if (findByCodigo(producto.getCodigo()) == null) {
			setChanged();
			return this.productos.add(producto);
		} else {
			throw new Exception("El codigo " + producto.getCodigo() + " ya existe.");
		}
	}
	
	/**
	 * A�ade una lista de productos al inventario
	 * @param productos a a�adir
	 * @return el resultado de la operaci�n de a�adir
	 * @throws Exception
	 */
	public boolean addAll(ArrayList<Producto> productos) throws Exception {
		for (Producto p : productos) {
			try { add(p); }
			catch (Exception ex) {
				throw new Exception(ex.getMessage()); 
			}
		}
		return true;
	}
	
	/**
	 * Obtiene un producto por su �ndice en el inventario.
	 * @param index el �ndice en el inventario.
	 * @return el producto si ha sido encontrado.
	 */
	public Producto get(int index) {
		return this.productos.get(index);
	}
	
	/**
	 * Busca la existencia del c�digo en el inventario de productos
	 * @param codigo a buscar
	 * @return si se encuentra el producto es devuelto
	 */
	public Producto findByCodigo(String codigo) {
		for (Producto item: productos) {
			if (item.getCodigo().equals(codigo)) return item;
		}
		return null;
	}
	
	/**
	 * Busca la existencia de una determinada descripci�n en el inventario de productos.
	 * @param descripcion a buscar.
	 * @return si es encontrado, se devuelve el producto.
	 */
	public Producto findByDescripcion(String descripcion) {
		for (Producto item: productos)
			if (item.getDescripcion().startsWith(descripcion)) return item;
		return null;
	}
	
	/**
	 * Elimina un producto del inventario.
	 * @param producto el producto a eliminar.
	 * @return el resultado de la operaci�n de eliminar el producto.
	 */
	public boolean remove(Producto producto) {
		boolean result = this.productos.remove(producto);
		setChanged();
		return result;
	}
	
	/**
	 * Elimina un producto del inventario busc�ndolo por su �ndice en el inventario.
	 * @param index el �ndice en el inventario.
	 * @return si ha sido encontrado, se devuelve el producto eliminado.
	 */
	public Producto remove(int index) {
		Producto item = this.productos.remove(index);
		setChanged();
		return item;
	}
	
	/**
	 * Retorna el tama�o del inventario de productos.
	 * @return
	 */
	public int size() {
		return this.productos.size();
	}
	
	/**
	 * Importa el directorio de productos desde archivo.
	 * @return la operaci�n de importar.
	 * @throws Exception es lanzada cuando existe alg�n problema en la importaci�n.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	 * Exporta el inventario de productos a archivo.
	 * @return el resultado de la exportaci�n.
	 */
	public boolean exportar() {
		return super.exportar(productos, archivo);
	}
	
	/**
	 * Genera la representaci�n en un string del inventario de productos.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		for (Producto item: productos) bfr.append(bfr + " " + item.toString());
		
		return bfr.toString();
	}
	
	
}
