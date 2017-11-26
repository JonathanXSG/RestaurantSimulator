package DataStructures;

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
	public void addAtPosition(int i, E e) throws Exception {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XDNode<E> oldNode= (XDNode<E>) findNodeAtIndex(i);
			XDNode<E> tempNode = new XDNode<E>(e, oldNode.getPrev(),oldNode);
			oldNode.getPrev().setNext(tempNode);
			oldNode.setPrev(tempNode);
			length++;
		}
	}

	@Override
	public E removeFirst() throws Exception{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.head.getElement();
		this.head = this.head.getNext();
		length--;
		return oldElement;
	}

	@Override
	public E removeLast() throws Exception{
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.tail.getElement();
		this.tail = this.tail.getPrev();
		length--;
		return oldElement;
	}

	@Override
	public E replaceAtIndex(int i, E e) throws Exception {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XDNode<E> tempNode = findNodeAtIndex(i);
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
	
	private XDNode<E> findNodeAtIndex(int index) throws Exception{
		XDNode<E> tempNode= this.head;
		
		if(index > this.length || index<0) throw new Exception("Index is out of Bounds");
		else if(index==0) return head;
		else if(index==length-1) return tail;
		else{
			for(int i=0 ; i<=index ; i++){
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

}