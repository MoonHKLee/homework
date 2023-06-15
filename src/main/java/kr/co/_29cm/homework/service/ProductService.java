package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(String productId);
    List<Product> findAllProducts();
}
