package NewApproach;

import java.util.HashMap;
import java.util.Optional;

/*
Follows LRU principle
This Cache library follows LRU principle
 */
public class CacheLib implements CacheLibInterface {

    private final HashMap<String, RemoveListNode> storageMap = new HashMap<>();
    private final RemoveList removeListQueue = new RemoveList();
    private final int CACHE_MAX_SIZE;

    public CacheLib(Integer cacheSize) {
        this.CACHE_MAX_SIZE=cacheSize;
    }

    @Override
    public void put(String key, String value) { //O(1)
        RemoveListNode node = new RemoveListNode(key, value);
        synchronized (this) {
            if (storageMap.containsKey(key)) {
                var nodeToRemove = storageMap.get(key);
                removeListQueue.deleteNode(nodeToRemove); //O(n), search for 1-st occurence
            } else {
                if (storageMap.size() >= CACHE_MAX_SIZE) { //O(1) - HashMap stores it
                    var itemToRemove = removeListQueue.popBegin(); //O(1) - remove first from the queue
                    storageMap.remove(itemToRemove.getKey()); //O(1)
                }
            }
            removeListQueue.addNodeEnd(node); //O(1)
            storageMap.put(key, node); //O(1)
        }
    }

    @Override
    public Optional<String> get(String key) { //O(n)
        synchronized (this) {
            var node = storageMap.get(key);
            removeListQueue.deleteNode(node); //O(n)
            removeListQueue.addNodeEnd(node); //O(n), add to the tail of the queue
            return Optional.of(node.getValue());
        }
    }

    @Override
    public void delete(String key) {
        synchronized (this) {
            var node = storageMap.get(key);
            removeListQueue.deleteNode(node); //O(n), remove from the queue
            storageMap.remove(key); //O(1)
        }
    }

    @Override
    public void clear() { //O(1)
        synchronized (this) {
            removeListQueue.clear();
            storageMap.clear();
            }
    }

    @Override
    public CacheStatistics getStatistics() { //O(1)
        int currentCacheSize;
        synchronized (this) {
            currentCacheSize =  storageMap.size(); //O(1)
            //removeListQueue = removeListQueue.size();
        }
        return new CacheStatistics(this.CACHE_MAX_SIZE, currentCacheSize, 0);
    }

    @Override
    public void printContent() { //O(n)
        System.out.println(" ");
        System.out.println("===Cache content===");
        synchronized (this) {
            System.out.println("Memory content: " + storageMap);
            System.out.println("LRU content: " + removeListQueue);
        }
        System.out.println("===/Cache content===");
        System.out.println(" ");
    }

}
