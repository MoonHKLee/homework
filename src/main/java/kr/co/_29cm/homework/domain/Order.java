package kr.co._29cm.homework.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final int DELIVERY_FEE = 2500;
    public static final int DELIVERY_FREE_PRICE = 50_000;
    private final List<Product> list;
    private int orderPrice;
    private int totalPrice;

    public Order() {
        this.list = new ArrayList<>();
        this.orderPrice = 0;
        this.totalPrice = 0;
    }

    public void add(Product product) {
        if (list.contains(product)) {
            int index = list.indexOf(product);
            Product origin = list.get(index);
            origin.addCount(product.getCount());
            return;
        }
        list.add(product);
    }

    public List<Product> getList() {
        return list;
    }

    public int getOrderPrice() {
        return list.stream()
                .mapToInt(v -> v.getPrice() * v.getCount())
                .sum();
    }

    public int getTotalPrice() {
        if (isFreeDelivery()) {
            return getOrderPrice();
        }
        return getOrderPrice() + DELIVERY_FEE;
    }

    public int getDeliveryFee() {
        return DELIVERY_FEE;
    }

    public boolean isFreeDelivery() {
        return getOrderPrice() >= DELIVERY_FREE_PRICE;
    }
}
