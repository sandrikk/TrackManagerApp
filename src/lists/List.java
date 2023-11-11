package lists;

public interface List<T> {

    boolean add(T node);

    boolean contains(T node);

    T get(int index);

    int indexOf(T node);

    boolean remove(T node);

    boolean remove(int index);

    boolean isEmpty();

    int size();

    void clear();
}
