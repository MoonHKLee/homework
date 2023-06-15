package kr.co._29cm.homework.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    Optional<Product> findProductById(String id);
}
