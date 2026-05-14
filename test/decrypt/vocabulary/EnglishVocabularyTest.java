package decrypt.vocabulary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EnglishVocabularyTest {

    private EnglishVocabulary vocabulary;

    @BeforeEach
    void setUp() {
        vocabulary = new EnglishVocabulary();
    }

    @Test
    void getLettersReturns26Letters() {
        assertEquals(26, vocabulary.getLetters().size());
    }

    @Test
    void getLettersContainsAllEnglishLetters() {
        Set<Character> letters = vocabulary.getLetters();
        for (char c = 'a'; c <= 'z'; c++) {
            assertTrue(letters.contains(c), "Missing letter: " + c);
        }
    }

    @Test
    void getLettersAreAllLowercase() {
        for (char c : vocabulary.getLetters()) {
            assertFalse(Character.isUpperCase(c), "Uppercase letter found: " + c);
        }
    }

    @Test
    void getLettersOrderedByFrequencyReturns26Letters() {
        assertEquals(26, vocabulary.getLettersOrderedByFrequency().size());
    }

    @Test
    void getLettersOrderedByFrequencyFirstLetterIsE() {
        assertEquals('e', vocabulary.getLettersOrderedByFrequency().get(0));
    }

    @Test
    void getLettersOrderedByFrequencyLastLetterIsZ() {
        List<Character> ordered = vocabulary.getLettersOrderedByFrequency();
        assertEquals('z', ordered.get(ordered.size() - 1));
    }

    @Test
    void getLettersOrderedByFrequencyIsUnmodifiable() {
        List<Character> ordered = vocabulary.getLettersOrderedByFrequency();
        assertThrows(UnsupportedOperationException.class, () -> ordered.add('a'));
    }

    @Test
    void getLettersIsUnmodifiable() {
        Set<Character> letters = vocabulary.getLetters();
        assertThrows(UnsupportedOperationException.class, () -> letters.add('a'));
    }

    @Test
    void getIndexOfLetterReturnsZeroForA() {
        assertEquals(0, vocabulary.getIndexOfLetter('a'));
    }

    @Test
    void getIndexOfLetterReturns25ForZ() {
        assertEquals(25, vocabulary.getIndexOfLetter('z'));
    }

    @Test
    void getIndexOfLetterThrowsForUppercase() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getIndexOfLetter('A'));
    }

    @Test
    void getIndexOfLetterThrowsForNonAlphabetChar() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getIndexOfLetter('!'));
    }

    @Test
    void getLetterByIndexReturnsAForZero() {
        assertEquals('a', vocabulary.getLetterByIndex(0));
    }

    @Test
    void getLetterByIndexReturnsZFor25() {
        assertEquals('z', vocabulary.getLetterByIndex(25));
    }

    @Test
    void getLetterByIndexThrowsForNegativeIndex() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getLetterByIndex(-1));
    }

    @Test
    void getLetterByIndexThrowsForIndexOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getLetterByIndex(26));
    }

    @Test
    void getTotalLettersReturns26() {
        assertEquals(26, vocabulary.getTotalLetters());
    }

    @Test
    void getLettersOrderedByFrequencyMatchesExpectedOrder() {
        List<Character> expected = List.of(
            'e', 't', 'a', 'o', 'h', 'i', 'n', 's', 'r', 'd', 'l', 'u', 'm',
            'w', 'c', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z'
        );
        assertEquals(expected, vocabulary.getLettersOrderedByFrequency());
    }
}
