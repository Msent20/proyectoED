package TDAPila;


/**
 * Implementacion de la excepcion de pila vaca
 * 
 * @author Giovanni Lorenzo
 *
 */

public class EmptyStackException extends Exception{
	
	/**
	 * Constructor de la excepcion
	 * @param s Mensaje a exhibir 
	 */
	public EmptyStackException(String s) {
		super(s);
	}

}
