package Heap;
import java.util.Iterator;

import Tree.BinarySearchTree;

public class Jeu implements Iterable {

    public BinarySearchTree[] heapArray;           // Array of players (trees of cards)
    private int size;
    private int maxPlayers = 15;                   // Maximum number of players allowed in the game

    public Jeu (){
        size = 0;
        heapArray = new BinarySearchTree[maxPlayers];       // 10 players initial maximum capacity

    }

    public int getSize(){
        return size;
    }

    /* Ajouter un nouveau joueur */
    public void ajout(int value){
        BinarySearchTree newPlayer = new BinarySearchTree();
        newPlayer.point = value;                     // Set the player's point to "value"
        insert(newPlayer);
    }

    /* Insert the tree of a new player to the game */
    public void insert(BinarySearchTree player){
        try {
            heapArray[size] = player;
            size++;
            
        } catch (Exception e) {
            throw new IllegalStateException("Maximum number of players reached");
        }
    }
    
    /* Update the number of points of a player */
    public void updateValue(BinarySearchTree player, int points){
        player.point = points;
    }

    @Override
    public Iterator iterator() {
        return new heapIterator<BinarySearchTree>();
    }

    private class heapIterator<E> implements Iterator<E>{
        private int position = -1;

        @Override
        public boolean hasNext() {
            return position+1 < size;
        }

        @Override
        public E next() {
            position++;
            return (E) heapArray[position];
        }
       
    }
}
