/**
 * Created by mwatson on 12/10/15.
 */
import java.util.List;
import java.util.ArrayList;

public class DoubleHashMap<K extends Comparable<K>, V> {

    private HashMapNode<K, V>[] items;
    public int modulus;
    public int multiplier;
    public int modulus2;
    public int hashMapSize;
    private int putCollisions=0;
    private int putFailures=0;
    private int totalCollisions=0;
    private int maxCollisions=0;
    HashMapNode defunctPosition=new HashMapNode<>(null, null);

    // updated construction
    // construct a HashMap with 4000 places and given hash parameters
    public DoubleHashMap(int multiplier, int modulus, int secondaryModulus){
        this.hashMapSize=4000;
        this.multiplier=multiplier;
        this.modulus=modulus;
        this.modulus2=secondaryModulus;
        this.items=(HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
    }

    // construct a HashMap with given capacity and given hash parameters
    public DoubleHashMap(int hashMapSize, int multiplier, int modulus, int secondaryModulus){
        this.hashMapSize=hashMapSize;
        this.multiplier=multiplier;
        this.modulus=modulus;
        this.modulus2=secondaryModulus;
        this.items=(HashMapNode<K, V>[]) new HashMapNode[hashMapSize];
    }

    public int hash(K key){
        return(Math.abs(multiplier * key.hashCode()) % modulus);
    }

    public int secondaryHash(K key){
        return(modulus2-(Math.abs(key.hashCode())%modulus2));
    }

    public int size(){
        return keys().size();
    }

    public boolean isEmpty(){
        if(size()==0){
            return true;
        }
        else{
            return false;
        }
    }

    //interface methods
    public List<K> keys(){
        List<K> keys=new ArrayList<>();
        for(HashMapNode<K, V> entry : items){
            if(entry!=null && entry.getKey()!=null){
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
        int hash2=secondaryHash(key);
        final int initHash=hash;
        int index=0;
        int collisions=0;
        while(items[hash]!=null && items[hash].getKey()!=null){
            if(index==0){
                putCollisions++;
            }
            collisions++;
            totalCollisions++;
            hash+=hash2;
            hash%=hashMapSize;
            if(hash==initHash){
                putFailures++;
                throw new RuntimeException("Double Hashing failed to find a free position.");
            }
            index++;
        }

        if(collisions>maxCollisions){
            maxCollisions=collisions;
        }

        items[hash]=new HashMapNode<K, V>(key, value);
        return null;
    }

    public V get(K key){
        int indexHash=hash(key);
        int hash2=secondaryHash(key);
        while(items[indexHash]!=null){
            if(items[indexHash].getKey()==key){
                return items[indexHash].getValue();
            }
            indexHash+=hash2;
            indexHash%=hashMapSize;
        }
        return null;

    }

    public V remove(K key){
        int indexHash=hash(key);
        int hash2=secondaryHash(key);
        while(items[indexHash]!=null){
            if(items[indexHash].getKey()==key){
                V originalValue=items[indexHash].getValue();
                items[indexHash]=defunctPosition;
                return originalValue;
            }
            indexHash+=hash2;
            indexHash%=hashMapSize;
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

    public int putFailures(){
        return this.putFailures();
    }

    public void resetStatistics() {
        putCollisions = 0;
        totalCollisions = 0;
        maxCollisions = 0;
    }
}