package decrypt.substitutor;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubstitutorTest {

    @Test
    void substituteReplacesLowercaseLetter() {
        Substitutor substitutor = new Substitutor(List.of(new Substitution('a', 'z')));
        assertEquals("z", substitutor.substitute("a"));
    }

    @Test
    void substituteReplacesUppercaseLetterWithUppercase() {
        Substitutor substitutor = new Substitutor(List.of(new Substitution('a', 'z')));
        assertEquals("Z", substitutor.substitute("A"));
    }

    @Test
    void substituteLeavesUnknownLetterUnchanged() {
        Substitutor substitutor = new Substitutor(List.of(new Substitution('a', 'z')));
        assertEquals("b", substitutor.substitute("b"));
    }

    @Test
    void substituteLeavesNonLetterUnchanged() {
        Substitutor substitutor = new Substitutor(List.of(new Substitution('b', 'c')));
        assertEquals("!", substitutor.substitute("!"));
    }

    @Test
    void substituteHandlesComplexExample() {
        Substitutor substitutor = new Substitutor(List.of(
            new Substitution('b', 'c'),
            new Substitution('c', 'b')
        ));
        assertEquals("ac!Cb", substitutor.substitute("ab!Bc"));
    }

    @Test
    void substituteHandlesEmptyString() {
        Substitutor substitutor = new Substitutor(List.of(new Substitution('a', 'b')));
        assertEquals("", substitutor.substitute(""));
    }

    @Test
    void constructorThrowsOnDuplicateOldLetter() {
        List<Substitution> duplicates = List.of(
            new Substitution('a', 'b'),
            new Substitution('a', 'c')
        );
        assertThrows(IllegalArgumentException.class, () -> new Substitutor(duplicates));
    }

    @Test
    void constructorThrowsOnNullList() {
        assertThrows(IllegalArgumentException.class, () -> new Substitutor(null));
    }

    @Test
    void substituteThrowsOnNullData() {
        Substitutor substitutor = new Substitutor(List.of());
        assertThrows(IllegalArgumentException.class, () -> substitutor.substitute(null));
    }

    @Test
    void constructorDoesNotAllowMutationViaOriginalList() {
        java.util.ArrayList<Substitution> list = new java.util.ArrayList<>();
        list.add(new Substitution('a', 'b'));
        Substitutor substitutor = new Substitutor(list);
        list.add(new Substitution('c', 'd'));
        assertEquals("c", substitutor.substitute("c"));
    }
}
