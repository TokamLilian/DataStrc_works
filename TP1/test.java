// test without autograder
import Stack.ArrayStack;
import DoubleStack.ArrayDoubleStack;

public class test {

    public static void main(String[] args) {
        InsertAndPop();
    }
    
    public static void ArrayStack_Test () {
        ArrayStack newSimpleArray = new ArrayStack(1);
        System.out.println(newSimpleArray);
    }

    public static void ArrayDoubleStack_Test () {
        ArrayDoubleStack newDoubleArray = new ArrayDoubleStack(1);
        System.out.println(newDoubleArray);
    }
    
    public static void InsertAndPop(){
        
        ArrayDoubleStack newDoubleArray = new ArrayDoubleStack(5); 
        newDoubleArray.Push(true, 5); 
        newDoubleArray.Push(false,10); 
        newDoubleArray.Push(false, 15); 
        newDoubleArray.Push(true, 11); 
        newDoubleArray.Push(false, 7); 
        System.out.println("Popped element from stack1 is "+ ": " + newDoubleArray.Pop(true)); 
        newDoubleArray.Push(false, 40); 
        System.out.println("Popped element from stack2 is " + ": " + newDoubleArray.Pop(false)); 
        
        System.out.println("Top element from stack1 is "+ ": " + newDoubleArray.Top(true)); 
        System.out.println("Top element from stack2 is "+ ": " + newDoubleArray.Top(false)); 

        System.out.println("Size of stack1 is "+ ": " + newDoubleArray.Size(true)); 
        System.out.println("Size of stack2 is "+ ": " + newDoubleArray.Size(false)); 
    }

}
