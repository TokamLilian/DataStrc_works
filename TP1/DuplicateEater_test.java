import Stack.ArrayStack;
import DuplicateEater.DuplicateEater;

public class DuplicateEater_test {
    public static void main(String[] args) {
        
        String[] list1 = {"stack", "list", "list", "queue", "tree" };
        String[] list2 = {"tree", "stack", "stack", "tree" };
        String[] list3 = {"stack", "stck", "stack", "tree" };
        String[] list4 = {"stak", "stck", "sack", "queue" };
        
        ArrayStack<String> stack = createArray(list1);
        System.out.println(DuplicateEater.pairDestroyer(stack));

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
}
