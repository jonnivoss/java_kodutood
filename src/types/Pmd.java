package types;

public class Pmd {

    public static void main(String[] args) {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        System.out.println(containsTrueCell(matrix));
        System.out.println(findFirstTrueCell(matrix));
        System.out.println(countTrueRow(matrix));
    }

    private static void printMatrix(boolean[][] sampleMatrix) {
        for (boolean[] row : sampleMatrix) {
            for (boolean element : row) {
                System.out.print(element ? "X" : "O");
            }
            System.out.println();
        }
    }

    // intentionally bad code
    public static boolean containsTrueCell(boolean[][] matrix) {
        for(boolean[] booleans : matrix){
            for (boolean bool : booleans){
                if(bool){
                    return true;
                }
            }
        }
        return false;
    }

    // intentionally bad code
    public static int findFirstTrueCell(boolean[][] matrix) {
        int counter = 0;
        for(boolean[] booleans : matrix){
            for (boolean bool : booleans){
                if(bool){
                    return counter;
                }
                counter++;
            }
        }
        return -1;
    }

    // intentionally bad code
    public static int countTrueRow(boolean[][] matrix) {
        for (boolean[] booleans : matrix) {
            for (boolean bool : booleans) {
                if(bool){
                    int count = 0;
                    for(boolean b : booleans){
                        if(b){
                            count++;
                        }
                    }
                    return count;
                }
            }
        }
        return -1;
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[3][3];

        matrix[2][1] = true;
        matrix[2][2] = true;

        return matrix;
    }

}
