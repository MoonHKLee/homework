package kr.co._29cm.homework.domain;

import kr.co._29cm.homework.exception.NoOrderException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void add_빈목록에_상품추가() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);

        //when
        order.add(product1);

        //then
        assertThat(order.getList().contains(product1)).isTrue();
    }

    @Test
    void add_목록에_이미_존재하는_상품추가() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("1","product1", 1000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.getList().size()).isEqualTo(1);
        assertThat(order.getList().get(0).getCount()).isEqualTo(2);
    }

    @Test
    void getList_정상호출() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("2","product2", 1000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.getList()).hasSize(2);
    }

    @Test
    void getList_비어있음() {
        //given
        Order order = new Order();

        //then
        assertThatCode(order::getList).isInstanceOf(NoOrderException.class)
                .hasMessage("주문 목록이 비어있습니다.");
    }

    @Test
    void getOrderPrice_주문액수() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("2","product2", 2000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.getOrderPrice()).isEqualTo(3000);
    }

    @Test
    void getTotalPrice_무료배달비_이상() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("2","product2", 49_000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.getTotalPrice()).isEqualTo(50_000);
    }

    @Test
    void getTotalPrice_무료배달비_미만() {
        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("2","product2", 4000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.getTotalPrice()).isEqualTo(7500);
    }

    @Test
    void getDeliveryFee() {
        //given
        Order order = new Order();

        //then
        assertThat(order.getDeliveryFee()).isEqualTo(2500);
    }

    @Test
    void isFreeDelivery_50000_이상() {

        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 50000, 1);

        //when
        order.add(product1);

        //then
        assertThat(order.isFreeDelivery()).isTrue();
    }

    @Test
    void isFreeDelivery_50000_미만() {

        //given
        Order order = new Order();
        Product product1 = new Product("1","product1", 1000, 1);
        Product product2 = new Product("2","product2", 4000, 1);

        //when
        order.add(product1);
        order.add(product2);

        //then
        assertThat(order.isFreeDelivery()).isFalse();
    }
}
