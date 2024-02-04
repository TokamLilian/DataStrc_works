package DuplicateEater;

import Stack.ArrayStack;

public class DuplicateEater {
    public static void main(String[] args) {
        
        String[] list1 = {"stack", "list", "list", "queue", "tree" };
        String[] list2 = {"tree", "stack", "stack", "tree" };
        String[] list3 = {"stack", "stck", "stack", "tree" };
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

    public static int pairDestroyer2(ArrayStack<String> myStack){
        ArrayStack<String> temp = new ArrayStack<>();

        String topWord = myStack.Pop();
        boolean duplicateFound = false;
        int iteration = 0;
        int originalStackSize = myStack.size();

        while (!duplicateFound){

            if (!myStack.isEmpty()){

                String nextWord = myStack.Pop();
                if (topWord == nextWord){

                    if (myStack.size() == 0) duplicateFound = true;

                    else{
                        return pairDestroyer2(myStack);
                    }
                    
                }else{
                    temp.Push(nextWord);
                }

            }else {
                // empty temp except the previous topWords from myStack
                while (temp.size() != iteration){
                    myStack.Push(temp.Pop());
                }

                temp.Push(topWord);
                iteration ++;
                
                if (temp.size() == originalStackSize) duplicateFound = true;
                if (myStack.size() > 0) return pairDestroyer2(myStack);
            }
        }
        return myStack.size();

    }

}
