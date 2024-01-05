import java.util.HashMap;
import java.util.Map;

/**
 * There are three types of edits that can be performed on strings: insert a character, remove a character or replace a character.
 * Given two strings, write a function to check if they are one or zero edits away.
 */
public class OneAway {
    private final static int EXPECTED_ARGS_LENGTH = 2;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java OneAway string0 string1");
            return;
        }

        char[] string0 = args[0].toCharArray();
        char[] string1 = args[1].toCharArray();

        System.out.printf("Are %s and %s one or zero edits away: %s%n", new String(string0), new String(string1), areOneAway(string0, string1) ? "true" : "false");
    }

    /**
     * This solution increments the value at an index character for each character of the first string and decrements it for each character of the second string.
     * Strings are one or zero edits away if and only if there is at most one value 1, one value -1, and the rest of the table is filled with 0.
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    private static boolean areOneAway(char[] string0, char[] string1) {
        int[] characterCounts = new int[Character.MAX_VALUE];
        
        for (var character : string0) {
            characterCounts[character] += 1;
        }
        for (var character : string1) {
            characterCounts[character] -= 1;
        }

        boolean isOneFound = false;
        boolean isMinusOneFound = false;
        for (var count : characterCounts) {
            if (1 == count) {
                if (isOneFound) {
                    return false;
                }
                isOneFound = true;
            } else if (-1 == count) {
                if (isMinusOneFound) {
                    return false;
                }
                isMinusOneFound = true;
            } else if (0 != count) {
                return false;
            }
        }
        return true;
    }
}
