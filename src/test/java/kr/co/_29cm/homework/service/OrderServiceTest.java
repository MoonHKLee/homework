package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.ProductRepository;
import kr.co._29cm.homework.exception.SoldOutException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;
    @Test
    void order_재고남음() {
        Order order = new Order();
        order.add(new Product("768848","product1", 1000, 5));

        //when
        orderService.order(order);

        //then
        Product product = productRepository.findById("768848")
                .orElseThrow(()-> new NoSuchElementException("해당 상품이 존재하지 않습니다."));
        assertThat(product.getCount()).isEqualTo(40);
    }

    @Test
    void order_재고없음() {
        //given
        Order order = new Order();
        order.add(new Product("768848","product1", 1000, 50));

        //then
        assertThatCode(()->orderService.order(order))
                .isInstanceOf(SoldOutException.class)
                .hasMessage("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");

    }
}
