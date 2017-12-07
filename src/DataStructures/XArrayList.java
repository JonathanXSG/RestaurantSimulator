package DataStructures;

import java.util.Iterator;

import DataStructures.Interfaces.*;

/**
 * @author Jonathan Santiagp
 * @param <E>
 */
public class XArrayList<E> implements XList<E>{
	private E[] elements; 
	private int size; 

	@SuppressWarnings("unchecked")
	public XArrayList() { 
		elements = (E[]) new Object[10]; 
		size = 0; 
	} 
	
	@Override
	public void add(int index, E e) throws IndexOutOfBoundsException {
		if(index<0 || index > size){
			throw new IndexOutOfBoundsException(" add() Invalid index: "+ index);
		}
		else{
			if(size+1 > elements.length/2 + elements.length/4){
				changeCapacity(elements.length);
			}
			if(elements[index]!=null) {
				shiftToRight(index,size-1);
			}
			elements[index]=e;
			size++;
		}
	}

	@Override
	public void add(E e) {
		if(size+1 > elements.length/2 + elements.length/4){
			changeCapacity(elements.length);
		}
		elements[size]=e;
		size++;
	}
	
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index<0 || index > elements.length-1){
			throw new IndexOutOfBoundsException(" remove() Invalid index: "+ index);
		}
		else{
			E element = elements[index];
			elements[index] = null;
			shiftToLeft(index,size-1);
			size--;
			if(size < elements.length/2 - elements.length/4){
				changeCapacity(-elements.length/2);
			}
			return element;
		}
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index<0 || index > elements.length-1){
			throw new IndexOutOfBoundsException(" get() Invalid index: "+ index);
		}
		else{
			return elements[index];
		}
	}

	@Override
	public E set(int index, E e) throws IndexOutOfBoundsException {
		if(index<0 || index > elements.length-1){
			throw new IndexOutOfBoundsException(" set() Invalid index: "+ index);
		}
		else{
			E eTemp = elements[index];
			elements[index]=e;
			return eTemp;
		}
	}

	@Override
	public int size() {
		return size;
	}	
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	@SuppressWarnings("unchecked")
	private void changeCapacity(int change) { 
		int newCapacity = elements.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = elements[i]; 
		} 
		elements = newElement; 
	}

	/**
	 * Method to shift the elements of the internal array to the right
	 * @param lower index to start from
	 * @param upper index that will be moved
	 */
	private void shiftToRight(int lower, int upper) { 
		for (int pos = upper; pos >= lower; pos--)
			elements[pos+1] = elements[pos]; 
	}

	/**
	 * Method to shift the elements of the internal array to the left
	 * @param lower index to start from
	 * @param upper index that will be moved
	 */
	private void shiftToLeft(int lower, int upper) { 
		for (int pos = lower; pos <= upper; pos++)
			elements[pos] = elements[pos+1]; 
	}

	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
