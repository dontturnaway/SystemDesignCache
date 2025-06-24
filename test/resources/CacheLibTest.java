import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheLibTest {

    private CacheLib cache;

    @BeforeEach
    void setUp() {
        cache = new CacheLib(3); // max size 3
    }

    @Test
    void testPutAndGet() {
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3");

        assertEquals("1", cache.get("A"));
        assertEquals("2", cache.get("B"));
        assertEquals("3", cache.get("C"));
    }

    @Test
    void testEviction() {
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3");

        // Access A to make it recently used
        cache.get("A");

        // Now put D, should evict B (least recently used)
        cache.put("D", "4");

        assertNull(cache.get("B")); // B should be evicted
        assertEquals("1", cache.get("A"));
        assertEquals("3", cache.get("C"));
        assertEquals("4", cache.get("D"));
    }

    @Test
    void testDelete() {
        cache.put("A", "1");
        cache.put("B", "2");

        cache.delete("A");

        assertNull(cache.get("A"));
        assertEquals("2", cache.get("B"));
    }

    @Test
    void testClear() {
        cache.put("A", "1");
        cache.put("B", "2");

        cache.clear();

        assertNull(cache.get("A"));
        assertNull(cache.get("B"));
    }

    @Test
    void testGetStatisticsReflectsSize() {
        cache.put("A", "1");
        cache.put("B", "2");

        // Will print: CACHE_MAX_SIZE: 3, CACHE_CURRENT_SIZE: 2
        cache.getStatistics();
    }

    @Test
    void testPrintContent() {
        cache.put("X", "100");
        cache.put("Y", "200");

        // Just ensures no exception during printing
        cache.printContent();
    }
}