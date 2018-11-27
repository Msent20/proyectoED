package TDALista;

import java.util.Iterator;
import TDAArbol.Position;
import TDAArbol.InvalidPositionException;
import TDAArbol.BoundaryViolationException;

/**
 * Implementacion de una Lista doblemente enlazada con centinelas
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo generico
 */

public class DoubleLinkedList<E> implements PositionList<E>{
	protected int size;
	protected NodoE<E> head, tail;
	
	/**
	 *	Crea una lista doblemente enlazada con centinelas
	 */
	
	public DoubleLinkedList() {
		size=0;
		head= new NodoE<E>();
		tail= new NodoE<E>();
		head.setSiguiente(tail);
		tail.setAnterior(head);
	}
	
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}

	public Position<E> first() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("Lista vacia.");
		return head.getSiguiente();
	}
	public Position<E> last() throws EmptyListException {
		if(size==0)
			throw new EmptyListException("Lista vacia.");
		return tail.getAnterior();
	}
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoE<E> n= checkPosition(p);
		if( n.getSiguiente()==tail)
			throw new BoundaryViolationException("");
		return n.getSiguiente();
	}
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		NodoE<E> n= checkPosition(p);
		if(n.getAnterior()==head)
			throw new BoundaryViolationException("");
		return n.getAnterior();
	}
	public void addFirst(E element) {
		NodoE<E> nuevo= new NodoE<E>();
		nuevo.setElemento(element);
		nuevo.setAnterior(head);
		nuevo.setSiguiente(head.getSiguiente());
		head.getSiguiente().setAnterior(nuevo);
		head.setSiguiente(nuevo);
		size++;
	}
	public void addLast(E element) {
		NodoE<E> nuevo= new NodoE<E>();
		nuevo.setElemento(element);
		nuevo.setAnterior(tail.getAnterior());
		nuevo.setSiguiente(tail);
		tail.getAnterior().setSiguiente(nuevo);
		tail.setAnterior(nuevo);
		size++;
	}
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		NodoE<E> n= checkPosition(p);
		NodoE<E> nuevo= new NodoE<E>();
		nuevo.setElemento(element);
		nuevo.setSiguiente(n.getSiguiente());
		nuevo.setAnterior(n);
		n.getSiguiente().setAnterior(nuevo);
		n.setSiguiente(nuevo);
		size++;
	}

	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		NodoE<E> n= checkPosition(p);
		NodoE<E> nuevo= new NodoE<E>();
		nuevo.setElemento(element);
		nuevo.setAnterior(n.getAnterior());
		nuevo.setSiguiente(n);
		n.getAnterior().setSiguiente(nuevo);
		n.setAnterior(nuevo);
		size++;
	}
	public E remove(Position<E> p) throws InvalidPositionException {
		if(size==0)
			throw new InvalidPositionException("ListaVacia");
		NodoE<E> n= checkPosition(p);
		E toret= n.element();
		
		n.getSiguiente().setAnterior(n.getAnterior());
		n.getAnterior().setSiguiente(n.getSiguiente());
		n=null;
		size--;
		return toret;
	}
	public E set(Position<E> p, E element) throws InvalidPositionException {
		NodoE<E> n=checkPosition(p);
		if(size==0)
			throw new InvalidPositionException("");
		E toret= n.element();
		n.setElemento(element);
		return toret;
	}
	public Iterator<E> iterator(){
		return new ElementIterator<E>( this );
	}
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new DoubleLinkedList<Position<E>>();
		
			if(size!=0){
				NodoE<E> pos=head.getSiguiente();
				while(pos != null){
					lista.addLast(pos);
					pos= (pos != tail.getAnterior() ? pos.getSiguiente() : null);
				}
			}
			
		return lista;
	}
	
	private NodoE<E> checkPosition(Position<E>p) throws InvalidPositionException{
		try {
			if(p==null)
				throw new InvalidPositionException("pos nula");
			return(NodoE<E>)p;
		}catch(ClassCastException e) {throw new InvalidPositionException("");}
	}
	
}
