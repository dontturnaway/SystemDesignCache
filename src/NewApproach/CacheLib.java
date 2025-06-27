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
                removeListQueue.delete(nodeToRemove);
            } else {
                if (storageMap.size() >= CACHE_MAX_SIZE) {
                    var itemToRemove = removeListQueue.popBegin();
                    storageMap.remove(itemToRemove.getKey());
                }
            }
            removeListQueue.addToEnd(node);
            storageMap.put(key, node);
        }
    }

    @Override
    public Optional<String> get(String key) {
        synchronized (this) {
            var node = storageMap.get(key);
            removeListQueue.delete(node);
            removeListQueue.addToEnd(node);
            return Optional.of(node.getValue());
        }
    }

    @Override
    public void delete(String key) {
        synchronized (this) {
            var node = storageMap.get(key);
            removeListQueue.delete(node);
            storageMap.remove(key);
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            removeListQueue.truncate();
            storageMap.clear();
            }
    }

    @Override
    public CacheStatistics getStatistics() {
        int currentCacheSize;
        int removeListQueueSize;
        synchronized (this) {
            currentCacheSize =  storageMap.size();
            removeListQueueSize = removeListQueue.getSize();
        }
        return new CacheStatistics(this.CACHE_MAX_SIZE, currentCacheSize, removeListQueueSize);
    }

    @Override
    public void printContent() {
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
