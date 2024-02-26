package tp2;

public class LinkedList {
    private Node head;

    //(définition de la classe Node)
    private class Node {
        int valeur; // la valeur de chaque noeud
        Node next; // un noeud

        Node(int value) {
            this.valeur = value;
            this.next = null;
        }
    }

    // Méthode pour retirer tous les éléments avec une certaine valeur (récursif)
    public void removeValue(int value) {
        head = removeValueRecursive(head, value);
    }

    private Node removeValueRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.valeur == value) {
            return removeValueRecursive(current.next, value);
        }

        current.next = removeValueRecursive(current.next, value);
        return current;
    }

    // Méthode pour renvoyer le n-ième élément en partant de la fin (itératif)
    public int returnNLast(int nLast) {
        if (nLast <= 0 || head == null) {
            return -1; // Valeur arbitraire pour indiquer une erreur ou une condition invalide
        }

        Node slow = head;
        Node fast = head;

        for (int i = 0; i < nLast; i++) {
            if (fast == null) {
                return -1; // Valeur arbitraire pour indiquer une erreur ou une condition invalide
            }
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.valeur;
    }

    // Méthode pour vérifier si l'élément est dans la liste (itératif)
    public boolean checkInList(int value) {
        Node current = head;
        while (current != null) {
            if (current.valeur == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Méthode pour renvoyer la valeur maximale stockée dans la liste (récursif)
    public int maxValue() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        return maxValueRecursive(head);
    }

    private int maxValueRecursive(Node current) {
        if (current == null) {
            return Integer.MIN_VALUE;
        }

        int maxInRest = maxValueRecursive(current.next);
        return Math.max(current.valeur, maxInRest);
    }

    // Méthode pour obtenir la taille de la liste
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Méthode pour vérifier si la liste est vide
    public boolean isEmpty() {
        return head == null;
    }

    // Méthode pour obtenir la première valeur de la liste
    public int first() {
        if (head == null) {
            throw new IllegalStateException("La liste est vide");
        }
        return head.valeur;
    }

    // Méthode pour obtenir la dernière valeur de la liste
    public int last() {
        if (head == null) {
            throw new IllegalStateException("La liste est vide");
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.valeur;
    }

    // Méthode pour ajouter une valeur au début de la liste
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    // Méthode pour ajouter une valeur à la fin de la liste
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Méthode pour supprimer le premier élément de la liste
    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("La liste est vide");
        }
        head = head.next;
    }

    // Méthode pour supprimer le dernier élément de la liste
    public void removeLast() {
        if (head == null) {
            throw new IllegalStateException("La liste est vide");
        }

        if (head.next == null) {
            head = null;
            return;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    // Méthode pour afficher la liste
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.valeur + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void insertionSort() {
        if (head == null || head.next == null) {
            return; // La liste est déjà triée ou vide
        }

        Node sorted = null; // Tête de la partie triée

        // Parcourir la liste non triée
        Node current = head;
        while (current != null) {
            // Stocker le prochain nœud à traiter
            Node next = current.next;

            // Insérer la valeur dans la partie triée
            addInOrder(current.valeur);

            // Mettre à jour la tête de la partie triée
            sorted = head;

            // Passer au prochain nœud dans la liste non triée
            current = next;
        }

        // Mettre à jour la tête de la liste avec la partie triée
        head = sorted;
    }

    // Méthode pour ajouter un élément dans une liste ordonnée (récursif)
    public void addInOrder(int value) {
        Node nouvNoeud = new Node(value);

        if (head == null || head.valeur >= nouvNoeud.valeur) {
            
            nouvNoeud.next = head;   // l'ancienne tete devient la valeur apres le nouveau noeud qui devient la tete
            head = nouvNoeud;      // Nouveau nœud doit devenir la nouvelle tête
        } else {
            // Chercher l'emplacement d'insertion dans la partie triée
            Node current = head; // On stocke la tete dans un noeud pour pouvoir faire le tri
         // si la tete est inferieur a la nouvelle valeur alors on passse au prochain jusqua trouver ou placer la nouvelle valeur
            while (current.next != null && current.next.valeur < nouvNoeud.valeur) { 
                current = current.next;
            }

            // Insérer le nouveau nœud après le nœud courant
            nouvNoeud.next = current.next;
            current.next = nouvNoeud;
        }
    }

   
}
    
