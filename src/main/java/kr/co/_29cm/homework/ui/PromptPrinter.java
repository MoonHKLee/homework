package kr.co._29cm.homework.ui;

import org.springframework.stereotype.Component;

@Component
public class PromptPrinter {
    public void print() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
    }

    public void printProductNumber() {
        System.out.print("상품번호 : ");
    }

    public void printProductCount() {
        System.out.print("수량 : ");
    }

    public void printEndMessage() {
        System.out.println("고객님의 주문 감사합니다.");
    }

    public void printWrongInput() {
        System.out.println("잘못된 입력입니다.");
    }
}
