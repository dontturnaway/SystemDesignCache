package NewApproach;

public class Main {
    public static void main(String[] args) {

        System.out.println("New Approach");
        System.out.println("");

        CacheLib cache = new CacheLib(10);

        for (int i = 1; i <= 10; i++) {
            cache.put(Integer.toString(i), "Item No " + i);
        }
        System.out.println("Added 14 elements to the cache");
        cache.getStatistics().printStatistics();
        cache.printContent();

        System.out.println("Get K=5 from the cache");
        cache.getStatistics().printStatistics();
        cache.get("5");
        cache.printContent();

        System.out.println("Delete K=6 from the cache");
        cache.delete("6");
        cache.getStatistics().printStatistics();
        cache.printContent();
    }
}