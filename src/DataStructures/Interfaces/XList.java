package DataStructures.Interfaces;

/**
 * @author Jonathan , Adahid
 * @param <E>
 */
public interface XList<E> extends Iterable<E>{
	
    /**
     * Adds a new element to the end of the list.
     * @param e The new element. 
     */
    public void add(E e); 
    
    /**
     * Adds a new element to the list.
     * @param i index for the new element
     * @param e the new element 
     * @throws IndexOutOfBoundsException if i is 0 or bigger than size.
     */
    public void add(int i, E e) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the position. 
     * @param i the index to be deleted.
     * @return the element that was removed.
     * @throws IndexOutOfBoundsException if i smaller than 0 or bigger than size-1
     */
    public E remove(int i) throws IndexOutOfBoundsException;

    /**
     * Replaces the element with the one being provided.
     * @param i the index in the list to be replaced.
     * @param e the new element.
     * @return the value replaced.
     * @throws IndexOutOfBoundsException if i is smaller than 0 or bigger than size-1
     */
    public E set(int i, E e) throws IndexOutOfBoundsException;
    
    /**
     * Returns the element in the list.
     * @param i the index of the position of the element.
     * @return the element.
     * @throws IndexOutOfBoundsException if i smaller than 0 or bigger than size-1
     */
    public E get(int i) throws IndexOutOfBoundsException;
    
    /**
     * Returns the amount of elements in list.
     * @return number of elements in the list.
     */
    public int size();

    /**
     * Determines if the list is empty.
     * @return true if list empty, false if not.
     */
    public boolean isEmpty();

	

}
