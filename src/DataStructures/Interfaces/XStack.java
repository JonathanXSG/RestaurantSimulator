package DataStructures.Interfaces;


public interface XStack<E> {

	void push(E e); 
	E pop() throws Exception; 
	
	E peek() throws Exception; 
}
