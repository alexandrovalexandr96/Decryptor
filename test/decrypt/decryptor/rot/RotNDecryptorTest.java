package decrypt.decryptor.rot;

import decrypt.substitutor.Substitution;
import decrypt.vocabulary.EnglishVocabulary;
import decrypt.vocabulary.RussianVocabulary;
import decrypt.vocabulary.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RotNDecryptorTest {

    private Vocabulary englishVocabulary;

    @BeforeEach
    void setUp() {
        englishVocabulary = new EnglishVocabulary();
    }

    @Test
    void decryptReturns26SubstitutionsForEnglish() {
        RotNDecryptor decryptor = new RotNDecryptor(1, englishVocabulary);
        assertEquals(26, decryptor.decrypt("").size());
    }

    @Test
    void decryptRot2MapsAToc() {
        RotNDecryptor decryptor = new RotNDecryptor(2, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        assertEquals('c', map.get('a'));
    }

    @Test
    void decryptRot2MapsBToD() {
        RotNDecryptor decryptor = new RotNDecryptor(2, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        assertEquals('d', map.get('b'));
    }

    @Test
    void decryptRot2WrapsAroundYToA() {
        RotNDecryptor decryptor = new RotNDecryptor(2, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        assertEquals('a', map.get('y'));
    }

    @Test
    void decryptRot2WrapsAroundZToB() {
        RotNDecryptor decryptor = new RotNDecryptor(2, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        assertEquals('b', map.get('z'));
    }

    @Test
    void decryptRot0MapsEachLetterToItself() {
        RotNDecryptor decryptor = new RotNDecryptor(0, englishVocabulary);
        for (Substitution sub : decryptor.decrypt("")) {
            assertEquals(sub.getOldLetter(), sub.getNewLetter());
        }
    }

    @Test
    void decryptNegativeNWorksCorrectly() {
        RotNDecryptor decryptor = new RotNDecryptor(-1, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        assertEquals('z', map.get('a'));
    }

    @Test
    void decryptReturnsUnmodifiableList() {
        RotNDecryptor decryptor = new RotNDecryptor(1, englishVocabulary);
        List<Substitution> result = decryptor.decrypt("any text");
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Substitution('a', 'b')));
    }

    @Test
    void decryptWorksWithRussianVocabulary() {
        RotNDecryptor decryptor = new RotNDecryptor(1, new RussianVocabulary());
        assertEquals(33, decryptor.decrypt("").size());
    }

    @Test
    void constructorThrowsForNullVocabulary() {
        assertThrows(IllegalArgumentException.class, () -> new RotNDecryptor(1, null));
    }

    @Test
    void decryptThrowsForNullData() {
        RotNDecryptor decryptor = new RotNDecryptor(1, englishVocabulary);
        assertThrows(IllegalArgumentException.class, () -> decryptor.decrypt(null));
    }

    @Test
    void decryptLargeNWorksCorrectly() {
        // ROT2147483647: 2147483647 % 26 = 23, so a -> x
        RotNDecryptor decryptor = new RotNDecryptor(Integer.MAX_VALUE, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        int expectedShift = Math.floorMod(Integer.MAX_VALUE, 26);
        char expectedForA = (char) ('a' + expectedShift);
        assertEquals(expectedForA, map.get('a'));
    }

    @Test
    void decryptMinIntNWorksCorrectly() {
        RotNDecryptor decryptor = new RotNDecryptor(Integer.MIN_VALUE, englishVocabulary);
        Map<Character, Character> map = buildMap(decryptor.decrypt(""));
        int expectedShift = Math.floorMod(Integer.MIN_VALUE, 26);
        char expectedForA = (char) ('a' + expectedShift);
        assertEquals(expectedForA, map.get('a'));
    }

    private Map<Character, Character> buildMap(List<Substitution> substitutions) {
        return substitutions.stream()
            .collect(Collectors.toMap(Substitution::getOldLetter, Substitution::getNewLetter));
    }
}
