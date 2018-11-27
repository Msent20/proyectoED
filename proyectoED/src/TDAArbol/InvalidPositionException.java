package TDAArbol;

/**
 *  Retorna el caso de una posición inválida
 *  @author Giovanni Lorenzo
 */

public class InvalidPositionException extends Exception {
	/**
	 * Crea una excepcion de posicion invalida
	 * @param msg mensaje de la excepción
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}

}
