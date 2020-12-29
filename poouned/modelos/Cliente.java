package poouned.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.aeat.valida.Validador;

/**
 * Esta clase abstracta representa a los clientes de la empresa.
 * En ella se mantienen los atributos comunes a los clientes
 * particulares y empresas.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 */
public abstract class Cliente extends AbstractModel implements Serializable {

	protected UUID codigo;
	protected String domicilio;
	protected Date fechaAlta;
	protected static final long serialVersionUID = 7060094205581317736L;
	
	/**
	 * Constructor de la clase.
	 */
	protected Cliente() {
		setCodigo();
		setFechaAlta();
	}
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param codigo código único del cliente
	 * @param fechaAlta fecha de alta en el sistema
	 * @param domicilio domicilio del cliente
	 */
	protected Cliente(UUID codigo, Date fechaAlta, String domicilio) {
		this.codigo 	= codigo;
		this.fechaAlta	= fechaAlta;
		this.domicilio 	= domicilio;
	}
	
	/**
	 * Método para obtener el id del cliente
	 * Se implementa en las clases concretas que hereden de esta
	 * en el caso de un particular retornará el nif
	 * en el caso de una empresa retornará el cif
	 * 
	 * @return el id del usuario
	 */
	public abstract String getId();
	
	/**
	 * Ajusta el id del cliente
	 * 
	 * @param id String con un identificador del cliente
	 */
	public abstract void setId(String id);
	
	/**
	 * Obtiene la denominación del cliente.
	 * Se implementa en las clases concretas que hereden de esta
	 * en el caso de un particular retornará el nombre y apellidos,
	 * en el caso de una empresa retornará la razón social
	 * 
	 * @return denominacion String con la denominación del cliente
	 */
	public abstract String getDenominacion();
	
	/**
	 * Ajusta la denominación del cliente
	 * 
	 * Al no poder distinguir en el caso de clientes particulares
	 * el nombre y los apellidos, esta función deja de utilizarse
	 * 
	 * @param denominacion
	 */
	public abstract void setDenominacion(String denominacion);
	
	/**
	 * Obtiene el código único del cliente.
	 * @return el código único del cliente.
	 */
	public UUID getCodigo() {
		return codigo;
	}

	/**
	 * Crea un código único aleatorio para el cliente.
	 * Llama al método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public void setCodigo() {
		this.codigo = UUID.randomUUID();
		setChanged();
	}
	
	/**
	 * Ajusta el código para el cliente.
	 * Llama al método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 * 
	 */
	public void setCodigo(UUID codigo) {
		this.codigo = codigo;
		setChanged();
	}
	
	/**
	 * Obtiene el domicilio del cliente.
	 * @return el domicilio del cliente.
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * Ajusta el domicilio del cliente.
	 * @param domicilio del cliente.
	 * 
	 * Llama al método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
		setChanged();
	}

	/**
	 * Obtiene la fecha de alta del cliente en el sistema.
	 * @return la fecha de alta del cliente en el sistema.
	 * 
	 * Llama al método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Ajusta la fecha de alta del cliente en el sistema.
	 * 
	 * Llama al método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public void setFechaAlta() {
		this.fechaAlta = new Date();
		setChanged();
	}
	
	/**
	 * Ajusta la fecha de alta del cliente
	 * @param fechaAlta fecha de alta del cliente
	 * 
	 * Ejecuta el método setChanged para que los observadores
	 * asociados, ejecuten las acciones necesarias cuando
	 * este dato haya cambiado.
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
		setChanged();
	}
	
	/**
	 * Método para imprimir el cliente.
	 * 
	 * @return cliente String que representa el cliente.
	 */
	public String toString() {
		StringBuffer bfr = new StringBuffer();

		bfr.append("Código: " + codigo + "\n");
		bfr.append("Fecha Alta: " + fechaAlta + "\n");
		bfr.append("Domicilio: " + domicilio + "\n");
		
		return bfr.toString();
	}
	
	/**
	 * Método para validar los documentos de indentidad tanto de
	 * particulares (nif) como de empresas (cif) proporcionado
	 * como librería externa por la Agencia Tributaria.
	 * @param str el documento a validar.
	 * @return si el documento ha sido validado.
	 * @throws IllegalArgumentException en caso de que el documento no tenga un formato adecuado.
	 */
	protected boolean validaNif(String str) throws IllegalArgumentException {
		Validador validador = new Validador();
		int i = validador.checkNif(str);
		if (i > 0) return true;
		throw new IllegalArgumentException("El documento no tiene un formato válido.");
	}
	
}
