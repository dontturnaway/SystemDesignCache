public interface CacheLibInterface {
     void put(String key, String value);
     String get(String key);
     void delete(String key);
     void clear();
     void getStatistics();
     void printContent();
}
