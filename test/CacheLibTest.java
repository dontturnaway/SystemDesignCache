import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CacheLibTest {

    private CacheLib cache;

    @BeforeEach
    void setUp() {
        cache = new CacheLib(3);
    }

    @Test
    void testPutAndGetBasicValue() {
        cache.put("a", "1");
        Optional<String> value = cache.get("a");
        assertTrue(value.isPresent());
        assertEquals("1", value.get());
    }

    @Test
    void testEvictionOnMaxSize() {
        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("c", "3");
        cache.put("d", "4"); // should evict "a"

        assertFalse(cache.get("a").isPresent());
        assertTrue(cache.get("b").isPresent());
        assertEquals("2", cache.get("b").get());
    }

    @Test
    void testUpdateExistingKey() {
        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("a", "3"); // update value
        Optional<String> value = cache.get("a");
        assertTrue(value.isPresent());
        assertEquals("3", value.get());
    }

    @Test
    void testDeleteKey() {
        cache.put("a", "1");
        cache.delete("a");
        assertFalse(cache.get("a").isPresent());
    }

    @Test
    void testClearCache() {
        cache.put("a", "1");
        cache.put("b", "2");
        cache.clear();
        assertFalse(cache.get("a").isPresent());
        assertFalse(cache.get("b").isPresent());
    }

    @Test
    void testCacheStatistics() {
        cache.put("a", "1");
        cache.put("b", "2");
        CacheStatistics stats = cache.getStatistics();
        assertEquals(3, stats.cacheMaxSize);
        assertEquals(2, stats.cacheCurrentSize);
    }
}