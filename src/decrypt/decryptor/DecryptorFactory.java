package decrypt.decryptor;

import decrypt.decryptor.frequency.FrequencyDecryptor;
import decrypt.decryptor.rot.RotNDecryptor;
import decrypt.vocabulary.Vocabulary;

public class DecryptorFactory {
    private DecryptorFactory() {
    }

    public static Decryptor create(String decryptorName, Vocabulary vocabulary) {
        if (decryptorName == null) {
            throw new IllegalArgumentException("Decryptor name must not be null");
        }
        if (vocabulary == null) {
            throw new IllegalArgumentException("Vocabulary must not be null");
        }
        if (decryptorName.equals("FREQ")) {
            return new FrequencyDecryptor(vocabulary);
        }
        if (decryptorName.startsWith("ROT")) {
            String numberPart = decryptorName.substring(3);
            try {
                int n = Integer.parseInt(numberPart);
                return new RotNDecryptor(n, vocabulary);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "Invalid ROT decryptor name: '" + decryptorName + "'. Expected format: ROT<int>", e
                );
            }
        }
        throw new IllegalArgumentException(
            "Unknown decryptor name: '" + decryptorName + "'. Expected 'FREQ' or 'ROT<int>'"
        );
    }
}
