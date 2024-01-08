import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Write an algorithm such that if an alement in a MxN matrix is 0, its entire row and column are set to 0.
 */ 
public class ZeroMatrix {
    public static  void main(String[] args) {
        int[][] matrix = {
            {1, 1, 1, 1, 1},
            {0, 1, 0, 1, 1},
            {0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1},
        };

        System.out.println("Pre-zeroing:");
        print(matrix);
        System.out.println();
        zeroRowAndColumnIfZero(matrix);
        System.out.println("Post-zeroing:");
        print(matrix);
    }

    private static void print(int[][] matrix) {
        for (int row = 0; matrix.length > row; ++row) {
            for (int column = 0; matrix[row].length > column; ++column) {
                System.out.printf("%2d", matrix[row][column]);
            }
            System.out.println();
        }    
    }

    private static void zeroColumn(int[][] matrix, int column) {
        for (int row = 0; matrix.length > row; ++row) {
            matrix[row][column] = 0;
        }
    }

    private static void zeroRow(int[][] matrix, int row) {
        for (int column = 0; matrix[row].length > column; ++column) {
            matrix[row][column] = 0;
        }
    }

    private static void zeroRowAndColumnIfZero(int[][] matrix) {
        Set<Pair<Integer>> zeroIndices = new HashSet<>();

        for (int row = 0; matrix.length > row; ++row) {
            for (int column = 0; matrix[row].length > column; ++column) {
                if (0 == matrix[row][column]) {
                    zeroIndices.add(new Pair<Integer>(row, column));
                }
            }
        }
        for (var index : zeroIndices) {
            zeroRow(matrix, index.getFirst());
            zeroColumn(matrix, index.getSecond());
        }
    }

    private static class Pair<T> {
        private final T first;
        private final T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (null == o || getClass() != o.getClass()) {
                return false;
            }
            Pair<?> that = (Pair<?>) o;
            
            return Objects.equals(getFirst(), that.getFirst()) &&
                Objects.equals(getSecond(), that.getSecond());
        }

        public T getFirst() {
            return first;
        }

        public T getSecond() {
            return second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}