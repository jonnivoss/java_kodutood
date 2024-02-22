package oo.hide;

public class Counter {

    private int start;
    private int step;
    private int stepsTaken;
    public Counter(int start, int step) {
        this.start = start;
        this.step = step;
        stepsTaken = 0;
    }

    public int nextValue() {
        if(stepsTaken == 0){
            stepsTaken++;
            return start;
        }
        start += step;
        return start;
    }
}
