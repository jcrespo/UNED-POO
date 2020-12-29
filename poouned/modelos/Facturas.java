package poouned.modelos;

import java.util.ArrayList;
import java.util.UUID;

import poouned.util.PersistidorArchivo;

/**
 * Esta clase representa el conjunto de facturas de la empresa.
 * Internamente trabaja con ArrayList pero hace ciertas comprobaciones
 * adicionales (por ejemplo comprobar si existe la factura antes de
 * ser insertada). Los m�todos p�blicos se llaman del mismo modo que los
 * de ArrayList.
 * 
 * @author Francisco Javier Crespo Jim�nez.
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
	 * A�adir una factura a la lista de facturas.
	 * @param factura a a�adir.
	 * @return el resultado de a�adir la factura.
	 * @throws Exception.
	 */
	public boolean add(Factura factura) throws Exception {
		if (findByNumeroFactura(factura.getNumeroFactura()) == null) {
			setChanged();
			return this.facturas.add(factura);
		}else {
			throw new Exception("La factura con n�mero " + factura.getNumeroFactura() + " ya existe.");
		}
	}
	
	/**
	 * A�adir una lista de facturas a la lista existente.
	 * @param facturas a a�adir.
	 * @return el resultado de a�adir la lista.
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
	 * Buscar una factura por su c�digo.
	 * @param numeroFactura c�digo de la factura.
	 * @return si existe, la factura buscada.
	 */
	public Factura findByNumeroFactura(UUID numeroFactura) {
		for (Factura item: facturas) {
			if (item.getNumeroFactura().equals(numeroFactura)) return item;
		}
		return null;
	}
	
	/**
	 * Obtiene una factura por su �ndice en la lista.
	 * @param index el �ndice a recuperar.
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
	 * @return el resultado de la eliminaci�n.
	 */
	public boolean remove(Factura factura) {
		boolean result = this.facturas.remove(factura);
		setChanged();
		return result;
	}
	
	/**
	 * Elimina una factura de la lista de facturas.
	 * @param index posici�n en la lista a eliminar.
	 * @return si ha sido encontrada, la factura eliminada.
	 */
	public Factura remove(int index) {
		Factura item = this.facturas.remove(index);
		setChanged();
		return item;
	}
	
	/**
	 * Devuelve el tama�o de la lista de facturas.
	 * @return tama�o de la lista.
	 */
	public int size() {
		return this.facturas.size();
	}
	
	/**
	 * Importa una lista de facturas desde un archivo.
	 * @return el resultado de la importaci�n.
	 * @throws Exception lanzada en caso de error en la importaci�n.
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
	 * Exporta una lista de facturas a un archivo.
	 * @return el resultado de la exportaci�n.
	 */
	public boolean exportar() {
		return super.exportar(facturas, archivo);
	}
	
	/**
	 * Representaci�n en un string de la lista de facturas.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		
		for (Factura item: facturas) bfr.append(bfr + " " + item.toString());
		
		return bfr.toString();
	}
}