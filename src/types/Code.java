package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    public static void main(String[] args) {

        System.out.println(isolatedSquareCount()); //

    }

    //done 5.
    public static int sum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    //done 6.
    public static double average(int[] numbers) {
        Double summa = Double.valueOf(sum(numbers));
        return summa / Double.valueOf(numbers.length);
    }

    //done 7.
    public static Integer minimumElement(int[] integers) {
        if(integers.length == 0){
            return null;
        } else if (integers.length == 1) {
            return integers[0];
        }
        Integer min = integers[0];
        for (int i = 1; i < integers.length; i++) {
            if (integers[i] < min){
                min = integers[i];
            }
        }
        return min;
    }

    //done 8.
    public static String asString(int[] elements) {
        String out = "";
        if (elements.length == 0){
            return out;
        }
        out += elements[0];
        for (int i = 1; i < elements.length; i++) {
            String space = ", ";
            out += space;
            if(elements[i] < 0){
                out += elements[i];
            }else {
                out += elements[i];
            }
        }
        return out;
    }

    //done 9.
    public static Character mode(String input) {
        if(input.isEmpty()){
            return null;
        }
        int len = input.length();
        char letter = input.charAt(0), temp;
        int counter, lastcounter = 0;
        String array = "";
        for (int i = 0; i < len; i++) {
            counter = 0;
            temp = input.charAt(i);
            if(array.indexOf(temp) != -1){
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (temp == input.charAt(j)) {
                    counter++;
                }
            }
            if (counter > lastcounter) {
                lastcounter = counter;
                letter = temp;
            }
            array += temp;
        }
        return letter;
    }

    //done 10.
    public static String squareDigits(String s) {
        String out = "";
        Integer temp;
        for (int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                String temp1 = "";
                temp1 += s.charAt(i);
                temp = Integer.parseInt(temp1);
                temp *= temp;
                out += temp;
            }else {
                out += s.charAt(i);
            }
        }
        return out;
    }


    private static boolean hasTrue(boolean[][] matrix, int row, int col) {
        int[] arr = minAndMax(row, col);

        for (int i = arr[0]; i <= arr[1]; i++) {
            for (int j = arr[2]; j <= arr[3]; j++) {
                if(i == row && j == col){
                    continue;
                }
                if(matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //done 12.
    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();
        int isolatedCount = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] && hasTrue(matrix, row, col)) {
                    isolatedCount++;
                }
            }
        }

        return isolatedCount;
    }

    private static int[] minAndMax(int row, int col){
        int endRow = row > 8? 9 : row + 1;
        int endCol = col > 8? 9 : col + 1;
        int begRow = row < 1? 0 : row - 1;
        int begCol = col < 1? 0 : col - 1;
        return new int[]{begRow,endRow,begCol,endCol};
    }

    //done 11.
    public static boolean isIsolated(int row, int col) {
        boolean[][] matrix = getSampleMatrix();

        if(!matrix[row][col]){
            return false;
        }

        int[] arr = minAndMax(row, col);

        for (int i = arr[0]; i <= arr[1]; i++) {
            for (int j = arr[2]; j <= arr[3]; j++) {
                if(i == row && j == col){
                    continue;
                }
                if(matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
