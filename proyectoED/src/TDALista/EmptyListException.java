package TDALista;

/**
 * Implmentacion de exception con lista vacia
 * @author Giovanni Lorenzo
 *
 */

	public class EmptyListException extends Exception{
		
		/**
		 * Constructor de la excepcion
		 * @param msg Mensaje a exhibir
		 */
		
		public EmptyListException(String msg) {
		super(msg);
		}
	}
