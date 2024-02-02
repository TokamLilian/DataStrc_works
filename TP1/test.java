// test without autograder
import Stack.ArrayStack;
import DoubleStack.ArrayDoubleStack;

public class test {

    public static void main(String[] args) {
        ArrayStack_Test();
        ArrayDoubleStack_Test();
    }
    
    public static void ArrayStack_Test () {
        ArrayStack<Integer> newSimpleArray = new ArrayStack<>();
        insertAndPopSS(newSimpleArray);
        topAndSizeSS(newSimpleArray);
    }

    public static void insertAndPopSS (ArrayStack<Integer> stack) {
        try{
            for (int i=0;i<6;i++) {
                int randNum = (int)(Math.random()*9 + 1);
                stack.Push(randNum);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try{

            int size = stack.size();
            long temp;
            for (int j=0;j<size+1;j++) {
                temp = stack.Pop();
                System.out.print("\n" + "Poped element is " + temp);
            }
        }catch  (Exception e){
            System.out.println("\n" + e.getMessage());
        }
       
    }    

    public static void topAndSizeSS(ArrayStack<Integer> stack) {
        System.out.println("\n");
        System.out.println("Size of stack is " + stack.size());
        
        try{
            System.out.println("Top element of stack is " + stack.Top());
        }catch  (Exception e){
            System.out.println(e.getMessage());
        }
        
    }

    public static void ArrayDoubleStack_Test () {
        ArrayDoubleStack newDoubleArray = new ArrayDoubleStack(1);
        InsertAndPopDS(newDoubleArray);
        TopAndSizeDS(newDoubleArray);
    }
    
    public static void InsertAndPopDS(ArrayDoubleStack doubleArray){
        
        doubleArray.Push(true, 5); 
        doubleArray.Push(false,10); 
        doubleArray.Push(false, 15); 
        doubleArray.Push(true, 11); 
        doubleArray.Push(false, 7); 
        System.out.println("Popped element from stack1 is "+ ": " + doubleArray.Pop(true)); 
        doubleArray.Push(false, 40); 
        System.out.println("Popped element from stack2 is " + ": " + doubleArray.Pop(false)); 
    }
    
    public static void TopAndSizeDS(ArrayDoubleStack doubleArray){
        System.out.println("Top element from stack1 is "+ ": " + doubleArray.Top(true)); 
        System.out.println("Top element from stack2 is "+ ": " + doubleArray.Top(false)); 

        System.out.println("Size of stack1 is "+ ": " + doubleArray.Size(true)); 
        System.out.println("Size of stack2 is "+ ": " + doubleArray.Size(false)); 

        System.out.println(doubleArray.isFull());
        doubleArray.print();
    }

}
