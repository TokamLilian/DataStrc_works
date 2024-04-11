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



public class DictionaryAVLTree {
    private AVLNode root;

    private int height(AVLNode N) {
        if (N == null) return 0;
        return N.height;
    }

    
    private AVLNode insert(AVLNode node, String word, String meaning) {
   
    }


    public void insert(String word, String meaning) {
        root = insert(root, word, meaning);
    }
    

    private AVLNode delete(AVLNode root, String word) {
       
    }
    

    public void delete(String word) {
        root = delete(root, word);
    }
    

    public String search(AVLNode root, String word) {
    
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

