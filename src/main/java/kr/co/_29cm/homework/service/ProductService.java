package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findProductById(String productId);
    List<Product> findAllProducts();
}
