package DuplicateEater;

import Stack.ArrayStack;

public class DuplicateEater {
    public static void main(String[] args) {
        
        String[] list1 = {"stack", "list", "list", "queue", "tree" };
        String[] list2 = {"tree", "stack", "stack", "tree" };
        ArrayStack<String> stack1 = createArray(list1);
        System.out.println(pairDestroyer(stack1));
        //createArray(list2);
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

        String topWord = null;

        int firstTop = 0;
        int secondTop = 0;
        //int topOfStack = myStack.size() - 1;

        while (firstTop < myStack.size()) {
            // Get the current word on top of the stack
            topWord = myStack.Pop();

            while (secondTop != myStack.size()) { //iterate on the elements of the stack

                // Push it back onto the stack so we can compare it to the next one
                // temp.Push(top);

                // Now get the "next" word
                String nextWord = myStack.Pop();

                // If they are a match, remove the duplicate
                if (topWord == nextWord) {
                    // maintain the removed the duplicate from the stack
                    if (temp.size() > 0) {
                        while (temp.size() != 0){//for (int i = 0; i < temp.size() - 1; i++){
                            myStack.Push(temp.Pop());
                        }
                    }
                    return myStack.size();
                } else {                        // Otherwise just push the original word
                    temp.Push(nextWord);      // Onto the temp stack
                }

                // Update our second-top variable for the next iteration
                //secondTop ++;
            }

            while (temp.size() != firstTop){
                myStack.Push(temp.Pop());
            }
            temp.Push(topWord);
            // Update our first-top variable for the next iteration
            firstTop ++;

        }
        return secondTop; //
    }

}
