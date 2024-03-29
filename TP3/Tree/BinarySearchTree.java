package Tree;

public class BinarySearchTree {

    private Node[] BSTarray;
    private int size;
    public int point;

    public BinarySearchTree() {
        BSTarray = null;
        size = 0;
        point = 0;
        
    }

    public static class Node{
        public int value;
        public int priority;
        public Node leftChild;
    
        public Node(){
            value = 0;
            priority = 0;
        }
    }

    /* 
     * Create a Binary search tree from an array 
     * @params  ```arr```: the array from which the binary tree is created, 
     * ```n```: the size of the array,
     *  ```max``` the maximum size of the binary tree
     * @ Author Tokam Lilian
     */
    public void create (int [] arr, int n, int max){
        if (n > max || n<0 || max<0) throw new IllegalStateException("out of bounds");
        
        BSTarray = new Node[max];
        for (int element : arr){
            insert(element);
        }
        
    }
    
    /* Return size of tree */
    public int size() {
        return size;
    }
    
    /* return true if tree is empty */
    public boolean isEmpty() {
        return size == 0;
    }
     
    /* Insert node in the BST*/
    public void insert(int key) {
        int max = BSTarray.length;
        if (size >= max) throw new IllegalStateException("Array is full");

        Node newNode = new Node();
        newNode.priority = key;
        
        if (size > 0) BSTarray[size - 1].leftChild = newNode;                 // Add reference of new node to the previous node
        BSTarray[size] = newNode;

        size++;
    }

    /* Return the maximum element of the tree */
    public int getMax() {
        if (size == 0) throw new IllegalStateException("empty tree");

        int max = BSTarray[0].priority;
        for (Node node : BSTarray){
            if (node == null) break;
            int key = node.priority;
            if (key >= max) max = key;
        }

        return max;
    }

    /* Search for a specific key in the tree */
    public boolean search(int key) {
        for (Node node : BSTarray){
            if (node == null) break;
            int value = node.priority;
            if (value == key) return true;
        }
        return false;
    }

    /* remove the node containing the given key */
    public void remove(int key) {
        if (search(key)){
            
            Node [] temp = new Node[BSTarray.length];
            int i = 0;
            for (Node node: BSTarray){
                if (node == null) break;
                int value = node.priority;
                node.leftChild = null; 
                if (value != key){
                    if (i>0) temp[i-1].leftChild = node;                           // add reference of next node
                    temp[i] = node;
                    i++;
                }else{
                }
            }
            
            BSTarray = temp;
            size--;
        }
    }
    
    /* Edit the binary search tree such that each node's key contains the sum of all it's superior keys */
    public void updateBST(Node root){
        int [] temp = this.copyArray();
        for (Node nodeToBeChanged : BSTarray){
            if (nodeToBeChanged == null) break;
            int valueToBeChanged = nodeToBeChanged.priority;

            for (int comparedValue : temp){
                if(comparedValue > nodeToBeChanged.priority) valueToBeChanged += comparedValue;
            }
            nodeToBeChanged.priority = valueToBeChanged;
        }
    }

    /* checks if the two arrays containing keys for a binary search tree define the same binary search tree */
    public boolean areSameBST(int [] array1, int [] array2){
        if (array1.length != array2.length) return false;

        int i = 0;
        for (int key : array1){
            if (key != array2[i]) return false;
            i++;
        }
        return true;
    }

    public Node getFirst(){
        return BSTarray[0];
    }

    public int getPoints(){
        return point;
    }

    private int[] copyArray(){
        int[] arr = new int[size];
        int i = 0;
        for (Node node : BSTarray){
            if (node == null) break;
            arr[i] = node.priority;
            i++;
        }

        return arr;

    }

    public void deleteTop(){
        BSTarray[0] = BSTarray[size-1];
        BSTarray[0].leftChild = BSTarray[1];
        BSTarray[size-1] = null;
        size--;
    }

    /* Add a new node to the tree */
    public void add(Node newNode){
        int max = BSTarray.length;
        if (size >= max) throw new IllegalStateException("Array is full");

        BSTarray[size] = newNode;
        size++;
    }

    public void UpdateValues(int diceValue){
        point += diceValue;
    }
    

    public void BuilHeap(){
        
        int startParent = (size/2)-1;
        while (startParent >= 0){

            int index = startParent;
            Node temp = BSTarray[index];
            int childIndex = 2*index + 1;

            while (childIndex < size){
                // If we have a right child and it's higher than our left child then swap with it
                if ((BSTarray[childIndex+1] != null && BSTarray[childIndex].value > BSTarray[childIndex+1].value)) {
                    childIndex++;// Move to the right child
                }

                // If the left child is higher or we don't have a right child then we're done with this parent
                // Swap temp with the smaller of its two children
                BSTarray[index] = BSTarray[childIndex];
                index = childIndex;
                childIndex = 2*index + 1;
            }
            BSTarray[index] = temp; // Set the original value in the correct place
            startParent--;
        }
    }
}
