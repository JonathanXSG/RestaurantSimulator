package DataStructures.Interfaces;

public interface XLinkedList <E> extends XList<E>{

	int size();
	boolean isEmpty();
	
	void addFirst(E e); 
	void addLast(E e); 
	
	E removeFirst() throws IndexOutOfBoundsException; 
	E removeLast() throws IndexOutOfBoundsException; 	
	
	
}
