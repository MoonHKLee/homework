package kr.co._29cm.homework.domain;

import kr.co._29cm.homework.exception.NoOrderException;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static final int DELIVERY_FEE = 2500;
    private static final int DELIVERY_FREE_PRICE = 50_000;
    private final List<Product> list;

    public Order() {
        this.list = new ArrayList<>();
    }

    public void add(Product product) {
        if (list.contains(product)) {
            int index = list.indexOf(product);
            Product origin = list.get(index);
            origin.increaseCount(product.getCount());
            return;
        }
        list.add(product);
    }

    public List<Product> getList() {
        if (list.isEmpty())
            throw new NoOrderException("주문 목록이 비어있습니다.");
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
