package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Test
    void getProductBy_정상() {
        //given
        Product product = productService.getProductBy("768848");

        //then
        assertThat(product.getId()).isEqualTo("768848");
        assertThat(product.getName()).isEqualTo("[STANLEY] GO CERAMIVAC 진공 텀블러/보틀 3종");
        assertThat(product.getPrice()).isEqualTo(21000);
        assertThat(product.getCount()).isEqualTo(45);
    }

    @Test
    void getProductBy_상품없음() {
        //given
        assertThatCode(()->productService.getProductBy("00123"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("상품이 존재하지 않습니다.");
    }

    @Test
    void findAllProducts() {
        //when
        List<Product> products = productService.findAllProducts();

        //then
        assertThat(products.size()).isEqualTo(19);

    }
}
