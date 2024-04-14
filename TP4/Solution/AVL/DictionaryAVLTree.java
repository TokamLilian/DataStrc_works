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

    AVLNode rightRotate(AVLNode y)  
    {  
        AVLNode x = y.left;  
        AVLNode T2 = x.right;  
  
        // Perform rotation  
        x.right = y;  
        y.left = T2;  
  
        // Update heights  
        y.height = max(height(y.left), height(y.right)) + 1;  
        x.height = max(height(x.left), height(x.right)) + 1;  
  
        // Return new root  
        return x;  
    }  
  
    // A utility function to left 
    // rotate subtree rooted with x  
    // See the diagram given above.  
    AVLNode leftRotate(AVLNode x)  
    {  
        AVLNode y = x.right;  
        AVLNode T2 = y.left;  
  
        // Perform rotation  
        y.left = x;  
        x.right = T2;  
  
        // Update heights  
        x.height = max(height(x.left), height(x.right)) + 1;  
        y.height = max(height(y.left), height(y.right)) + 1;  
  
        // Return new root  
        return y;  
    }  

    int getBalance(AVLNode N) 
    {  
        if (N == null)  
            return 0;  
  
        return height(N.left) - height(N.right);  
    }  

    /* Insert a new word and it's meaning to the dictionary and maintain order */
    private AVLNode insert(AVLNode node, String word, String meaning) {
        /* 1. Perform the normal BST insertion */
        if(node == null) return (new AVLNode(word, meaning));

        if (word.compareTo(node.word) < 0) 
            node.left = insert(node.left, word, meaning);  
        else if (word.compareTo(node.word) > 0)
            node.right = insert(node.right, word, meaning);  
        else // Duplicate words not allowed  
            return node;  
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));  
  
        /* 3. Get the balance factor of this ancestor node to check whether this node became unbalanced */
        int balance = getBalance(node);  
  
        // If this node becomes unbalanced

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
   
        /* return the (unchanged) node pointer */
        return node;
    }


    public void insert(String word, String meaning) {
        root = insert(root, word, meaning);
    }
    
    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /* Remove a word from the dictionary */
    private AVLNode delete(AVLNode root, String word) {
        if (root == null) {
            return root;
        }

        if (word.compareTo(root.word) < 0) {
            root.left = delete(root.left, word);
        } else if (word.compareTo(root.word) > 0) {
            root.right = delete(root.right, word);
        } else {
            if (root.left == null || root.right == null) {
                AVLNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                AVLNode temp = minValueNode(root.right);
                root.word = temp.word;
                root.right = delete(root.right, temp.word);
            }
        }

        if (root == null) {
            return root;
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
    
    /* Look for the definition of a given word in the dictionary */
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

