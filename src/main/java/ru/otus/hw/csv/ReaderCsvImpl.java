package ru.otus.hw.csv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.hw.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@PropertySource("classpath:application.properties")
public class ReaderCsvImpl implements ReaderCsv {
    private String filePathRu;
    private String filePathEn;

    public ReaderCsvImpl(@Value("${filePathRu}")String filePathRu, @Value("${filePathEn}")String filePathEn) {
        this.filePathRu = filePathRu;
        this.filePathEn = filePathEn;
    }

    public String getFilePathRu() {
        return filePathRu;
    }

    public String getFilePathEn() {
        return filePathEn;
    }

    @Override
    public List<Question> getQuestions(Locale locale) {
        List<Question> result;

        if(locale.getDefault().equals(Locale.ENGLISH)){
            result = getLocaleQuestions(filePathEn);
        }else{
            result = getLocaleQuestions(filePathRu);
        }
        return result;
    }

    public List<Question> getLocaleQuestions(String filePath) {
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
