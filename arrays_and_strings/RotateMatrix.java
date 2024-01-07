/**
 * Given an image represented by an NxN matrix, where each pixel in the image is represented by an image,
 * write a method to rotate the image by 90 degrees.
 * Can you do this in place?
 */
public class RotateMatrix {
    private final static int EXPECTED_ARGS_LENGTH = 1;
    private final static int MAX_N = 9;
    
    public static void main(String[] args) {
        if (EXPECTED_ARGS_LENGTH != args.length) {
            System.out.println("Usage: java RotateMatrix n");
            return;
        }

        int n = Integer.parseInt(args[0]) % MAX_N + 1;
        int[][] picture = new int[n][n];

        fill(picture);
        System.out.println("Pre-rotation:");
        print(picture);
        rotate(picture);
        System.out.println();
        System.out.println("Post-rotation:");
        print(picture);
    }

    private static void fill(int[][] picture) {
        for (int i = 0; picture.length > i; ++i) {
            for (int j = 0; picture[0].length > j; ++j) {
                picture[i][j] = i * 10 + j;
            }
        }
    }

    private static void print(int[][] picture) {
        for (int i = 0; picture.length > i; ++i) {
            for (int j = 0; picture[0].length > j; ++j) {
                System.out.printf("%2d ", picture[i][j]);
            }
            System.out.println();
        }    
    }

    /**
     * This solutions rotates the matrix layer by layer, starting with the outermost one.
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    private static void rotate(int[][] picture) {
        int n = picture.length - 1;
        int depth = n / 2;
        for (int i = 0; depth > i; ++i) {
            for (int j = i; n - i > j; ++j) {
                int temp1 = picture[j][n - i];
                picture[j][n - i] = picture[i][j];
                int temp2 = picture[n - i][n - j];
                picture[n - i][n - j] = temp1;
                temp1 = picture[n - j][i];
                picture[n - j][i] = temp2;
                picture[i][j] = temp1;
            }
        }
    }
}
