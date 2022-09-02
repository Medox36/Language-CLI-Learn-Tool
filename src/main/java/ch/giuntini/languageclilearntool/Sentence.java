package ch.giuntini.languageclilearntool;

public class Sentence {
    private final String originalSentence;
    private final String originalTranslation;
    private String blankedTranslation;
    private String[] removedWords;

    public Sentence(String originalSentence, String translation) {
        this.originalSentence = originalSentence;
        originalTranslation = translation;
    }

    public void prepare(int rmNWords, Difficulty difficulty) {
        String[] temp = WordUtil.getInstance().removeWords(originalTranslation, rmNWords, difficulty);
        blankedTranslation = temp[0];
        removedWords = new String[temp.length - 1];
        System.arraycopy(temp, 1, removedWords, 0, temp.length - 1);
    }

    public String getOriginalSentence() {
        return originalSentence;
    }

    public String getOriginalTranslation() {
        return originalTranslation;
    }

    public String getBlankedTranslation() {
        return blankedTranslation;
    }

    public String[] getRemovedWords() {
        return removedWords;
    }
}
