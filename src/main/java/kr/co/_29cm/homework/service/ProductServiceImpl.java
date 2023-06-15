package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product findProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(()-> new NoSuchElementException("상품이 존재하지 않습니다."));
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>)productRepository.findAll();
    }
}
