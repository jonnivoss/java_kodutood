package junit;

import java.util.Arrays;

public class Code {

    public static void main(String[] args) {
        int[] arr = {1,2,1};
        System.out.println(Arrays.toString(removeDuplicates(arr)));
    }
    public static boolean isSpecial(int candidate) {
        int remainder = candidate % 11;
        return remainder >= 0 && remainder <= 3;
    }

    public static int longestStreak(String inputString) {
        if(inputString == null || inputString.isEmpty()){
            return 0;
        }
        int counter = 0;
        int result = counter;
        Character temp = inputString.charAt(0);
        for(Character c : inputString.toCharArray()){
            if(temp != c){
                temp = c;
                counter = 0;
            }
            counter++;
            if(counter > result){
                result = counter;
            }
        }
        return result;
    }

    public static Character mode(String inputString) {
        if(inputString == null || inputString.isEmpty()){
            return null;
        }

        char letter = inputString.charAt(0), temp;
        int counter, lastcounter = 0;
        String array = "";
        for (int i = 0; i < inputString.length(); i++) {
            temp = inputString.charAt(i);
            if(array.indexOf(temp) != -1){
                continue;
            }
            counter = getCharacterCount(inputString,temp);
            if (counter > lastcounter) {
                lastcounter = counter;
                letter = temp;
            }
            array += temp;
        }
        return letter;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        int counter = 0;
        for (int j = 0; j < allCharacters.length(); j++) {
            if (targetCharacter == allCharacters.charAt(j)) {
                counter++;
            }
        }
        return counter;
    }

    private static int[] append(int[] integers, int number){
        int[] array = Arrays.copyOf(integers, integers.length + 1);
        array[array.length-1] = number;
        return array;
    }
    private static boolean isInList(int[] integers, int number){
        for (int integer : integers) {
            if (number == integer) {
                return true;
            }
        }
        return false;
    }
    public static int[] removeDuplicates(int[] integers) {
        if(integers == null || integers.length == 0){
            return null;
        }
        int[] temp = new int[0];
        temp = append(temp,integers[0]);
        for (int i = 1; i < integers.length; i++) {
            if(!isInList(temp,integers[i])){
                temp = append(temp,integers[i]);
            }

        }
        return temp;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] array = removeDuplicates(integers);
        int sum = 0;
        for (int number: array){
            sum += number;
        }
        return sum;
    }
}
