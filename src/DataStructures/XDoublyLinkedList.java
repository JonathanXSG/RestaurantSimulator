package DataStructures;

import java.util.Iterator;

import DataStructures.Interfaces.XLinkedList;
import DataStructures.Interfaces.XNode;

public class XDoublyLinkedList <E> implements XLinkedList<E>{

	private XDNode<E> head, tail; 
	private int length; 
	
	public XDoublyLinkedList(){
		this.length = 0;
		this.head = new XDNode<E>();
		this.tail = new XDNode<E>();
		this.head.setNext(tail);
		this.tail.setPrev(head);
	}

	@Override
	public void addFirst(E e) {
		XDNode<E> tempNode = new XDNode<E>(e, null, this.head);
		this.head.setPrev(tempNode);
		length++;
	}

	@Override
	public void addLast(E e) {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XDNode<E> tempNode = new XDNode<E>(e, this.tail,null);
		this.tail.setNext(tempNode);
		length++;
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XDNode<E> oldNode= getNode(i);
			XDNode<E> tempNode = new XDNode<E>(e, oldNode.getPrev(),oldNode);
			oldNode.getPrev().setNext(tempNode);
			oldNode.setPrev(tempNode);
			length++;
		}
	}

	@Override
	public void add(E e) {
		addFirst(e);
	}


	@Override
	public E removeFirst() throws IndexOutOfBoundsException{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.head.getElement();
		this.head = this.head.getNext();
		length--;
		return oldElement;
	}

	@Override
	public E removeLast() throws IndexOutOfBoundsException{
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.tail.getElement();
		this.tail = this.tail.getPrev();
		length--;
		return oldElement;
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		if(i==0) return removeFirst();
		else if(i==length-1) return removeLast();
		else{
			XDNode<E> tempNode= getNode(i);
			tempNode.getPrev().setNext(tempNode.getNext());
			tempNode.getNext().setPrev(tempNode.getPrev());
			length--;
			return tempNode.getElement();
		}
	}
	
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		XDNode<E> tempNode = getNode(i);
		E oldElement = tempNode.getElement();
		tempNode.setElement(e);
		return oldElement;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		return getNode(i).getElement();
	}

	private XDNode<E> getNode(int i) throws IndexOutOfBoundsException {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XDNode<E> tempNode= this.head;
		if(i > this.length || i<0) throw new IndexOutOfBoundsException("Index is out of Bounds");
		else if(i==0) return head;
		else if(i==length-1) return tail;
		else{
			for(int j=0 ; j<=i ; j++){
				tempNode = tempNode.getNext();
			}
		}
		return tempNode;
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		return this.length==0;
	}

	@Override
	public int getIndexOf(XNode<E> node) throws Exception {
		int index = 0;
        if (node.getElement() == null) {
            throw new Exception(" Object provided is null");
        } 
        else {
            for (XDNode<E> n = head; n != null; n = n.getNext()) {
                if (node.getElement().equals(n.getElement())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}