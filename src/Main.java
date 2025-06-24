public class Main {
    public static void main(String[] args) {
        CacheLib cache = new CacheLib();

        for (int i = 1; i <= 14; i++) {
            cache.put(Integer.toString(i), "Item No");
        }
        System.out.println("Added 14 elements to the cache");
        cache.getStatistics();
        cache.printContent();

        System.out.println("Get K=5 from the cache");
        cache.get("5");
        cache.printContent();

        System.out.println("Delete K=6 from the cache");
        cache.delete("6");
        cache.printContent();
    }
}