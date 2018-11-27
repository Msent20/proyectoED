package TDAArbol;

/**
 * Retorna el caso de sobrepasar los limites de la estructura
 * @author Giovanni Lorenzo
 *
 */

public class BoundaryViolationException extends Exception {
	/**
	 * Crea una excepcion de sobrepasar lo limites de la estructura
	 * @param msg mensaje de la excepción
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}


}
