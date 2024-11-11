package solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import solitaire.Card.Suit;

enum HandResult {
	PLAYERWIN,
	DEALERWIN,
	PUSH
}

public class Blackjack {
	private static List<Card> deck;
	public static ArrayList<Card> playerHand = new ArrayList<Card>();
	public static ArrayList<Card> dealerHand = new ArrayList<Card>();
	
	private static void setupDeck() {
		Suit[] suits = new Suit[]{Suit.Spades, Suit.Clubs, Suit.Hearts, Suit.Diamonds};
		
		for (int i = 1; i <= 13; i++) {
			for (int j = 0; j < 4; j++) {
				deck.add(new Card(i, suits[j]));
			}
		}
		
		Collections.shuffle(deck);
	}
	
	public Blackjack() {
		setupDeck();
	}
	
	//the part of your program that's in charge of game rules goes here.
	public static HandResult determineHandResult(ArrayList<Card> playerCards, ArrayList<Card> dealerCards) {
		int playerHandValue = 0;
		int dealerHandValue = 0;
		
		for (int i = 0; i < playerCards.size(); i++) playerHandValue += playerCards.get(i).gameValue;
		for (int i = 0; i < dealerCards.size(); i++) dealerHandValue += dealerCards.get(i).gameValue;
		
		if (playerHandValue > dealerHandValue) return HandResult.PLAYERWIN;
		else if (playerHandValue < dealerHandValue) return HandResult.DEALERWIN;
		else return HandResult.PUSH;
	}
	
	public static Card drawNextCard() {
		Card card = deck.get(0);
		deck.remove(0);
		
		return card;
	}
	
	public static void addToHand(String hand, Card card) {
		if (hand.equals("player")) playerHand.add(card);
		else if (hand.equals("dealer")) dealerHand.add(card);
		else throw new Error("Atempted to add to illegal hand");
	}
	
	public static boolean determineBust(ArrayList<Card> hand) {
		int handValue = 0;
		
		for (int i = 0; i < hand.size(); i++) handValue += hand.get(i).gameValue;
		
		if (handValue > 21) return true;
		else return false;
	}
	
	public static boolean determineBlackjack(ArrayList<Card> hand) {
		if (hand.size() == 2) {
			if (hand.get(0).value >= 10 && hand.get(1).value == 1) return true;
			if (hand.get(1).value >= 10 && hand.get(0).value == 1) return true;
		}
		
		return false;
	}
	
	public static void reset() {
		playerHand = new ArrayList<Card>();
		dealerHand = new ArrayList<Card>();
	}
}

