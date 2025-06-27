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

    public void printStatistics() {
        System.out.println("CACHE_MAX_SIZE: " + cacheMaxSize);
        System.out.println("CACHE_CURRENT_SIZE: " + cacheCurrentSize);
        System.out.println("CACHE_LRU_QUEUE_SIZE: " + LruQueueSize);
    }

}
