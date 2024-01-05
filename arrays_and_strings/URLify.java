/**
 * Write a method to replace all spaces in a string with '%20'.
 * You may assume that the string has sufficient space at the end to hold the additional characters,
 * and that you are given the true length of the string.
 * The operation should be performed in place.
 */ 
public class URLify {
    private final static int EXPECTED_ARGS_LENGTH = 2;
    private final static int URL_SPACE_ADDITIONAL_LENGTH = 2;

    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java URLify stringWithAdditionalSpace trueLength");
            return;
        }

        char[] string = args[0].toCharArray();
        int trueLength = Integer.parseInt(args[1]);
        int offsetMultiplier = (string.length - trueLength) / 2;

        for (int i = trueLength - 1; 0 <= i; --i) {
            if (' ' == string[i]) {
                string[i + offsetMultiplier * URL_SPACE_ADDITIONAL_LENGTH] = '0';
                string[i + offsetMultiplier * URL_SPACE_ADDITIONAL_LENGTH - 1] = '2';
                string[i + offsetMultiplier * URL_SPACE_ADDITIONAL_LENGTH - 2] = '%';
                --offsetMultiplier;
            } else {
                string[i + offsetMultiplier * URL_SPACE_ADDITIONAL_LENGTH] = string[i];
            }
        }

        for (var character : string) {
            System.out.print(character);
        }
        System.out.println();
    }
}