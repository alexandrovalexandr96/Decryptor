package decrypt.decryptor.frequency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyAnalysisTest {

    @Test
    void computeFrequenciesCountsSingleLetter() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("a");
        assertEquals(1, result.getNumberOfAppearances('a'));
    }

    @Test
    void computeFrequenciesCountsMultipleOccurrences() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("aaa");
        assertEquals(3, result.getNumberOfAppearances('a'));
    }

    @Test
    void computeFrequenciesConvertsUppercaseToLowercase() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("Aa");
        assertEquals(2, result.getNumberOfAppearances('a'));
    }

    @Test
    void computeFrequenciesCountsNonLetterAsIs() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("!!");
        assertEquals(2, result.getNumberOfAppearances('!'));
    }

    @Test
    void computeFrequenciesHandlesComplexExample() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("ab!Bc");
        assertEquals(1, result.getNumberOfAppearances('a'));
        assertEquals(2, result.getNumberOfAppearances('b'));
        assertEquals(1, result.getNumberOfAppearances('c'));
        assertEquals(1, result.getNumberOfAppearances('!'));
    }

    @Test
    void computeFrequenciesHandlesEmptyString() {
        FrequencyAnalysisResult result = FrequencyAnalysis.computeCharacterFrequencies("");
        assertEquals(0, result.getNumberOfAppearances('a'));
    }

    @Test
    void computeFrequenciesThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
            () -> FrequencyAnalysis.computeCharacterFrequencies(null));
    }
}
