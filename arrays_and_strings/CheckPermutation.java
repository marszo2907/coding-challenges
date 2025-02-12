import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {
    private final static int EXPECTED_ARGS_LENGTH = 2;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java CheckPermutation string1 string2");
            return;
        }

        char[] string0 = args[0].toCharArray();
        char[] string1 = args[1].toCharArray();

        System.out.printf("Using a brute force algorithm: %s%n", sovleWithBruteForce(string0, string1) ? "true" : "false");
        System.out.printf("Using a hash table: %s%n", solveWithHashTable(string0, string1) ? "true" : "false");
    }

    private static void calculatePermutations(List<char[]> permutations, char[] string) {
        calculatePermutations(permutations, string, 0);
    }

    private static void calculatePermutations(List<char[]> permutations, char[] string, int begin) {
        if (begin >= string.length) {
            permutations.add(string);
        } else {
            for (int i = begin; string.length > i; ++i) {
                swap(string, begin, i);
                calculatePermutations(permutations, Arrays.copyOf(string, string.length), begin + 1);
            }
        }
    }

    /**
     * This solution calculates every possible permutation of the string0 and compares them to the string1.
     * Time complexity: O(s * s!)
     * Space complexity: O(s!)
     */
    private static boolean sovleWithBruteForce(char[] string0, char[] string1) {
        if (string0.length != string1.length) {
            return false;
        }
        
        List<char[]> permutations = new ArrayList<>();
        calculatePermutations(permutations, string0);

        for (var permutation : permutations) {
            if (Arrays.equals(permutation, string1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This solution maps each character of the string0 to its count, iterates through each character of the string1 and decreases its mapped count by 1.
     * Strings are equal if and only if each mapped character has a count of 0.
     * Time complexity: O(s)
     * Space complexity: O(1) - the map will be limited to the ASCII range of values
     */
    private static boolean solveWithHashTable(char[] string0, char[] string1) {
        if (string0.length != string1.length) {
            return false;
        }

        Map<Character, Integer> characterCount = new HashMap<>();
        for (var character : string0) {
            int currentCount = characterCount.getOrDefault(character, 0);
            characterCount.put(character, currentCount + 1);
        }

        for (var character : string1) {
            int currentCount = characterCount.getOrDefault(character, 0);
            if (0 >= currentCount) {
                return false;
            }
            characterCount.put(character, currentCount - 1);
        }

        for (var key : characterCount.keySet()) {
            int count = characterCount.get(key);
            if (0 != count) {
                return false;
            }
        }
        return true;
    }

    private static void swap(char[] string, int index1, int index2) {
        char temp = string[index1];
        string[index1] = string[index2];
        string[index2] = temp;
    }
}