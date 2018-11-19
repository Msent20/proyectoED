package TDAArbol;

/**
*  Retorna el caso de un árbol vacío
*  @author Giovanni Lorenzo
*/

public class EmptyTreeException extends Exception {

	/**
	 * Constructor de la excepción
	 * @param msg mensaje de la excepción
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}

}
