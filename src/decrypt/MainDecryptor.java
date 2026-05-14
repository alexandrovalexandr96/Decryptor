package decrypt;

import decrypt.decryptor.Decryptor;
import decrypt.decryptor.DecryptorFactory;
import decrypt.substitutor.Substitution;
import decrypt.substitutor.Substitutor;
import decrypt.vocabulary.Vocabulary;
import decrypt.vocabulary.VocabularyFactory;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;

// NOTE: Код ниже менять запрещено!

public class MainDecryptor {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Expected 4 arguments: input file, output file, vocabulary name (EN|RU), decryptor name (FREQ|ROT1|ROT2|...)");
            System.exit(-1);
            return;
        }
        Path inputFile = Path.of(args[0]);
        Path outputFile = Path.of(args[1]);
        String vocabularyName = args[2];
        String decryptorName = args[3];

        if (!Files.exists(inputFile)) {
            System.err.println("Input file '" + inputFile + "' doesn't exist");
            System.exit(-1);
            return;
        }
        if (Files.exists(outputFile) && !Files.isRegularFile(outputFile)) {
            System.err.println("Output file '" + outputFile + "' is not a regular file");
            System.exit(-1);
            return;
        }

        Vocabulary vocabulary;
        try {
            vocabulary = VocabularyFactory.create(vocabularyName);
        } catch (Exception e) {
            System.err.println("Bad vocabulary name: '" + vocabularyName + "'");
            e.printStackTrace(System.err);
            System.exit(-1);
            return;
        }
        Decryptor decryptor;
        try {
            decryptor = DecryptorFactory.create(decryptorName, vocabulary);
        } catch (Exception e) {
            System.err.println("Bad decryptor name: '" + decryptorName + "'");
            e.printStackTrace(System.err);
            System.exit(-1);
            return;
        }

        System.exit(run(inputFile, outputFile, vocabulary, decryptor));
    }

    private static int run(Path inputFile, Path outputFile, Vocabulary vocabulary, Decryptor decryptor) {
        try {
            String encryptedText = Files.readString(inputFile);

            List<Substitution> substitutions = decryptor.decrypt(encryptedText);
            Substitutor substitutor = new Substitutor(substitutions);
            String decryptedText = substitutor.substitute(encryptedText);
            printSubstitutions(substitutions, vocabulary, System.out);

            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, decryptedText, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            return 0;
        } catch (Exception e) {
            System.err.println("Error during decryption");
            e.printStackTrace(System.err);
            return -1;
        }
    }

    private static void printSubstitutions(List<Substitution> substitutions, Vocabulary vocabulary, PrintStream out) {
        substitutions
                .stream()
                .sorted(Comparator.comparing(sub -> vocabulary.getIndexOfLetter(sub.getOldLetter())))
                .map(it -> it.getOldLetter() + " -> " + it.getNewLetter())
                .forEach(out::println);
    }
}