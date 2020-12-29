package poouned.modelos;

import java.util.Observable;

/**
 * Esta clase abstracta se utiliza como clase padre de las clases que representan
 * el modelo de negocio de la aplicación.
 * A su vez, se hereda de Observable y se implementa el método setChanged
 * para informar a los observadores que el modelo ha cambiado sus datos.
 * 
 * @author Francisco Javier Crespo Jiménez
 * @category modelos
 */
public abstract class AbstractModel extends Observable {
	
	/**
	 * Método que reimplementa el método setChanged de manera que
	 * cuando se produce un cambio en los modelos, se notifique
	 * a los observadores que tenga asociados.
	 */
	@Override
	protected synchronized void setChanged() {
		super.setChanged();
        notifyObservers();
    }

}