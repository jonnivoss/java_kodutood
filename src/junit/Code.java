package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        int remainder = candidate % 11;
        return remainder >= 0 && remainder <= 3;
    }

    public static int longestStreak(String inputString) {
        if(inputString == null || inputString.isEmpty()){
            return 0;
        }
        Integer counter = 0;
        Integer result = counter;
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

    public static int[] removeDuplicates(int[] integers) {
        return null;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        return 0;
    }
}
