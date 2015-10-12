import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mwatson on 12/10/15.
 */
public class DoubleHashMapTest {
    @Test
    public void testConstruction(){
        HashMap<Integer, Integer> m = new HashMap<>(10,10);
        assertEquals(0, m.size());
        assertEquals(true, m.isEmpty());
        assertEquals(0, m.keys().size());

        assertEquals(null, m.get(0));
    }

    @Test
    public void testTinyMap(){
        HashMap<Integer, Integer> m = new HashMap<>(10,10);

        m.put(0, 15);

        assertEquals(15, (int)m.get(0));
        assertEquals(1, m.size());
        assertEquals(false, m.isEmpty());
        assertEquals(1, m.keys().size());

    }

    @Test
    public void testTinyCollisions(){
        HashMap<Integer, Integer> m = new HashMap<>(10,10);

        m.put(0, 15);

        assertEquals(15, (int)m.get(0));
        assertEquals(15, (int)m.put(0, 25));
        assertEquals(25, (int)m.get(0));

        m.put(1, 100);
        assertEquals(100, (int)m.get(1));

        m.put(10000, 125);
        assertEquals(125, (int)m.get(10000));
    }

    @Test
    public void testTinyHardCollisions(){
        HashMap<Integer, Integer> m = new HashMap<>(10,7);

        m.put(0, 15);

        assertEquals(15, (int)m.get(0));
        assertEquals(15, (int)m.put(0, 25));
        assertEquals(25, (int)m.get(0));

        m.put(1, 100);
        assertEquals(100, (int)m.get(1));

        assertEquals(null, m.put(15, 125));
        assertEquals(125, (int)m.get(15));
        assertEquals(3, m.keys().size());

        assertEquals(null, m.put(11, 11));
        assertEquals(null, m.put(12, 12));
        assertEquals(null, m.put(13, 13));
        assertEquals(null, m.put(127, 127));

        assertEquals(127, (int)m.get(127));

    }

    @Test
    public void testHashFunction(){
        HashMap<Integer, Integer> m = new HashMap<>(10,10);

        assertEquals(0, m.hash(10000));
        assertEquals(0, m.hash(15));
        assertEquals(0, m.hash(25));
        assertEquals(0, m.hash(50));
        assertEquals(0, m.hash(1));

        assertEquals(0, m.hash(11));
        assertEquals(0, m.hash(12));
        assertEquals(0, m.hash(13));
        assertEquals(0, m.hash(127));

        HashMap<Integer, Integer> map = new HashMap<>(10,7);

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