package decrypt.vocabulary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RussianVocabularyTest {

    private RussianVocabulary vocabulary;

    @BeforeEach
    void setUp() {
        vocabulary = new RussianVocabulary();
    }

    @Test
    void getLettersReturns33Letters() {
        assertEquals(33, vocabulary.getLetters().size());
    }

    @Test
    void getLettersContainsYo() {
        assertTrue(vocabulary.getLetters().contains('ё'));
    }

    @Test
    void getLettersAreAllLowercase() {
        for (char c : vocabulary.getLetters()) {
            assertFalse(Character.isUpperCase(c), "Uppercase letter found: " + c);
        }
    }

    @Test
    void getLettersOrderedByFrequencyReturns33Letters() {
        assertEquals(33, vocabulary.getLettersOrderedByFrequency().size());
    }

    @Test
    void getLettersOrderedByFrequencyFirstLetterIsO() {
        assertEquals('о', vocabulary.getLettersOrderedByFrequency().get(0));
    }

    @Test
    void getLettersOrderedByFrequencyLastLetterIsYo() {
        List<Character> ordered = vocabulary.getLettersOrderedByFrequency();
        assertEquals('ё', ordered.get(ordered.size() - 1));
    }

    @Test
    void getLettersOrderedByFrequencyIsUnmodifiable() {
        List<Character> ordered = vocabulary.getLettersOrderedByFrequency();
        assertThrows(UnsupportedOperationException.class, () -> ordered.add('а'));
    }

    @Test
    void getLettersIsUnmodifiable() {
        Set<Character> letters = vocabulary.getLetters();
        assertThrows(UnsupportedOperationException.class, () -> letters.add('а'));
    }

    @Test
    void getIndexOfLetterReturnsZeroForA() {
        assertEquals(0, vocabulary.getIndexOfLetter('а'));
    }

    @Test
    void getIndexOfLetterThrowsForUppercase() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getIndexOfLetter('А'));
    }

    @Test
    void getIndexOfLetterThrowsForNonAlphabetChar() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getIndexOfLetter('a'));
    }

    @Test
    void getLetterByIndexReturnsAForZero() {
        assertEquals('а', vocabulary.getLetterByIndex(0));
    }

    @Test
    void getLetterByIndexThrowsForNegativeIndex() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getLetterByIndex(-1));
    }

    @Test
    void getLetterByIndexThrowsForIndexOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> vocabulary.getLetterByIndex(33));
    }

    @Test
    void getTotalLettersReturns33() {
        assertEquals(33, vocabulary.getTotalLetters());
    }

    @Test
    void yoAndYeHaveDifferentIndices() {
        assertNotEquals(vocabulary.getIndexOfLetter('е'), vocabulary.getIndexOfLetter('ё'));
    }

    @Test
    void getLettersOrderedByFrequencyMatchesExpectedOrder() {
        List<Character> expected = List.of(
            'о', 'е', 'а', 'н', 'и', 'т', 'с', 'л', 'в', 'р', 'к', 'м', 'у', 'д', 'п',
            'ы', 'я', 'б', 'г', 'з', 'ч', 'ь', 'й', 'х', 'ж', 'ш', 'ю', 'ц', 'э', 'щ',
            'ф', 'ъ', 'ё'
        );
        assertEquals(expected, vocabulary.getLettersOrderedByFrequency());
    }
}
