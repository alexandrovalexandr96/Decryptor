package decrypt.substitutor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Substitutor {
    private final Map<Character, Character> substitutionMap;

    public Substitutor(List<Substitution> substitutions) {
        if (substitutions == null) {
            throw new IllegalArgumentException("Substitutions list must not be null");
        }
        substitutionMap = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        for (Substitution substitution : substitutions) {
            if (substitution == null) {
                throw new IllegalArgumentException("Substitution must not be null");
            }
            char oldLetter = substitution.getOldLetter();
            if (!seen.add(oldLetter)) {
                throw new IllegalArgumentException("Duplicate oldLetter in substitutions: " + oldLetter);
            }
            substitutionMap.put(oldLetter, substitution.getNewLetter());
        }
    }

    public String substitute(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null");
        }
        StringBuilder result = new StringBuilder(data.length());
        for (char c : data.toCharArray()) {
            char lower = Character.toLowerCase(c);
            if (substitutionMap.containsKey(lower)) {
                char replacement = substitutionMap.get(lower);
                if (Character.isUpperCase(c)) {
                    result.append(Character.toUpperCase(replacement));
                } else {
                    result.append(replacement);
                }
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
