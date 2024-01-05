import java.util.HashMap;
import java.util.Map;

/**
 * Implement an algorithm to determine if a string has all the unique characters.
 * What if you cannot use additional data structures?
 */
public class IsUnique {
    private final static int EXPECTED_ARGS_LENGTH = 1;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java IsUnique string");
            return;
        }

        char[] string = args[0].toCharArray();
        System.out.printf("Using a hash table: %s%n", solveWithHashTable(string) ? "true" : "false");
        System.out.printf("Using an algorithm with a constant space complexity: %s%n", solveWithConstantSpace(string) ? "true" : "false");
    }

    private static void quickSort(char[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(char[] array, int begin, int end) {
        if (begin < end) {
            char pivot = array[end];
            int i = begin - 1;

            for (int j = begin; end > j; ++j) {
                if (pivot > array[j]) {
                    i++;
                    char swapTemp = array[i];
                    array[i] = array[j];
                    array[j] = swapTemp;
                }
            }

            char swapTemp = array[++i];
            array[i] = array[end];
            array[end] = swapTemp;

            quickSort(array, begin, i - 1);
            quickSort(array, i + 1, end);
        }
    }

    /**
     * This solution sorts the characters in a string using the quicksort algorithm, iterates through the sorted string and compares adjacent characters.
     * Time complexity (average case): O(nlogn)
     * Space complexity: O(1) 
     */ 
    private static boolean solveWithConstantSpace(char[] string) {
        quickSort(string);

        char previousCharacter = string[0];
        for (int i = 1; string.length > i; ++i) {
            if (previousCharacter == string[i]) {
                return false;
            }
            previousCharacter = string[i];
        }
        return true;
    }

    /**
     * This solution utilizes a hash table to store the information if the character was previously present.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static boolean solveWithHashTable(char[] string) {
        Map<Character, Boolean> isCharacterPresent = new HashMap<>();

        for (var character : string) {
            if(isCharacterPresent.getOrDefault(character, false)) {
                return false;
            }
            isCharacterPresent.put(character, true);
        }
        return true;
    }
}
