package solitaire;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

enum HandResult {
	PLAYERWIN,
	DEALERWIN,
	PUSH
}

public class Blackjack {
	private static Queue<Card> deck;
	
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
		return deck.poll();
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
}
