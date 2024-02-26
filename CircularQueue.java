package tp2;

public class CircularQueue {
    private int[] elements; // Tableau pour stocker les éléments de la file
    private int size; // Nombre d'éléments actuellement dans la file
    private int head; // Index de l'élément de tête de la file
    private int tail; // Index de l'élément de queue de la file
    private static final int Tail_max = 100; // Capacité maximale de la file

    // Constructeur de la file d'attente circulaire
    public CircularQueue() {
        elements = new int[Tail_max]; // Initialisation du tableau avec la taille maximale
        size = 0; // Initialisation de la taille à 0
        head = 0; // La tête commence à l'index 0
        tail = -1; // La queue commence à -1 pour indiquer qu'elle est vide
    }

    // Méthode pour vérifier si la file est vide
    public boolean isEmpty() {
        return size == 0;
    }

    // Méthode pour vérifier si la file est pleine
    public boolean isFull() {
        return size == Tail_max;
    }

    // Méthode pour obtenir la taille de la file
    public int size() {
        return size;
    }

    // Méthode pour ajouter un élément à la file
    public void enqueue(int value) {
        if (!isFull()) { // Vérifier si la file n'est pas pleine
            tail = (tail + 1) % Tail_max; // Mettre à jour l'index de la queue de manière circulaire
            elements[tail] = value; // Ajouter la valeur à la queue
            size++; // Incrémenter la taille de la file
        } else {
            throw new IllegalStateException("Queue is full"); // Lancer une exception si la file est pleine
        }
    }

    // Méthode pour retirer et retourner l'élément de tête de la file
    public int dequeue() {
        if (isEmpty()) { // Vérifier si la file est vide
            throw new IllegalStateException("Queue is empty"); // Lancer une exception si la file est vide
        } else {
            int value = elements[head]; // Prendre la valeur de tête
            head = (head + 1) % Tail_max; // Mettre à jour l'index de tête de manière circulaire
            size--; // Décrémenter la taille de la file
            return value; // Retourner la valeur de tête
        }
    }

    // Méthode pour obtenir l'élément de tête sans le retirer
    public int front() {
        if (isEmpty()) { // Vérifier si la file est vide
            throw new IllegalStateException("Queue is empty"); // Lancer une exception si la file est vide
        } else {
            return elements[head]; // Retourner la valeur de tête
        }
    }

    // Méthode pour obtenir l'élément de queue sans le retirer
    public int rear() {
        if (isEmpty()) { // Vérifier si la file est vide
            throw new IllegalStateException("Queue is empty"); // Lancer une exception si la file est vide
        } else {
            return elements[tail]; // Retourner la valeur de queue
        }
    }
    public void reverse() {
        int start = head;
        int end = tail;
        while(start != end && (end + 1) % Tail_max != start) {
            int temp = elements[start];
            elements[start] = elements[end];
            elements[end] = temp;

            start = (start + 1) % Tail_max;
            end = (end - 1 + Tail_max) % Tail_max;
        }
    }

    // Vérifier si un élément est dans la file
    public boolean checkInQueue(int value) {
        int index = head;
        for (int i = 0; i < size; i++) {
            if (elements[index] == value) {
                return true;
            }
            index = (index + 1) % Tail_max;
        }
        return false;
    }

    // Retirer la première apparition de la valeur (récursivement)
    public void remove(int value) {
        removeRecursive(head, tail, value, false);
    }
    private void removeRecursive(int start, int end, int value, boolean found) {
        if (size == 0 || start == (end + 1) % Tail_max) {
            return; // Cas de base : file vide ou parcourue entièrement
        }
        if (elements[start] == value && !found) {
            // L'élément trouvé, on le marque et on continue pour décaler les suivants
            found = true;
        }
        if (found) {
            // Décale les éléments pour "supprimer" l'élément trouvé
            elements[start] = elements[(start + 1) % Tail_max];
        }
        removeRecursive((start + 1) % Tail_max, end, value, found);
        if (start == tail) {
            tail = (tail - 1 + Tail_max) % Tail_max; // Mettre à jour la queue
            size--; // Décrémenter la taille
        }
    }

    // Méthode pour afficher tous les éléments de la file
    public void print() {
        for (int i = 0; i < size; i++) {
            // Afficher chaque élément en partant de la tête et en avançant de manière circulaire
            System.out.print(elements[(head + i) % Tail_max] + " ");
        }
        System.out.println(); // Passer à la ligne suivante après avoir affiché tous les éléments
    }

    // Les fonctions personnalisées seront implémentées ici...
}

