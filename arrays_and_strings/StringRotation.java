/**
 * Assume you have a method isSubstring which checks if one word is a substring of another.
 * Given two strings s1 and s2, write a code to check if s2 is a rotation of s1 using only one call to isSubstring.
 */ 
public class StringRotation {
    private final static int EXPECTED_ARGS_LENGTH = 2;
    
    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java StringRotation string1 string2");
            return;
        }

        System.out.printf("Is %s a rotation of %s? %s%n", args[1], args[0], (args[0] + args[0]).contains(args[1]) ? "true" : "false");
    }
}