package TDACola;

/**
 * Implementacion de excepción con cola vacia
 * @author Bruno Velazquez
 *
 */

public class EmptyQueueException extends Exception {
	
	/**
	 * Crea la excepción de cola vacia
	 * @param s Mensaje a exhibir
	 */
	public EmptyQueueException(String s) { 
		super(s);
	
	}

}
