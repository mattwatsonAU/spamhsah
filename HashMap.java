import java.util.ArrayList;
import java.util.List;

public class HashMap<K extends Comparable<K>, V> {

    private HashMapNode<K, V>[] items;
    public int modulus;
    public int multiplier;
    public int hashMapSize;
    private int Collisions=0;
    private int totalCollisions=0;
    private int maxCollisions=0;

    // construct a HashMap with 4000 places and given hash parameters
    public HashMap(int multiplier, int modulus){
        this.items = (HashMapNodes<K, V>[]) new hashMapNode[4000];
        this.hashMapSize = 4000;
        this.multiplier = multiplier;
        this.modulus = modulus;
    }


    // construct a HashMap with given capacity and given hash parameters
    public HashMap(int hashMapSize, int multiplier, int modulus){
        this.items = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
        this.hashMapSize = hashMapSize;
        this.multiplier = multiplier;
        this.modulus = modulus;
    }



    // hashing
    public int hash(K key){
        return (Math.abs(multiplier * key.hashCode()) % modulus);
    }
    // size (return the number of nodes currently stored in the map)
    public int size(){
        return keys().size();
    }
    public boolean isEmpty(){
        if(size() == 0) {
            return true;
        }
        else{
            return false;
        }
    }
    // interface methods
    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for(HashMapNode<K, V> entry : items){
            if(entry != null){
                keys.add(entry.key);
            }
        }
        return keys;
    }
    public V put(K key, V value)
    public V get(K key)
    public V remove(K key)
}