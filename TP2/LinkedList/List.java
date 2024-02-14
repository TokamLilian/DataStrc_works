package LinkedList;

/*
 * List interface with methods implemented by Lists
 */
public interface List<T> {
    //void add(int index, int element);
    //void add(int element);

    //void remove(int index);

    //int get(int index);
    //int getId(int element);

    int size();
    boolean isEmpty();

    int first();
    int last();

    void addFirst(int value);
    void addLast(int value);

    void removeFirst();
    void removeLast();

}
