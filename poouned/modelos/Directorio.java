package poouned.modelos;

import java.util.ArrayList;

import poouned.util.PersistidorArchivo;

/**
 * Esta clase representa el conjunto de clientes de la empresa.
 * Internamente trabaja con ArrayList pero hace ciertas comprobaciones
 * adicionales (por ejemplo comprobar si existe el cliente antes de
 * ser insertado). Los m�todos p�blicos se llaman del mismo modo que los
 * de ArrayList.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category modelos
 * @see ArrayList
 */
@SuppressWarnings("serial")
public class Directorio extends AbstractListModel {

	private ArrayList<Cliente> clientes;
	private String archivo = "directorio.dat";
	
	/**
	 * Constructor de la clase.
	 */
	public Directorio() {
		super();
		this.clientes = new ArrayList<Cliente>();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Directorio(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}
	

	/**
	 * A�ade un cliente al directorio
	 * 
	 * @param cliente cliente a a�adir
	 * @return boolean el resultado de la operaci�n de a�adir el cliente.
	 * @throws IllegalArgumentException si el identificador del cliente ya exist�a se lanza esta excepci�n gen�rica
	 */
	public boolean add(Cliente cliente) throws IllegalArgumentException {
		if (findById(cliente.getId()) == null) {
			setChanged();
			return this.clientes.add(cliente);
		}else {
			throw new IllegalArgumentException("El cliente con id " + cliente.getId() + " ya existe.");
		}
	}
	

	/**
	 * A�ade un ArrayList de clientes al directorio.
	 * @param clientes ArrayList de clientes a a�adir al directorio.
	 * @return boolean el resultado de la inserci�n
	 * @throws Exception Si existe un error en la inserci�n se lanza esta excepci�n
	 */
	public boolean addAll(ArrayList<Cliente> clientes) throws Exception {
		for (Cliente c : clientes) {
			try { add(c); }
			catch (Exception ex) {
				throw new Exception(ex.getMessage());
			}
		}
		return true;
	}
	
	/**
	 * Obtiene un cliente del directorio
	 * @param index �ndice del array a obtener
	 * @return el cliente solicitado
	 */
	public Cliente get(int index) {
		return this.clientes.get(index);
	}
	
	/**
	 * Obtiene un ArrayList con todos los clientes del directorio.
	 * @return ArrayList con todos los clientes del directorio.
	 */
	public ArrayList<Cliente> getAll() {
		return clientes;
	}
	
	/**
	 * Busca por el id del cliente si existe en el directorio.
	 * Al ser un identificador �nico basta con ser encontrado una vez
	 * para no tener que seguir comprobando.
	 * 
	 * @param id identificador a buscar.
	 * @return cliente en caso de ser encontrado.
	 */
	public Cliente findById(String id) {
		for (Cliente item: clientes) {
			if (item.getId().equals(id)) return item;
		}
		return null;
	}

	/**
	 * Realiza la importaci�n de clientes al directorio.
	 * 
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 * @return boolean verdadero si la importaci�n tiene �xito.
	 * @throws Exception lanza esta excepci�n si se produce un problema en la importaci�n.
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
	 * Exporta los clientes del directorio a un archivo.
	 * @return el resultado de la exportaci�n.
	 */
	public boolean exportar() {
		return super.exportar(clientes, archivo);
	}
	
	/**
	 * Elimina un cliente del directorio.
	 * 
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 * @param cliente El cliente a eliminar.
	 * @return boolean El resultado de la eliminaci�n.
	 */
	public boolean remove(Cliente cliente) {
		boolean result = this.clientes.remove(cliente);
		setChanged();
		return result;
	}
	
	/**
	 * Elimina un cliente del directorio.
	 * 
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 * @param index �ndice del array del directorio donde se encuentra el cliente a eliminar.
	 * @return el resultado de la eliminaci�n.
	 */
	public Cliente remove(int index) {
		Cliente item = this.clientes.remove(index);
		setChanged();
		return item;
	}
	
	
	/**
	 * Devuelve el n�mero de clientes en el directorio.
	 * @return  el n�mero de clientes en el directorio.
	 */
	public int size() {
		return this.clientes.size();
	}
	
	/**
	 * Representaci�n en un String del directorio.
	 * Recorre el directorio delegando la tarea de representar
	 * cada cliente a la clase Cliente.
	 * 
	 * @return string representaci�n del directorio
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();
		
		for (Cliente item: clientes) bfr.append(bfr + " " + item.toString());
		
		return bfr.toString();
	}
}