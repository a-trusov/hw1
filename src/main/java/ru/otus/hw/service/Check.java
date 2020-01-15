package ru.otus.hw.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.hw.csv.ReaderCsv;
import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
@PropertySource("classpath:application.properties")
public class Check {
    private ReaderCsv reader;
    private MessageSource messageSource;
    private double goodPercent;
    private List<Question> questions;

    public Check(ReaderCsv reader, MessageSource messageSource,
                 @Value("${good.percent}")double goodPercent) {
        this.reader = reader;
        this.messageSource = messageSource;
        this.goodPercent = goodPercent;
    }

    public void start() {
        int count = 0;
        StringBuilder names = new StringBuilder();

        Locale locale = Locale.ROOT;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please choose your language!\nПожалуйста выберите ваш язык!\nEN or RU");
            locale.setDefault(getLocale(scanner.nextLine().toLowerCase()));
            questions = reader.getQuestions(locale);

            int goodResult = (int)(questions.size() * goodPercent);

            System.out.println(messageSource.getMessage("second.name", null, locale));
            names.append(scanner.nextLine());

            System.out.println(messageSource.getMessage("first.name", null, locale));
            names.append(" ");
            names.append(scanner.nextLine());
            String name = names.toString();

            System.out.println(messageSource.getMessage("ask.question",  new String[]{name}, locale));

            for (Question question : questions) {
                System.out.println(question.getQuestion());
                if (scanner.nextLine().equalsIgnoreCase(question.getAnswer())) {
                    count++;
                }
            }

            if (count >= goodResult) {
                System.out.println(messageSource.getMessage("answer.good",  new String[]{name}, locale));
            } else {
                System.out.println(messageSource.getMessage("answer.bad", new String[]{name}, locale));
            }

        }
    }

    private Locale getLocale(String locales) {
        Locale locale = null;
        switch (locales) {
            case "en":
                locale = Locale.ENGLISH;
                break;
            case "ru":
            default:
                locale = Locale.getDefault();
        }
        return locale;
    }

    public ReaderCsv getReader() {
        return reader;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public double getGoodPercent() {
        return goodPercent;
    }
}
