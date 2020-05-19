import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;


public class DequeCyclic implements Deque<Object>{
	
	private int currentSize; //current size int
	private int left; //left most
	private Object [] line; //queue array
	
	/*
	 * Constructor where capacity indicates the size
	 * of the array that builds DequeCyclic, not the
	 * data structure itself.
	 */
	public DequeCyclic(int capacity){
		line = new Object[capacity];
		left = 0;
		currentSize = 0;
		// create second index currentSize for dequeue size
	}
	
	/*
	 * @return true if the queue empty 
	 */
	public boolean isEmpty(){
		return currentSize == 0;
	}
	
	/**
	 * 
	 * @return true if the queue is full (capacity of the array is
	 * equal to the length of the deque)
	 */
	public boolean isFull(){
		return currentSize == line.length;
	}
	
	/**
	 * add object c as the left-most object in the deque, 
	 * or throw an Overflow exception if the deque is full.
	 * @param c
	 * @throws Overflow if deque is full.
	 */
	public void pushLeft(Object c) throws Overflow{
		if(!isFull()){
			left = (left - 1 + line.length) % line.length;
			line[left] = c;
			currentSize++;
		}
		else throw new Overflow("Deque is full");
	}
	
	/**
	 * add object c as the right-most object in the deque, 
	 * or throw an Overflow exception if the deque is full.
	 * @param c
	 */
	public void pushRight(Object c) throws Overflow{
		if(!isFull()){
			//right = (left + currentSize + 1)%line.length;
			line[(left + currentSize)%line.length] = c;
			currentSize++;
		}
		else throw new Overflow("Deque is full");
	}
	
	/**
	 * @Return left-most object in the deque, 
	 * or @throw an Underflow exception if the deque is empty.
	 * 
	 */
	public Object peekLeft() throws Underflow{
		if(!isEmpty()){
			return line[left];
		}
		else throw new Underflow("Deque is empty");
	}
	
	/*
	 * return the right-most object in the deque, 
	 * or throw an Underflow exception if the deque is empty.
	 */
	public Object peekRight() throws Underflow{
		if(!isEmpty()){
			return line[(left+currentSize-1)%line.length];
		}
		else throw new Underflow("Deque is empty");
	}
	
	/**
	 * Remove and @return right-most object in the deque, or @throw an Underflow exception if the deque is empty.
	 */
	public Object popRight() throws Underflow{
		if(!isEmpty()){
			Object temp = peekRight();
			currentSize--;
			return temp;
		}
		else throw new Underflow("Deque is empty");
	}

	/**
	 * remove and @return left-most object in the deque, or @throw an Underflow exception if the deque is empty.
	 */
	public Object popLeft() throws Underflow{
		if(!isEmpty()){
			Object temp = peekLeft();
			left = (left + 1)%line.length;
			currentSize--;
			return temp;
		}
		else throw new Underflow("Deque is empty");
	}
	

}