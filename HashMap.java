import java.util.ArrayList;
import java.util.List;

public class HashMap<K extends Comparable<K>, V> {
    // construct a HashMap with 4000 places and given hash parameters
    public HashMap(int multiplier, int modulus)
    // construct a HashMap with given capacity and given hash parameters
    public HashMap(int hashMapSize, int multiplier, int modulus)
    // hashing
    public int hash(K key)
    // size (return the number of nodes currently stored in the map)
    public int size()
    public boolean isEmpty()
    // interface methods
    public List<K> keys()
    public V put(K key, V value)
    public V get(K key)
    public V remove(K key)
}