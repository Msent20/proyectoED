package TDAColaCP;

/**
 * Interface de Entry para Entrada
 * @author Bruno Velazquez
 *
 * @param <K> tipo de dato de clave
 * @param <V> tipo de dato de valor
 */


public interface Entry <K,V>{
	
	/**Retorna el atributo key.
	 * @return Atributo key de la entrada.
	 */
	public K getKey();
	
	/**Retorna el atributo value
	 * @return Atributo value de la entrada.
	 */
	public V getValue();
	
	/**Compara la llave y el valor de la entrada que recibe el mensaje con la entrada que pasa como parametro.
	 * @param o entrada a comparar con la entrada que recibe el mensaje.
	 * @return Verdadero si las entradas son iguales, falso en caso contrario.
	 */
	public boolean equals (Object o);
}