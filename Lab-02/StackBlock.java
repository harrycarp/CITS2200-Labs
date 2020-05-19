
import CITS2200.Overflow;
import CITS2200.Stack;
import CITS2200.Underflow;

public class StackBlock implements Stack{
	
	private Object[] arr_obj; //tstack
	private int top; //top of Stack
	
	public StackBlock(int s){
		top = -1;
		arr_obj = new Object[s];
	}

	@Override
	public boolean isEmpty() {
		//checks if the top is non-existent with -1 pointer
		return top == -1;
	}
	
	public boolean isFull(){
		//checks if the there's an element at the end of the stack
		return top == arr_obj.length -1;
	}

	@Override
	public void push(Object o) throws Overflow {
		//add object at the end of the stack if the astack is NOT full
		if(!isFull()){
			arr_obj[++top] = o;
		}
		else throw new Overflow("Error Overflow: Stack is full");
	}
	
	@Override
	public Object examine() throws Underflow {
		//check if stack is empty
		if(!isEmpty()){
			//if not empty, return the top of the stack
			return arr_obj[top];
		}
		else throw new Underflow("Stack is empty!");
	}

	@Override
	public Object pop() throws Underflow {
		if(!isEmpty()){
			Object firstItem = arr_obj[top];
			top--;
			return firstItem;
		}
		else throw new Underflow("Error Underflow: Stack is empty");
	}
}
