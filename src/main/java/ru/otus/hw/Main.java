package ru.otus.hw;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.service.Check;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Check check = context.getBean(Check.class);
        check.start();
    }
}
