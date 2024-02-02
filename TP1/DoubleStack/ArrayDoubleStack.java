package DoubleStack;

public class ArrayDoubleStack implements DoubleStack{
    private double[] array; 
    private int top1 , top2;
    private int maxSize;

    public ArrayDoubleStack(int n) {

        if (n < 1) throw new IllegalArgumentException("Size must be positive integer");

        maxSize = n;
        top1 = n/2; 
        top2 = n/2 + 1; 
        array = new double[n];
    }

    /**  Push 'e' onto the stack and return true. If the stack is already full, return false.<p/>
    * {@code arr} = 0 for first stack <p/>
    * {@code arr} = 1 for second stack
    * 
    * @author Lilian Tokam
    */
    public boolean Push(boolean arr, double e) {

        if (arr == false) {
        // push element 'e' to first stack (left)
            if (top1 < maxSize - 1){
                top1++;
                array[top1] = e;

                return true;
            }else{
                System.out.println("Stack Overflow" + " By element : " + e);
                return false;
            }    

        }else if (arr == true){
        // push element 'e' to the second stack (right)
            if (top2 > 0) {
                top2 --;
                array[top2] = e;

                return true;
            }else{
                System.out.println("Stack Overflow" + " By element : " + e);
                return false;
            }
        }else{
            return false;
        }
    }
    
    /** Remove the last element off the stack and return it. If the stack is empty, return nothing <p/>
     * {@code arr} = 0 for first stack <p/>
     * {@code arr} = 1 for second stack
     * 
     * @author Lilian Tokam
    */
    public double Pop(boolean arr) {
        double temp = 0;
        if (arr == false) {
        // pop element from first stack (left)
            if (top1 >= maxSize/2 + 1){
                temp = array[top1];
                top1--;
    
            }else{
                System.out.println("Stack Underflow");
            }    
            return temp;
    
        }else if (arr == true){
        // pop element from the second stack (right)
            if (top2 <= maxSize/2) {
                temp = array[top2];
                top2 ++;

            }else{
                System.out.println("Stack Underflow");
            }
            return temp;

        }else{
            return temp;
        }

    }
    
    /** Return the last element on the stack <p/>
     * {@code arr} = 0 for first stack <p/>
     * {@code arr} = 1 for second stack
     * 
     * @author Lilian Tokam
    */
    public double Top(boolean arr){
        return array[top2];
    }
    
    /**
    * Return the length of the stack <p/>
    * {@code arr} = 0 for first stack <p/>
    * {@code arr} = 1 for second stack
    * 
    * @author Lilian Tokam
    */
    public int size(boolean arr){
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
