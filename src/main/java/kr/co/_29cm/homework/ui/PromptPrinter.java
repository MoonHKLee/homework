package kr.co._29cm.homework.ui;

import java.io.BufferedReader;
import java.io.IOException;

public class PromptPrinter {
    public static String getCommand(BufferedReader bufferedReader) throws IOException {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        return bufferedReader.readLine();
    }

    public static String getProductId(BufferedReader bufferedReader) throws IOException {
        System.out.print("상품번호 : ");
        return bufferedReader.readLine();
    }

    public static int getProductCount(BufferedReader bufferedReader) throws IOException {
        System.out.print("수량 : ");
        return Integer.parseInt(bufferedReader.readLine());
    }

    public static void printEndMessage() {
        System.out.println("고객님의 주문 감사합니다.");
    }

    public static void printWrongInput() {
        System.out.println("잘못된 입력입니다.");
    }
}
