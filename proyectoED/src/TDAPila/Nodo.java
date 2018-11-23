package TDAPila;

/**
 * Implementación de nodo para pila simplemente enlazada
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo rótulo genérico
 */

public class Nodo<E> {
	
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * Crea un para pila simplemente enlazada
	 * @param e Rotulo del nodo
	 * @param sig Nodo siguiente
	 */
	
	public Nodo(E e, Nodo<E> sig) {	
		elemento=e;
		siguiente=sig;
	}
	
	/**
	 * Devuelve rotulo del nodo
	 * @return rotulo genérico del nodo
	 */
	
	public E element(){	return elemento;}
	
	/**
	 * Devuelve nodo siguiente 
	 * @return nodo siguiente
	 */
	
	public Nodo<E> getSiguiente(){return siguiente;}
	
	/**
	 * Establece el nodo siguiente
	 * @param s Nodo siguiente a establecer
	 */
	
	public void setSiguiente(Nodo<E> s) {
		siguiente= s;
	}
	
	/**
	 * Establece rotulo del nodo
	 * @param e Rotulo a establecer
	 */
	
	public void setElemento(E e) {
		elemento=e;
	}
	

}
