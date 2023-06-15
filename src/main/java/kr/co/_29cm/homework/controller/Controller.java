package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.NoOrderException;
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
    private final PromptPrinter promptPrinter;
    private final OrderService orderService;
    private final ProductService productService;

    public void run() throws IOException {
        do {
            try {
                String input = promptPrinter.getCommand();
                if (isQuitCommand(input)) {
                    promptPrinter.printEndMessage();
                    break;
                }
                if (isOrderCommand(input)) {
                    ProductPrinter.printList(productService.findAllProducts());
                    Order order = order();
                    OrderPrinter.printList(order);
                    OrderPrinter.printPrice(order);
                    continue;
                }
                promptPrinter.printWrongInput();
            } catch (SoldOutException | NoOrderException e) {
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
            String productId = promptPrinter.getProductId();
            if (productId.isEmpty()) {
                break;
            }
            try {
                Product product = productService.findProductById(productId);
                int count = promptPrinter.getProductCount();
                order.add(new Product(productId, product.getName(), product.getPrice(), count));
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean isQuitCommand(String input) {
        return input.equals("q");
    }

    private boolean isOrderCommand(String input) {
        return input.equals("o");
    }
}
