package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;

@SpringBootApplication
public class HomeworkApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(HomeworkApplication.class, args);
        Controller controller = context.getBean(Controller.class);
        controller.run();
        BufferedReader bufferedReader = context.getBean(BufferedReader.class);
        bufferedReader.close();
    }
}
