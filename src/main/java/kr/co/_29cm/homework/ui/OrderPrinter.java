package kr.co._29cm.homework.ui;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderPrinter {
    public void printList(Order order) {
        System.out.println("주문내역:");
        System.out.println("--------------------------------------------");
        List<Product> list = order.getList();
        list.forEach(v -> System.out.println(v.getName() + " - " + v.getCount() + "개"));
        System.out.println("--------------------------------------------");
    }

    public void printPrice(Order order) {
        System.out.println("주문금액: " + getPriceFormat(order.getOrderPrice())+"원");
        if (!order.isFreeDelivery()) {
            System.out.println("배송비: " + getPriceFormat(order.getDeliveryFee())+"원");
        }
        System.out.println("--------------------------------------------");
        System.out.println("지불금액: " + getPriceFormat(order.getTotalPrice())+"원");
        System.out.println("--------------------------------------------");
    }

    private String getPriceFormat(int price) {
        return String.format("%,d", price);
    }
}
