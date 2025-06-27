package OldApproach;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

/*
Follows LRU principle
This Cache library follows LRU principle
 */

//Optional
//DELETE FOR DUPLICATED KEY
//Send links O(n) -> O(1)

public class CacheLib implements CacheLibInterface {

    private final HashMap<String, String> storageMap = new HashMap<>();
    private final LinkedList<String> deleteQueue = new LinkedList<>();
    private final int CACHE_MAX_SIZE;

    public CacheLib(Integer cacheSize) {
        this.CACHE_MAX_SIZE=cacheSize;
    }

    @Override
    public void put(String key, String value) { //O(1)
        synchronized (this) {
            if (storageMap.containsKey(key)) {
                deleteQueue.remove(key); //O(n), search for 1-st occurence
            } else {
                if (storageMap.size() >= CACHE_MAX_SIZE) { //O(1) - HashMap stores it
                    String itemToRemove = deleteQueue.remove(); //O(1) - remove first from the queue
                    storageMap.remove(itemToRemove); //O(1)
                }
            }
            deleteQueue.addLast(key); //O(1)
            storageMap.put(key, value); //O(1)
        }
    }

    @Override
    public Optional<String> get(String key) { //O(n)
        synchronized (this) {
            deleteQueue.remove(key); //O(n)
            deleteQueue.offer(key); //O(n), add to the tail of the queue
            return Optional.of(storageMap.get(key));
        }
    }

    @Override
    public void delete(String key) {
        synchronized (this) {
            deleteQueue.remove(key); //O(n), remove from the queue
            storageMap.remove(key); //O(1)
        }
    }

    @Override
    public void clear() { //O(1)
        synchronized (this) {
            deleteQueue.clear();
            storageMap.clear();
            }
    }

    @Override
    public CacheStatistics getStatistics() { //O(1)
        int currentCacheSize;
        synchronized (this) {
            currentCacheSize =  storageMap.size(); //O(1)
        }
        return new CacheStatistics(this.CACHE_MAX_SIZE, currentCacheSize);
    }

    @Override
    public void printContent() { //O(n)
        System.out.println(" ");
        System.out.println("===Cache content===");
        synchronized (this) {
            System.out.println("Memory content: " + storageMap);
            System.out.println("LRU content: " + deleteQueue);
        }
        System.out.println("===/Cache content===");
        System.out.println(" ");
    }

}
