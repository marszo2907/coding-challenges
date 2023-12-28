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
        
        System.out.printf("Using hash table: %s%n", solveWithHashTable(args[0]) ? "true" : "false");
        System.out.printf("Using alg. with constant space complexity: %s%n", solveWithConstantSpace(args[0].toCharArray()) ? "true" : "false");
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
     * This solution utilizes a hash table to store the number of occurences of each character of the string.
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    private static boolean solveWithHashTable(String string) {
        Map<Character, Integer> characterCounts = new HashMap<>();
        boolean isUnique = true;

        for (var character : string.toCharArray()) {
            int count = characterCounts.getOrDefault(character, 0);
            if (0 != count) {
                isUnique = false;
            }
            characterCounts.put(character, ++count);
        }

        return isUnique;
    }
}
