package Heap;

import Tree.BinarySearchTree;

public class Main {
    private static Jeu game1 = new Jeu();
    
    private static int manches = 2;
    private static int maxCards = 6;
    private static int maxPoints = 15;

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        int[] array1 = {2, 5, 3};
        int[] array2 = {6, 11, 1};

        try {
            createPlayer(array1);
            createPlayer(array2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tour(0);
        play();

    }

    private static void createPlayer(int[] playerArray) throws Exception{
        int len = playerArray.length;
        if (len>maxCards) throw new Exception("Player can not more cards than prescribed ");

        BinarySearchTree player = new BinarySearchTree();
        player.create(playerArray, len, maxCards);
        player.BuilHeap();
        try {
            game1.insert(player);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static int shuffleDice(){
        int[] values = {2, 1, 0, 0, -1, -2};
        int index = (int) (Math.random() * 6);
        return values[index];
    }

    public void fusion(Jeu j2){
        //Jeu [] tournoi;
        // for all BST player in j2, add the player in game1's heaparray

    }

    /* Execute the game  */
    public static void play(){
        for (int round = 0; round < manches; round ++ ){
            for (Object tree : game1){
                BinarySearchTree player = (BinarySearchTree) tree;
                int shuffled = shuffleDice();
                player.UpdateValues(shuffled);
                if (player.getPoints() >= maxPoints) return;
                player.deleteTop();                     // remove the first element of the tree (played card)
                player.BuilHeap();                      // rebuild the heap property
            } 
            tour(round);
        }
    }

    /* Build a heap from the highest priorities of each player
     * @params round : the roumd determines which card to look at
    */
    private static void tour(int round){
        int size = game1.getSize();
        int startParent = (size/2)-1;

        while (startParent >= 0){

            int index = startParent;
            BinarySearchTree[] players = game1.heapArray;
            BinarySearchTree temp = players[index];
            int childIndex = 2*index + 1;

            while (childIndex < size){
                // If we have a right child and it's higher than our left child then swap with it
                if ((players[childIndex+1] != null && players[childIndex].getFirst().value > players[childIndex+1].getFirst().value)) {
                    childIndex++;// Move to the right child
                }

                // If the left child is higher or we don't have a right child then we're done with this parent
                // Swap temp with the smaller of its two children
                players[index] = players[childIndex];
                index = childIndex;
                childIndex = 2*index + 1;
            }
            players[index] = temp; // Set the original value in the correct place
            startParent--;
        }

    }

}
