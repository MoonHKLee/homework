package kr.co._29cm.homework.ui;

import kr.co._29cm.homework.domain.Product;

import java.util.List;

public class ProductPrinter {
    public static void printList(List<Product> products) {
        System.out.println(
            getFormat(20, "상품번호") +
            getFormat(70, "상품명") +
            getFormat(20, "판매가격") +
            getFormat(10, "재고수"));

        products.forEach((product) -> System.out.println(
                        getFormat(20, product.getId()) +
                        getFormat(70, product.getName()) +
                        getFormat(20, product.getPrice() + "") +
                        getFormat(10, product.getCount() + "")));
    }

    private static String getFormat(int width, String name) {
        return String.format("%-" + width + "s", name);
    }
}
