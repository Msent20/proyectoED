package TDAArbol;

/**
*  Retorna el caso de un árbol vacío
*  @author Giovanni Lorenzo
*/

public class EmptyTreeException extends Exception {

	/**
	 * Crea una excepcion de árbol vacío
	 * @param msg mensaje de la excepción
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}

}
