package kr.co._29cm.homework.domain;

import kr.co._29cm.homework.exception.SoldOutException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {
    @Id
    private String id;
    private String name;
    private int price;
    private int count;

    public void increaseCount(int count) {
        this.count += count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void decreaseCount(int count) {
        if (this.count < count) {
            throw new SoldOutException("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
        }
        this.count -= count;
    }
}
