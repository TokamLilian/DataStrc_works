package BinarySearchTree;

public interface BinaryTree {
    
    /*  Method to get the size of the binary tree */
    public int size();

    /*  Method to check if the binary tree is empty */
    public boolean isEmpty(); 

    /*  Method to insert a new node in the binary tree */
    public void insert(int key);

    /*  Method to get the maximum element of the binary tree */
    public void getMax();
    
    /* A recursive function to find a key in the binary tree */
    boolean search(int key);

    /*  Method to remove a node in the binary tree */
    public void remove(int key);
    
    /*  Method to insert a new node in the binary tree at a position*/
    //public void add(int key, int id); 
  
  
    /* A utility function to print the contents of the binary tree */
    //void print();  

}
