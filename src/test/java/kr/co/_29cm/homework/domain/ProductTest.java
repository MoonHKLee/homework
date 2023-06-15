package kr.co._29cm.homework.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ProductTest {

    @Test
    void increaseCount_정상동작() {
        //given
        Product product = new Product("1","product1", 1000, 1);

        //when
        product.increaseCount(10);

        //then
        assertThat(product.getCount()).isEqualTo(11);
    }

    @Test
    void decreaseCount_정상동작() {
        //given
        Product product = new Product("1","product1", 1000, 10);

        //when
        product.decreaseCount(10);

        //then
        assertThat(product.getCount()).isEqualTo(0);
    }

    @Test
    void decreaseCount_재고부족() {
        //given
        Product product = new Product("1","product1", 1000, 10);

        //then
        assertThatCode(() -> product.decreaseCount(11))
                .hasMessage("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
    }
}
