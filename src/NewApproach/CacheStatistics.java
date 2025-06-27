package NewApproach;

public class CacheStatistics {

    final Integer cacheMaxSize;
    final Integer cacheCurrentSize;
    final Integer LruQueueSize;

    public CacheStatistics(Integer cacheMaxSize, Integer cacheCurrentSize, Integer LruQueueSize) {
        this.cacheMaxSize = cacheMaxSize;
        this.cacheCurrentSize = cacheCurrentSize;
        this.LruQueueSize = LruQueueSize;
    }

    public String toString() {
        return "CACHE_MAX_SIZE: " + cacheMaxSize.toString() +
                "; CACHE_CURRENT_SIZE: " + cacheCurrentSize.toString() +
                "; CACHE_LRU_QUEUE_SIZE: " + LruQueueSize.toString();
    }

}
