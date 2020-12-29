package poouned.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Esta clase representa a los clientes de tipo particular.
 * Hereda de la clase abstracta cliente.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 * @see Cliente
 */
public class Particular extends Cliente implements Serializable {

	private String nif;
	private String nombre;
	private String apellidos;
	private static final long serialVersionUID = 4542820959061139504L;
	
	/**
	 * Constructor de la clase.
	 */
	public Particular() {
		super();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Particular(UUID codigo, Date fechaAlta, String domicilio, String nif, String nombre, String apellidos) {
		super(codigo, fechaAlta, domicilio);
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	/**
	 * Obtiene el id del particular
	 * @return el id que en caso del particular es el nif
	 */
	@Override
	public String getId() {
		return nif;
	}
	
	/**
	 * Obtiene la denominación del particular.
	 * @return la denominación que en el caso del particular
	 * es en nombre y apellidos.
	 */
	@Override
	public String getDenominacion() {
		return nombre + " " + apellidos;
	}

	/**
	 * Obtiene el nif del cliente particular
	 * @return el nif del cliente particular
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Ajusta el nif del cliente particular
	 * @param nif del cliente particular
	 */
	public void setNif(String nif) {
		if (super.validaNif(nif.toUpperCase().trim())) {
			this.nif = nif.toUpperCase();
			setChanged();
		}
	}

	/**
	 * Obtiene el nombre del particular
	 * @return el nombre del particular
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Ajusta el nombre del particular
	 * @param nombre el nombre del particular
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
		setChanged();
	}

	/**
	 * Obtiene los apellidos del particular
	 * @return los apellidos del particular
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Ajusta los apellidos del particular
	 * @param los apellidos del particular
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
		setChanged();
	}
	
	/**
	 * Ajusta el id del particular
	 * 
	 */
	@Override
	public void setId(String id) {
		this.nif = id;
		setChanged();
	}
	
	/**
	 * Ajusta la denominación del cliente particular
	 * @param denominacion la denominación del cliente particular
	 */
	@Override
	public void setDenominacion(String denominacion) {
		if (denominacion.startsWith(nombre)) {
			apellidos = (String) denominacion.subSequence(nombre.length() + 1, denominacion.length());
			
		} else {
			nombre = (String) denominacion.subSequence(0, (denominacion.length() - apellidos.length() + 1));
		}
		setChanged();
	}
	
	/**
	 * Representación en string de un cliente particular
	 */
	public String toString() {
		return nif + " - " + nombre + " "+ apellidos;
	}
	

	
	

}
