package ch.giuntini.languageclilearntool;

import java.util.Arrays;
import java.util.Collections;

public class WordUtil {

    public String removeWords(String sentence, int rmNWords) {
        int wordCount = countWords(sentence);
        if (rmNWords > wordCount) {
            throw new IllegalArgumentException("can't remove more words than are in the sentence, words in sentence:"
                    + wordCount);
        }
        String[] splitWords = splitWords(sentence);
        double[] weight = weightWords(sentence);
        int[] complexOrder = sortWeightDescAndCompare(weight);
        int[] rmIndex = new int[rmNWords];

        for (int i = 0; i < rmNWords; i++) {
            if (rmNWords < wordCount && Math.random() > 0.68) {
                rmIndex[i] = complexOrder[i + 1];
            } else {
                rmIndex[i] = complexOrder[i];
            }
        }

        blankWordsAtIndexes(splitWords, rmIndex);

        return arrayToSentence(splitWords);
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

    private int[] sortWeightDescAndCompare(double[] originalWeight) {
        double[] temp = originalWeight.clone();
        temp = Arrays.stream(temp).boxed().sorted(Collections.reverseOrder()).mapToDouble(Double::doubleValue).toArray();

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

    private double getLowestValue(double[] arr) {
        double[] temp = arr.clone();
        Arrays.sort(temp);
        return temp[0];
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

}
