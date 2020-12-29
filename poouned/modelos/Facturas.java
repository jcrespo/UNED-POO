package poouned.modelos;

import java.util.ArrayList;
import java.util.UUID;

import poouned.util.PersistidorArchivo;

/**
 * Esta clase representa el conjunto de facturas de la empresa.
 * Internamente trabaja con ArrayList pero hace ciertas comprobaciones
 * adicionales (por ejemplo comprobar si existe la factura antes de
 * ser insertada). Los métodos públicos se llaman del mismo modo que los
 * de ArrayList.
 * 
 * @author Francisco Javier Crespo Jiménez.
 * @category modelos
 * @see ArrayList
 */
@SuppressWarnings("serial")
public class Facturas extends AbstractListModel {

	private ArrayList<Factura> facturas;
	private String archivo = "facturas.dat";
	
	/**
	 * Constructor de la clase.
	 */
	public Facturas() {
		this.facturas = new ArrayList<Factura>();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Facturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}

	/**
	 * Añadir una factura a la lista de facturas.
	 * @param factura a añadir.
	 * @return el resultado de añadir la factura.
	 * @throws Exception.
	 */
	public boolean add(Factura factura) throws Exception {
		if (findByNumeroFactura(factura.getNumeroFactura()) == null) {
			setChanged();
			return this.facturas.add(factura);
		}else {
			throw new Exception("La factura con número " + factura.getNumeroFactura() + " ya existe.");
		}
	}
	
	/**
	 * Añadir una lista de facturas a la lista existente.
	 * @param facturas a añadir.
	 * @return el resultado de añadir la lista.
	 * @throws Exception.
	 */
	public boolean addAll(ArrayList<Factura> facturas) throws Exception {
		for (Factura f : facturas) {
			try { add(f); }
			catch (Exception ex) {
				throw new Exception(ex.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * Buscar una factura por su código.
	 * @param numeroFactura código de la factura.
	 * @return si existe, la factura buscada.
	 */
	public Factura findByNumeroFactura(UUID numeroFactura) {
		for (Factura item: facturas) {
			if (item.getNumeroFactura().equals(numeroFactura)) return item;
		}
		return null;
	}
	
	/**
	 * Obtiene una factura por su índice en la lista.
	 * @param index el índice a recuperar.
	 * @return la factura buscada.
	 */
	public Factura get(int index) {
		return this.facturas.get(index);
	}
	
	/**
	 * Obtiene la lista completa de facturas.
	 * @return la lista completa de facturas.
	 */
	public ArrayList<Factura> getAll() {
		return facturas;
	}
	
	/**
	 * Elimina una factura de la lista de facturas.
	 * @param factura a eliminar.
	 * @return el resultado de la eliminación.
	 */
	public boolean remove(Factura factura) {
		boolean result = this.facturas.remove(factura);
		setChanged();
		return result;
	}
	
	/**
	 * Elimina una factura de la lista de facturas.
	 * @param index posición en la lista a eliminar.
	 * @return si ha sido encontrada, la factura eliminada.
	 */
	public Factura remove(int index) {
		Factura item = this.facturas.remove(index);
		setChanged();
		return item;
	}
	
	/**
	 * Devuelve el tamaño de la lista de facturas.
	 * @return tamaño de la lista.
	 */
	public int size() {
		return this.facturas.size();
	}
	
	/**
	 * Importa una lista de facturas desde un archivo.
	 * @return el resultado de la importación.
	 * @throws Exception lanzada en caso de error en la importación.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	 * Exporta una lista de facturas a un archivo.
	 * @return el resultado de la exportación.
	 */
	public boolean exportar() {
		return super.exportar(facturas, archivo);
	}
	
	/**
	 * Representación en un string de la lista de facturas.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		
		for (Factura item: facturas) bfr.append(bfr + " " + item.toString());
		
		return bfr.toString();
	}
}