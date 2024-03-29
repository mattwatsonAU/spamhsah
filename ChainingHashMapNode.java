/**
 * Created by mwatson on 11/10/15.
 */
public class ChainingHashMapNode<K extends Comparable<K>, V> {
    public K key;
    public V value;
    public ChainingHashMapNode next;

    //construction
    public ChainingHashMapNode(K key, V value){
        this.next=null;
        this.key=key;
        this.value=value;
    }

    //get methods
    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public ChainingHashMapNode<K, V> getNext(){
        return this.next;
    }

    //set methods
    public void setValue(V newValue){
        this.value=newValue;
    }

    public void setNext(ChainingHashMapNode<K, V> next){
        this.next=next;
    }
}
