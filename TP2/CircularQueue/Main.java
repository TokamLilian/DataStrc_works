public class Main {
    
    public static void main(String[] args) {
        CircularQueue<Integer> myQueue = new CircularQueue<Integer>();
        testQueueing(myQueue);
    }

    public static void testQueueing(CircularQueue<Integer> queue){
        queue.enqueue(3);
        queue.enqueue(7);
        queue.enqueue(1);
        queue.enqueue(12);
        queue.enqueue(4);
        queue.enqueue(7);
        queue.enqueue(9);

        //queue.remove(9, 5);
        //queue.reverse();
        //queue.checkInQueue(9);
        
        System.out.println("Size is: " + queue.size());
        queue.print();
        System.out.println("\nFront of queue: " + queue.front());
        System.out.println("Rear of queue: " + queue.rear());
        
        System.out.println("\nDequed value: " + queue.dequeue());
        System.out.println("Dequed value: " + queue.dequeue());
        queue.enqueue(11);
        System.out.println("New front of queue: " + queue.front());
        System.out.println("New rear of queue: " + queue.rear());

        queue.print();
    }

}
