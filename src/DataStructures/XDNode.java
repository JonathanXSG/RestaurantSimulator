package DataStructures;

import DataStructures.Interfaces.XNode;

public class XDNode <E> implements XNode<E> {
	
	private E element;
	private XDNode<E> prev;
	private XDNode<E> next;
	
	public XDNode() {
		this.element = null; 
		this.next = null;
		this.prev = null;
	}

	public XDNode(E e) { 
		this.element = e; 
		this.next = null;
		this.prev = null;
	}
	
	public XDNode(E e, XDNode<E> p, XDNode<E> n) { 
		this.element = e; 
		this.prev = p; 
		this.next = n; 
	}
	
	public XDNode<E> getPrev() {
		return prev;
	}
	public void setPrev(XDNode<E> prev) {
		this.prev = prev;
	}
	public XDNode<E> getNext() {
		return next;
	}
	public void setNext(XDNode<E> next) {
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

