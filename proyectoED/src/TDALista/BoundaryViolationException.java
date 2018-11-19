package TDALista;

/**
 * Implementacion de la excepcion de sobrepasar los limites de la estructura
 * @author Giovanni Lorenzo
 *
 */

public class BoundaryViolationException extends Exception {
	
	/**
	 * Constructor de la excepcion
	 * @param msg Mensaje a exhibir
	 */
	 public BoundaryViolationException(String msg) {
		 super(msg);
	 }
}
