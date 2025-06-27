package NewApproach;

public class Main {
    public static void main(String[] args) {

        System.out.println("New Approach");
        System.out.println();

        CacheLib cache = new CacheLib(10);

        int itemsToAdd=10;
        for (int i = 1; i <= itemsToAdd; i++) {
            cache.put(Integer.toString(i), "Item No " + i);
        }
        System.out.println("Added " + itemsToAdd + " elements to the cache");
        System.out.println(cache.getStatistics());
        cache.printContent();

        System.out.println("Get K=5 from the cache");
        System.out.println(cache.getStatistics());
        cache.get("5");
        cache.printContent();

        System.out.println("Delete K=6 from the cache");
        cache.delete("6");
        System.out.println(cache.getStatistics());
        cache.printContent();
    }
}