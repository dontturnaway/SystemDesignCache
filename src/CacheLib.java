import java.util.HashMap;
import java.util.LinkedList;

/*
Follows LRU principle
This Cache library follows LRU principle
 */

public class CacheLib implements CacheLibInterface {

    private final HashMap<String, String> storage = new HashMap<>();
    private final LinkedList<String> deleteQueue = new LinkedList<>();
    private final static int CACHE_MAX_SIZE = 10;

    @Override
    public void put(String key, String value) { //O(1)
        if (storage.size() >= CACHE_MAX_SIZE) { //O(1) - HashMap stores it
            String itemToRemove = deleteQueue.remove(); //O(1) - remove first from the queue
            storage.remove(itemToRemove); //O(1)
        }
        deleteQueue.addLast(key); //O(1)
        storage.put(key, value); //O(1)
    }

    @Override
    public String get(String key) { //O(n)
        deleteQueue.remove(key); //O(n)
        deleteQueue.offer(key); //O(n), add to the tail of the queue
        return storage.get(key);
    }

    @Override
    public void delete(String key) {
        deleteQueue.remove(key); //O(n), remove from the queue
        storage.remove(key); //O(1)
    }

    @Override
    public void clear() { //O(1)
        deleteQueue.clear();
        storage.clear();
    }

    @Override
    public void getStatistics() { //O(1)
        HashMap<String, Integer> stat = new HashMap<>();
        stat.put("CACHE_MAX_SIZE", CACHE_MAX_SIZE); //O(1)
        stat.put("CACHE_CURRENT_SIZE", storage.size()); //O(1)
        System.out.println("Statistics: " + stat);
    }

    @Override
    public void printContent() { //O(n)
        System.out.println(" ");
        System.out.println("===Cache content===");
        System.out.println("Memory content: " + storage);
        System.out.println("LRU content: " + deleteQueue);
        System.out.println("===/Cache content===");
        System.out.println(" ");
    }
}
