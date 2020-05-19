import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

/**
 * @author Harry Carpenter (22723303)
 *
 */
public class PriorityQueueLinked implements PriorityQueue<Object>{
	
	/**
	 * Init front of queue
	 */
	private Link<Object> front;
	
	/**
	 * 	Generic Constructor creating a null front of queue
	 */
	public PriorityQueueLinked(){
		front = null;
	}

	/**
	 *	Returns the object at the front of the queue with
	 *	the highest priority and removes it.
	 *	Throws underflow if the queue is empty.
	 * 	@Return Front Of Queue
	 * 	@Throw Undeflow Error
	 */
	@Override
	public Object dequeue() throws Underflow {
		if(!isEmpty()){
			Object tempObj = (Object) front.element;
			front = front.next;
			return tempObj;
		}
		else throw new Underflow("Queue is Empty!");
	}
	
	/**
	 * 	Add item to queue depending on importance
	 * @return VOID()
	 * @throws IllegalValue
	 */
	@Override
	public void enqueue(Object item, int importance) throws IllegalValue {
		if(importance < 0){
			throw new IllegalValue("You can't have a negative priority!");
		}
		else{
			if(isEmpty() || importance > front.priority){ front = new Link<Object>(item,importance,front);}
			else{
				Link<Object> findSpot = front;
				while(findSpot.next != null && findSpot.next.priority >= importance){
					findSpot = findSpot.next;
				}
				findSpot.next = new Link<Object>(item,importance,findSpot.next);
			}
		}
	}

	/**
	 * Returns the object at the front of the priority Queue
	 * @return Front of the PQueue
	 */
	@Override
	public Object examine() throws Underflow {
		if(!isEmpty()){
			return (Object) front.element;
		}
		else throw new Underflow(" Nothing left in the queue!");
	}
	
	/**
	 * 	Checks if the priority queue is empty.
	 * 	@return Boolean
	 */
	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public Iterator<Object> iterator() {
		return new PriorityIterator();
	}
	
	
	/**
	 * Iterator for priority queue.
	 * hasNext() @returns True if next ref ! Null
	 * @throws OutOfBounds
	 */
	public class PriorityIterator implements Iterator<Object>{

		Link<Object> current;
		
		public PriorityIterator(){
			current = new Link<Object>(null,0,front);
		}
		
		//	Returns true if next reference is not to null
		@Override
		public boolean hasNext() {
			return current.next != null;
		}
		
		//	Moves link to next item and returns its Object
		//	Throws Out of Bounds if reaches end of queue.
		@Override
		public Object next() throws OutOfBounds {
			if(hasNext()){
				current = current.next;
				return current.element;
			}
			else{
				throw new OutOfBounds("No more items in queue :(");
			}
		}
	}
	
	/**
	 * Internal class Link to make a base structure
	 * that holds an Object
	 */
	public class Link<Object>{
		Object element;
		int priority;
		Link<Object> next;
		
		public Link(Object item, int priority, Link<Object> n){
			this.element = item;
			this.priority = priority;
			this.next = n;
		}
	}
}
