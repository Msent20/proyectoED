package TDAArbol;

/**
 *  Retorna el caso de una posición inválida
 *  @author Giovanni Lorenzo
 */

public class InvalidPositionException extends Exception {
	/**
	 * Constructor de la excepción
	 * @param msg mensaje de la excepción
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}

}
