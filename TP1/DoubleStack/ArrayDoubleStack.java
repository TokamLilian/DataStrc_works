package DoubleStack;

public class ArrayDoubleStack implements DoubleStack{
    private double[] array; 
    private int top1 = -1; 
    private int top2 = array.length; 
    final int maxSize = top1+top2;

    public ArrayDoubleStack(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("Size must be positive integer");
        }
        array = new double[n];
    }

    /**  Push 'e' onto the stack and return true. If the stack is already full, return false. */
    public boolean Push(boolean one, double e) {

        if (isFull()) {
            return false;
        }else{
            array[++top2] = e;
            return true;
        }
    }
    
    /**  Remove the last element off the stack and return it. If the stack is empty, return nothing*/
    public double Pop(boolean one) {
        double temp = 0;
        // to be changed!
        if (!isFull()) temp = array[top2--];
    
        return temp;
    }
    
    /** Return the last element on the stack*/
    public double Top(boolean one){
        return array[top2];
    }
    
    /**
     *   Return the length of the stack*
    */
    public int size(boolean one){
        return top1 + top2;
    }
    
    // /** Return true if the stack is empty.*/
    // public boolean isEmpty() {
    //     return (top1 == -1 || top2 == -1);
    // }

    /** return true if the double stack is full.*/
    public boolean isFull() {
       return (top1 == maxSize/2 && top2 == maxSize - 1);
    }
    
    /** print the content of the two stacks */

    //Idea: We could create two objects of type ArrayStack and store them in the same array (double array)
    //so that each element can have a defined "toString" of the implemented class "ArrayStack"
    public void print() {
        System.out.println(array.toString());;
    }

}
