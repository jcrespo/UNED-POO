package poouned.modelos;

import java.util.ArrayList;

import poouned.util.PersistidorArchivo;


/**
 * Esta clase representa el conjunto de productos dados de alta en el inventario
 * de la aplicación. Internamente trabaja con ArrayList pero hace ciertas 
 * comprobaciones adicionales (como comprobar si existe el código del producto
 * antes de ser insertado o implementar métodos de búsqueda por código o descripción).
 * Los métodos públicos se llaman del mismo modo que los de ArrayList.
 * 
 * @author Francisco Javier Crespo Jiménez
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
	 * Añade un producto al inventario
	 * @param producto a añadir
	 * @return el resultado de añadir el producto en la lista
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
	 * Añade una lista de productos al inventario
	 * @param productos a añadir
	 * @return el resultado de la operación de añadir
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
	 * Obtiene un producto por su índice en el inventario.
	 * @param index el índice en el inventario.
	 * @return el producto si ha sido encontrado.
	 */
	public Producto get(int index) {
		return this.productos.get(index);
	}
	
	/**
	 * Busca la existencia del código en el inventario de productos
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
	 * Busca la existencia de una determinada descripción en el inventario de productos.
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
	 * @return el resultado de la operación de eliminar el producto.
	 */
	public boolean remove(Producto producto) {
		boolean result = this.productos.remove(producto);
		setChanged();
		return result;
	}
	
	/**
	 * Elimina un producto del inventario buscándolo por su índice en el inventario.
	 * @param index el índice en el inventario.
	 * @return si ha sido encontrado, se devuelve el producto eliminado.
	 */
	public Producto remove(int index) {
		Producto item = this.productos.remove(index);
		setChanged();
		return item;
	}
	
	/**
	 * Retorna el tamaño del inventario de productos.
	 * @return
	 */
	public int size() {
		return this.productos.size();
	}
	
	/**
	 * Importa el directorio de productos desde archivo.
	 * @return la operación de importar.
	 * @throws Exception es lanzada cuando existe algún problema en la importación.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean importar() throws Exception {

		PersistidorArchivo p = new PersistidorArchivo();
		try {
			addAll((ArrayList) p.importar(this.archivo));
			setChanged();
			return true;
		} catch (Exception e) {
			throw new Exception("Error en la importación.");
		}
	}
	
	/**
	 * Exporta el inventario de productos a archivo.
	 * @return el resultado de la exportación.
	 */
	public boolean exportar() {
		return super.exportar(productos, archivo);
	}
	
	/**
	 * Genera la representación en un string del inventario de productos.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		for (Producto item: productos) bfr.append(bfr + " " + item.toString());
		
		return bfr.toString();
	}
	
	
}
