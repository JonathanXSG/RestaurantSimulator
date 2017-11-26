package DataStructures.Interfaces;

public interface XQueue<E> {

	int size(); 
	boolean isEmpty(); 
	
	void enqueue(E e); 
	E dequeue() throws Exception; 
	
	E peek() throws Exception; 

}
