package ru.otus.hw.csv;

import ru.otus.hw.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCsvImpl implements ReaderCsv {
    private String filePath;

    public ReaderCsvImpl() {
    }

    public ReaderCsvImpl(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream(filePath), "utf-8"))) {
            String str ="";
            while((str = reader.readLine()) != null) {
                String[] record = str.split(";");
                result.add(new Question(record[0], record[1]));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
