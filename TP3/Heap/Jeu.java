package Heap;
import java.util.Iterator;

import Tree.BinarySearchTree;
import Tree.Node;

public class Jeu implements Iterable {

    private BinarySearchTree[] heapArray;           // Array of players (trees of cards)
    private int size;

    public Jeu (){
        size = 0;
        heapArray = new BinarySearchTree[10];       // 10 players initial maximum capacity

    }

    /* Ajouter un nouveau joueur */
    public void ajout(int value){
        BinarySearchTree newPlayer = new BinarySearchTree();
        Node card = new Node();
        card.setValue(value);
        newPlayer.add(card);
        insert(newPlayer);
    }

    public void insert(BinarySearchTree player){
        try {
            heapArray[size] = player;
            size++;
            
        } catch (Exception e) {
            throw new IllegalStateException("Maximum number of players reached");
        }
    }
    
    public void updateValue(BinarySearchTree player, int points){
        player.getFirst().setValue(points);                         // the number of points of a player is the value of it's root node
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
