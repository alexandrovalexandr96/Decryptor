package decrypt.substitutor;

public class Substitution {
    private final char oldLetter;
    private final char newLetter;

    public Substitution(char oldLetter, char newLetter) {
        if (Character.isUpperCase(oldLetter)) {
            throw new IllegalArgumentException("oldLetter must be lowercase, got: " + oldLetter);
        }
        if (Character.isUpperCase(newLetter)) {
            throw new IllegalArgumentException("newLetter must be lowercase, got: " + newLetter);
        }
        this.oldLetter = oldLetter;
        this.newLetter = newLetter;
    }

    public char getOldLetter() {
        return oldLetter;
    }

    public char getNewLetter() {
        return newLetter;
    }
}
