package kr.co._29cm.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private int price;
    private int count;

}
