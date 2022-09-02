package ch.giuntini.languageclilearntool;

import java.util.Arrays;
import java.util.Collections;

public class WordUtil {

    private static WordUtil INSTANCE;

    public void removeWords(Sentence sentence, int rmNWords, Difficulty difficulty, boolean isTranslation) {
        String workingString = isTranslation ? sentence.getOriginalTranslation() : sentence.getOriginalSentence();

        int wordCount = countWords(workingString);
        if (rmNWords > wordCount) {
            throw new IllegalArgumentException("can't remove more words than are in the sentence, words in sentence:"
                    + wordCount);
        }
        String[] splitWords = splitWords(workingString);
        String[] original = splitWords.clone();
        double[] weight = weightWords(workingString);
        int[] rmIndex = new int[rmNWords];

        switch (difficulty) {
            case EASY: {
                int[] complexOrder = sortWeightAscAndCompare(weight);
                for (int i = 0; i < rmNWords; i++) {
                    if (rmNWords < wordCount && Math.random() > 0.68 && i+1 < wordCount) {
                        rmIndex[i] = complexOrder[i + 1];
                    } else {
                        rmIndex[i] = complexOrder[i];
                    }
                }
                blankWordsAtIndexesExactCharCount(splitWords, rmIndex);
                break;
            }
            case NORMAL: {
                int[] complexOrder = sortWeightAscAndCompare(weight);
                for (int i = 0; i < rmNWords; i++) {
                    if (rmNWords < wordCount && Math.random() > 0.34&& i+1 < wordCount) {
                        rmIndex[i] = complexOrder[i + 1];
                    } else {
                        rmIndex[i] = complexOrder[i];
                    }
                }
                blankWordsAtIndexesExactCharCount(splitWords, rmIndex);
                break;
            }
            case HARD: {
                int[] complexOrder = sortWeightDescAndCompare(weight);
                for (int i = 0; i < rmNWords; i++) {
                    if (rmNWords < wordCount && Math.random() > 0.76 && i+1 < wordCount) {
                        rmIndex[i] = complexOrder[i + 1];
                    } else {
                        rmIndex[i] = complexOrder[i];
                    }
                }
                blankWordsAtIndexes(splitWords, rmIndex);
                break;
            }
            case EXTREME: {
                blankAllWords(splitWords);
                break;
            }
        }

        String[] removedWords = new String[rmNWords];
        rmIndex = Arrays.stream(rmIndex).sorted().toArray();

        int i = 0;
        for (int index : rmIndex) {
            removedWords[i] = original[index];
            i++;
        }

        if (isTranslation) {
            sentence.setBlankedTranslation(arrayToSentence(splitWords));
            sentence.setRemovedTranslationWords(removedWords);
        } else {
            sentence.setBlankedOriginal(arrayToSentence(splitWords));
            sentence.setRemovedOriginalWords(removedWords);
        }
    }

    public void blankWordsAtIndexesExactCharCount(String[] words, int[] indexes) {
        for (int index : indexes) {
            int tempLength = words[index].length();
            words[index] = "_".repeat(tempLength);
        }
    }

    private void blankWordsAtIndexes(String[] words, int[] indexes) {
        for (int index : indexes) {
            words[index] = "_".repeat(8);
        }
    }

    private void blankAllWords(String[] words) {
        Arrays.fill(words, "_".repeat(8));
    }

    private void blankAllWordsExactCharCount(String[] words) {
        for (int i = 0; i < words.length; i++) {
            int tempLength = words[i].length();
            words[i] = "_".repeat(tempLength);
        }
    }

    private int[] sortWeightDescAndCompare(double[] originalWeight) {
        double[] temp = originalWeight.clone();
        temp = Arrays.stream(temp).boxed().sorted(Collections.reverseOrder()).mapToDouble(Double::doubleValue).toArray();

        return sortIndexes(originalWeight, temp);
    }

    private int[] sortWeightAscAndCompare(double[] originalWeight) {
        double[] temp = originalWeight.clone();
        temp = Arrays.stream(temp).boxed().sorted().mapToDouble(Double::doubleValue).toArray();

        return sortIndexes(originalWeight, temp);
    }

    private int[] sortIndexes(double[] originalWeight, double[] temp) {
        int[] oldIndexes = new int[originalWeight.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < originalWeight.length; j++) {
                if (temp[j] == originalWeight[i]) {
                    oldIndexes[i] = j;
                }
            }
        }

        return oldIndexes;
    }

    private int countWords(String sentence) {
        return splitWords(sentence).length;
    }

    private String[] splitWords(String sentence) {
        return sentence.split("\\s+");
    }

    private double[] weightWords(String sentence) {
        int wordCount = countWords(sentence);
        double[] weight = new double[wordCount];
        String[] words = splitWords(sentence);

        for (int i = 0; i < wordCount; i++) {
            weight[i] = (words[i].length() * 2) / 27d;
            if (Character.isUpperCase(words[i].charAt(0))) {
                weight[i] += 0.002;
                if (words[i].endsWith("s")) {
                    weight[i] += 0.004;
                }
            } else {
                weight[i] += 0.003;
            }
            if (words[i].contains("'")) {
                weight[i] += 0.006;
            }
            //TODO experiment more with complexity
        }

        return weight;
    }

    public String arrayToSentence(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i + 1 != arr.length) {
                sb.append(" ");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public static WordUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WordUtil();
        }
        return INSTANCE;
    }
}
