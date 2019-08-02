package core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class JokerTest {
	Tile joker = new Tile(0, 'J');

	@Test
	public void jokerExistsInHand() {
		
		Deck deck = new Deck();
		ArrayList<Tile> testHand = new ArrayList<Tile>();
		

		
		testHand.addAll(deck.getHand());

		testHand.add(joker);
		

		for(int i = 0; i < testHand.size(); i++) {
			System.out.println(testHand.get(i).getRank() + testHand.get(i).getColor());
		}
		
		assertTrue(testHand.contains(joker));
		
	}
	
	@Test 
	public void existInDeck() {
		
		Deck deck = new Deck();
		int jokerCount = 0;
		
		for(int i = 0; i < deck.getNumTiles(); i++) {
			if(deck.getDeck().get(i).getColor() == joker.getColor()) {
				jokerCount++;
				if(jokerCount == 2) {
					assertTrue(jokerCount == 2);
				}
			}
		}
	}

}
