package TDACola;

/**
 * Implementacion de excepción con cola vacia
 * @author Bruno Velazquez
 *
 */

public class EmptyQueueException extends Exception {
	
	/**
	 * Constructor de la excepción
	 * @param s Mensaje a exhibir
	 */
	public EmptyQueueException(String s) { 
		super(s);
	
	}

}
