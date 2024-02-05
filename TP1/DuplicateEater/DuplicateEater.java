package DuplicateEater;

import Stack.ArrayStack;

public class DuplicateEater {

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