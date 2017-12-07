package DataStructures;

import java.util.Iterator;

import DataStructures.Interfaces.*;
import Restaurant.Customer;

public class XSinglyLinkedList <E> implements XList<E>{

	/**
	 * @author Adahid Galan
	 *
	 * @param <E>
	 */
	private class XSNode<E>{
		private E element;
		private XSNode<E> next;

		/**
		 * Default constructor.
		 */
		public XSNode() {
			this.element = null; 
			this.next = null;
		}

		
		/**
		 * Constructor to add new element.
		 * @param e
		 */
		public XSNode(E e) { 
			this.element = e; 
			this.next = null;
		}

		/**
		 * Constructor to add new element and its next.
		 * @param e
		 * @param n
		 */
		public XSNode(E e,XSNode<E> n) { 
			this.element = e; 
			this.next = n; 
		}
		
		
		/**
		 * Get element.
		 * @return element.
		 */
		public E getElement() {
			return this.element; 
		}
	}
	
	/**
	 * Private instances.
	 */

	private XSNode<E> head, tail; 
	private int length; 
	private Iterator<E> iteratorMethod;
	
	

	/**
	 * Default Constructor.
	 */
	public XSinglyLinkedList(){
		this.length = 0;
		this.head = new XSNode<E>();
		this.tail = new XSNode<E>();
		this.head.next = (tail);
		this.tail.next = (null);
	}



	/**
	 * Adds a new element at the first position of the list.
	 * @param e
	 */
	public void addFirst(E e) {
		XSNode<E> tempNode = new XSNode<E>(e,this.head);
		this.head = tempNode;
		if(length==0) {
			tail=tempNode;
		}
		length++;
	}



