package decrypt.decryptor.frequency;

import decrypt.decryptor.Decryptor;
import decrypt.substitutor.Substitution;
import decrypt.vocabulary.Vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FrequencyDecryptor implements Decryptor {
    private final Vocabulary vocabulary;

    public FrequencyDecryptor(Vocabulary vocabulary) {
        if (vocabulary == null) {
            throw new IllegalArgumentException("Vocabulary must not be null");
        }
        this.vocabulary = vocabulary;
    }

    @Override
    public List<Substitution> decrypt(String data) {
        // NOTE: Запрещается менять реализацию этого метода!
        FrequencyAnalysisResult frequencyAnalysisResult = FrequencyAnalysis.computeCharacterFrequencies(data);
        List<Substitution> substitutions = createSubstitutions(frequencyAnalysisResult);
        return Collections.unmodifiableList(substitutions);
    }

    private List<Substitution> createSubstitutions(FrequencyAnalysisResult analysisResult) {
        List<Character> alphabetLetters = new ArrayList<>(vocabulary.getLetters());
        alphabetLetters.sort(
            Comparator
                .comparingInt((Character c) -> analysisResult.getNumberOfAppearances(c))
                .reversed()
                .thenComparingInt(c -> vocabulary.getIndexOfLetter(c))
        );
        List<Character> frequencyOrder = vocabulary.getLettersOrderedByFrequency();
        List<Substitution> substitutions = new ArrayList<>(alphabetLetters.size());
        for (int i = 0; i < alphabetLetters.size(); i++) {
            substitutions.add(new Substitution(alphabetLetters.get(i), frequencyOrder.get(i)));
        }
        return substitutions;
    }
}
