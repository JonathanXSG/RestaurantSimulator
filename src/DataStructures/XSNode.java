package DataStructures;

public class XSNode <E> implements XNode<E>{
	
	private E element;
	private XNode<E> next;
	
	public XSNode() {
		this.element = null; 
		this.next = null;
	}

	public XSNode(E e) { 
		this.element = e; 
		this.next = null;
	}
	
	public XSNode(E e,XNode<E> n) { 
		this.element = e; 
		this.next = n; 
	}
	
	public XNode<E> getNext() {
		return next;
	}
	public void setNext(XNode<E> next) {
		this.next = next;
	}
	public E getElement() {
		return this.element; 
	}
	public void setElement(E data) {
		element = data; 
	} 
	
	@Override
	public String toString() {
		return "XNode [element=" + element +"]";
	}
	
}
