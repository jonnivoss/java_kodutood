package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        //find if there are duplicate values and if they are 1 pair, 2 pair, triple, or four of a kind
        HandType findGroups = getMultiples();
        if (findGroups != null) {
            return findGroups;
        }

        HandType straight = getStraight();
        HandType flush = getFlush();
        if(flush != null && straight != null){
            return HandType.STRAIGHT_FLUSH;
        } else if (flush != null) {
            return HandType.FLUSH;
        } else if (straight != null) {
            return HandType.STRAIGHT;
        }

        return HandType.HIGH_CARD;
    }

    private HandType getFlush(){
        Set<Card.CardSuit> suits = new HashSet<>();
        for (Card card : cards){
            suits.add(card.getSuit());
        }
        if(suits.size() == 1){
            return HandType.FLUSH;
        }
        return null;
    }
    private HandType getStraight(){
        if(cards.size() != 5){
            return null;
        }
        int sCounter = 0;
        if(cards.getFirst().getValue() == Card.CardValue.A){
            sCounter++;
        }
        for (Card.CardValue cardValue : Card.CardValue.values()){
            if(cards.get(sCounter).getValue() == cardValue){
                sCounter++;
            }else {
                sCounter = 0;
            }
            if (sCounter == 5){
                return HandType.STRAIGHT;
            }
        }
        return null;
    }

    private HandType getMultiples() {
        Map<Card.CardValue, Integer> valueCount = new HashMap<>();
        sameCardCounter(valueCount);

        int numberOfPairs=0;
        int numberOfTriples=0;
        for (Map.Entry<Card.CardValue, Integer> c : valueCount.entrySet()) {
            if(c.getValue() == 2){
                numberOfPairs++;
            }
            if (c.getValue() == 3){
                numberOfTriples++;
            }
            if (c.getValue() == 4){
                return HandType.FOUR_OF_A_KIND;
            }
        }
        if (numberOfPairs == 1 && numberOfTriples == 1){
            return HandType.FULL_HOUSE;
        } else if (numberOfPairs == 1) {
            return HandType.ONE_PAIR;
        } else if (numberOfPairs == 2) {
            return HandType.TWO_PAIRS;
        } else if (numberOfTriples == 1) {
            return HandType.TRIPS;
        }
        return null;
    }

    private void sameCardCounter(Map<Card.CardValue, Integer> valueCount) {
        for (Card card : cards) {
            if(valueCount.containsKey(card.getValue())){
                valueCount.put(card.getValue(), valueCount.get(card.getValue())+1);
            }else {
                valueCount.put(card.getValue(),1);
            }
        }
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {

        return 0;
    }
}
