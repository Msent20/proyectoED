package TDAArbol;
import TDALista.*;

/**
 * Implementacion de nodo con rotulo para arbol
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo generico del rotulo
 */

public class TNodo<E> implements Position<E>{
	private PositionList<TNodo<E>> hijos;
	private TNodo<E> padre;
	private E element;
	
	/**
	 * Crea un nodo para árbol general con referencia a sus hijos, padre y rotulo nulos
	 */
	
	public TNodo(){
		hijos= new DoubleLinkedList<TNodo<E>>();
		padre=null;
		element=null;
	}
	
	/**
	 * Crea un nodo para árbol general con referencia a sus hijos, con padre nulo y rotulo no nulo
	 * @param elemento rotulo a establecer
	 */
	
	public TNodo(E elemento) {
		hijos= new DoubleLinkedList<TNodo<E>>();
		element=elemento;
		padre=null;
	}
	
	/**
	 * Crea un nodo para árbol general con referencia a sus hijos, con padre y rotulo no nulos
	 * @param elemento rotulo a establecer
	 * @param p padre a establecer
	 */
	public TNodo(E elemento, TNodo<E> p) {
		hijos= new DoubleLinkedList<TNodo<E>>();
		element=elemento;
		padre=p;
	}
	public E element() {
		return element;
	}
	
	/**
	 * Establece un nuevo padre al nodo
	 * @param p padre a establecer
	 */
	public void setPadre(TNodo<E> p) {
		padre=p;
	}
	
	/**
	 * Retorna el padre del nodo
	 * @return padre del nodo
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	
	/**
	 * Establece rotulo nuevo al nodo
	 * @param elemento rotulo a establecer
	 */
	public void setElemento(E elemento) {
		element=elemento;
	}
	
	/**
	 * Retorna una lista iterable de los hijos del nodo
	 * @return lista de hijos
	 */
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
}
