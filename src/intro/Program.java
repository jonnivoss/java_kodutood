package intro;

public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");

        System.out.println(integer); // 205
    }

    public static String asBinaryString(int input) {
        return "";
    }

    public static int asInteger(String input) {
        int number = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '1'){
                number += 1 << (7-i); //millegi pärast pow ei tööta
            }
        }
        return number;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.

        return 0;
    }
}
