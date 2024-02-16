package CircularQueue;

public class Main {
    
    public static void main(String[] args) {
        CircularQueue<Integer> myQueue = new CircularQueue<>();
        testQueueing(myQueue);
    }

    public static void testQueueing(CircularQueue<Integer> queue){
        queue.enqueue(0);
        queue.enqueue(0);
        queue.enqueue(0);
        queue.enqueue(0);
        queue.enqueue(0);

        queue.print();
    }

}
