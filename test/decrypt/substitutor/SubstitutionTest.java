package decrypt.substitutor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubstitutionTest {

    @Test
    void constructorStoresOldLetter() {
        Substitution substitution = new Substitution('a', 'b');
        assertEquals('a', substitution.getOldLetter());
    }

    @Test
    void constructorStoresNewLetter() {
        Substitution substitution = new Substitution('a', 'b');
        assertEquals('b', substitution.getNewLetter());
    }

    @Test
    void constructorThrowsWhenOldLetterIsUppercase() {
        assertThrows(IllegalArgumentException.class, () -> new Substitution('A', 'b'));
    }

    @Test
    void constructorThrowsWhenNewLetterIsUppercase() {
        assertThrows(IllegalArgumentException.class, () -> new Substitution('a', 'B'));
    }

    @Test
    void constructorThrowsWhenBothLettersAreUppercase() {
        assertThrows(IllegalArgumentException.class, () -> new Substitution('A', 'B'));
    }

    @Test
    void constructorAcceptsNonLetterCharacters() {
        Substitution substitution = new Substitution('!', '?');
        assertEquals('!', substitution.getOldLetter());
        assertEquals('?', substitution.getNewLetter());
    }

    @Test
    void constructorAcceptsSameLetter() {
        Substitution substitution = new Substitution('z', 'z');
        assertEquals('z', substitution.getOldLetter());
        assertEquals('z', substitution.getNewLetter());
    }
}
