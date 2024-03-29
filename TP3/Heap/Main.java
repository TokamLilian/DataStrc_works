package Heap;

import Tree.BinarySearchTree;

public class Main {
    private static Jeu game1 = new Jeu();
    
    private static int manches = 30;
    private static int maxCards = 20;
    private static int maxPoints = 10;

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        int[] array1 = {2, 6, 1, 7};
        int[] array2 = {5, 11, 3, 2};

        try {
            createPlayer(array1);
            createPlayer(array2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tour(0);

        try {
            play();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

    }

    private static void createPlayer(int[] playerArray) throws Exception{
        int len = playerArray.length;
        if (len>maxCards) throw new Exception("Player can not more cards than prescribed ");

        BinarySearchTree player = new BinarySearchTree();
        player.create(playerArray, len, maxCards);
        player.BuilHeap();
        try {
            System.out.println(player);
            game1.insert(player);
            
        } catch (Exception e) {
            throw e;
        }
    }

    public static int shuffleDice(){
        int[] values = {2, 1, 0, 0, -1, -2};
        int index = (int) (Math.random() * 6);
        return values[index];
    }

    public void fusion(Jeu j2){

        for (Object tree : j2){
            BinarySearchTree player = (BinarySearchTree) tree;
            try {
                game1.insert(player);
                
            } catch (Exception e) {
                System.err.println(e.getMessage() + " in the insertion of new player");
            }
            tour(0);                     // Build a new heap from new added players
        }

    }

    /* Execute the game  */
    public static void play() throws Exception{
        for (int round = 0; round < manches; round ++ ){
            for (Object tree : game1){
                BinarySearchTree player = (BinarySearchTree) tree;
                if(player.size() <= 0 ) throw new Exception("Player has no more card !");
                int shuffled = shuffleDice();
                player.UpdateValues(shuffled);
                if (player.getPoints() >= maxPoints) throw new Exception("Maximum points reached by " + player +" with " + player.point + " after " + (round+1) + " rounds");
                player.deleteTop();                     // remove the first element of the tree (played card)
                player.BuilHeap();                      // rebuild the heap property
            } 
            tour(round);
        }
    }

    public static void heapify(int index){
        int largest = index;        // Initialize largest as root
        int l = 2*index + 1;        // Left child of root
        int r = 2*index + 2;        // right child of root
        int size = game1.getSize();

        BinarySearchTree[] players = game1.heapArray;
        if (players[l] == null || players[r] == null) return;
        // If left child is larger than root
        if (l < size && players[l].getFirst().priority > players[largest].getFirst().priority)
            largest = l;
 
        // If right child is larger than largest so far
        if (r < size && players[r].getFirst().priority > players[largest].getFirst().priority)
            largest = r;
 
        // If largest is not root
        if (largest != index) {
            // swap at position  'largest' with index
            BinarySearchTree temp = players[index];
            players[index] = players[largest];
            players[largest] = temp;
            
 
            // Recursively heapify the affected sub-tree
            heapify(largest);
        }
    }

    /* Build a heap from the highest priorities of each player
     * @params round : the roumd determines which card to look at
    */
    private static void tour(int round){
        int startIdx = (game1.getSize() / 2) - 1;
 
        // traverse from last non-leaf node to heapify each node
        for (int index = startIdx; index >= 0; index--) {
            heapify(index);
        }

    }

}
