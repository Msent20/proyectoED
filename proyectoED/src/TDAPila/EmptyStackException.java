package TDAPila;


/**
 * Implementacion de la excepcion de pila vacia
 * 
 * @author Giovanni Lorenzo
 *
 */

public class EmptyStackException extends Exception{
	
	/**
	 * Crea la excepcion de una pila vacia
	 * @param s Mensaje a exhibir 
	 */
	public EmptyStackException(String s) {
		super(s);
	}

}
