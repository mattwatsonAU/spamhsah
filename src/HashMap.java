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
        this.hashMapSize = 4000;
        this.multiplier = multiplier;
        this.modulus = modulus;
        this.items = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
    }


    // construct a HashMap with given capacity and given hash parameters
    public HashMap(int hashMapSize, int multiplier, int modulus){
        this.hashMapSize = hashMapSize;
        this.multiplier = multiplier;
        this.modulus = modulus;
        this.items = (HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
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

    public V put(K key, V value){
        int index = hash(key) % hashMapSize;
        int count = index;

        if(items[index] == null){
            items[index] = new HashMapNode<>(key, value);
            return null;
        }

        if(items[count].getKey().equals(key)){
            V original = items[count].getValue();
            items[count].setValue(value);
            return original;
        }

        else if(!items[index].getKey().equals(key)){
            Collisions++;
            for(int i=0; i<hashMapSize; i++){
                count++;
                totalCollisions++;
                if(count>=hashMapSize){
                    count = 0;
                }

                if(i>maxCollisions){
                    maxCollisions=i;
                }

                if(items[count]==null){
                    items[count] = new HashMapNode<>(key, value);
                    return null;
                }

                if(items[count].getKey().equals(key)){
                    V original= items[count].getValue();
                    items[count].setValue(value);
                    return original;
                }
            }
        }
        return null;
    }

    public V get(K key){
        int index = hash(key) % hashMapSize;
        HashMapNode<K, V> entry= items[index];
        int count = index;

        if(entry==null){
            return null;
        }

        //do a check to see if the key is the one we're looking for
        if(!entry.key.equals(key)){
            for(int i=0; i<hashMapSize; i++){
                count++;
                if(count>=hashMapSize){
                    count=0;
                }
                if(items[count].getKey().equals(key)){
                    return items[count].getValue();
                }
            }
        }
        return entry.getValue();
    }

    public V remove(K key){
        int index = hash(key) % hashMapSize;
        int count = index;
        HashMapNode<K, V> entry = items[index];

        if(entry==null){
            return null;
        }

        if(entry.key.equals(key)){
            V tmp = entry.value;
            items[index] = null;
            return tmp;
        }

        if(!entry.key.equals(key)){
            for(int i=0; i<hashMapSize; i++){
                count++;
                if(count>=hashMapSize){
                    count=0;
                }
                if(items[count].getKey().equals(key)){
                    V tmp=items[count].getValue();
                    items[count]=null;
                    return tmp;
                }
            }
        }
        return null;
    }

    public int Collisions(){
        return this.Collisions;
    }

    public int totalCollisions(){
        return this.totalCollisions;
    }

    public int maxCollisions(){
        return this.maxCollisions;
    }

    public void resetStatistics(){
        Collisions=0;
        totalCollisions=0;
        maxCollisions=0;
    }
}