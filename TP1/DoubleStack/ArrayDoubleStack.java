package DoubleStack;

public class ArrayDoubleStack<E> implements DoubleStack{
    private final int maxSize = 100;
    private Object[] array; 
    private int top1 , top2;

    public ArrayDoubleStack() {

        //if (n < 1) throw new IllegalArgumentException("Size must be positive integer");
        //maxSize = n;

        top1 = maxSize/2; 
        top2 = maxSize/2 + 1; 
        array = new Object[maxSize];
    }

    /**  Push 'e' onto the stack and return true. If the stack is already full, return false.<p/>
    * {@code arr} = true for first stack <p/>
    * {@code arr} = false for second stack
    * 
    * @author Lilian Tokam
    */
    public boolean Push(boolean arr, E e) {

        if (arr == true) {
        // push element 'e' to first stack (growing right)

            // check for at least an empty space for adding a new element
            if (top1 < maxSize - 1){
                top1++;
                array[top1] = e;

                return true;
            }else{
                System.out.println("Stack Overflow" + " By element : " + e);
                return false;
            }    

        }else if (arr == false){
        // push element 'e' to the second stack (growing left)
            
            // at least an empty space
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
     * {@code arr} = true for first stack <p/>
     * {@code arr} = false for second stack
     * 
     * @author Lilian Tokam
    */
    public E Pop(boolean arr) {
        E temp;
        if (arr == true) {
        // pop element from second stack (right)
            if (top1 >= maxSize/2 + 1){
                temp = (E) array[top1];
                top1--;
                return temp;
    
            }else{
                throw  new RuntimeException("NoSuchElementException: The Stack Is Empty");
            }    
    
        }else if (arr == false){
        // pop element from the first stack (left)
            if (top2 <= maxSize/2) {
                temp = (E) array[top2];
                top2 ++;
                
                return temp;
            }else{
                throw  new RuntimeException("NoSuchElementException: The Stack Is Empty");
            }

        }else{
            throw new IllegalArgumentException("Invalid Argument: You must specify which stack to use.");
        }

    }
    
    /** Return the last element on the stack <p/>
     * {@code arr} = true for first stack <p/>
     * {@code arr} = false for second stack
     * 
     * @author Lilian Tokam
    */
    public E Top(boolean arr){
        E temp;
        if (arr == false) return (E) array[top2];
        else if (arr == true) return (E) array[top1];
        else throw new IllegalArgumentException();
    }
    
    /**
    * Return the length of the stack <p/>
    * {@code arr} = 0 for first stack <p/>
    * {@code arr} = 1 for second stack
    * 
    * @author Lilian Tokam
    */
    public int Size(boolean arr){
        int size = 0;
        if (arr == false) size = (maxSize/2 + 1) - top2;
        else if (arr == true) size = maxSize/2 - (maxSize - 1 - top1); 

        return size;
    }

    /** return true if the E stack is full.*/
    public boolean isFull() {
       return (top2 == 0 && top1 == maxSize - 1);
    }
    
    /** print the content of the two stacks */
    public void print() {
        System.out.println("Stack 1: ");

        for (int i=maxSize/2 + 1 ; i<=top1 ; i++)
            if (array[i] == null) break;
            else System.out.print(array[i] + " ");

        System.out.println("\n");

        System.out.println("Stack 2: ");
        for (int j=maxSize/2 ; j>=0 ; j--)
            if (array[j] == null) break;
            else System.out.print(array[j] + " ");

    }

}
