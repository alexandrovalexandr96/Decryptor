package decrypt.decryptor.frequency;

import decrypt.substitutor.Substitution;
import decrypt.vocabulary.EnglishVocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyDecryptorTest {

    private FrequencyDecryptor decryptor;

    @BeforeEach
    void setUp() {
        decryptor = new FrequencyDecryptor(new EnglishVocabulary());
    }

    @Test
    void decryptReturns26SubstitutionsForEnglish() {
        List<Substitution> substitutions = decryptor.decrypt("hello world");
        assertEquals(26, substitutions.size());
    }

    @Test
    void decryptMostFrequentTextLetterToMostFrequentAlphabetLetter() {
        // "aaabbc" -> a(3), b(2), c(1)
        // English frequency order: e, t, a, ...
        // So: a(text) -> e(alphabet), b(text) -> t, c(text) -> a
        List<Substitution> substitutions = decryptor.decrypt("aaabbc");
        Map<Character, Character> map = buildMap(substitutions);
        assertEquals('e', map.get('a'));
    }

    @Test
    void decryptSecondMostFrequentTextLetterToSecondMostFrequentAlphabetLetter() {
        List<Substitution> substitutions = decryptor.decrypt("aaabbc");
        Map<Character, Character> map = buildMap(substitutions);
        assertEquals('t', map.get('b'));
    }

    @Test
    void decryptTieBreaksByAlphabetIndex() {
        // "ba" -> b(1), a(1) — equal frequency
        // a has index 0, b has index 1 in English alphabet
        // so a -> e (most frequent), b -> t
        List<Substitution> substitutions = decryptor.decrypt("ba");
        Map<Character, Character> map = buildMap(substitutions);
        assertEquals('e', map.get('a'));
        assertEquals('t', map.get('b'));
    }

    @Test
    void decryptExampleFromSpec() {
        // "Aba": a(2), b(1)
        // most frequent alphabet letters: e, t
        // a -> e (most frequent text letter), b -> t
        List<Substitution> substitutions = decryptor.decrypt("Aba");
        Map<Character, Character> map = buildMap(substitutions);
        assertEquals('e', map.get('a'));
        assertEquals('t', map.get('b'));
    }

    @Test
    void decryptReturnsUnmodifiableList() {
        List<Substitution> result = decryptor.decrypt("hello");
        assertThrows(UnsupportedOperationException.class, () -> result.add(new Substitution('a', 'b')));
    }

    @Test
    void constructorThrowsForNullVocabulary() {
        assertThrows(IllegalArgumentException.class, () -> new FrequencyDecryptor(null));
    }

    private Map<Character, Character> buildMap(List<Substitution> substitutions) {
        return substitutions.stream()
            .collect(Collectors.toMap(Substitution::getOldLetter, Substitution::getNewLetter));
    }
}
