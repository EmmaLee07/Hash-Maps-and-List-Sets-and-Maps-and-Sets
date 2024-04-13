import java.util.ArrayList;
import java.util.List;

public class ListSet<E> implements Set<E> {
    List<E> list = new ArrayList<E>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public boolean contains(Object obj) {
        for (E e : list) {
            if (e.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        list.remove(obj);
        return true;
    }

    public boolean add(E item) {
        if (contains(item)) {
            return false;
        }
        list.add(item);
        return true;
    }
}
