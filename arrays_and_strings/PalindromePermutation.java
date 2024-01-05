import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome.
 * The palindrome does not need to be limited to dictionary words. You can ignore casing and non-letter characters.
 */
public class PalindromePermutation {
    private final static int EXPECTED_ARGS_LENGTH = 1;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java PalindromePermutation string");
            return;
        }

        char[] string = args[0].toCharArray();
        System.out.printf("Is %s a permutation of a palindrome: %s%n", new String(string), solveWithHashTable(string) ? "true" : "false");
    }

    /**
     * This solutions maps character of the string to their count and checks if the map contains at most one odd value.
     * Time complexity: O(n)
     * Space complexity: O(1) - the map will be limited to at most 26 characters
     */
    private static boolean solveWithHashTable(char[] string) {
        Map<Character, Integer> characterCounts = new HashMap<>();

        for (var character : string) {
            if ('A' <= character && 'Z' >= character) {
                int currentCount = characterCounts.getOrDefault(character, 0);
                characterCounts.put(character, currentCount + 1);
            } else if ('a' <= character && 'z' >= character) {
                char upperCaseCharacter = (char) (character - 'a' + 'A');
                int currentCount = characterCounts.getOrDefault(upperCaseCharacter, 0);
                characterCounts.put(upperCaseCharacter, currentCount + 1);
            }
        }

        boolean isOddPresent = false;
        for (var count : characterCounts.values()) {
            if (0 != count % 2) {
                if (isOddPresent) {
                    return false;
                } else {
                    isOddPresent = true;
                }
            }
        }
        return true;
    }
}