package LinkedList;

public class test {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        //testAddSorted(list, 15);
        //testRemoveAll(list, 12);
        testMax(list);
       
    }

    public static void testAddSorted(LinkedList list, int value){
        list.addLast(12);
        list.addLast(11);
        list.addLast(13);
        list.addLast(5);
        list.addLast(6);
        list.addInOrder(value);
    }
    
    public static void testRemoveAll(LinkedList list, int value){
        list.addLast(12);
        list.addLast(11);
        list.addLast(12);
        list.addLast(12);
        list.addLast(6);
        list.removeValue(value);

    }

    public static void testMax(LinkedList list){
        list.addLast(4);
        list.addLast(11);
        list.addLast(15);
        list.addLast(12);
        list.addLast(14);
        System.out.println(list.maxValue(5));

    }
    
}