	/**
	 * Adds a new element at the last position of the list.
	 * @param e
	 */
	public void addLast(E e) {
		XSNode<E> tempNode = new XSNode<E>(e, null);
		this.tail.next = (tempNode);
		tail = tempNode;
		length++;
	}


	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XSNode<E> prevNode = getNode(i-1);
			XSNode<E> tempNode = new XSNode<E>(e, prevNode.next);
			prevNode.next = (tempNode);
			length++;
		}
	}

	@Override
	public void add(E e) {
		if(length==0) {
			addFirst(e);
		}
		else {
			addLast(e);
		}
	}


	/**
	 * Removes first element in the list.
	 * @return removed element.
	 * @throws IndexOutOfBoundsException if first is null.
	 */
	public E removeFirst() throws IndexOutOfBoundsException{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.head.getElement();
		head = head.next;
		return oldElement;
	}


	/**
	 * Removes last element in the list.
	 * @return removed element.
	 * @throws IndexOutOfBoundsException if last is null.
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		E oldElement = this.tail.getElement();
		XSNode<E> prevNode= getNode(length-2);
		prevNode.next = null;
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
			XSNode<E> tempNode = prevNode.next;
			prevNode.next = (tempNode.next);
			length--;
			return tempNode.getElement();
		}
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		XSNode<E> tempNode = getNode(i);
		E oldElement = tempNode.getElement();
		tempNode.element = (e);
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

	
	/**
	 * Get Node at specific position
	 * @param i position
	 * @return node
	 * @throws IndexOutOfBoundsException if either list is empty or position is out of bounds.
	 */
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
				tempNode = tempNode.next;
			}
		}
		return tempNode;
	}

	@Override
	public Iterator<E> iterator() {
		if(iteratorMethod == null){
			setIterator("Pat");
		}

		return this.iteratorMethod;
	}

	/**
	 * Specifies which Iterator is going to be applied to the list.
	 * Choose between "Pat", "Mat", "Max" or "Pac".
	 * Any error or different string will be considered as Default("Pat").
	 * @param iterator
	 */
	public void setIterator(String iterator){
		switch (iterator){

		case "Pat":
			this.iteratorMethod = (Iterator<E>) new PatIterator();
			break;
		case "Mat":
			this.iteratorMethod = (Iterator<E>) new MatIterator();
			break;
		case "Max":
			this.iteratorMethod = (Iterator<E>) new MaxIterator();
			break;
		case "Pac":
			this.iteratorMethod = (Iterator<E>) new PacIterator();
			break;

		default:
			break;



		}

	}

	/**
	 * @author Jonathan Santiago
	 *
	 */
	private class MatIterator implements Iterator<Customer>{

		/**
		 * Private instances.
		 */
		private int count=0;
		private int turn=1;
		private XSNode<Customer> pointer =(XSNode<Customer>) head;
		private XSNode<Customer> highest = pointer;

		/**
		 * Default Constructor
		 */
		private MatIterator(){
			turn = pointer.getElement().getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			pointer =(XSNode<Customer>) head;
			Customer c = pointer.getElement();
			boolean next = false;

			if(count<=length) {
				for(int i = 1; i<=length ; i++) {
					c = pointer.getElement();
					if(!c.isOrderTaken()) {
						if(c.getArrivalTurn()<= turn) {
							if((c.getPatienceLevel() + c.getArrivalTurn()) - turn >=0 ) { //We can serve
								next = true;
								highest = pointer;
							}
							else {
								count++;
								c.setOrderTaken(true);
							}
						}
						else if(!next && count<=length) {
							turn = pointer.getElement().getArrivalTurn();
							i--;
							continue;
						}
					}
					pointer = pointer.next;
				}
			}
			return next;
		}

		@Override
		public Customer next() {
			count++;
			highest.getElement().setOrderTaken(true);
			turn += highest.getElement().getOrderTime();
			return highest.getElement();
		}

	}

	/**
	 * @author  Jonathan Santiago
	 *
	 */
	private class MaxIterator implements Iterator<Customer>{
		/**
		 * Private Instances.
		 */
		private int count=0;
		private int turn=1;
		private XSNode<Customer> pointer =(XSNode<Customer>) head;
		private XSNode<Customer> highest = pointer;

		/**
		 * Default Constructor.
		 */
		private MaxIterator(){
			turn = pointer.getElement().getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			pointer =(XSNode<Customer>) head;
			Customer c;
			double profit =0;
			boolean next = false;

			if(count<length) {
				for(int i = 1; i<=length ; i++) {
					c = pointer.getElement();
					if(!c.isOrderTaken()) {
						if(c.getArrivalTurn()<= turn) {
							if(c.getPatienceLevel() + c.getArrivalTurn() - turn >=0 ) { //We can serve
								if(c.getValue() > profit) {
									profit = c.getValue();
									next = true;
									highest = pointer;
								}
							}
							else {
								count++;
								c.setOrderTaken(true);
							}
						}
						else if(!next && count<=length) {
							turn = pointer.getElement().getArrivalTurn();
							i--;
							continue;
						}
					}
					pointer = pointer.next;
				}
			}
			return next;
		}

		@Override
		public Customer next() {
			count++;
			highest.getElement().setOrderTaken(true);
			turn += highest.getElement().getOrderTime();
			return highest.getElement();
		}

	}

	/**
	 * @author Adahid Galan
	 *
	 */
	private class PatIterator implements Iterator<Customer>{
		/**
		 * Private Instances.
		 */
		private int counter = 0;
		private int turn = 1;

		private XSNode<Customer> point = (XSNode<Customer>) head;
		private Customer tmp = null;

		/**
		 * Default Constructor.
		 */
		private PatIterator(){
			turn = point.getElement().getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			if(counter <= length){
				for(int i = counter; i<length;i++){
					Customer c = point.getElement();
					if(c.getPatienceLevel()+c.getArrivalTurn()-turn>=0){
						this.tmp = c;
						return true;
					}
					else{
						point = point.next;
						counter++;
					}
				}
			}
			return false;
		}

		@Override
		public Customer next() {
			turn = turn + point.getElement().getOrderTime();
			counter++;
			point = point.next;
			return tmp;
		}
	}

	/**
	 * @author Adahid Galan
	 *
	 */
	private class PacIterator implements Iterator<Customer>{
		/**
		 * Private Instances
		 */
		private int counter = 0;
		private int turn = 1;

		private XSNode<Customer> point = (XSNode<Customer>) head;
		private Customer lowest = point.getElement();
		private boolean newLow = false;

		/**
		 * Default Constructor.
		 */
		private PacIterator(){
			turn = point.getElement().getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			newLow = false;
			point = (XSNode<Customer>) head;
			lowest = null;
			Customer c = point.getElement();

			if(counter<=length){
				for(int i = 1; i<=length;i++){
					c = point.getElement();
					if((c.getArrivalTurn()<=turn)){
						if(!c.isOrderTaken()){
							if(c.getPatienceLevel()+c.getArrivalTurn()-turn>=0){
								if(lowest == null){
									lowest = c;
								}
								if(c.getOrderTime()<lowest.getOrderTime()){
									lowest = c;
									newLow = true;
								}
								else if(c.equals(lowest)){
									newLow = true;
								}
							}
							else{
								counter++;
								c.setOrderTaken(true);
							}
						}
					}
					else if(!newLow && counter<=length) {
						turn = point.getElement().getArrivalTurn();
						i--;
						continue;
					}
					point = point.next;
				}
			}
			return newLow;
		}


		@Override
		public Customer next() {
			turn = turn + lowest.getOrderTime();
			counter++;
			lowest.setOrderTaken(true);
			return lowest;
		}

	}

	/**
	 * Runs through the list and reset all customers for future implementations.
	 */
	public void resetCustomers() {
		XSNode<Customer> c = (XSNode<Customer>) head;
		for(int i=0 ; i< length ; i++) {
			c.getElement().setOrderTaken(false);
			c = c.next;
		}
	}


}
