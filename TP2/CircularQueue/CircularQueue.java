package CircularQueue;

public class CircularQueue<Integer> {
    
    private int [] queue;
    private final int maxSize = 10;
    private int front;
    private int rear;

    public CircularQueue() {
        queue = new int[maxSize];
        front = maxSize/2;
        rear = maxSize/2;

    }
     
    
    /** Returns the size of the queue */
    public int size() {
        return (rear - front +  maxSize) %maxSize;
    }
    

    /** returns true if the queue is empty */
    boolean isEmpty() {
        return (rear == front);
    }


    /** Adds an element at the end of the queue */
    public void enqueue(int data) {
        if (size() == maxSize - 1) throw new IllegalStateException("Queue is full");

       queue[rear] = data;
       rear = (rear + 1) %maxSize;
    }
    
    
    /** Removes and element from the begining of the queue */
    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Can't dequeue from an empty queue.");
        
        int data = queue[front];
        queue[front] = 0;                   // for garbage collection
        front = (front + 1) %maxSize;

        return data;
    }


    /** Returns the first element in the queue */
    public int front(){
        return queue[front];
    }


    /** Returns the last queued element */
    public int rear(){
        return queue[rear-1];
    }


    /** Print the values in the queue */
    public void print() {
        System.out.print("Queue: ");
        int index = front;
        int current = queue[index];

        while (current != 0) {
            System.out.print(current + " ");
            index = (index + 1) %maxSize;
            current = queue[+index];
        }
        System.out.println();
    }


    public void reverse(){

    }


    public boolean checkInQueue(int value){
        return true;
    }


    public void remove(int value){

    }


}
