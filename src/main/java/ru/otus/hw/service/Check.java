package ru.otus.hw.service;

import ru.otus.hw.csv.ReaderCsv;
import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Scanner;

public class Check {
    private ReaderCsv reader;

    public Check(ReaderCsv reader) {
        this.reader = reader;
    }

    public void start() {
        String firstName;
        String lastName;
        int count = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            List<Question> questions = reader.getQuestions();
            System.out.println("Фамилия?");
            lastName = scanner.nextLine();
            System.out.println("Имя?");
            firstName = scanner.nextLine();

            String name = firstName + " " + lastName;
            System.out.println(name + " ответтьте на вопросы");

            for (Question question : questions) {
                System.out.println(question.getQuestion());
                if (scanner.nextLine().equalsIgnoreCase(question.getAnswer())) {
                    count++;
                }
            }

            if (count >= questions.size() * 70/100) {
                System.out.println(name + " - тест сдан");
            } else {
                System.out.println(name + " - тест НЕ сдан");
            }

        }
    }
}
