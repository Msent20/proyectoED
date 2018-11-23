package TDAArbol;

import java.util.Iterator;
import TDALista.*;
import TDAPila.*;

/**
 * Implementacion de arbol con nodos con rotulo generico
 * @author Giovanni Lorenzo
 *
 * @param <E> tipo generico
 */

public class Arbol<E> implements Tree<E> {
	protected TNodo<E> root;
	protected int size;
	
	/**
	 * Constructor de la estructura
	 */
	
	public Arbol() {
		root=null;
		size=0;
	}

	public Iterator<E> iterator() {
		PositionList<E> lista= new DoubleLinkedList<E>();
		if(size!=0)
			preorder(root,lista);
		return lista.iterator();
	}
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista= new DoubleLinkedList<Position<E>>();
		if(size!=0)
			preOrdenPos(root,lista);
		return lista;
	}
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		TNodo<E> n= checkPosition(v);
		E toret= n.element();
		n.setElemento(e);
		return toret;
	}

	public Position<E> root() throws EmptyTreeException {
		if(size==0)
			throw new EmptyTreeException("");
		return root;
	}
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> n=checkPosition(v);
		if(root==n)
			throw new BoundaryViolationException("");
		return n.getPadre();
	}
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> p =checkPosition(v);
		PositionList<Position<E>> L= new DoubleLinkedList<Position<E>>();
		for (TNodo<E> h : p.getHijos())
			L.addLast(h);
		return L;
	}
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n=checkPosition(v);
		return n.getHijos().size()!=0;
	}
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> n=checkPosition(v);
		return n.getHijos().size()==0;
	}
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> n=checkPosition(v);
		return n==root;
	}
	public void createRoot(E e) throws InvalidOperationException {
		if(root!=null)
			throw new InvalidOperationException("");
		root=new TNodo<E>(e);
		size++;
	}
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> padre=checkPosition(p);
		TNodo<E> hijo= new TNodo<E>(e,padre);
		padre.getHijos().addFirst(hijo);
		size++;
		return hijo;
	}
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> padre=checkPosition(p);
		TNodo<E> hijo=new TNodo<E>(e,padre);
		padre.getHijos().addLast(hijo);
		size++;
		return hijo;
	}
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> padre=checkPosition(p);
		TNodo<E> hermano=checkPosition(rb);
		if(hermano.getPadre()!=padre)
			throw new InvalidPositionException("");
		TNodo<E> nuevo= new TNodo<E>(e,padre);
		try {
			Position<TNodo<E>> pos= padre.getHijos().first();
			while(pos.element()!=hermano) 
				pos=padre.getHijos().next( pos);
			padre.getHijos().addBefore( pos, nuevo);
		}catch(TDALista.InvalidPositionException| EmptyListException | TDALista.BoundaryViolationException m) {}
		size++;
		return nuevo;
	}
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> padre=checkPosition(p);
		TNodo<E> hermano=checkPosition(lb);
		if(hermano.getPadre()!=padre)
			throw new InvalidPositionException("Padre diferente.");
		TNodo<E> nuevo= new TNodo<E>(e,padre);
		try {
			Position<TNodo<E>> pos= padre.getHijos().first();
			while(pos.element()!=hermano) 
				pos= padre.getHijos().next(pos);
			padre.getHijos().addAfter(pos, nuevo);
		}catch(TDALista.InvalidPositionException| EmptyListException | TDALista.BoundaryViolationException m) {}
		size++;
		return nuevo;
	}
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> n=checkPosition(p);
		if(n.getHijos().size()!=0)
			throw new InvalidPositionException("");
		try {
			if(n==root)
				root=null;
			else {
			Position<TNodo<E>> pos= n.getPadre().getHijos().first();
			while(pos.element()!=n)
				pos=n.getPadre().getHijos().next(pos);
			n.getPadre().getHijos().remove(pos);
			}
		}catch(EmptyListException| TDALista.BoundaryViolationException | TDALista.InvalidPositionException e) {}
		size--;
	}
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		
		try {
			
			if (this.isEmpty())
				throw new InvalidPositionException("Arbol vacio");			
			
			TNodo<E> nodAEliminar = checkPosition(p);
			
			if (!isInternal(p))
				throw new InvalidPositionException("El nodo P es externo");
			
			if (nodAEliminar == root) {
				
				if (nodAEliminar.getHijos().size() == 1) {
					root = nodAEliminar.getHijos().first().element();
					size--;
				} 
				else {
						throw new InvalidPositionException("El nodo raiz tiene mas de un hijo");
				}				
			 } 
			else {						
				
				TNodo<E> nuevoPadre =  nodAEliminar.getPadre();
				PositionList<TNodo<E>> hijosNAE = nodAEliminar.getHijos();
				
				PositionList<TNodo<E>> padreNAE = nuevoPadre.getHijos();
				Position<TNodo<E>> primero = padreNAE.first();
		
				while( primero.element() != nodAEliminar) {
					primero = padreNAE.next(primero);
				}
			
				if (!padreNAE.isEmpty()) {
					
					while (!hijosNAE.isEmpty()) {
						Position<TNodo<E>> hijAInsert = hijosNAE.first();
						padreNAE.addBefore(primero, hijAInsert.element());
						
						TNodo<E> insertado = padreNAE.prev(primero).element();
						insertado.setPadre(nuevoPadre);
						hijosNAE.remove(hijAInsert);
					}
				}
				padreNAE.remove(primero);
				size--;
			}
			
		} catch (TDALista.BoundaryViolationException e){
			e.printStackTrace();
		} catch (TDALista.EmptyListException e) {
			e.printStackTrace();
		} catch (TDALista.InvalidPositionException e) {
			e.printStackTrace();
		}
	}


