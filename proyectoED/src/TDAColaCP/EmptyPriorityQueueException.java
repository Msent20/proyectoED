package TDAColaCP;

/**
 * Implementacion de excepcion con colaCP vacia
 * @author Bruno Velazquez
 *
 */

@SuppressWarnings("serial")
public class EmptyPriorityQueueException extends Exception{
	
	/**
	 * Constructor de la excepcion
	 * @param m Mensaje a exhibir
	 */
	public EmptyPriorityQueueException(String m) {
		super(m);
	}

}
