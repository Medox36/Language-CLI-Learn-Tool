package ch.giuntini.languageclilearntool;

import java.io.IOException;
import java.util.List;

public class LearnUtil {

    private final TranslationFileReader fileReader;
    private List<Sentence> sentences;

    public LearnUtil() {
        fileReader = new TranslationFileReader();
        try {
            sentences = fileReader.readSentences();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reReadFile() {
        try {
            sentences = fileReader.readSentences();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
