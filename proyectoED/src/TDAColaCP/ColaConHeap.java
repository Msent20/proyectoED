package TDAColaCP;


	/**
	 * Implementacion de cola con prioridad usando Heap
	 * @author Bruno Velazquez
	 *
	 * @param <K> clave de tipo generico
	 * @param <V> valor de tipo generico
	 */

	public class ColaConHeap<K,V> implements PriorityQueue<K,V>{
		protected Entrada<K,V> [] elems;
		protected Comparador<K> comp;
		protected int size;

		//Clase embebida
		private class Entrada<K,V> implements Entry<K,V>  {
			private K clave; 
			private V valor; 
			
			public Entrada(K clave, V valor) {
				this.clave = clave;
				this.valor = valor; 
			}
			public K getKey() { 
				return clave; 
			} 
			public V getValue() {
				return valor; 
			}
			public String toString() {
				return"("+clave+","+valor+")";
			}
			
			/**
			 * Establece la clave de la entrada
			 * @param k clave a establecer
			 */
			
			public void setKey(K k) {
				this.clave = k;
			}
			
			/**
			 * Establece el valor de la entrada
			 * @param v valor a establecer
			 */
			
			public void setValue(V v) {
				this.valor = v;
			}
		}
		
		
		/**
		 * Constructor de la colaCP
		 * @param comp comparador a utilizar
		 */
		
		@SuppressWarnings("unchecked")
		public ColaConHeap( Comparador<K> comp) {
			elems= (Entrada<K,V>[]) new Entrada[20];
			this.comp=comp;
			size=0;
		}
		
		
		public boolean isEmpty() {
			return size==0;
		}
		
		
		public int size() {
			return size;
		}
		
		
		public Entry<K, V> min() throws EmptyPriorityQueueException {
			if(size == 0)
				throw new EmptyPriorityQueueException("Cola vacia.");
			return elems[1];
		}
		
		
		public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
			if (key  == null)
				throw new InvalidKeyException("");
			
			if (size == elems.length - 1)
				agrandar();
			
			Entrada<K,V> entrada= new Entrada<K,V>(key,value);
			elems[++size]=entrada;
			int i=size;
			boolean seguir= true;
			
			while(i>1 && seguir) {
				Entrada<K,V> elemActual= elems[i];
				Entrada<K,V> elemPadre = elems[i/2];
				if( comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
					Entrada<K,V> aux= elems[i];
					elems[i]= elems[i/2];
					elems[i/2]=aux;
					i/=2;
				}else {
					seguir=false;     //Esta ordenada
				}	
			}
			return entrada;
		}
		
		
		private void agrandar() {
			@SuppressWarnings("unchecked")
			Entrada<K,V>[] elemsAux = (Entrada<K,V>[]) new Entrada[elems.length*2];
			for (int i = 0 ; i < elems.length ; i++) {
				elemsAux[i] = elems[i];
			}
			
			elems = elemsAux;
			
		}
		
		
		public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
			if (isEmpty())
				throw new EmptyPriorityQueueException("Cola vacia.");
			
			Entry<K,V> toRet = elems[1];
			
			if (size == 1) {
				elems[1] = null;
				size = 0;
			} 
			else {
				elems[1] = elems[size];
				elems[size] = null;
				size--;
				
				int i = 1;
				boolean seguir = true;
				
				while (seguir) {
					
					int hi = i*2;
					int hd = (i*2)+1;
					boolean tiene_Hijo_Izquierdo = hi <= size;
					boolean tiene_Hijo_Derecho = hd <= size;
					
					if (tiene_Hijo_Izquierdo) {
						
						int m = hi;
						
						if (tiene_Hijo_Derecho)
							
							if (comp.compare(elems[hd].getKey() , elems[m].getKey()) < 0)
								m = hd;
						
						if (comp.compare(elems[m].getKey(), elems[i].getKey()) < 0) {
							Entrada<K,V> aux = elems[i];
							elems[i] = elems[m];
							elems[m] = aux;
							i = m;
						} else 
							seguir = false;	
						
					}
					else
						seguir = false;
				}
			}
			return toRet;
		}	

	
}