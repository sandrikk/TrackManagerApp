package lists;

import java.util.function.Predicate;

public interface List<T> {

    boolean add(T element);

    void add(int index,T element) ;

    boolean contains(T element);

    void clear();

    T get(int index) ;

    int indexOf(T element);

    boolean isEmpty();


    T set(int index, T element);

    int size();


}
