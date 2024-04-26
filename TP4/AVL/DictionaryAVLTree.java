package AVL;
class AVLNode {
    String word, meaning;
    int height;
    AVLNode left, right;

    AVLNode(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.height = 1;
        this.left = this.right = null;
    }
}



class DictionaryAVLTree {
    private AVLNode root;

    private int height(AVLNode N) {
        if (N == null) return 0;
        return N.height;
    }

    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 

    /**right rotate subtree rooted with root */
    AVLNode rightRotate(AVLNode root) {  
        AVLNode leftChild = root.left;  
        AVLNode leftRight = leftChild.right;  
  
        //rotation  
        leftChild.right = root;  
        root.left = leftRight;  
  
        // Update heights  
        root.height = max(height(root.left), height(root.right)) + 1;  
        leftChild.height = max(height(leftChild.left), height(leftChild.right)) + 1;  
  
        // return new root  
        return leftChild;  
    }  
  
    /**left rotate subtree rooted with root */
    AVLNode leftRotate(AVLNode root) {  
        AVLNode rightChild = root.right;  
        AVLNode rightLeft = rightChild.left;  
   
        rightChild.left = root;  
        root.right = rightLeft;  
 
        root.height = max(height(root.left), height(root.right)) + 1;  
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;  
  
        return rightChild;  
    }  

    /** Returns the difference between the heights of left and right children  */
    int getBalance(AVLNode N) {  
        if (N == null)  
            return 0;  
  
        return height(N.left) - height(N.right);  
    }  

    /** Insert a new word and it's meaning to the dictionary and maintain order */
    private AVLNode insert(AVLNode node, String word, String meaning) {
        // - normal insertion
        if(node == null) return (new AVLNode(word, meaning));

        if (word.compareTo(node.word) < 0) 
            node.left = insert(node.left, word, meaning);  
        else if (word.compareTo(node.word) > 0)
            node.right = insert(node.right, word, meaning);  
        else // Duplicate words not allowed  
            return node;  
  
        // - update the height of parent node
        node.height = 1 + max(height(node.left), height(node.right));  
  
        // - check if current node became unbalanced by getting balance factor of parent node */
        int balance = getBalance(node);  
  
        // in case node is unbalanced

        // Left Left Case  
        if (balance > 1 && word.compareTo(node.left.word) < 0)  
            return rightRotate(node);  
  
        // Right Right Case  
        if (balance < -1 && word.compareTo(node.right.word) > 0)  
            return leftRotate(node);  
  
        // Left Right Case  
        if (balance > 1 && word.compareTo(node.left.word) > 0){
            node.left = leftRotate(node.left);  
            return rightRotate(node);
        }

        // Right Left Case  
        if (balance < -1 && word.compareTo(node.right.word) < 0){  
            node.right = rightRotate(node.right);  
            return leftRotate(node);  
        }  
   
        /* return the unchanged node pointer */
        return node;
    }


    public void insert(String word, String meaning) {
        root = insert(root, word, meaning);
    }
    
    /** Return the bottom left element */
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /** Remove a word from the dictionary */
    private AVLNode delete(AVLNode root, String word) {
        if (root == null) {
            return root;
        }

        if (word.compareTo(root.word) < 0) {            // delete on the left side if wordToDelete is smaller than current word
            root.left = delete(root.left, word);
        } else if (word.compareTo(root.word) > 0) {
            root.right = delete(root.right, word);
        } else {
            if (root.left == null || root.right == null) {
                AVLNode temp = null;                    // to compare which child is inexistent
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;                        // update new root where parent has been deleted
                }
            } else {
                AVLNode temp = minValueNode(root.right);
                root.word = temp.word;
                root.right = delete(root.right, temp.word);
            }
        }

        if (root == null) {
            return root;                                // return if root has no child
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }
    

    public void delete(String word) {
        root = delete(root, word);
    }
    
    /** Look for the definition of a given word in the dictionary */
    public String search(AVLNode root, String word) {
        if (root == null) {
            return null;
        }
        
        if(root.word.equals(word)) {
            return root.meaning;
        }

        if (word.compareTo(root.word) < 0) {
            return search(root.left, word);
        } else {
            return search(root.right, word);
        }
    }
    
    public String search(String word) {
        return search(root, word);
    }


    private void inOrderTraversal(AVLNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("Word: " + node.word + ", Meaning: " + node.meaning);
            inOrderTraversal(node.right);
        }
    }


    public void displayDictionary() {
        inOrderTraversal(root);
    }
}

class Main{

    public static void main(String[] args) {
        DictionaryAVLTree dictionary = new DictionaryAVLTree();
    
        // Example insertions
        String[][] wordsAndMeanings = {
                {"Harmony", "The combination of simultaneously sounded musical notes to produce chords and chord progressions having a pleasing effect."},
                {"Ephemeral", "Lasting for a very short time."},
                {"Serendipity", "The occurrence and development of events by chance in a happy or beneficial way."},
                {"Quintessential", "Representing the most perfect or typical example of a quality or class."},
                {"Eloquence", "Fluent or persuasive speaking or writing."},
                {"Melancholy", "A feeling of pensive sadness, typically with no obvious cause."},
                {"Labyrinth", "A complicated irregular network of passages or paths in which it is difficult to find one's way; a maze."},
                {"Solitude", "The state or situation of being alone."}
            };
    
        // Insert each word and meaning into the dictionary
        for (String[] entry : wordsAndMeanings) {
            dictionary.insert(entry[0], entry[1]);
        }
    
        // Display the dictionary before deletion
        System.out.println("Dictionary content in alphabetical order before deletion:");
        dictionary.displayDictionary();
    
        // Deleting a word from the dictionary
        String wordToDelete = "Ephemeral";
        System.out.println("\nDeleting word: " + wordToDelete);
        dictionary.delete(wordToDelete);
    
        // Display the dictionary after deletion
        System.out.println("Dictionary content in alphabetical order after deletion:");
        dictionary.displayDictionary();
    
        // Searching for meanings
        String[] wordsToSearch = {"Harmony", "Eloquence", "Ephemeral"};
        System.out.println("\nSearching for words:");
        for (String word : wordsToSearch) {
            System.out.println("Meaning of '" + word + "': " + dictionary.search(word));
        }
    }
}

