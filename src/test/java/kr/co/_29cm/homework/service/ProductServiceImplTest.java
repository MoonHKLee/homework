package kr.co._29cm.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Test
    void getProductBy() {
    }

    @Test
    void findAllProducts() {
    }
}
