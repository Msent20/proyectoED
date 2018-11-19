package TDACola;

/**
 * Implementacin de cola con arreglo circular
 * @author Bruno Velazquez
 *
 * @param <E> tipo de la cola
 */


public class ArrayQueue<E> implements Queue<E>{

	private int f , r , N;
	private E[] array;
 	
	/**
	 * Constructor de la cola
	 */
	
	public ArrayQueue() {
		f = r = 0;
		N = 200;
		array = (E[]) new Object[N];
	}
	
	
	public int size() {
		
		return  ((N-f+r)% N);
	}

	
	public boolean isEmpty() {
		
		return (f == r);
	}

	
	public E front() throws EmptyQueueException {
		
		if (isEmpty())
			throw new EmptyQueueException("");
		
		return array[f];
	}

	
	public void enqueue(E element) {
		
		if (size() == N -1 )
			agrandar();
		
		array[r] = element;
		r = (r+1)%N;
	}

	private void agrandar() {
		E[] arrayAux = (E[]) new Object[N*2];
		int i = f;
		while (i<N) {
			arrayAux[i-f] = array[i];
			i++;
		}
		
		for (int j = 0 ; j < f ; j++)
			arrayAux[(i-f)+j] = array[j];
		
		r = N - 1;
		f = 0;
		N = arrayAux.length;
		array = arrayAux;
	}

	
	public E dequeue() throws EmptyQueueException {
		
		if (isEmpty()) 

			throw new EmptyQueueException("");
		
		E toRet = array[f];
		array[f] = null;
		f = (f+1)%array.length;
		return toRet;
	}

}
