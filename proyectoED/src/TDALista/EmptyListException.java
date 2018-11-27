package TDALista;

/**
 * Implmentacion de exception con lista vacia
 * @author Giovanni Lorenzo
 *
 */

	public class EmptyListException extends Exception{
		
		/**
		 * Crea una excepcion de lista vacia
		 * @param msg Mensaje a exhibir
		 */
		
		public EmptyListException(String msg) {
		super(msg);
		}
	}
