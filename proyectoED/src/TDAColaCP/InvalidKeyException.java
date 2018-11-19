package TDAColaCP;

/**
 *	Implementacion de excepcion con clave invalida
 *
 * 	@author Bruno Velazquez
 *
 */


public class InvalidKeyException extends RuntimeException {
	
	/** 
	 * Constructor de la excepcion
	 * @param err Mensaje a exhibir
	 */
	public InvalidKeyException (String err){
		super(err);
	}
}
