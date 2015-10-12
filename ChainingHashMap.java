/**
 * Created by mwatson on 11/10/15.
 */
import java.util.ArrayList;
import java.util.List;

public class ChainingHashMap<K extends Comparable<K>, V> {
    private ChainingHashMapNode<K, V>[] items;
    public int modulus;
    public int multiplier;
    private int hashMapSize;

    // construct a HashMap with 4000 places and given hash parameters
    public ChainingHashMap(int multiplier, int modulus){
        this.hashMapSize=4000;
        this.multiplier=multiplier;
        this.modulus=modulus;
        this.items=(ChainingHashMapNode<K, V>[]) new ChainingHashMapNode[hashMapSize];
    }

    //hashing
    public int hash(K key){
        return (Math.abs(multiplier*key.hashCode())%modulus);
    }

    // size (return the number of nodes currently stored in the map)
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

    // interface
    public int[] getFullestBuckets(){
        int maxNum=0;
        int nodeMax=0;

        for(int i=0; i<hashMapSize; i++){
            ChainingHashMapNode<K, V> current=items[i];
            int currentSize=0;
            while(current!=null){
                currentSize++;
                current=current.getNext();
            }
            if(currentSize>maxNum){
                maxNum=currentSize;
            }
        }

        for(int i=0; i<hashMapSize; i++){
            ChainingHashMapNode<K, V> current=items[i];
            int currentSize=0;

            while(current!=null){
                currentSize++;
                current=current.getNext();
            }

            if(currentSize==maxNum){
                nodeMax++;
            }
        }

        int[] fullestBuckets={maxNum,nodeMax};
        return fullestBuckets;
    }
    public List<K> keys(){
        List<K> keys = new ArrayList<>();
        for(ChainingHashMapNode<K, V> entry : items){
            if(entry!=null){
                keys.add(entry.key);
            }
        }
        return keys;
    }
    public V put(K key, V value){
        if(hashMapSize==0){
            this.hashMapSize=1;
        }

        int index=hash(key) % hashMapSize;

        if(items[index]==null){
            items[index]=new ChainingHashMapNode<>(key, value);
            return null;
        }
        return null;
    }
    public V get(K key){
        int index=hash(key)%hashMapSize;
        ChainingHashMapNode<K, V> entry=items[index];
        int count=index;

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
        ChainingHashMapNode<K, V> entry = items[index];

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
}
