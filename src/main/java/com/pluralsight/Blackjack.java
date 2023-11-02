package com.pluralsight;
import java.util.*;
public class Blackjack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Greetings Peasant.");
        System.out.print("State the multitude of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();
        List<Players> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("What be thy name, knave? " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            players.add(new Players(playerName));
        }
        Deck deck = new Deck();
        deck.shuffle();
        for (Players player : players) {
            player.addCardToHand(deck.dealCard());
            player.addCardToHand(deck.dealCard());
        }
        for (Players player : players) {
            System.out.println(player.getName() + "'s hand:");
            List<Card> hand = player.getHand();
            for (Card card : hand) {
                System.out.println(card);
            }
            int handValue = player.calculateHandValue();
            System.out.println(player.getName() + "'s hand: " + handValue);
            System.out.println();
        }
        // Make 'em play, Jamie.
        for (Players player : players) {
            while (true) {
                System.out.print(player.getName() + ", dost thou desire to 'hit' or 'stay'? ");
                String decision = scanner.nextLine();
                if ("hit".equalsIgnoreCase(decision)) {
                    player.addCardToHand(deck.dealCard());
                    int handValue = player.calculateHandValue();
                    System.out.println(player.getName() + "'s hand:");
                    for (Card card : player.getHand()) {
                        System.out.println(card);
                    }
                    System.out.println(player.getName() + "'s hand value: " + handValue);
                    if (player.hasBusted()) {
                        System.out.println(player.getName() + " hath busted! Shame!");
                        break;
                    }
                } else if ("stay".equalsIgnoreCase(decision)) {
                    break;
                } else {
                    System.out.println("Thou speakest nonsense. Cleanse yourself and make sense, madman!");
                }
            }}
        // Jamie, crown the king.
        Players winner = determineWinner(players);
        if (winner != null) {
            System.out.println(winner.getName() + " has won! Now go pray the rosary, sinner.");
        } else {
            System.out.println("By Saint Bernardino's book burning bonfire! It's a tie!");
        }}
    public static Players determineWinner(List<Players> players) {
        Players closestTo21 = null;
        int maxHandValue = 0;
        for (Players player : players) {
            int handValue = player.calculateHandValue();
            if (handValue <= 21 && handValue > maxHandValue) {
                maxHandValue = handValue;
                closestTo21 = player;
            }
        }
        return closestTo21;
    }
}
