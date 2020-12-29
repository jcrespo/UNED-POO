package poouned.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Esta clase representa a los clientes de tipo empresa.
 * Hereda de la clase abstracta cliente.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category modelos
 * @see Cliente
 */
public class Empresa extends Cliente implements Serializable {

	private String cif;
	private String razonSocial;
	private static final long serialVersionUID = 8665724966942981167L;

	/**
	 * Constructor de la clase.
	 */
	public Empresa() {
		super();
	}
	
	/**
	 * Constructor de la clase.
	 */
	public Empresa(UUID codigo, Date fechaAlta, String domicilio, String razonSocial, String cif) {
		super(codigo, fechaAlta, domicilio);
		this.cif = cif;
		this.razonSocial = razonSocial;
	}

	/**
	 * Obtiene el identificador de la empresa.
	 * @return el identificador de la empresa. 
	 */
	@Override
	public String getId() {
		return getCif();
	}
	
	/**
	 * Obtiene la denominaci�n de la empresa.
	 * @return la denominaci�n de la empresa.
	 */
	@Override
	public String getDenominacion() {
		return razonSocial;
	}
	
	/**
	 * Obtiene el cif de la empresa.
	 * @return el cif de la empresa.
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * Ajusta el cif de la empresa.
	 * @param cif el cif de la empresa.
	 */
	public void setCif(String cif) {
		if (super.validaNif(cif.toUpperCase().trim())) {
			this.cif = cif.toUpperCase();
		}
		setChanged();
	}

	/**
	 * Obtiene la raz�n social de la empresa.
	 * @return la raz�n social de la empresa.
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Ajusta la raz�n social de la empresa.
	 * @param razonSocial la raz�n social de la empresa.
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
		setChanged();
	}
	
	/**
	 * Ajusta el id de la empresa.
	 * @param id el id de la empresa.
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	@Override
	public void setId(String id) {
		this.cif = id;
		setChanged();
	}
	
	/**
	 * Ajusta la denominaci�n de la empresa.
	 * @param la denominaci�n de la empresa.
	 * Llama al m�todo setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	@Override
	public void setDenominacion(String denominacion) {
		this.razonSocial = denominacion;
		setChanged();
	}
	
	/**
	 * Representaci�n en texto de la empresa.
	 */
	public String toString() {
		return cif + " - " + razonSocial;
	}
	
}
