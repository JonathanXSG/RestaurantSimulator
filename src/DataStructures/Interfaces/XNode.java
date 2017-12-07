package DataStructures.Interfaces;

public interface XNode<E> {
	
	/**
	 * Get Element wanted.
	 * @return Element;
	 */
	public E getElement();
	
	
	/**
	 * Changes existing Element with another.
	 * @param data
	 */
	public void setElement(E data);
}
