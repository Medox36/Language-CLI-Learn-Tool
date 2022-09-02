package ch.giuntini.languageclilearntool;

public class Sentence {
    private final String originalSentence;
    private final String originalTranslation;
    private String blankedOriginal;
    private String blankedTranslation;
    private String[] removedOriginalWords;
    private String[] removedTranslationWords;

    public Sentence(String originalSentence, String originalTranslation) {
        this.originalSentence = originalSentence;
        this.originalTranslation = originalTranslation;
    }

    public void prepare(int rmNWords, Difficulty difficulty) {
        WordUtil.getInstance().removeWords(this, rmNWords, difficulty, true);
        WordUtil.getInstance().removeWords(this, rmNWords, difficulty, false);
    }

    public String getOriginalSentence() {
        return originalSentence;
    }

    public String getOriginalTranslation() {
        return originalTranslation;
    }

    public String getBlankedOriginal() {
        return blankedOriginal;
    }

    public void setBlankedOriginal(String blankedOriginal) {
        this.blankedOriginal = blankedOriginal;
    }

    public String getBlankedTranslation() {
        return blankedTranslation;
    }

    public void setBlankedTranslation(String blankedTranslation) {
        this.blankedTranslation = blankedTranslation;
    }

    public String[] getRemovedOriginalWords() {
        return removedOriginalWords;
    }

    public void setRemovedOriginalWords(String[] removedOriginalWords) {
        this.removedOriginalWords = removedOriginalWords;
    }

    public String[] getRemovedTranslationWords() {
        return removedTranslationWords;
    }

    public void setRemovedTranslationWords(String[] removedTranslationWords) {
        this.removedTranslationWords = removedTranslationWords;
    }
}
