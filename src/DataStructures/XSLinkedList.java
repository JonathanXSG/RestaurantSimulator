package DataStructures;

public class XSLinkedList <E> implements XLinkedList<E>{

	private XSNode<E> head, tail; 
	private int length; 
	
	public XSLinkedList(){
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
	public void addAtPosition(int i, E e) throws Exception {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XSNode<E> prevNode= (XSNode<E>) findNodeAtIndex(i-1);
			XSNode<E> tempNode = new XSNode<E>(e, prevNode.getNext());
			prevNode.setNext(tempNode);
			length++;
		}
	}

	@Override
	public E removeFirst() throws Exception{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.head.getElement();
		head = (XSNode<E>) head.getNext();
		return oldElement;
	}

	@Override
	public E removeLast() throws Exception {
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.tail.getElement();
		XSNode<E> prevNode= findNodeAtIndex(length-2);
		prevNode.setNext(null);
		tail= prevNode;
		length--;
		return oldElement;
	}

	@Override
	public E replaceAtIndex(int i, E e) throws Exception {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XSNode<E> tempNode = findNodeAtIndex(i);
		E oldElement = tempNode.getElement();
		tempNode.setElement(e);
		return oldElement;
	}

	@Override
	public E get(int i) throws Exception {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		return findNodeAtIndex(i).getElement();
	}

	@Override
	public XNode<E> getNode(int i) throws Exception {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		return findNodeAtIndex(i);
	}
	
	@Override
	public int size() {
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		return this.length==0;
	}

	
	private XSNode<E> findNodeAtIndex(int index) throws Exception{
		XSNode<E> tempNode = this.head;
		if(index > this.length || index<0) throw new IndexOutOfBoundsException("Index provived is out of Bounds");
		else if(index==0) return head;
		else if(index==length-1) return tail;
		else{
			for(int i=0 ; i<=index ; i++){
				tempNode = (XSNode<E>) tempNode.getNext();
			}
		}
		return tempNode;
	}

}
