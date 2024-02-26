/*
 * Node Object of Linked List
 */
public class Node {
    private int data;
    private Node nextNode;
    //private Node previousNode;
    
    // Constructor to create a new node
    public Node(int d) {  
        data = d;   
        nextNode = null;  
        //previousNode = null;  

    }

    public int getData(){
        return this.data;
    }

    public Node getNext(){
        return nextNode; 
    }

    public void setNext(Node n){
        this.nextNode = n;  
    }


    // public void setPrevious(Node m){
    //     this.previousNode = m;  
    // }

}
