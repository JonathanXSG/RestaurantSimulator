package DataStructures.Interfaces;


public interface XStack<E> {
	int size(); 
	boolean isEmpty(); 
	
	void push(E e); 
	E pop() throws Exception; 
	
	E peek() throws Exception; 
}
