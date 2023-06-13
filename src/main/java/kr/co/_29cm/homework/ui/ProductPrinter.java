package kr.co._29cm.homework.ui;

import kr.co._29cm.homework.domain.Products;

public class ProductPrinter {
    public static void print() {
        System.out.println(
            getFormat(20, "상품번호") +
            getFormat(70, "상품명") +
            getFormat(20, "판매가격") +
            getFormat(10, "재고수"));

        Products.getTable()
                .forEach((id, product) -> System.out.println(
                        getFormat(20, id) +
                        getFormat(70, product.getName()) +
                        getFormat(20, product.getPrice() + "") +
                        getFormat(10, product.getCount() + "")));
    }

    private static String getFormat(int width, String name) {
        return String.format("%-" + width + "s", name);

    }
}
