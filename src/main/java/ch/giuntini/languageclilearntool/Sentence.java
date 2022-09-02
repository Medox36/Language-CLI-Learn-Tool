package ch.giuntini.languageclilearntool;

public class Sentence {
    private final String originalSentence;
    private final String originalTranslation;
    private String blankedTranslation;
    private String[] removedWords;

    public Sentence(String originalSentence, String originalTranslation) {
        this.originalSentence = originalSentence;
        this.originalTranslation = originalTranslation;
    }

    public void prepare(int rmNWords, Difficulty difficulty) {
        WordUtil.getInstance().removeWords(this, rmNWords, difficulty);
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

    public void setBlankedTranslation(String blankedTranslation) {
        this.blankedTranslation = blankedTranslation;
    }

    public String[] getRemovedWords() {
        return removedWords;
    }

    public void setRemovedWords(String[] removedWords) {
        this.removedWords = removedWords;
    }
}
