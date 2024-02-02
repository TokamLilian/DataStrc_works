package Stack;

public class ArrayStack implements Stack {
    private int maxSize = 100;        // maximum size of the stack (array length)
    private int topOfStack;           // index of the top item in the array
    private long[] theArray;          // reference to the array of items

    public ArrayStack(int s) {

        if (s < 1) throw new RuntimeException("Stack must be at least one element");
        maxSize = s;
        topOfStack = -1;
        theArray = new long[maxSize];
    }
    
    /**  Push 'it' onto the stack. If the stack is already full, do nothing.*/
    public void Push(long e) {
        if (!isFull()) {
            theArray[++topOfStack] = (long) e;
        }else{
            throw  new RuntimeException("Stack Full: Can't push");
        }
    }

    /**  Take the top item off the stack and return it. If the stack is empty, return nothing*/
    public long Pop() {
        long temp = 0;
        if (!isEmpty()) temp = theArray[topOfStack--];

        return temp;
    }

    /** Return the last element on the stack*/
    public long Top(){
        if (!isEmpty()) return theArray[topOfStack];
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
