package CircularQueue;
import LinkedList.Node;


public class CircularQueue<Integer> {
    private Node first, last;
    private static int [] queue;
    private static final int maxSize = 100;

    public static void CicularQueue() {
        queue = new int[maxSize];

    }
     
    
    /** Returns the size of the queue */
    public int size() {
        int count = 0;
        Node current = first;
        
        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }
    

    /** returns true if the queue is empty */
    boolean isEmpty() {
        return first == null;
    }


    /** Adds an element at the end of the queue */
    public void enqueue(int data) {
        if (isEmpty()) {
            first = new Node(data);
            last = first;
        } else {
            Node temp = new Node(data);
            last.setNext(temp);
            last = temp;
        }
    }
    
    
    /** Removes and element from the begining of the queue */
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Can't dequeue from an empty queue.");
        }
        
        int data = first.getData();
        first = first.getNext();
        if (first == null) {
            last = null; // The queue is now empty
        }
        return data;
    }


    /** Returns the first element in the queue */
    public int front(){
        return queue[0];
    }


    /** Returns the last queued element */
    public int rear(){
        return queue[size()-1];
    }


    /** Print the values in the queue */
    public void print() {
        System.out.print("Queue: ");
        Node current = first;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
