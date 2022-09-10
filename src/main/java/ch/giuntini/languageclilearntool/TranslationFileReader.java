package ch.giuntini.languageclilearntool;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TranslationFileReader {

    private final File file;

    public TranslationFileReader() {
        file = new File("translations.txt");
    }

    public List<Sentence> readSentences() throws IOException {
        if (!file.exists()) {
            if (file.createNewFile()) {
                Files.write(file.toPath(), StandardSentences.TEXT.getBytes(StandardCharsets.UTF_8));
            } else {
                throw new FileCreateException("The file for saving the translations couldn't be created");
            }
        }
        List<String> stringList = Files.readAllLines(file.toPath());
        List<Sentence> sentences = new ArrayList<>();
        for (String str : stringList) {
            // ignore comments
            if (str.startsWith("##") || str.isBlank()) {
                continue;
            }
            String[] temp = str.split(";");
            sentences.add(new Sentence(temp[0], temp[1]));
        }

        return sentences;
    }

}
