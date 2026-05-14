package decrypt.vocabulary;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RussianVocabulary implements Vocabulary {
    private static final List<Character> ALPHABET = List.of(
        'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
        'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
        'э', 'ю', 'я'
    );

    private static final List<Character> FREQUENCY_ORDER = List.of(
        'о', 'е', 'а', 'н', 'и', 'т', 'с', 'л', 'в', 'р', 'к', 'м', 'у', 'д', 'п',
        'ы', 'я', 'б', 'г', 'з', 'ч', 'ь', 'й', 'х', 'ж', 'ш', 'ю', 'ц', 'э', 'щ',
        'ф', 'ъ', 'ё'
    );

    private static final Set<Character> LETTERS =
        Collections.unmodifiableSet(new LinkedHashSet<>(ALPHABET));

    public RussianVocabulary() {
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
                "Letter not in Russian alphabet or not lowercase: " + c
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
