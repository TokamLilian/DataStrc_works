package CircularQueue;

public class CircularQueue<Integer> {
    
    private int [] queue;
    private final int maxSize = 100;
    private int front;
    private int rear;

    public CircularQueue() {
        queue = new int[maxSize];
        front = maxSize/2;
        rear = maxSize/2;

    }
     

    /** Returns the element at a given position of the queue */
    public int get(int index) throws IndexOutOfBoundsException{
        if (index < front && index >= rear) throw new IndexOutOfBoundsException("Index out of range");
        return queue[index];

    }


    public int getMaxSize(){
        return maxSize;
    }


    /**
     * Sets the element at the index on the grid to a new value in the queue
     * @param neighboor
     * @param i
     */
    public void set(int neighboor, int i) {
        queue[neighboor] = i;
    }


    public int getFrontIndex(){
        return front;
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

        while (index >= front && index < rear) {
            System.out.print(current + " ");
            index = (index + 1) %maxSize;
            current = queue[+index];
        }
        System.out.println();
    }


    /** Reverse the order of the Circular Queue */
    public void reverse(){
        int temp;
        int newFront = front;
        int newRear = rear-1;
        int size = size();

        for (int i=0; i < size/2; i++){
            temp = queue[newFront];
            queue[newFront] = queue[newRear];
            queue[newRear] = temp;

            newFront = (newFront + 1) %maxSize;
            newRear = (newRear - 1 + maxSize) %maxSize;
        }

    }

    /**
     * Returns true if the given value is present in the queue
     * @param value
     * @return : True or False
     */
    public boolean checkInQueue(int value){
        int index = front;
        while (index >= front || index < rear){
            if (queue[index] == value) return true;
            index = (index + 1) %maxSize;
        }
        return false;
    }

    /**
     * Removes the first occurance of a given value
     * @param value : The integer whose first occurance will be removed
     * @param index : The index from which to start from (can be front or rear)
     */
    public void remove(int value, int index){
        if(!checkInQueue(value)) return;        //If it's not there, do nothing
        int current = queue[index];
        
        if (current == value){    
            while (index >= front || index < rear) {
                queue[index] = queue[(index - 1 + maxSize)%maxSize];   //Move everything one step forward
                index = (index - 1 + maxSize)%maxSize;                                                
            }

            front = (front + 1) %maxSize;                                 //Increase the front
            return;
        }
        remove(value, (index +1) %maxSize);
    }

}
