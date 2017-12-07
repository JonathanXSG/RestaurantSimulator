package DataStructures.Interfaces;

public interface XLinkedList <E> extends XList<E>{
	
	/**
	 * Add Element in the first Position of the list.
	 * @param e The new Element.
	 */
	void addFirst(E e); 
	
	/**
	 * Add Element in the last Position of the list.
	 * @param e The new Element.
	 */
	void addLast(E e); 
	
	/**
	 * Remove first element in the List.
	 * @return Element removed at first Position.
	 * @throws IndexOutOfBoundsException if first is null.
	 */
	E removeFirst() throws IndexOutOfBoundsException; 
	
	/**Remove first element in the List.
	 * @return Element removed at last Position.
	 * @throws IndexOutOfBoundsException if last is null.
	 */
	E removeLast() throws IndexOutOfBoundsException; 	
	
	
}
