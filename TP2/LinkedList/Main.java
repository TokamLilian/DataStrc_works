package LinkedList;

public class Main {
    public static void main(String[] args) {
        //addRemoveTest();
        getTest();
    }

    public static void printError(Exception e){
        System.out.println("Caught Exception: " + e.getMessage() );
    }

    public static void addRemoveTest(){
        LinkedList<Integer> myList = new LinkedList<>();
    
        //Adding elements to the list.
        myList.add(12);
        myList.add(7);
        myList.add(2);
        myList.add(17);
        myList.add(1);
        //System.out.println("Original List: " + myList);
        System.out.println("The size is : "  + myList.size());
        System.out.println("Original Linked List : "  + myList.toString());


        //Removing element from the list using index.
        int removeIndex = 3;
        myList.remove(removeIndex);
        System.out.println("\nAfter removing element at Index " + removeIndex + ": " + myList.toString());
        
        int addIndex = 3;
        myList.add(addIndex, 100);
        System.out.println("\nElement added at Index " + addIndex + ": " + myList.toString());
        
    }

    public static void getTest(){
        LinkedList<Integer> stringList = new LinkedList<>();

        stringList.add(21);
        stringList.add(8);
        stringList.add(1, 9);

        try{
            int requiredItem = stringList.get(10);
            System.out.println("Retrieved Item: " + requiredItem);
        }catch (Exception e){
            printError(e);
        }
            
        try{
            int itemIndex = stringList.get(21);
            System.out.println("Retrieved index: " + itemIndex);

        }catch (Exception e){
            printError(e);
        }
    }
}
