package decrypt.decryptor.frequency;

import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysis {
    private FrequencyAnalysis() {
    }

    public static FrequencyAnalysisResult computeCharacterFrequencies(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : data.toCharArray()) {
            char key = Character.isLetter(c) ? Character.toLowerCase(c) : c;
            frequencies.put(key, frequencies.getOrDefault(key, 0) + 1);
        }
        return new FrequencyAnalysisResult(frequencies);
    }
}
