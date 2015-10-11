import static org.junit.Assert.*;
/**
 * Created by mwatson on 11/10/15.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class ChainingHashMapTest {


    @Test
    public void testConstruction(){
        ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,10);
        assertEquals(0, m.size());
        assertEquals(true, m.isEmpty());
        assertEquals(0, m.keys().size());

        assertEquals(null, m.get(0));
    }

    @Test
    public void testTinyMap(){
        ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,10);

        m.put(0, 15);

        assertEquals(15, (int)m.get(0));
        assertEquals(1, m.size());
        assertEquals(false, m.isEmpty());
        assertEquals(1, m.keys().size());

    }

    @Test
    public void testHashFunction(){
        ChainingHashMap<Integer, Integer> m = new ChainingHashMap<>(10,10);

        assertEquals(0, m.hash(10000));
        assertEquals(0, m.hash(15));
        assertEquals(0, m.hash(25));
        assertEquals(0, m.hash(50));
        assertEquals(0, m.hash(1));

        assertEquals(0, m.hash(11));
        assertEquals(0, m.hash(12));
        assertEquals(0, m.hash(13));
        assertEquals(0, m.hash(127));

        ChainingHashMap<Integer, Integer> map = new ChainingHashMap<>(10,7);

        assertEquals(5, map.hash(10000));
        assertEquals(3, map.hash(15));
        assertEquals(5, map.hash(25));
        assertEquals(3, map.hash(50));

        assertEquals(5, map.hash(11));
        assertEquals(1, map.hash(12));
        assertEquals(4, map.hash(13));
        assertEquals(3, map.hash(127));
    }

}
