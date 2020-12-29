package poouned.modelos;

import java.util.Observable;

/**
 * Esta clase abstracta se utiliza como clase padre de las clases que representan
 * el modelo de negocio de la aplicaci�n.
 * A su vez, se hereda de Observable y se implementa el m�todo setChanged
 * para informar a los observadores que el modelo ha cambiado sus datos.
 * 
 * @author Francisco Javier Crespo Jim�nez
 * @category modelos
 */
public abstract class AbstractModel extends Observable {
	
	/**
	 * M�todo que reimplementa el m�todo setChanged de manera que
	 * cuando se produce un cambio en los modelos, se notifique
	 * a los observadores que tenga asociados.
	 */
	@Override
	protected synchronized void setChanged() {
		super.setChanged();
        notifyObservers();
    }

}