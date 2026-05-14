package decrypt.decryptor.frequency;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyAnalysisResultTest {

    @Test
    void getNumberOfAppearancesReturnsCountForPresentLetter() {
        FrequencyAnalysisResult result = new FrequencyAnalysisResult(Map.of('a', 3));
        assertEquals(3, result.getNumberOfAppearances('a'));
    }

    @Test
    void getNumberOfAppearancesReturnsZeroForAbsentLetter() {
        FrequencyAnalysisResult result = new FrequencyAnalysisResult(Map.of('a', 1));
        assertEquals(0, result.getNumberOfAppearances('z'));
    }

    @Test
    void getNumberOfAppearancesThrowsForUppercaseLetter() {
        FrequencyAnalysisResult result = new FrequencyAnalysisResult(Map.of('a', 1));
        assertThrows(IllegalArgumentException.class, () -> result.getNumberOfAppearances('A'));
    }

    @Test
    void constructorThrowsForUppercaseLetterKey() {
        assertThrows(IllegalArgumentException.class,
            () -> new FrequencyAnalysisResult(Map.of('A', 1)));
    }

    @Test
    void constructorThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new FrequencyAnalysisResult(null));
    }

    @Test
    void constructorAllowsNonLetterKeys() {
        FrequencyAnalysisResult result = new FrequencyAnalysisResult(Map.of('!', 5));
        assertEquals(5, result.getNumberOfAppearances('!'));
    }

    @Test
    void constructorDoesNotAllowMutationViaOriginalMap() {
        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();
        map.put('a', 1);
        FrequencyAnalysisResult result = new FrequencyAnalysisResult(map);
        map.put('b', 99);
        assertEquals(0, result.getNumberOfAppearances('b'));
    }

    @Test
    void constructorThrowsForNullValue() {
        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();
        map.put('a', null);
        assertThrows(IllegalArgumentException.class, () -> new FrequencyAnalysisResult(map));
    }

    @Test
    void constructorThrowsForNullKey() {
        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();
        map.put(null, 1);
        assertThrows(IllegalArgumentException.class, () -> new FrequencyAnalysisResult(map));
    }

    @Test
    void constructorThrowsForNegativeFrequency() {
        assertThrows(IllegalArgumentException.class,
            () -> new FrequencyAnalysisResult(Map.of('a', -1)));
    }
}
