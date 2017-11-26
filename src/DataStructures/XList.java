package DataStructures;

public interface XList<E> {
	
    /**
     * Adds a new element to the list.
     * @param i index of the position where the new element is
     *       to be placed.
     * @param e the new element to be inserted
     * @throws IndexOutOfBoundsException if i<0 or i>size.
     */
    public void add(int i, E e)
    throws IndexOutOfBoundsException;
    
    /**
     * Adds a new element to the list. Once added, the new element will
     * be the last element of the list. Other elements in the list 
     * remain in the same positions as they are before the operation
     * is applied. 
     * @param e reference to the new element to add to the list. 
     */
    public void add(E e); 

    /**
     * Returns the element in  the list.
     * @param i the index of the position of the element to be returned
     * @return the reference to the element.
     * @throws IndexOutOfBoundsException if i<0 or i>size-1
     */
    public E get(int i) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the position from the list. 
     * @param i the index  in the list to be  deleted.
     * @return the reference to the element that was removed.
     * @throws IndexOutOfBoundsException if i<0 or i>size-1
     */
    public E remove(int i) throws IndexOutOfBoundsException;

    /**
     * Replaces the element with the one being provided.
     * @param i the index in the list to be replaced.
     * @param e the new element.
     * @return reference to the value replaced.
     * @throws IndexOutOfBoundsException if i<0 or i>size-1
     */
    public E set(int i, E e) throws IndexOutOfBoundsException;
    
    /**
     * Returns the number of elements in the list.
     * @return the number of elements currently in the list.
     */
    public int size();

    /**
     * Determines of the list is empty.
     * @return true if list empty, false if not.
     */
    public boolean isEmpty();

}
