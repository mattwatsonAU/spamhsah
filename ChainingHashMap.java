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

    // construct a HashMap with given capacity and given hash parameters
    public ChainingHashMap(int hashMapSize, int multiplier, int modulus) {
        this.hashMapSize=hashMapSize;
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
        int index=hash(key)%hashMapSize;
        int count=index;
        int count2=count;

        if(items[index]==null){
            items[index]=new ChainingHashMapNode<>(key, value);
            return null;
        }

        if(items[index].getKey().equals(key)){
            V origin=items[index].getValue();
            ChainingHashMapNode<K, V> currentNode=items[index];

            while(currentNode.getNext()!=null){
                currentNode=currentNode.getNext();
            }

            currentNode.setNext(new ChainingHashMapNode<>(key, value));
            return origin;
        }

        if(!items[index].getKey().equals(key)){
            for(int i=0; i<hashMapSize; i++){
                count++;
                if(count>=hashMapSize){
                    count=0;
                }

                if (items[count]!=null && items[count].getKey().equals(key)){
                    V origin=items[index].getValue();
                    ChainingHashMapNode<K, V> currentNode=items[index];

                    while(currentNode.getNext()!=null){
                        currentNode=currentNode.getNext();
                    }

                    currentNode.setNext(new ChainingHashMapNode<>(key, value));
                    return origin;
                }
            }
        }

        for(int i=0; i<hashMapSize; i++){
            count2++;
            if(count2>=hashMapSize){
                count2=0;
            }
            if(items[count2]==null){
                items[count2]=new ChainingHashMapNode<K, V>(key, value);
                return null;
            }
        }
        return null;
    }
    public V get(K key){
        int index=hash(key)%hashMapSize;
        int count=index;
        ChainingHashMapNode<K, V> entry=items[index];


        if(entry==null){
            return null;
        }

        if(entry.getKey().equals(key)){
            return entry.getValue();
        }
        //do a check to see if the key is the one we're looking for and keep going until found or back to index 0
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
        return null;
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