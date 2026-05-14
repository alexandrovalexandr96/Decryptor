package decrypt.vocabulary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyFactoryTest {

    @Test
    void createReturnsEnglishVocabularyForEN() {
        Vocabulary vocabulary = VocabularyFactory.create("EN");
        assertInstanceOf(EnglishVocabulary.class, vocabulary);
    }

    @Test
    void createReturnsRussianVocabularyForRU() {
        Vocabulary vocabulary = VocabularyFactory.create("RU");
        assertInstanceOf(RussianVocabulary.class, vocabulary);
    }

    @Test
    void createThrowsForUnknownName() {
        assertThrows(IllegalArgumentException.class, () -> VocabularyFactory.create("FR"));
    }

    @Test
    void createThrowsForNull() {
        assertThrows(IllegalArgumentException.class, () -> VocabularyFactory.create(null));
    }

    @Test
    void createThrowsForLowercaseName() {
        assertThrows(IllegalArgumentException.class, () -> VocabularyFactory.create("en"));
    }

    @Test
    void createThrowsForEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> VocabularyFactory.create(""));
    }
}
