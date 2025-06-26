import java.util.Optional;

public interface CacheLibInterface {
     void put(String key, String value);
     Optional<String> get(String key);
     void delete(String key);
     void clear();
     CacheStatistics getStatistics();
     void printContent();
}
