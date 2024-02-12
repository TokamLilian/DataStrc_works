package LinkedList;

public class LinkedList<Integer> implements List {   
    private Node tail; // tail of list  
    private Node head; // tale of list  
    
    /* Constructor to create an empty list */ 
    public LinkedList() {
        this.tail = new Node(0);
        this.head = new Node(0);
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
      
    /** Inserts element at a specific position of the list. */
    @Override
    public void add(int index, int element) throws IndexOutOfBoundsException{ 
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();    
        
        Node node = new Node(element);             // Create a new node with the given data.
        Node currentNode = head;                   // Start from the first node in the list.
        int position = 0;                          // The position of the inserted node.

        if (index == 0){                           // If we want to insert at the beginning...
            node.setNext(head);                    // Set next pointer of new node to existing head.
            //head.setPrevious(node); 
            head = node;                           // And set head to be our new node.

        }else if (index == size() ){ add(element);        // Or if we want to append to the end...

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

    /*
     * Add item at tail (end) of linkedlist (append).
     */
    @Override
    public void add(int element){
        Node node =  new Node(element);
        if (tail.getData() == 0) {                      // If the list is empty, make this node the tail.
            tail = node;                                   // Set the tail of the list to be the new node.
            head = node;                                   // Also set it as the head for simplicity.
            //tail.setPrevious(head);
            head.setNext(node);
        }else{
            //node.setPrevious(tail);                            // Point the new node's previous to the current tail.
            tail.setNext(node);                                // Set next pointer of tail to point to new node.

            tail = node;                                       // Update the tail of the List
        }
    }

    /** Removes the element at a specific position in the list. */
    @Override
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


    @Override
    /*
     * Return the element stored at the given index
     */
    public int get(int index){
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index out of range: "+index+" Size:"+size());
        int position = 0;
        Node current = head;

        while (position++ < index)
            current = current.getNext();

        return (int) current.getData();
        
    }
    
    
    @Override
    /*
     * Return the index of the given element
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

    @Override
    public String  toString(){
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

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int first() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
    }

    @Override
    public int last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
    }

    @Override
    public void addFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFirst'");
    }

    @Override
    public void removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public void addLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLast'");
    }

}