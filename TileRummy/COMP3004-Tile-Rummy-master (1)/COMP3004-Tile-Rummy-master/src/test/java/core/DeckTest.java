package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class DeckTest {
	
	@Test
	public void isThereOneHundredFourTiles_True() {
		
		Deck deck = new Deck();
		
		assertTrue(deck.getNumTiles() == 104);
	}
	@Test
	public void isDeckShuffled_False() {
		Deck shuffledDeck = new Deck();
		Deck regularDeck = new Deck();
		
		shuffledDeck.shuffle();
		
		assertFalse(shuffledDeck == regularDeck);
	}
	
	@Test
	public void printDeck() {
		Deck deck = new Deck();
		deck.shuffle();
		deck.displayDeck();
	}
	
	@Test
	public void ableToGetCardFromDeck_True() {
		Deck deck = new Deck();
		deck.shuffle();
		Tile tile = deck.draw();
		System.out.print("Tile ");
		tile.displayTile();
		System.out.println("");
	}
	
	@Test
	public void getHandThatHoldsFourteenTiles_True() {
		Deck deck = new Deck();
		deck.shuffle();
		ArrayList<Tile> hand = new ArrayList<Tile>();
		hand = deck.getHand();
		for (Tile t : hand) {
			System.out.print("Hand Tile:  ");
			t.displayTile();
			System.out.println(" ");
		}
		deck.displayDeck();
		assertTrue(hand.size() == 14);
	}
	
}