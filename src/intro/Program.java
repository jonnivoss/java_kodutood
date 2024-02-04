package intro;

public class Program {

    public static void main(String[] args) {

        int integer = asInteger("11001101");
        String string = asBinaryString(205);

        System.out.println(integer); //1111111
        System.out.println(string); // 205
    }

    public static String asBinaryString(int input) {
        String output = "";
        while(input > 0){
            if(input % 2 == 1){
                output = '1' + output;
            }else {
                output = '0' + output;
            }
            input = input / 2;
        }
        return output;
    }

    public static int asInteger(String input) {
        //midagu midagi tööta hindaminw
        int number = 0;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '1'){
                number += pow(2, input.length()-i-1);
            }
        }
        return number;
    }

    private static int pow(int arg, int power) {
        // Java has Math.pow() but this time write your own implementation.
        int output = 1;
        for (int i = 0; i < power; i++){
            output *= arg;
        }

        return output;
    }
}
