package TDAPila;

/**
 * Implementacion de una pila con nodos simplemente enlazados
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo de la pila
 */


public class PilaEnlazada<E> implements Stack<E> {
	protected int size;
	protected Nodo<E> head;
	
	/**
	 * Crea una pila vacia
	 */
	public PilaEnlazada() {
		head= null;
		size=0;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public E top() throws EmptyStackException {
		if(size==0)
			throw new EmptyStackException("Pila vacia.");
		return head.element();
	}
	public void push(E element) {
		Nodo<E> nuevo=new Nodo<E>(element,head);
		head=nuevo;
		size++;
	}
	public E pop() throws EmptyStackException {
		if(size==0)
			throw new EmptyStackException("Pila vacia.");
		E toret= head.element();
		head=head.getSiguiente();
		size--;
		return toret;
		}
}
