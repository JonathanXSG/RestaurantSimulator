package DataStructures;

import java.util.Iterator;
import DataStructures.Interfaces.*;
import Restaurant.Customer;

/**
 * @author Jonathan , Adahid
 */
public class XSinglyLinkedList implements XList<Customer>{
	/**
	 * Private instances.
	 */
	private XSNode head, tail; 
	private int length; 
	private Iterator<Customer> iteratorMethod;
	

	/**
	 * Default Constructor.
	 */
	public XSinglyLinkedList(){
		this.length = 0;
		this.head = new XSNode();
		this.tail = new XSNode();
		this.head.next = (tail);
		this.tail.next = (null);
	}

	/**
	 * Adds a new element at the first position of the list.
	 * @param e
	 */
	public void addFirst(Customer e) {
		XSNode tempNode = new XSNode(e,this.head);
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
	public void addLast(Customer e) {
		XSNode tempNode = new XSNode(e, null);
		this.tail.next = (tempNode);
		tail = tempNode;
		length++;
	}


	@Override
	public void add(int i, Customer e) throws IndexOutOfBoundsException {
		if(i==0) addFirst(e);
		else if(i==length-1)addLast(e);
		else {
			XSNode prevNode = getNode(i-1);
			XSNode tempNode = new XSNode(e, prevNode.next);
			prevNode.next = (tempNode);
			length++;
		}
	}

	@Override
	public void add(Customer e) {
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
	public Customer removeFirst() throws IndexOutOfBoundsException{
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		Customer oldElement = this.head.element;
		head = head.next;
		return oldElement;
	}


	/**
	 * Removes last element in the list.
	 * @return removed element.
	 * @throws IndexOutOfBoundsException if last is null.
	 */
	public Customer removeLast() throws IndexOutOfBoundsException {
		if (tail == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		Customer oldElement = this.tail.element;
		XSNode prevNode= getNode(length-2);
		prevNode.next = null;
		tail= prevNode;
		length--;
		return oldElement;
	}

	@Override
	public Customer remove(int i) throws IndexOutOfBoundsException {
		if(i==0) return removeFirst();
		else if(i==length-1) return removeLast();
		else{
			XSNode prevNode= getNode(i-1);
			XSNode tempNode = prevNode.next;
			prevNode.next = (tempNode.next);
			length--;
			return tempNode.element;
		}
	}

	@Override
	public Customer set(int i, Customer e) throws IndexOutOfBoundsException {
		XSNode tempNode = getNode(i);
		Customer oldElement = tempNode.element;
		tempNode.element = (e);
		return oldElement;
	}

	@Override
	public Customer get(int i) throws IndexOutOfBoundsException {
		return getNode(i).element;
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
	 * @Author Jonathan
	 * Get Node at specific position
	 * @param i position
	 * @return node
	 * @throws IndexOutOfBoundsException if either list is empty or position is out of bounds.
	 */
	private XSNode getNode(int i) throws IndexOutOfBoundsException {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is Empty."); 
		}
		XSNode tempNode= head;
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
	public Iterator<Customer> iterator() {
		if(iteratorMethod == null){
			setIterator("Pat");
		}
		return this.iteratorMethod;
	}

	/**
	 * @Author Adahid
	 * Specifies which Iterator is going to be applied to the list.
	 * Choose between "Pat", "Mat", "Max" or "Pac".
	 * Any error or different string will be considered as Default("Pat").
	 * @param iterator
	 */
	public void setIterator(String iterator){
		switch (iterator){
		case "Pat":
			this.iteratorMethod = new PatIterator();
			break;
		case "Mat":
			this.iteratorMethod = new MatIterator();
			break;
		case "Max":
			this.iteratorMethod = new MaxIterator();
			break;
		case "Pac":
			this.iteratorMethod = new PacIterator();
			break;
		default:
			break;
		}

	}

	/**
	 * @author Jonathan
	 */
	private class MatIterator implements Iterator<Customer>{
		/**
		 * Private instances.
		 */
		private int count=0;
		private int turn=1;
		private XSNode pointer = head;
		private XSNode highest = pointer;

		/**
		 * Default Constructor
		 */
		private MatIterator(){
			turn = pointer.element.getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			pointer = head;
			Customer c = pointer.element;
			boolean next = false;

			if(count<=length) {
				for(int i = 1; i<=length ; i++) {
					c = pointer.element;
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
						// If we haven't found a customer to serve and the current customer has a turn above the current
						// we set the turn to it's turn of arrival and rerun this customer.
						else if(!next && count<=length) {
							turn = pointer.element.getArrivalTurn();
							i--;
							continue;
						}
						else break;
					}
					pointer = pointer.next;
				}
			}
			return next;
		}

		@Override
		public Customer next() {
			count++;
			highest.element.setOrderTaken(true);
			turn += highest.element.getOrderTime();
			return highest.element;
		}

	}

	/**
	 * @author  Jonathan
	 *
	 */
	private class MaxIterator implements Iterator<Customer>{
		/**
		 * Private Instances.
		 */
		private int count=0;
		private int turn=1;
		private XSNode pointer = head;
		private XSNode highest = pointer;

		/**
		 * Default Constructor.
		 */
		private MaxIterator(){
			turn = pointer.element.getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			pointer = head;
			Customer c;
			double profit =0;
			boolean next = false;

			if(count<length) {
				for(int i = 1; i<=length ; i++) {
					c = pointer.element;
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
						// If we haven't found a customer to serve and the current customer has a turn above the current
						// we set the turn to it's turn of arrival and rerun this customer.
						else if(!next && count<=length) {
							turn = pointer.element.getArrivalTurn();
							i--;
							continue;
						}
						else break;
					}
					pointer = pointer.next;
				}
			}
			return next;
		}

		@Override
		public Customer next() {
			count++;
			highest.element.setOrderTaken(true);
			turn += highest.element.getOrderTime();
			return highest.element;
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

		private XSNode point = head;
		private Customer tmp = null;

		/**
		 * Default Constructor.
		 */
		private PatIterator(){
			turn = point.element.getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			if(counter <= length){
				for(int i = counter; i<length;i++){
					Customer c = point.element;
					if(c.getArrivalTurn()<= turn) {
						if(c.getPatienceLevel()+c.getArrivalTurn()-turn>=0){
							this.tmp = c;
							return true;
						}
						else{
							counter++;
						}
					}
					// If we haven't found a customer to serve and the current customer has a turn above the current
					// we set the turn to it's turn of arrival and rerun this customer.
					else {
						turn = point.element.getArrivalTurn();
						i--;
						continue;
					}
					point = point.next;
				}
			}
			return false;
		}

		@Override
		public Customer next() {
			turn = turn + point.element.getOrderTime();
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

		private XSNode point = head;
		private Customer lowest = point.element;
		private boolean newLow = false;

		/**
		 * Default Constructor.
		 */
		private PacIterator(){
			turn = point.element.getArrivalTurn();
		}

		@Override
		public boolean hasNext() {
			newLow = false;
			point = head;
			lowest = null;
			Customer c = point.element;

			if(counter<=length){
				for(int i = 1; i<=length;i++){
					c = point.element;
					if(!c.isOrderTaken()){
						if((c.getArrivalTurn()<=turn)){
						
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
						// If we haven't found a customer to serve and the current customer has a turn above the current
						// we set the turn to it's turn of arrival and rerun this customer.
						else if(!newLow && counter<=length) {
							turn = point.element.getArrivalTurn();
							i--;
							continue;
						}
						else break;
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
	 * @Author Jonathan
	 * Runs through the list and reset all customers for future implementations.
	 */
	public void resetCustomers() {
		XSNode c = head;
		for(int i=0 ; i< length ; i++) {
			c.element.setOrderTaken(false);
			c = c.next;
		}
	}
	
	/**
	 * @author Adahid Galan
	 * @param <E>
	 */
	private class XSNode{
		private Customer element;
		private XSNode next;

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
		public XSNode(Customer e) { 
			this.element = e; 
			this.next = null;
		}

		/**
		 * Constructor to add new element and its next.
		 * @param e
		 * @param n
		 */
		public XSNode(Customer e,XSNode n) { 
			this.element = e; 
			this.next = n; 
		}
		
	}


}
