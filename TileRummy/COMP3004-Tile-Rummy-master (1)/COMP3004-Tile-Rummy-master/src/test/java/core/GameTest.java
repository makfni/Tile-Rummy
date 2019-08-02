package core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class GameTest {
	@Test
	public void gameDeck_InitializedAndShuffled_False() {
		Game game = new Game();
		game.setUp();
		
		Deck deck = new Deck();
		Hand hand1 = new Hand(deck);
		Hand hand2 = new Hand(deck);
		Hand hand3 = new Hand(deck);
		Hand hand4 = new Hand(deck);
		
		assertFalse(game.deck == deck);
	}

	@Test
	public void gamePlayers_InitializedAndHandDisplayed_True() {
		Game game = new Game();
		game.setUp();
		game.displayInfo();
		
		assertTrue(game.player.getHand().getNumTiles() == 14);
		assertTrue(game.aiOne.hand.getNumTiles() == 14);
		assertTrue(game.aiTwo.hand.getNumTiles() == 14);
		assertTrue(game.aiThree.hand.getNumTiles() == 14);
	}
	
//	@Test
//	public void spotJoker_True() {
//		Game game = new Game();
//		Deck deck = new Deck();
//		Joker joker = new Joker();
//		Player player = new Player();
//		boolean hasJoker = false; 
//		
//		ArrayList<Tile> myHand = deck.getHand();
//		
//		Tile newJoker = joker.getJoker();
//		myHand.add(newJoker);
//		
//		player.getHand().getHand().addAll(myHand);
//		
//		while(game.playerTurn()) {
//			
//		}
////		for(int i = 0; i < player.getHand().getNumTiles(); i++) {
////			System.out.println(player.getHand().getHand().get(i).getRank());
////		}
//		
//		game.playerTurn();
//		
//		}
		

	
	@Test
	public void checkForWinner_True() {
		Game game = new Game();
		game.setUp();
		Hand hand = new Hand();
		game.player.getHand().setHand(hand.getHand());
		
		assertTrue(game.checkForWinner());
	}
	
//	@Test
//	public void playerWinsFirstTurn_True() {
//		Game game = new Game();
//		game.setUp();
//		
//		Tile t1 = new Tile(10, 'R');
//		Tile t2 = new Tile(11, 'R');
//		Tile t3 = new Tile(12, 'R');
//		Tile t4 = new Tile(13, 'R');
//		Hand hand = new Hand();
//		hand.addTile(t1);
//		hand.addTile(t2);
//		hand.addTile(t3);
//		hand.addTile(t4);
//	
//		game.player.getHand().setHand(hand.getHand());
//		
//		Meld m = new Meld();
//		String s = "{R10, R11, R12, R13}";
//		game.player.play(table, deck);
//		
//		assertTrue(game.checkForWinner());
//	}
//	
	public void runGame_True() {
		Game game = new Game();
		game.setUp();
		game.runGame();
	}
}
