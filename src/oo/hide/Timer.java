package oo.hide;

public class Timer {
    private long startTime;

    public Timer(){
        this.startTime = System.currentTimeMillis();
    }

    public String getPassedTime() {
        long currentTime = System.currentTimeMillis() - startTime;
        String time = "" + currentTime;
        return time;
    }
}
