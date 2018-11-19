package TDALista;

/**
 * Implementacion de excepcion con posicion invalida
 * @author Giovanni Lorenzo
 *
 */

public class InvalidPositionException extends Exception{
	
	/**
	 * Constructor de la excepcion
	 * @param msg Mensaje a exhibir
	 */
	
	public InvalidPositionException(String msg) {
		super(msg);
	}



}
