package DataStructures;

import DataStructures.Interfaces.XNode;

public class XSNode <E> implements XNode<E>{
	
	private E element;
	private XSNode<E> next;
	
	public XSNode() {
		this.element = null; 
		this.next = null;
	}

	public XSNode(E e) { 
		this.element = e; 
		this.next = null;
	}
	
	public XSNode(E e,XSNode<E> n) { 
		this.element = e; 
		this.next = n; 
	}
	
	public XSNode<E> getNext() {
		return next;
	}
	public void setNext(XSNode<E> next) {
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
