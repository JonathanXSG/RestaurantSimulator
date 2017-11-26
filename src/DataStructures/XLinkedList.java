package DataStructures;

public interface XLinkedList <E> {

	int size();
	boolean isEmpty();
	
	void addFirst(E e); 
	void addLast(E e); 
	void addAtPosition(int i, E e)throws Exception;
	
	E removeFirst() throws Exception; 
	E removeLast() throws Exception; 
	
	E replaceAtIndex(int i, E e)throws Exception; 
	
	E get(int i)throws Exception;
	XNode<E> getNode(int i)throws Exception;
	
	
	
}
