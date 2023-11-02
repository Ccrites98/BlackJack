package com.pluralsight;
import java.util.ArrayList;
import java.util.List;
public class Players {
    private final String name;
    private List<Card> hand;
    public Players(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
    }
    public String getName() {
        return name;
    }
    public void addCardToHand(Card card) {
        hand.add(card);
    }
    public List<Card> getHand() {
        return hand;
    }
    public int calculateHandValue() {
        int handValue = 0;
        int numAces = 0;
        for (Card card : hand) {
            String rank = card.getRank();
            if (rank.equals("Ace")) {
                handValue += 11;
                numAces++;
            } else if (rank.equals("King") || rank.equals("Queen") || rank.equals("Jack")) {
                handValue += 10;
            } else {
                handValue += Integer.parseInt(rank);
            }
        }
        while (numAces > 0 && handValue > 21) {
            handValue -= 10;
            numAces--;
        }
        return handValue;
    }
    public boolean hasBusted() {
        return calculateHandValue() > 21;
    }
}