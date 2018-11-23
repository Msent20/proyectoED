package logica;

import java.util.Iterator;
import TDAArbol.*;
import TDACola.*;
import TDAColaCP.*;
import TDAPila.*;

/**
 * Implementacion de la logica del programa 
 * @author Giovanni Lorenzo & Bruno Velazquez
 *
 */

public class logica {
	private Arbol<String> a;
	
	/**
	 * Constructor de la logica
	 */
	
	public logica() {
		a = new Arbol<String>();
	}
	/**
	 * Crea un arbol con un rotulo raiz especificado
	 * @param rot rotulo raiz
	 */
	public void cargar_Arbol(String rot){
		try{
			a.createRoot(rot);
		}catch(InvalidOperationException m) {}
	}
	
	/**
	 * Agrega un nodo con rotulo r como ultimo hijo de un nodo con rotulo p
	 * @param p rotulo de nodo padre
	 * @param r rotulo de nuevo nodo
	 * @return true si fue agregado con exito, false en caso contrario
	 */
	
	public boolean agregar_Nodo(String p, String r) {
		boolean notexiste = true;
		try {
			Iterator<String> it= a.iterator();
			while (it.hasNext() && notexiste){
				String s = it.next();
				if (s.equals(r))
					notexiste = false;
			}
			if (notexiste){
				boolean padre = false;
				Iterator<Position<String>> ite = a.positions().iterator();
				Position<String> pos = null;
				while (ite.hasNext() && !padre) {
					pos = ite.next();
					if (pos.element().equals(p)) {
						padre=true;
					}
				}
				if(padre)
					a.addLastChild(pos, r);
				else
					notexiste=padre;
					
			}
		}catch( InvalidPositionException e) {}
		return notexiste;
	}
	
	
	/**
	 * Muestra el arbol en preorden
	 * @return cadena de rotulos en preorden
	 */
	public String mostrar_Preorden() {
		String lista = "[ ";
		for (Position<String> p : a.positions() )
			lista += p.element() + ", ";
		lista += " ]";
		return lista;
	}
	
	
	/**
	 * Muestra el arbol en postorden
	 * @return cadena de rotulos en postorden
	 */
	public String mostrar_PostOrden() {
		String listaRet = "[ ";
			try {
				listaRet=postOrden( a.root() , listaRet);
				
			} catch (EmptyTreeException e) {}
		listaRet += " ] ";

		return listaRet;
	}

	private String postOrden(Position<String> posicion, String listaRet) {
		try {
			for (Position<String> pos : a.children(posicion)) {
				listaRet=postOrden( pos , listaRet);
			}
			
		} catch (InvalidPositionException e) {}
		listaRet += posicion.element() + " , ";
		return listaRet;
	}
	
	/**
	 * Lista los archivos del arbol
	 * @return cadena de archivos
	 */
	
	public String listar_Archivos() {
		String lista = " ";
		if (a.size() > 1) {
			for (Position<String> p : a.positions() )
				try {
					if (a.isExternal(p))
						lista += p.element() + "\n";
				} catch (InvalidPositionException e) {}
		}
		lista += " ";
		return lista;
	}

	/**
	 * Lista las carpetas del arbol (incluyendo la raiz)
	 * @return cadena de carpetas
	 */
	public String listar_Carpetas(){
		String lista = " ";
		for (Position<String> p : a.positions()) {
			try {
				if (a.isInternal(p))
					lista += p.element() + "\n";
			} catch (InvalidPositionException e) {}
		}
		lista += " ";
		return lista;
	}
	
	/**
	 * Muestra el arbol por niveles
	 * @return cadena de rotulos del arbol separado por niveles
	 */
	public String mostrar_Niveles() {
		 
		String lista  = "";
		ArrayQueue<Position<String>> cola = new ArrayQueue<Position<String>>();
		if (!a.isEmpty()){
			try {
				cola.enqueue(a.root());
			
			cola.enqueue(null);
			while (!cola.isEmpty()) {
				Position<String> p = cola.dequeue();
				if (p != null) {
					lista += p.element() + " ";
					for (Position<String> pos : a.children(p))
						cola.enqueue(pos);
				}
				else {
					lista += "\n";
					if (!cola.isEmpty())
						cola.enqueue(null);
				}
			}
		} catch (EmptyTreeException | EmptyQueueException | InvalidPositionException e) {}
	}
		return lista;
	}
	
	/**
	 * Muestra la ruta de un archivo con rotulo rot desde el nodo raiz
	 * @param rot archivo con el cual buscar la ruta
	 * @return ruta del archivo
	 */
	public String mostrar_Ruta(String rot) {
		String toRet = "";
		try {
			
				PilaEnlazada<String> pila = new PilaEnlazada<String>();
				boolean encontre = false;
				
					Iterator<Position<String>> ite = a.positions().iterator();
					Position<String> pos = null;
					while (ite.hasNext() && !encontre) {
						pos = ite.next();
						if (pos.element().equals(rot) && a.isExternal(pos))
							encontre = true;
					}
					if (encontre) {
						do {
							pila.push(pos.element());
							pos= a.parent(pos);
						}while(!a.isRoot(pos));
						pila.push(a.root().element());
						while(!pila.isEmpty()) {
							toRet += "/"+pila.pop();
						}
					}
				
			
		} catch ( EmptyStackException | InvalidPositionException | BoundaryViolationException | EmptyTreeException e ) {}
		return toRet;
	}
	
	
	/**
	 * Simula una impresion de archivos con prioridad segun su profundidad en el arbol
	 * @return cadena de rotulos de archivos
	 */
	public String simular_Impresion() {
		String toRet = "";
		ColaConHeap<Integer,String> colacc = new ColaConHeap<Integer,String>(new Comparador<Integer>());
		PilaEnlazada<String> pila = new PilaEnlazada<String>(); 
		
		try {
				if(a.size()>1) {
				for (Position<String> pos : a.positions()) {
					
					if (a.isExternal(pos)) {
						
						Position<String> aux = pos;
						int prof = 0;
						
						while (!a.isRoot(aux)) {				
							prof++;
							aux = a.parent(aux);
						}
						
						colacc.insert(prof, pos.element());
					}
				}
				}
				while (!colacc.isEmpty())
					pila.push(colacc.removeMin().getValue());
				
				while (!pila.isEmpty())
					toRet += pila.pop() + "\n";
				
		} catch ( EmptyPriorityQueueException | InvalidKeyException | InvalidPositionException | BoundaryViolationException | EmptyStackException e) {}
			
		return toRet;
		
	}
	
	/**
	 * clona el arbol original
	 * @return arbol clonado
	 */
	
	public Arbol<String> clonar_Arbol(){
		Arbol<String> toRet = new Arbol<String>();
		try {
			toRet.createRoot(a.root().element());
			clonarRec(a.root() , toRet , toRet.root());
		} catch (InvalidOperationException | EmptyTreeException e) {}
		return toRet;
	}

	private void clonarRec(Position<String> p, Arbol<String> aux , Position<String> auxNodo) {
		try {
			for (Position<String> pos : a.children(p)) {
				
				Position<String> nodonuevo = aux.addLastChild( auxNodo , pos.element());
				clonarRec(pos,aux,nodonuevo);
			}
			
		} catch (InvalidPositionException e) {}
	}

}
