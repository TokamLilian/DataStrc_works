package SpecialStack;

public class ArraySpecialStack<E extends Comparable<E>> implements SpecialStack<E> {
    private final int maxSize = 100;
    private int topOfStack;             // index of the top item in the array
    private Object[] theArray;          // reference to the array of items
    private Object[] maxStack;          // Stack to store maximum elements
    
    public ArraySpecialStack() {

        topOfStack = -1;
        theArray = new Object[maxSize];
        maxStack = new Object[maxSize];
    }

    @Override
    public E getMax() throws StackEmptyException {
        // returns the maximum element stored in the array
        if (isEmpty()){
            throw new StackEmptyException("Empty stack:  can't retrieve max value");
        }  

        @SuppressWarnings("unchecked")
        E max = (E) maxStack[topOfStack];
        return max;

    }
    
    /**  Push 'e' onto the stack. If the stack is already full, do nothing.*/
    public void Push(E e) {
        int size = size();
        if (!isFull()) {
            theArray[++topOfStack] = e;

            // update maxStack
            if (maxStack[0] == null){
                maxStack[0] = e;
            }
            else if (size == 0 || e.compareTo((E) maxStack[topOfStack-1]) > 0){
                maxStack[size] = e;
            }else{
                maxStack[size] = maxStack[topOfStack-1];
            }

        }else{
            throw  new RuntimeException("Stack Full: Can't push");
        }
    }

    /**  Take the top item off the stack and return it. If the stack is empty, return nothing*/
    public E Pop() {
        
        if (!isEmpty()){
            @SuppressWarnings("unchecked")
            E temp = (E) theArray[topOfStack--];
            theArray[size()] = null;        //for garbage collection
            
            // update maxStack
            if (size() > 0){
                maxStack[size()] = maxStack[topOfStack];
            }
            return temp;
            
        }else throw new IllegalStateException("Stack is empty");

    }

    /** Return the last element on the stack*/
    public E Top(){
        if (!isEmpty()) return (E)theArray[topOfStack];
        else throw new RuntimeException("Stack Empty: There isn't a top element");
    }
    
    /**
     *   Return the length of the stack*
    */
    public int size(){
        return topOfStack + 1;
    }

    /** Return true if the stack is empty.*/
    public boolean isEmpty() {
        return (topOfStack == -1);
    }

    // return true if the stack is full.
    public boolean isFull() {
        return (topOfStack == maxSize - 1);
    }

    // return a string representation of the contents of the stack.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=topOfStack;i>=0;i--){
            sb.append(theArray[i]);
            if (i != 0) sb.append(", ");
        }
        return sb.toString();
    }

}
