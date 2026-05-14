package decrypt.vocabulary;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class EnglishVocabulary implements Vocabulary {
    private static final List<Character> ALPHABET = List.of(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    );

    private static final List<Character> FREQUENCY_ORDER = List.of(
        'e', 't', 'a', 'o', 'h', 'i', 'n', 's', 'r', 'd', 'l', 'u', 'm',
        'w', 'c', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'x', 'j', 'q', 'z'
    );

    private static final Set<Character> LETTERS =
        Collections.unmodifiableSet(new LinkedHashSet<>(ALPHABET));

    public EnglishVocabulary() {
    }

    @Override
    public Set<Character> getLetters() {
        return LETTERS;
    }

    @Override
    public List<Character> getLettersOrderedByFrequency() {
        return FREQUENCY_ORDER;
    }

    @Override
    public int getIndexOfLetter(char c) {
        int index = ALPHABET.indexOf(c);
        if (index == -1) {
            throw new IllegalArgumentException(
                "Letter not in English alphabet or not lowercase: " + c
            );
        }
        return index;
    }

    @Override
    public char getLetterByIndex(int index) {
        if (index < 0 || index >= ALPHABET.size()) {
            throw new IllegalArgumentException("Index out of bounds: " + index);
        }
        return ALPHABET.get(index);
    }
}
