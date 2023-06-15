package kr.co._29cm.homework.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PromptPrinter {
    private final BufferedReader bufferedReader;
    public String getCommand() throws IOException {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        return bufferedReader.readLine();
    }

    public String getProductId() throws IOException {
        System.out.print("상품번호 : ");
        return bufferedReader.readLine().trim();
    }

    public int getProductCount() throws IOException {
        System.out.print("수량 : ");
        return Integer.parseInt(bufferedReader.readLine());
    }

    public void printEndMessage() {
        System.out.println("고객님의 주문 감사합니다.");
    }

    public void printWrongInput() {
        System.out.println("잘못된 입력입니다.");
    }

    private boolean isEmpty(String id) {
        return !StringUtils.hasText(id.trim());
    }
}
