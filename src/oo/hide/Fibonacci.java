package oo.hide;

import javax.sound.midi.Soundbank;

public class Fibonacci {

    private int c = 0; //counter


    public int nextValue() {
        int a = 0;
        if(c < 1){
            c++;
            return a;
        }
        int number = 0;
        int b = 1;

        for (int i = 0; i < c; i++) {
            a = b;
            b = number;
            number = a + b;
        }
        c++;
        return number;
    }

}
