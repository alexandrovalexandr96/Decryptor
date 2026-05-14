package decrypt.decryptor.rot;

import decrypt.decryptor.Decryptor;
import decrypt.substitutor.Substitution;
import decrypt.vocabulary.Vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotNDecryptor implements Decryptor {
    private final int n;
    private final Vocabulary vocabulary;

    public RotNDecryptor(int n, Vocabulary vocabulary) {
        if (vocabulary == null) {
            throw new IllegalArgumentException("Vocabulary must not be null");
        }
        this.n = n;
        this.vocabulary = vocabulary;
    }

    @Override
    public List<Substitution> decrypt(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        int total = vocabulary.getTotalLetters();
        int shift = Math.floorMod(n, total);
        List<Substitution> substitutions = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            char oldLetter = vocabulary.getLetterByIndex(i);
            int newIndex = (i + shift) % total;
            char newLetter = vocabulary.getLetterByIndex(newIndex);
            substitutions.add(new Substitution(oldLetter, newLetter));
        }
        return Collections.unmodifiableList(substitutions);
    }
}