public void removeNode(Position<E> p) throws InvalidPositionException {
	TNodo<E> n= checkPosition(p);
	try{
	if(n==root) {
		if(n.getHijos().isEmpty())
			root=null;
		if(n.getHijos().size()==1) {
			root=root.getHijos().first().element();
			root.setPadre(null);
		}
		if(n.getHijos().size()>1){
			throw new InvalidPositionException("La raiz tiene muchos hijos.");
		}
	}
	else{
		TNodo<E> nuevoPadre =  n.getPadre();
		PositionList<TNodo<E>> hijosNAE = n.getHijos();
		
		PositionList<TNodo<E>> padreNAE = nuevoPadre.getHijos();
		Position<TNodo<E>> primero = padreNAE.first();

		while( primero.element() != n) {
			primero = padreNAE.next(primero);
		}
	
		if (!padreNAE.isEmpty()) {
			
			while (!hijosNAE.isEmpty()) {
				Position<TNodo<E>> hijAInsert = hijosNAE.first();
				padreNAE.addBefore(primero, hijAInsert.element());
				
				TNodo<E> insertado = padreNAE.prev(primero).element();
				insertado.setPadre(nuevoPadre);
				hijosNAE.remove(hijAInsert);
			
			}
		padreNAE.remove(primero);
		}
	}if(n.getHijos().size()!=0){
		PositionList<TNodo<E>> hijosPadre = n.getPadre().getHijos();
		Position<TNodo<E>> pos=hijosPadre.first();
		while(pos.element()!=n)
			pos=hijosPadre.next(pos);
		hijosPadre.remove(pos);
		pos.element().setPadre(null);
	}
	
	}catch(TDALista.InvalidPositionException|TDALista.BoundaryViolationException|EmptyListException m) { m.printStackTrace();}
	size--;
}
	private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException{
		if(size==0)
			throw new InvalidPositionException("Arbol vacio");
		if(v==null)
			throw new InvalidPositionException("");
		TNodo<E> toret=null;
		try {
			toret= (TNodo<E>) v;
		}catch(ClassCastException m) {}
		return toret;
	}
	private void preorder(TNodo<E> v, PositionList<E> lista) {
		lista.addLast(v.element());
		for(TNodo<E> e: v.getHijos())
			preorder(e,lista);
	}
	private void preOrdenPos(TNodo<E> v, PositionList<Position<E>> lista) {
		lista.addLast(v);
		for(TNodo<E> e: v.getHijos()){
			preOrdenPos(e,lista);}
	}

}
