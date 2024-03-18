package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {
        LinkedList<List<String>> streaks = new LinkedList<>();


        for (char character : input.toCharArray()){
            String currentSymbol = String.valueOf(character);

            if (streaks.size() == 0){
                streaks.add(new ArrayList<>(Arrays.asList(currentSymbol)));
            }else if(streaks.getLast().contains(currentSymbol)){
                streaks.getLast().add(currentSymbol);
            }else {
                streaks.add(new ArrayList<>(Arrays.asList(currentSymbol)));
            }
        }
        return streaks;
    }
}
