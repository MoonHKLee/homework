package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.SoldOutException;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.ui.OrderPrinter;
import kr.co._29cm.homework.ui.ProductPrinter;
import kr.co._29cm.homework.ui.PromptPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class Controller {
    private final BufferedReader bufferedReader;
    private final OrderService orderService;
    private final ProductService productService;

    public void run() throws IOException {
        do {
            try {
                String input = PromptPrinter.getCommand(bufferedReader);
                if (isQuitCommand(input)) {
                    PromptPrinter.printEndMessage();
                    break;
                }
                if (isOrderCommand(input)) {
                    ProductPrinter.printList(productService.findAllProducts());
                    Order order = order();
                    OrderPrinter.printList(order);
                    OrderPrinter.printPrice(order);
                    continue;
                }
                PromptPrinter.printWrongInput();
            } catch (SoldOutException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private Order order() throws IOException {
        Order order = new Order();
        setUpOrderDataFromInput(order);
        orderService.order(order);
        return order;
    }

    private void setUpOrderDataFromInput(Order order) throws IOException {
        while (true) {
            String productId = PromptPrinter.getProductId(bufferedReader);
            if (isEmpty(productId)) {
                break;
            }
            try {
                Product product = productService.findProductById(productId);
                int count = PromptPrinter.getProductCount(bufferedReader);
                order.add(new Product(productId, product.getName(), product.getPrice(), count));
            } catch (NoSuchElementException e) {
                ProductPrinter.printNoProduct();
            }
        }
    }

    private boolean isEmpty(String id) {
        return !StringUtils.hasText(id.trim());
    }

    private boolean isQuitCommand(String input) {
        return input.equals("q");
    }

    private boolean isOrderCommand(String input) {
        return input.equals("o");
    }
}
