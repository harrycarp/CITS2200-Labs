import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import CITS2200.BinaryTree;
import CITS2200.Iterator;

public class BinTree<E> extends BinaryTree<E>{

	public BinTree() {
		super();
	}
	
	public BinTree(E item, BinaryTree<E> b1, BinaryTree<E> b2){
		super(item,b1,b2);
	}
	
	BinaryTree<E> root = this;
	
	//checks to see if there's a left child
	public boolean hasLeft(){
		//Ret true if left child exists
		return !this.getLeft().isEmpty();
	}
	
	//checks to see if there is a right child
	public boolean hasRight(){
		//Ret true if right child exists
		return !this.getRight().isEmpty();
	}
	
	/**
	 * Checks if one Binary Tree is equal in all
	 * values and node locations to this binary tree
	 */
	@Override //override original func
	public boolean equals(Object bT) {
		

		//check abstract type matches root
		if(!(bT instanceof BinaryTree<?>)){
			return false;
		}
	
		//cast as subclass
		BinaryTree<E> trial = (BinaryTree<E>) bT;
		

		 //check node for similar ext and int properties
		if(!hasLeft() != trial.getLeft().isEmpty() || 
				!hasRight() != trial.getRight().isEmpty()){
			return false;
		}
		
		//check if items are (not) equal
		if(!this.getItem().equals(trial.getItem())){
			return false;
		}
		

		 //Checks if left child calls recursively
		if(hasLeft() && !this.getLeft().equals(trial.getLeft())){
			return false;
		}
		
		//Checks if right child calls recursively
		if(hasRight() && !this.getRight().equals(trial.getRight())){
			return false;
		}
		
		//if all statements pass true, then func returns true
		return true;
		
	}
	
	//iterator for E Types
	@Override
	public Iterator<E> iterator() {
		
		return new InOrderIterator();
	}
	
	//Sub class for the Interior
	public class InOrderIterator implements Iterator<E>{
		
		//Initialising a Queue of appropriate type
		private Queue<BinaryTree<E>> treeBuffer;
		
		//If tree root is not empty adds pointer to Queue
		public InOrderIterator(){
			//create a bugger for the tree
			treeBuffer = new LinkedList<BinaryTree<E>>();
			//if theres a root, add a pointer to EOQ
			if(root != null){
				//add pointer to end of queue
				treeBuffer.add(root);
			}
		}
		
		//Checks if queue is empty or not
		@Override
		public boolean hasNext() {
			return !treeBuffer.isEmpty();
		}
		
		//@returns next E extracted from the iterator list
		@Override
		public E next(){
			//End scenario of iteration
			if(!hasNext()){
				throw new NoSuchElementException("No more items!");
			}
			//pointer is removed from front of queue
			//and into temporary variable
			BinaryTree<E> focus = treeBuffer.poll();
			
			//adds left child pointer to queue if present
			if(!focus.getLeft().isEmpty()){
				treeBuffer.add(focus.getLeft());
			}
			
			//adds right child pointer to queue if present
			if(!focus.getRight().isEmpty()){
				treeBuffer.add(focus.getRight());
			}
			
			//returns the actual object from the queue
			return focus.getItem();
		}
		
		//if rem fails, throw exception
		public void remove(){
			throw new UnsupportedOperationException("Invalid operation: can't remove object or component");
		}
	}
}
	