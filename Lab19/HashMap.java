import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {
    private List<Map.Node<K, V>>[] list;

    public HashMap() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int n) {
        list = (ArrayList<Map.Node<K, V>>[]) new ArrayList[n];

        // populate the array with Lists
        for (int i = 0; i < list.length; i++)
            list[i] = new ArrayList<Map.Node<K, V>>();
    }

    private int getIndex(Object key) {
        int hashCode = key.hashCode();
        return (hashCode < 0 ? -hashCode : hashCode) % list.length;
    }

    public boolean containsKey(Object key) {
        int index = getIndex(key);
        for (Map.Node<K, V> node : list[index]) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for (List<Map.Node<K, V>> sublist : list) {
            for (Map.Node<K, V> node : sublist) {
                if (node.getValue() != null && node.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for (List<Map.Node<K, V>> sublist : list) {
            if (!sublist.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        int size = 0;
        for (List<Map.Node<K, V>> sublist : list) {
            size += sublist.size();
        }
        return size;
    }

    public V get(Object key) {
        int index = getIndex(key);
        for (Map.Node<K, V> node : list[index]) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    public V put(K key, V value) {
        int index = getIndex(key);
        for (Map.Node<K, V> node : list[index]) {
            if (node.getKey().equals(key)) {
                V oldValue = node.getValue();
                node.setValue(value);
                return oldValue;
            }
        }

        list[index].add(new Map.Node<K, V>(key, value));
        return null;
    }

    public V remove(Object key) {
        int index = getIndex(key);
        for (Map.Node<K, V> node : list[index]) {
            if (node.getKey().equals(key)) {
                V value = node.getValue();
                list[index].remove(node);
                return value;
            }
        }
        return null;
    }

    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (List<Map.Node<K, V>> sublist : list) {
            for (Map.Node<K, V> node : sublist) {
                values.add(node.getValue());
            }
        }
        return values;
    }

    public Set<K> keySet() {
        Set<K> keys = new ListSet<>();
        for (List<Map.Node<K, V>> sublist : list) {
            for (Map.Node<K, V> node : sublist) {
                keys.add(node.getKey());
            }
        }
        return keys;
    }
}
