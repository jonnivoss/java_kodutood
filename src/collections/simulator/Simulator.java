package collections.simulator;

import java.util.*;

public class Simulator {

    private double iterations;
    public Simulator(double iterations) {
        this.iterations = iterations;
    }

    public Map<HandType, Double> calculateProbabilities() {
        Map<HandType, Integer> hands = new HashMap<>();
        Map<HandType,Double> odds = new HashMap<>();

        for (HandType handType : HandType.values() ) {
            hands.put(handType,0);
        }
        for (int i = 0; i < iterations; i++) {
            Hand hand = getRandomHand(); // Assuming you have this function to generate a random hand
            HandType pokerHand = hand.getHandType();
            hands.put(pokerHand, hands.get(pokerHand) + 1);
        }
        for (HandType hand : HandType.values()) {
            odds.put (hand, hands.get(hand) / iterations * 100);
        }
        return odds;
    }

    private Hand getRandomHand(Hand ... hands){
        Hand hand = new Hand();
        Random r = new Random();
        int cards = 0;
        Card.CardValue[] values = Card.CardValue.values();
        Card.CardSuit[] suits = Card.CardSuit.values();
        while (cards != 5) {
            Card.CardValue randomValue = values[r.nextInt(Card.CardValue.values().length)];
            Card.CardSuit randomSuit = suits[r.nextInt(Card.CardSuit.values().length)];
            Card temp = new Card(randomValue,randomSuit);
            if(!hand.contains(temp)){
                if((hands.length == 2 && !hands[0].contains(temp) && !hands[1].contains(temp))){
                    hand.addCard(temp);
                    cards ++;
                } else if (hands.length != 2) {
                    hand.addCard(temp);
                    cards ++;
                }
            }
        }
        return hand;
    }


    public double getWinningOdds(Hand player1hand, Hand player2hand) {
        int p1Wins = 0;
        for (int i = 0; i < iterations; i++) {
            Hand table = getRandomHand(player1hand,  player2hand);
            Hand p1 = new Hand();
            Hand p2 = new Hand();
            for (Card card : table.getCards()){
                p1.addCard(card);
                p2.addCard(card);
            }
            p1.addCard(player1hand.getCards().getFirst());
            p1.addCard(player1hand.getCards().getLast());
            p2.addCard(player2hand.getCards().getFirst());
            p2.addCard(player2hand.getCards().getLast());
            int compare = p1.compareTo(p2);
            if(compare > 0){
                p1Wins++;
            }
        }
        System.out.println((double) p1Wins / iterations * 100);
        return p1Wins / iterations * 100;
    }

}
