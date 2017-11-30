package DataStructures;

import DataStructures.Interfaces.*;

public class XSinglyLinkedList <E> implements XLinkedList<E>{

	private XSNode<E> head, tail; 
	private int length; 
	
	public XSinglyLinkedList(){
		this.length = 0;
		this.head = new XSNode<E>();
		this.tail = new XSNode<E>();
		this.head.setNext(tail);
		this.tail.setNext(null);
	}

	@Override
	public void addFirst(E e) {
		XSNode<E> tempNode = new XSNode<E>(e,this.head);
		this.head.setNext(tempNode);
		if(length==0) {
			tail=tempNode;
		}
		length++;
	}

	@Override
	public void addLast(E e) {
		XSNode<E> tempNode = new XSNode<E>(e, null);
		this.tail.setNext(tempNode);
		tail = tempNode;
		length++;
	}
	
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XSNode<E> prevNode = getNode(i-1);
			XSNode<E> tempNode = new XSNode<E>(e, prevNode.getNext());
			prevNode.setNext(tempNode);
			length++;
		}
	}

	@Override
	public void add(E e) {
		addLast(e);
	}

	@Override
	public E removeFirst() throws IndexOutOfBoundsException{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.head.getElement();
		head = head.getNext();
		return oldElement;
	}

	@Override
	public E removeLast() throws IndexOutOfBoundsException {
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.tail.getElement();
		XSNode<E> prevNode= getNode(length-2);
		prevNode.setNext(null);
		tail= prevNode;
		length--;
		return oldElement;
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		if(i==0) return removeFirst();
		else if(i==length-1) return removeLast();
		else{
			XSNode<E> prevNode= getNode(i-1);
			XSNode<E> tempNode = prevNode.getNext();
			prevNode.setNext(tempNode.getNext());
			length--;
			return tempNode.getElement();
		}
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		XSNode<E> tempNode = getNode(i);
		E oldElement = tempNode.getElement();
		tempNode.setElement(e);
		return oldElement;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		return getNode(i).getElement();
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
            for (XSNode<E> n = head; n != null; n = n.getNext()) {
                if (node.getElement().equals(n.getElement())) {
                    return index;
                }
                index++;
            }
        }
        return -1;
	}
	
	private XSNode<E> getNode(int i) throws IndexOutOfBoundsException {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XSNode<E> tempNode= head;
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


}
