/**
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * If the compressed string would not become smaller than the original string, your method should return the original string.
 */
public class StringCompression {
    private final static int EXPECTED_ARGS_LENGTH = 1;
    private final static int LENGTH_DIFFERENCE_BASELINE = 2;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java StringCompression string");
            return;
        }

        System.out.printf("%s compression result: %s%n", args[0], compress(args[0]));
    }

    /**
     * This solutions puts the compressed string in a dynamic array and adjusts the length difference with each insertion.
     * Time complexity (amortized): O(n)
     * Space complexity: O(n)
     */
    private static String compress(String string) {
        int characterCount = 0;
        StringBuilder compressedStringBuilder = new StringBuilder();
        int lengthDifference = 0;
        char previousCharacter = '\0';

        for (var character : string.toCharArray()) {
            if ('\0' != previousCharacter && previousCharacter != character) {
                compressedStringBuilder
                    .append(previousCharacter)
                    .append(characterCount);
                lengthDifference += (characterCount - LENGTH_DIFFERENCE_BASELINE);
                characterCount = 0;
            }
            previousCharacter = character;
            ++characterCount;
        }
        compressedStringBuilder
            .append(previousCharacter)
            .append(characterCount);
        lengthDifference += (characterCount - LENGTH_DIFFERENCE_BASELINE);

        if (0 < lengthDifference) {
            return compressedStringBuilder.toString();
        }
        return string;
    }
}
