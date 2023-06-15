package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.exception.NoOrderException;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.ui.OrderPrinter;
import kr.co._29cm.homework.ui.ProductPrinter;
import kr.co._29cm.homework.ui.Prompter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class Controller {
    private final Prompter prompter;
    private final OrderService orderService;
    private final ProductService productService;

    public void run() throws IOException {
        do {
            try {
                String input = prompter.getCommand();
                if (isQuitCommand(input)) {
                    prompter.printEndMessage();
                    break;
                }
                if (isOrderCommand(input)) {
                    List<Product> products = productService.findAllProducts();
                    ProductPrinter.printList(products);
                    Order order = order();
                    OrderPrinter.printList(order);
                    OrderPrinter.printPrice(order);
                    continue;
                }
                prompter.printWrongInput();
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
            String productId = prompter.getProductId();
            if (productId.isEmpty()) {
                break;
            }
            try {
                Product product = productService.getProductBy(productId);
                int count = prompter.getProductCount();
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
