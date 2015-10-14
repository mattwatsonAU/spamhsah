import java.util.ArrayList;
import java.util.List;

public class HashMap<K extends Comparable<K>, V> {

    private HashMapNode<K, V>[] items;
    public int modulus;
    public int multiplier;
    public int hashMapSize;
    private int totalCollisions=0;
    private int maxCollisions=0;
    private int putCollisions=0;

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
        for(int i=0; i<hashMapSize; i++){
            if(items[i]!=null){
                if(items[i].getKey().equals(key)){
                    V originalVal=items[i].getValue();
                    items[i].setValue(value);
                    return originalVal;
                }
            }
        }

        int hash=hash(key);
        hash%=hashMapSize;
        final int initHash=hash;
        int index=0;
        int collisions=0;
        while(items[hash]!=null && items[hash].getKey()!=null){
            if(index==0){
                putCollisions++;
            }
            collisions++;
            totalCollisions++;
            hash++;
            hash%=hashMapSize;
            index++;
        }

        if(collisions>maxCollisions){
            maxCollisions=collisions;
        }
        items[hash]=new HashMapNode<K, V>(key, value);
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

    public int putCollisions(){
        return this.putCollisions;
    }

    public int totalCollisions(){
        return this.totalCollisions;
    }

    public int maxCollisions(){
        return this.maxCollisions;
    }

    public void resetStatistics(){
        putCollisions=0;
        totalCollisions=0;
        maxCollisions=0;
    }
}