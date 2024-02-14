package LinkedList;

public class LinkedList implements List<Integer> {   
    private Node tail; // tail of list  
    private Node head; // tale of list  
    
    /* Constructor to create an empty list */ 
    public LinkedList() {
        this.tail = new Node(0);
        this.head = new Node(0);
    }  


    /** Inserts element at a specific position of the list. */
    public void add(int index, int element) throws IndexOutOfBoundsException{ 
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();    
        
        Node node = new Node(element);             // Create a new node with the given data.
        Node currentNode = head;                   // Start from the first node in the list.
        int position = 0;                          // The position of the inserted node.

        if (index == 0){                           // If we want to insert at the beginning...
            node.setNext(head);                    // Set next pointer of new node to existing head.
            //head.setPrevious(node); 
            head = node;                           // And set head to be our new node.

        }else if (index == size() ){ addLast(element);        // Or if we want to append to the end...

        } else {                                   // Otherwise, find the correct position and insert there.
            while (position++ < index) {           // Move through the list until we reach the right position.
                while (position++ < index)             // Keep going until we reach the desired position.
                    currentNode = currentNode.getNext();// Go one step forward.

                node.setNext(currentNode.getNext());//Set next pointer of new node to what was previously after the target.
                //node.setPrevious(currentNode) ;     // Set previous pointer of new node to the found node's previous.

                // what was previously the next node.
                //currentNode.getNext().setPrevious(node);
                currentNode.setNext(node);      // Set next pointer of old node to new node.
            }
        } 
    }


    /** Removes the element at a specific position in the list. */
    public void remove(int index){
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        
        Node retval = head;                      // Save the value that will be returned
        Node previous = head;                    // Points to the node before the one we're looking for.
        int pos = 0;                             // Current position in the list.
        while (pos++ < index) {                  // Loop through the list until we find the right spot.
            previous = retval;                   // Move the "previous" pointer along.
            retval = retval.getNext();           // Move "retval" to point to the next node.
        }

        if (retval == tail) tail = previous;               // If the removed node was the last one, update the tail.
        else previous.setNext(retval.getNext());           // Cut the link between the two nodes.

        if (retval == head) head = retval.getNext();       // If the removed node was the first one, update the head.
        //else retval.getNext().setPrevious(previous);       // And also cut the other end.

        retval.setNext(null);   // Nullify pointers so they don't hold any garbage.
        //retval.setPrevious(null);  // Nullify pointers so they don't point to garbage.

    }


    /** Return the element at the given index
     */
    public int get(int index){
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index out of range: "+index+" Size:"+size());
        int position = 0;
        Node current = head;

        while (position++ < index)
            current = current.getNext();

        return (int) current.getData();
        
    }
    
    
    /** Return the index of the given element
     */
    public int getId(int element){
        Node gotNode = head;
        int count = 0;
    
        while (gotNode != null){
            if (gotNode.getData() == element){
                return count;
            }
            gotNode = gotNode.getNext();
            count ++;
        }
        throw new RuntimeException("Element not found");
        
    }

    // @Overide
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = head;

        while(currentNode != null){
            sb.append(currentNode.getData() + ", ");
            currentNode=currentNode.getNext();
        }

        if(size() > 0)sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1).append("]");
        else          sb.append("]");
        return sb.toString();
    }


    /**  Returns the number of elements in the list. */
    @Override
    public int size() {
        Node current = head;        
        int count = 0;  
        while (current != null) {             
            count++;                           
            current = current.getNext();
        }                            
        return count;  
    } 


    @Override
    public boolean isEmpty() {
        return (head == tail && head.getData() == 0);
    }


    @Override
    public int first() {
        return head.getData();
    }


    @Override
    public int last() {
        return tail.getData();
    }


    /** Add item at head (begining) of linkedlist.*/
    @Override
    public void addFirst(int value) {
        Node node =  new Node(value);
        if (head.getData() == 0) {                         // If the list is empty, make this node the head.
            head = node;                                   // Set it as the head for simplicity.
            tail = node;                                   // Also set the tail of the list to be the new node.
        }else{
            node.setNext(head);                            // Set next pointer of current node to point to previous head.
            head = node;                                   // Update the head of the List
        }
    }


    /** Add item at tail (end) of linkedlist (append).*/
    @Override
    public void addLast(int value) {
        Node node =  new Node(value);
        if (tail.getData() == 0) {                         // If the list is empty, make this node the tail.
            tail = node;                                   // Set the tail of the list to be the new node.
            head = node;                                   // Also set it as the head for simplicity.
            head.setNext(node);
        }else{
            tail.setNext(node);                            // Set next pointer of tail to point to new node.
            tail = node;                                   // Update the tail of the List
        }
    }


    @Override
    public void removeFirst() {
        Node temp = head.getNext();
        head.setNext(null);

        head = temp;
        
    }


    @Override
    public void removeLast() {
        int size = size();
        Node retval = head;                      // Save the value that will be returned
        Node previous = head;                    // Points to the node before the one we're looking for.
        int pos = 0;                             // Current position in the list.

        while (pos++ < size) {                   // Loop through the list until we find the right spot.
            previous = retval;                   // Move the "previous" pointer along.
            retval = retval.getNext();           // Move "retval" to point to the next node.
        }

        previous.setNext(null);
        tail = previous;                         // The removed node was the last one hence update the tail.
    }
    
    
    /** Return the n-th element from the end backwards */
    public int returnNLast(int nLast){
        int size = size();
        int temp = get(size-nLast);
        
        return temp;
    }
    
    
    /**
     * Returns true if the given element is in the list
     * @param value
     * @return true or false
     */
    public boolean checkInList(int value){
        Node gotNode = head;
    
        while (gotNode != null){
            if (gotNode.getData() == value) return true;
            gotNode = gotNode.getNext();
        }

        return false;
    }


    /** Recursive method to remove an element present in the list */
    public void removeValue(int value){
        int retIndex = getId(value);
        remove(retIndex);
    }


    /**
     * Returns the maximum element stored in the list by recursion
     * @return
     */
    public int maxValue(){
        int maxVal = 0;
        Node currNode = head;
        maxVal = currNode.getData();

        while (currNode.getNext() != null){
            currNode = currNode.getNext();
            int comparedValue = currNode.getData();

            if (comparedValue > maxVal) maxVal = comparedValue;
        }

        return maxVal;

    }


    /** Sort the list */
    public void insertionSort(){

    }
    

    /**
     * Recursively add a given element in an ordered list
     * @param value
     */
    public void addInOrder(int value){

    }
    
}