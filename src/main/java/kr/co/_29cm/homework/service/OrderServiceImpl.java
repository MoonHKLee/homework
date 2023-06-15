package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public void order(Order order) {
        List<Product> list = order.getList();
        list.forEach(this::updateCount);
    }

    private void updateCount(Product v) {
        Product product = productRepository.findById(v.getId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        product.decreaseCount(v.getCount());
        productRepository.save(product);
    }
}
