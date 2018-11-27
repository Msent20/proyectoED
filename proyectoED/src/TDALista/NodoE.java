package TDALista;
import TDAArbol.Position;

/**
 * Implementacion del nodo con referencia al nodo siguiente y anterior
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo generico
 */

public class NodoE<E> implements Position<E>{
	private E elemento;
	private NodoE<E> siguiente,anterior;
	
	/**
	 * Crea un nodo doblemente enlazado con rotulo nulo
	 */
	
	public NodoE(){
		elemento=null;
		siguiente=null;
		anterior=null;
	}
	
	/**
	 * Retorna el nodo siguiente
	 * @return nodo siguiente
	 */
	public NodoE<E> getSiguiente(){
		return siguiente;
	}
	
	/**
	 * Establece el nodo siguiente
	 * @param s nuevo nodo siguiente
	 */
	public void setSiguiente(NodoE<E> s) {
		siguiente=s;
	}
	
	/**
	 * Retorna el nodo anterior
	 * @return nodo anterior
	 */
	public NodoE<E> getAnterior(){
		return anterior;
	}
	
	/**
	 * Establece el nodo anterior
	 * @param a nuevo nodo anterior
	 */
	public void setAnterior(NodoE<E> a){
		anterior=a;
	}
	
	
	public E element() {
		return elemento;
	}
	
	/**
	 * Establece el rotulo del nodo
	 * @param e rotulo a establecer
	 */
	public void setElemento(E e) {
		elemento=e;
	}

}
