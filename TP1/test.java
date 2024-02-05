// test without autograder
import Stack.ArrayStack;
import DoubleStack.ArrayDoubleStack;
import SpecialStack.ArraySpecialStack;

public class test {

    public static void main(String[] args) {
        // ArrayStack_Test();
        // ArrayDoubleStack_Test();
        ArraySpecialStack_Test();
        }
    

    public static void ArrayStack_Test () {
        System.out.println("Running tests on ArrayStack:");
        ArrayStack<Integer> newSimpleArray = new ArrayStack<>();
        insertAndPopS(newSimpleArray);
        topAndSizeS(newSimpleArray);
    }
    
    public static void insertAndPopS (ArrayStack<Integer> stack) {
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

    public static void topAndSizeS(ArrayStack<Integer> stack) {
        System.out.println("\n");
        System.out.println("Size of stack is " + stack.size());
        
        try{
            System.out.println("Top element of stack is " + stack.Top());
        }catch  (Exception e){
            System.out.println(e.getMessage());
        }
        
    }


    public static void ArrayDoubleStack_Test () {
        System.out.println("\n"+ "Running tests on ArrayDoubleStack:");
        ArrayDoubleStack<Double> newDoubleArray = new ArrayDoubleStack<>();
        InsertAndPopDS(newDoubleArray);
        TopAndSizeDS(newDoubleArray);
    }
    
    public static void InsertAndPopDS(ArrayDoubleStack<Double> doubleArray){
        
        doubleArray.Push(true, 5.00); 
        doubleArray.Push(false,(double) 10); 
        doubleArray.Push(false, (double)15); 
        doubleArray.Push(true, (double)11); 
        doubleArray.Push(false, (double)7); 
        System.out.println("Popped element from stack1 is "+ ": " + doubleArray.Pop(true)); 
        doubleArray.Push(false, (double)40); 
        System.out.println("Popped element from stack2 is " + ": " + doubleArray.Pop(false)); 
    }
    
    public static void TopAndSizeDS(ArrayDoubleStack<Double> doubleArray){
        System.out.println("Top element from stack1 is "+ ": " + doubleArray.Top(true)); 
        System.out.println("Top element from stack2 is "+ ": " + doubleArray.Top(false)); 

        System.out.println("Size of stack1 is "+ ": " + doubleArray.Size(true)); 
        System.out.println("Size of stack2 is "+ ": " + doubleArray.Size(false)); 

        System.out.println(doubleArray.isFull());
        doubleArray.print();
    }

    
    public static void ArraySpecialStack_Test () {
        pushAndPopSS();
    }

    public static void pushAndPopSS() {
        ArraySpecialStack<Integer> stack = new ArraySpecialStack<>();
        
        stack.Push(3);
        stack.Push(5);
        stack.Push(2);
        stack.Push(7);

        try{
            System.out.println("Maximum element: " + stack.getMax());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        stack.Pop();
        try{
            System.out.println("Maximum element after pop: " + stack.getMax());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }    

        stack.Pop();
        try{
            System.out.println("Maximum element after second pop: " + stack.getMax());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        stack.Pop();
        try{
            System.out.println("Maximum element after third pop: " + stack.getMax());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
