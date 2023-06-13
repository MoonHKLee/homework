package kr.co._29cm.homework;

import kr.co._29cm.homework.ui.ProductPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HomeworkApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);
        ProductPrinter.print();
    }

}
