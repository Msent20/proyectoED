package TDAColaCP;

/**
 *	Implementacion de excepcion con clave invalida
 *
 * 	@author Bruno Velazquez
 *
 */


public class InvalidKeyException extends RuntimeException {
	
	/** 
	 * Crea una excepcion de clave invalida
	 * @param err Mensaje a exhibir
	 */
	public InvalidKeyException (String err){
		super(err);
	}
}
