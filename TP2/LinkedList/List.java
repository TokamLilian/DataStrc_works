package LinkedList;

/*
 * List interface with methods implemented by Lists
 */
public interface List<T> {
   
    int size();
    boolean isEmpty();

    int first();
    int last();

    void addFirst(int value);
    void addLast(int value);

    void removeFirst();
    void removeLast();

}
