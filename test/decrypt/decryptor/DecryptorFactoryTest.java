package decrypt.decryptor;

import decrypt.decryptor.frequency.FrequencyDecryptor;
import decrypt.decryptor.rot.RotNDecryptor;
import decrypt.vocabulary.EnglishVocabulary;
import decrypt.vocabulary.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecryptorFactoryTest {

    private Vocabulary vocabulary;

    @BeforeEach
    void setUp() {
        vocabulary = new EnglishVocabulary();
    }

    @Test
    void createReturnsFrequencyDecryptorForFREQ() {
        Decryptor decryptor = DecryptorFactory.create("FREQ", vocabulary);
        assertInstanceOf(FrequencyDecryptor.class, decryptor);
    }

    @Test
    void createReturnsRotDecryptorForROT3() {
        Decryptor decryptor = DecryptorFactory.create("ROT3", vocabulary);
        assertInstanceOf(RotNDecryptor.class, decryptor);
    }

    @Test
    void createReturnsRotDecryptorForROT0() {
        Decryptor decryptor = DecryptorFactory.create("ROT0", vocabulary);
        assertInstanceOf(RotNDecryptor.class, decryptor);
    }

    @Test
    void createReturnsRotDecryptorForNegativeN() {
        Decryptor decryptor = DecryptorFactory.create("ROT-1", vocabulary);
        assertInstanceOf(RotNDecryptor.class, decryptor);
    }

    @Test
    void createReturnsRotDecryptorForLargeN() {
        Decryptor decryptor = DecryptorFactory.create("ROT123", vocabulary);
        assertInstanceOf(RotNDecryptor.class, decryptor);
    }

    @Test
    void createThrowsForUnknownName() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create("UNKNOWN", vocabulary));
    }

    @Test
    void createThrowsForEmptyString() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create("", vocabulary));
    }

    @Test
    void createThrowsForNullName() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create(null, vocabulary));
    }

    @Test
    void createThrowsForNullVocabulary() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create("FREQ", null));
    }

    @Test
    void createThrowsForROTWithoutNumber() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create("ROT", vocabulary));
    }

    @Test
    void createThrowsForROTWithNonNumericSuffix() {
        assertThrows(IllegalArgumentException.class,
            () -> DecryptorFactory.create("ROTabc", vocabulary));
    }
}
