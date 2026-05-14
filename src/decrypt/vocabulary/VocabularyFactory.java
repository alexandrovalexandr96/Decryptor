package decrypt.vocabulary;

public class VocabularyFactory {
    private VocabularyFactory() {
    }

    public static Vocabulary create(String vocabularyName) {
        if (vocabularyName == null) {
            throw new IllegalArgumentException("Vocabulary name must not be null");
        }
        return switch (vocabularyName) {
            case "EN" -> new EnglishVocabulary();
            case "RU" -> new RussianVocabulary();
            default -> throw new IllegalArgumentException(
                "Unknown vocabulary name: '" + vocabularyName + "'. Expected 'EN' or 'RU'"
            );
        };
    }
}
