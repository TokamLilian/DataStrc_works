package DuplicateEater;

import Stack.ArrayStack;

public class DuplicateEater {
    public static void main(String[] args) {
        
        String[] list1 = {"stack", "list", "list", "queue", "tree" };
        String[] list2 = {"tree", "stack", "stack", "tree" };
        String[] list3 = {"stack", "stck", "stack", "tree" };
        String[] list4 = {"stak", "stck", "sack", "queue" };
        //ArrayStack<String> stack1 = createArray(list1);
        //System.out.println(pairDestroyer(stack1));
        ArrayStack<String> stack2 = createArray(list1);
        System.out.println(pairDestroyer2(stack2));

    }

    public static ArrayStack<String> createArray(String[] list){
        ArrayStack<String> stack = new ArrayStack<>();
        
        for (int i=0; i<list.length; i++){            
            String element = list[i];
            stack.Push(element);
        }
        return stack;
    }

    /*
     * Checks if two similar words within the sequence meet and eliminates them
     */
    public static int pairDestroyer(ArrayStack<String> myStack){
        ArrayStack<String> temp = new ArrayStack<>();

        boolean duplicatesAllFound = false;
        String topWord = null;

        int firstTop = 0;
        int secondTop = 0;

        while (firstTop < myStack.size()) {
            // Get the current word on top of the stack
            topWord = myStack.Pop();

            while (secondTop != myStack.size()) { //iterate on the elements of the stack
                
                // Now get the "next" word
                String nextWord = myStack.Pop();

                // If they are a match, remove the duplicate
                if (topWord == nextWord) {
                    if (myStack.size() < 2 && temp.size() < 2) duplicatesAllFound = true;
                    // maintain the removed the duplicate from the stack
                    if (temp.size() > 0 && myStack.size() > 0) { //!
                        while (temp.size() != 0){
                            myStack.Push(temp.Pop());
                        }
                    }
                    if (myStack.size() < 2 || duplicatesAllFound == true) 
                        return myStack.size();
                    else break;
                } else {                        // Otherwise just push the original word
                    temp.Push(nextWord);      // Onto the temp stack
                }

                // Update our second-top variable for the next iteration
                //secondTop ++;
            }
            // empty temp except the previous topWord from myStack
            while (temp.size() != firstTop){
                myStack.Push(temp.Pop());
            }
            temp.Push(topWord);
            // Update our first-top variable for the next iteration
            firstTop ++;

        }
        return secondTop; //
    }

    public static ArrayStack<String> destroyer(ArrayStack<String> reducedStack){
        ArrayStack<String> temp = new ArrayStack<>();
        String topWord = reducedStack.Pop();

        while (reducedStack.size() > 0) {
            String nextWord = reducedStack.Pop();
            
            if (topWord == nextWord){
                while (!temp.isEmpty()){
                    reducedStack.Push(temp.Pop());
                }
                return reducedStack;
            }else{
                temp.Push(nextWord);
            }
        }

        if (temp.size() > 0){
            while (!temp.isEmpty()){
                reducedStack.Push(temp.Pop());
            }

            return reducedStack;

        } else{ 
            return reducedStack;
        }
    }


    public static int pairDestroyer2(ArrayStack<String> myStack){
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

        if (!temp.isEmpty()){
            while (!temp.isEmpty()){
                myStack.Push(temp.Pop());
            }
        }
        return myStack.size();

    }
}