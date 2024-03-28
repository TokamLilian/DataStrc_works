package Tree;

public class Main {

     public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

      // Create
      //int[] myArray = {4, 2, 5, 9, 11, 3, 6, 13, 14, 1};
      int[] myArray = {4, 2, 5, 9, 11};
      bst.create(myArray, myArray.length, 12);

      // Size
      System.out.println("Size of the BST: " +bst.size());

      // Insert
      bst.insert(7);
      bst.insert(19);

      // getMax
      System.out.print("The maximum value in the tree is : " + bst.getMax()+ "\n");

      // Search
      boolean found = bst.search(10);
      if (found)
         System.out.println("Found !");
      else
         System.out.println("Not Found");

      // Remove
      bst.remove(9);
      bst.remove(50);

      // Update
      Node root = bst.getFirst();
      bst.updateBST(root);

      // areSame
      int[]  arr2 = {8, 7, 1};
      int[]  arr1 = {8, 7, 1};
      System.out.println(bst.areSameBST(arr2, arr1));
     }
}