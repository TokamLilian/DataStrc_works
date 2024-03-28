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
        int index = (int) Math.random();
        return values[index];
    }

    public void fusion(Jeu j2){
        //Jeu [] tournoi;
        // for all BST player in j2, add the player in game1's heaparray

    }

    public static void play(){
        for (int round = 0; round < manches; round ++ ){
            for (Object tree : game1){
                BinarySearchTree player = (BinarySearchTree) tree;
                //      compare the priority of each player of game1 to select who plays first
                int shuffled = shuffleDice();
                player.UpdateValues(shuffled);
                player.deleteTop();                     // remove the first element of the tree (played card)
            } 
            tour(round);
        }

    }

    /* Build a heap from the highest priorities of each player
     * @params round : the roumd determines which card to look at
    */
    private static void tour(int round){
        

    }
}
