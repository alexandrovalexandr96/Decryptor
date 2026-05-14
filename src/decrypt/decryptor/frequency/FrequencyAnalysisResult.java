package decrypt.decryptor.frequency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysisResult {
    private final Map<Character, Integer> charToFrequency;

    public FrequencyAnalysisResult(Map<Character, Integer> charToFrequency) {
        if (charToFrequency == null) {
            throw new IllegalArgumentException("charToFrequency must not be null");
        }
        for (Map.Entry<Character, Integer> entry : charToFrequency.entrySet()) {
            if (entry.getKey() == null) {
                throw new IllegalArgumentException("charToFrequency must not contain null keys");
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("charToFrequency must not contain null values");
            }
            if (entry.getValue() < 0) {
                throw new IllegalArgumentException(
                    "Frequency must not be negative, got: " + entry.getValue() + " for '" + entry.getKey() + "'"
                );
            }
            char c = entry.getKey();
            if (Character.isLetter(c) && Character.isUpperCase(c)) {
                throw new IllegalArgumentException("Letters in charToFrequency must be lowercase, got: " + c);
            }
        }
        this.charToFrequency = Collections.unmodifiableMap(new HashMap<>(charToFrequency));
    }

    public int getNumberOfAppearances(char c) {
        if (Character.isLetter(c) && Character.isUpperCase(c)) {
            throw new IllegalArgumentException("Letter must be lowercase, got: " + c);
        }
        return charToFrequency.getOrDefault(c, 0);
    }
}
