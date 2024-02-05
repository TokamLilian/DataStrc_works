package DuplicateEater;

import Stack.ArrayStack;

public class DuplicateEater {
    public static void main(String[] args) {
        
        String[] list1 = {"stack", "list", "list", "queue", "tree" };
        String[] list2 = {"tree", "stack", "stack", "tree" };
        String[] list3 = {"stack", "stck", "stack", "tree" };
        String[] list4 = {"stak", "stck", "sack", "queue" };
        
        ArrayStack<String> stack = createArray(list1);
        System.out.println(pairDestroyer(stack));

    }

    /*
     * Create a stack from the given array of strings and return it. 
     */
    public static ArrayStack<String> createArray(String[] list){
        ArrayStack<String> stack = new ArrayStack<>();
        
        for (int i=0; i<list.length; i++){            
            String element = list[i];
            stack.Push(element);
        }
        return stack;
    }


    /**
     * This method pushes all the elements in temp into "stack".
     */
    public static ArrayStack<String> emptyTemp(ArrayStack<String> stack, ArrayStack<String> temp){

        while (!temp.isEmpty()){
            stack.Push(temp.Pop());
        }

        return stack;
    }

    
    /**
     * This method compares elements at top of the "stack". If they are equal, it pops both  
     * from "stack". Otherwise, it pushes the top of "stack" to "temp" and pops it from "stack".
     */
    public static ArrayStack<String> destroyer(ArrayStack<String> reducedStack){
        ArrayStack<String> temp = new ArrayStack<>();
        String topWord = reducedStack.Pop();

        while (reducedStack.size() > 0) {
            String nextWord = reducedStack.Pop();
            
            if (topWord == nextWord){
                reducedStack = emptyTemp(reducedStack, temp);
                return reducedStack;

            }else{
                temp.Push(nextWord);
            }
        }
        
        reducedStack = emptyTemp(reducedStack, temp);

        return reducedStack;

    }


    /*
     * Checks if two similar words within the sequence meet and eliminates them
     */
    public static int pairDestroyer(ArrayStack<String> myStack){
        ArrayStack<String> temp = new ArrayStack<>();

        while (myStack.size() > 0){
                
            String topWord = myStack.Pop();
            myStack.Push(topWord);
            
            int originalStackSize = myStack.size();
            myStack = destroyer(myStack);

            if (myStack.size() == originalStackSize - 1)
            // at condition that topword is NOT a duplicate ie stack size reduced
                temp.Push(topWord); 
            
        }

        myStack = emptyTemp(myStack, temp);
        return myStack.size();

    }
}