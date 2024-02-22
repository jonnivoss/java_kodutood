package oo.hide;

import javax.sound.midi.Soundbank;

public class Fibonacci {

    public static int c = 0; //counter


    public int nextValue() {
        int number = 0;
        int a = 0;
        int b = 1;
        if(c < 1){
            c++;
            return a;
        }
        for (int i = 0; i < c; i++) {
            a = b;
            b = number;
            number = a + b;
        }
        c++;
        return number;
    }

}
