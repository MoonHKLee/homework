package kr.co._29cm.homework;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.ProductRepository;
import kr.co._29cm.homework.domain.Products;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.ui.OrderPrinter;
import kr.co._29cm.homework.ui.ProductPrinter;
import kr.co._29cm.homework.ui.PromptPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Controller {
    private final BufferedReader bufferedReader;
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final ProductPrinter productPrinter;
    private final PromptPrinter promptPrinter;
    private final OrderPrinter orderPrinter;

    public void run() throws IOException {
        do {
            try {
                promptPrinter.print();
                String input = bufferedReader.readLine();
                if (isQuitCommand(input)) {
                    promptPrinter.printEndMessage();
                    break;
                }
                if (isOrderCommand(input)) {
                    productPrinter.print();
                    Order order = new Order();
                    setUpOrderDataFromInput(order);
                    orderService.order(order);
                    orderPrinter.printList(order);
                    orderPrinter.printPrice(order);
                    continue;
                }
                promptPrinter.printWrongInput();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private void setUpOrderDataFromInput(Order order) throws IOException {
        while (true) {
            promptPrinter.printProductNumber();
            String productId = bufferedReader.readLine();
            if (isEmpty(productId)) {
                break;
            }
            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                productPrinter.printNoProduct();
                continue;
            }
            promptPrinter.printProductCount();
            int count = Integer.parseInt(bufferedReader.readLine());
            Product newProduct = product.get();
            order.add(new Product(productId, newProduct.getName(), newProduct.getPrice(), count));
        }
    }

    private static boolean isEmpty(String id) {
        return !StringUtils.hasText(id.trim());
    }

    private boolean isQuitCommand(String input) {
        return input.equals("q");
    }

    private boolean isOrderCommand(String input) {
        return input.equals("o");
    }
}
