package kr.co._29cm.homework.domain;

import java.util.concurrent.ConcurrentHashMap;

public class Products {
    private static final ConcurrentHashMap<String, Product> productTable = new ConcurrentHashMap<>();

    public static void put(String id, Product product) {
        productTable.put(id, product);
    }

    public static Product get(String id) {
        return productTable.get(id);
    }

    public static ConcurrentHashMap<String, Product> getTable() {
        return productTable;
    }

    public static boolean contains(String id) {
        return productTable.containsKey(id);
    }

    public static void decreaseCount(String id, int count) {
        Product product = productTable.get(id);
        product.decreaseCount(count);
    }
}
