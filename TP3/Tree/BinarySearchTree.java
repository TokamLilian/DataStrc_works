package Tree;

public class BinarySearchTree implements BinaryTree {

    private Node[] BSTarray;
    private int size;

    public BinarySearchTree() {
        BSTarray = null;
        size = 0;
        
    }

    /* 
     * Create a Binary search tree from an array 
     * @params  ```arr```: the array from which the binary tree is created, ```n```: the size of the array, ```max``` the maximum size of the binary tree
     * @ Author Tokam Lilian
     */
    public void create (int [] arr, int n, int max){
        if (n > max || n<0 || max<0) throw new IllegalStateException("out of bounds");
        
        BSTarray = new Node[max];
        for (int element : arr){
            insert(element);
        }
        
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void insert(int key) {
        int max = BSTarray.length;
        if (size >= max) throw new IllegalStateException("Array is full");

        Node newNode = new Node();
        newNode.setPriority(key);
        BSTarray[size] = newNode;

        size++;
    }

    @Override
    public int getMax() {
        if (size == 0) throw new IllegalStateException("empty tree");

        int max = BSTarray[0].getPriority();
        for (Node node : BSTarray){
            if (node == null) break;
            int key = node.getPriority();
            if (key >= max) max = key;
        }

        return max;
    }

    @Override
    public boolean search(int key) {
        for (Node node : BSTarray){
            if (node == null) break;
            int value = node.getPriority();
            if (value == key) return true;
        }
        return false;
    }

    @Override
    public void remove(int key) {
        if (search(key)){
            
            Node [] temp = new Node[BSTarray.length];
            int i = 0;
            for (Node node: BSTarray){
                if (node == null) break;
                int value = node.getPriority();
                if (value != key){
                    temp[i] = node;
                    i++;
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
            int valueToBeChanged = nodeToBeChanged.getPriority();

            for (int comparedValue : temp){
                if(comparedValue > nodeToBeChanged.getPriority()) valueToBeChanged += comparedValue;
            }
            nodeToBeChanged.setPriority(valueToBeChanged);
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

    public int[] copyArray(){
        int[] arr = new int[size];
        int i = 0;
        for (Node node : BSTarray){
            if (node == null) break;
            arr[i] = node.getPriority();
            i++;
        }

        return arr;

    }
    
}
