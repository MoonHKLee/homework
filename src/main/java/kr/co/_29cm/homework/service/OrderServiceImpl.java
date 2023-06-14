package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    @Override
    public void order(Order order) {
        List<Product> all = (List<Product>)productRepository.findAll();
    }
}
