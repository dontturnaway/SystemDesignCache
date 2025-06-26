public class CacheStatistics {

    final Integer cacheMaxSize;
    final Integer cacheCurrentSize;

    public CacheStatistics(Integer cacheMaxSize, Integer cacheCurrentSize) {
        this.cacheMaxSize = cacheMaxSize;
        this.cacheCurrentSize = cacheCurrentSize;
    }

    public void printStatistics() {
        System.out.println("CACHE_MAX_SIZE: " + cacheMaxSize);
        System.out.println("CACHE_CURRENT_SIZE: " + cacheCurrentSize);
    }

}
