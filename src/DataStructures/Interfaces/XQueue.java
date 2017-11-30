package DataStructures.Interfaces;

public interface XQueue<E> {
	
	void enqueue(E e); 
	E dequeue() throws Exception; 
	
	E peek() throws Exception; 

}
