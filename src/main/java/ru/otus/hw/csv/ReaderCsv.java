package ru.otus.hw.csv;

import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Locale;

public interface ReaderCsv {
    List<Question> getQuestions(Locale locale);
}
