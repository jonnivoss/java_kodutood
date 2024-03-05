package exceptions.bad;

public class BadTryCatch {

    public boolean containsSingleLetters(String input) {

        int index = 0;

        if (input == null){
            return false;
        }

        while (index < input.length() - 1) {
            if (input.charAt(index) == input.charAt(index + 1)) {
                return false;
            }
            index++;
        }

        return true;
    }


}
