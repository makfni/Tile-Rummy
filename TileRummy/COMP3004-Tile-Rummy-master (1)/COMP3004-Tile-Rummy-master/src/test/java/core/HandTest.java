package core;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class HandTest {
	@Test
	public void initialFourteenTilesInHand_True() {
		//Deck
		Deck deck = new Deck();
		deck.shuffle();
		//Hand
		Hand hand = new Hand(deck);
		
		assertTrue( hand.getNumTiles() == 14);
	}
	
	@Test
	public void isHandShuffled_False() {
		//Deck
		Deck deck1 = new Deck();
		deck1.shuffle();
		Deck deck2 = new Deck();
		deck2.shuffle();
		
		//Hand
		Hand hand1 = new Hand(deck1);
		Hand hand2	= new Hand(deck2);
		
		assertFalse( hand1 == hand2);
	}
	
	@Test
	public void printHand() {
		//Deck
		Deck deck = new Deck();
		deck.shuffle();
		
		//Hand 
		Hand hand = new Hand(deck);
		hand.displayHand();
	}
	
	@Test
	public void addTileToHand_True() {
		Deck deck = new Deck();
		Hand hand = new Hand(deck);
		Tile tile = deck.draw();
		
		hand.addTile(tile);
		
		assertTrue(hand.getNumTiles() == 15);
		assertTrue(deck.getNumTiles() == 89);
	}
	
	@Test
	public void removeTileFromHand_True() {
		Deck deck = new Deck();
		Hand hand = new Hand(deck);
		Tile tile = deck.draw();
		
		hand.addTile(tile);
		assertTrue(hand.getNumTiles() == 15);
		
		hand.removeTile(tile);
		assertTrue(hand.getNumTiles() == 14);
	}
	
	@Test
	public void playTile_True() {
		//Game Objects
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand(deck);
		
		//A hypothetical meld currently in play on the board/field 
		Meld meld = new Meld();
		
		Tile mtile1 = new Tile(1, 'R');
		Tile mtile2 = new Tile(2, 'R');
		Tile mtile3 = new Tile(3, 'R');
		Tile mtile4 = new Tile(4, 'R');
		
		meld.addTile(mtile1);
		meld.addTile(mtile2);
		meld.addTile(mtile3);
		meld.addTile(mtile4);
		
		//String Meld that is going to be obtained(Input) from User / Players that wish to play those Tiles
		//Hypothetically already included in player hand
		String s = "{R5, R6, R7, R8}";
		
		Tile htile1 = new Tile(5, 'R');
		Tile htile2 = new Tile(6, 'R');
		Tile htile3 = new Tile(7, 'R');
		Tile htile4 = new Tile(8, 'R');
		
		hand.addTile(htile1);
		hand.addTile(htile2);
		hand.addTile(htile3);
		hand.addTile(htile4);
		
		//Current Hand
		System.out.println(" ");
		System.out.println("Current Player Hand: ");
		hand.displayHand();
		
		System.out.println("Current Meld tiles to be played on: ");
		for(Tile t: meld.getMeld()) {
			t.displayTile();
			System.out.print(" ");
		}
		System.out.println("");
		
		//Play Tiles
		hand.play(meld, s);
		
		//Display Updated Meld
		System.out.println("Updated Meld: ");
		for(Tile t: meld.getMeld()) {
			t.displayTile();
			System.out.print(" ");
		}
		System.out.println("");
		
		//Display Updated Hand
		System.out.println("New Player Hand: ");
		hand.displayHand();
		assertTrue(hand.getNumTiles() == 14);
	}
	
	@Test
	public void getSets_True() {
		Deck deck = new Deck();
		//deck.shuffle();
		Hand hand = new Hand();
		
		//Because Deck is not Shuffled Hand will consist of the last 14 cards on top of deck
		// {O13, O12, O11, O10, O9, O8, O7, O6, O5, O4, O3, O2, O1, B13}	
		//O1 Already Exists in deck so add the other 3 colors equivalent 
		Tile tile1 = new Tile(3, 'R');
		Tile tile2 = new Tile(3, 'G');
		Tile tile3 = new Tile(3, 'B');
		
		//O8 Already Exists in deck so add the other 3 colors equivalent 
		Tile tile4 = new Tile(7, 'R');
		Tile tile5 = new Tile(7, 'G');
		Tile tile6 = new Tile(7, 'B');
		Tile tile7 = new Tile(7, 'O');
		
		hand.addTile(tile1);
		hand.addTile(tile4);
		hand.addTile(tile5);
		hand.addTile(tile2);
		hand.addTile(tile3);
		hand.addTile(tile6);
		hand.addTile(tile7);
		
		//Print Hand
		System.out.println("Hand: ");
		hand.displayHand();
		
		//Print Sets
		System.out.println("ALL POSSIBLE SETS IN HAND: " + hand.getSets().size());
		ArrayList< Meld > sets = hand.getSets();
		for(int i = 0; i < sets.size(); i++) {
			Meld set = sets.get(i);
			System.out.println("");
			System.out.print("{");
			for(Tile t : set.getMeld()) {
				t.displayTile();
				System.out.print(" ");
			}
			System.out.println("}");
			System.out.println("");
			
		}
		
				
		assertTrue(2 == hand.getSets().size());
	}
	
	public void getOverThirtySets_True() {
		Deck deck = new Deck();
		//deck.shuffle();
		Hand hand = new Hand();
		
		//Because Deck is not Shuffled Hand will consist of the last 14 cards on top of deck
		// {O13, O12, O11, O10, O9, O8, O7, O6, O5, O4, O3, O2, O1, B13}	
		//O1 Already Exists in deck so add the other 3 colors equivalent 
		Tile tile1 = new Tile(6, 'R');
		Tile tile2 = new Tile(6, 'G');
		Tile tile3 = new Tile(6, 'B');
		
		//O8 Already Exists in deck so add the other 3 colors equivalent 
		Tile tile4 = new Tile(7, 'R');
		Tile tile5 = new Tile(7, 'G');
		Tile tile6 = new Tile(7, 'B');
		
		Tile tile7 = new Tile(7, 'O');
		
		hand.addTile(tile1);
		hand.addTile(tile4);
		hand.addTile(tile5);
		hand.addTile(tile2);
		hand.addTile(tile3);
		hand.addTile(tile6);
		hand.addTile(tile7);
		
		//Print Hand
		System.out.println("Hand: ");
		hand.displayHand();
		
		//Print Sets
		System.out.println("ALL POSSIBLE SETS IN HAND: " + hand.getSets().size());
		ArrayList< Meld > sets = hand.getSets();
		for(int i = 0; i < sets.size(); i++) {
			Meld set = sets.get(i);
			System.out.println("");
			System.out.print("{");
			for(Tile t : set.getMeld()) {
				t.displayTile();
				System.out.print(" ");
			}
			System.out.println("}");
			System.out.println("");
			
		}
		
				
		assertTrue(2 == hand.getSets().size());
	}
	
	@Test
	public void getRuns_True() {
		Deck deck = new Deck();
		//deck.shuffle();
		Hand hand1 = new Hand(deck);
		Hand hand2 = new Hand(deck);
		
		
		Tile t1 = new Tile(2,'R');
		Tile t2 = new Tile(3,'R');
		Tile t3 = new Tile(3,'R');
		Tile t4 = new Tile(4,'R');
		Tile t5 = new Tile(4,'R');
		Tile t6 = new Tile(5,'R');
		Tile t7 = new Tile(6,'R');
		Tile t8 = new Tile(11,'G');
		hand1.addTile(t1);
		hand1.addTile(t2);
		hand1.addTile(t3);
		hand1.addTile(t4);
		hand1.addTile(t5);
		hand1.addTile(t6);
		hand1.addTile(t7);
		hand1.addTile(t8);
		
		
		
		
		for(Tile t: hand2.getHand()) {
			hand1.addTile(t);
		}
		
		
		//Print Hand
		System.out.println("Hand: ");
		hand1.displayHand();
		
		//Print Sets
		System.out.println("ALL POSSIBLE RUNS IN HAND: " + hand1.getRuns().size());
		ArrayList< Meld > sets = hand1.getRuns();
		for(int i = 0; i < sets.size(); i++) {
			Meld set = sets.get(i);
			System.out.println("");
			System.out.print("{");
			for(Tile t : set.getMeld()) {
				t.displayTile();
				System.out.print(" ");
			}
			System.out.println("}");
			System.out.println("");
		}
		
		assertTrue(hand1.getRuns().size() == 4);
	}
	
	@Test
	public void getAllMelds_True(){
		Deck deck = new Deck();
		Hand hand = new Hand();
		
		//Run 1
		Tile t1 = new Tile(2, 'R');
		Tile t2 = new Tile(3, 'R');
		Tile t3 = new Tile(4, 'R');
		Tile t4 = new Tile(5, 'R');
		Tile t5 = new Tile(6, 'R');
		
		//Run 2
		Tile t6 = new Tile(5, 'B');
		Tile t7 = new Tile(6, 'B');
		Tile t8 = new Tile(7, 'B');

		//Set 1
		Tile t9 = new Tile(8, 'R');
		Tile t10 = new Tile(8, 'G');
		Tile t11 = new Tile(8, 'O');
		
		//Set 1
		Tile t12 = new Tile(10, 'R');
		Tile t13 = new Tile(10, 'G');
		Tile t14 = new Tile(10, 'B');
		Tile t15 = new Tile(10, 'O');
		
		hand.addTile(t1);
		hand.addTile(t2);
		hand.addTile(t3);
		hand.addTile(t4);
		hand.addTile(t5);
		hand.addTile(t6);
		hand.addTile(t7);
		hand.addTile(t8);
		hand.addTile(t9);
		hand.addTile(t10);
		hand.addTile(t11);
		hand.addTile(t12);
		hand.addTile(t13);
		hand.addTile(t14);
		hand.addTile(t15);
		
		//Get All Melds
		ArrayList< Meld > melds = hand.getAllMelds();
		
		//Print Melds
		System.out.println("ALL POSSIBLE MELDS IN HAND: " + melds.size());
		for(int i = 0; i < melds.size(); i++) {
			Meld meld = melds.get(i);
			System.out.println("");
			System.out.print("{");
			for(Tile t : meld.getMeld()) {
				t.displayTile();
				System.out.print(" ");
			}
			System.out.println("}");
			System.out.println("");
		}
		
		assertTrue(melds.size() == 4);
	}

	@Test
	public void getHighestMeld_True() {
		Deck deck = new Deck();
		Hand hand = new Hand();
		
		//Run 1 (RANK 20)
		Tile t1 = new Tile(2, 'R');
		Tile t2 = new Tile(3, 'R');
		Tile t3 = new Tile(4, 'R');
		Tile t4 = new Tile(5, 'R');
		Tile t5 = new Tile(6, 'R');
		
		//Run 2 (RANK 18)
		Tile t6 = new Tile(5, 'B');
		Tile t7 = new Tile(6, 'B');
		Tile t8 = new Tile(7, 'B');

		//Set 1 (RANK 24)
		Tile t9 = new Tile(8, 'R');
		Tile t10 = new Tile(8, 'G');
		Tile t11 = new Tile(8, 'O');
		
		//Set 1 (RANK 40)
		Tile t12 = new Tile(10, 'R');
		Tile t13 = new Tile(10, 'G');
		Tile t14 = new Tile(10, 'B');
		Tile t15 = new Tile(10, 'O');
		
		hand.addTile(t1);
		hand.addTile(t2);
		hand.addTile(t3);
		hand.addTile(t4);
		hand.addTile(t5);
		hand.addTile(t6);
		hand.addTile(t7);
		hand.addTile(t8);
		hand.addTile(t9);
		hand.addTile(t10);
		hand.addTile(t11);
		hand.addTile(t12);
		hand.addTile(t13);
		hand.addTile(t14);
		hand.addTile(t15);
		
		Meld highestMeld = hand.getHighestMeld();
		
		assertTrue(highestMeld.getMeldRank() == 40);
	}
	
	@Test
	public void getThirtyPointTest() {
		Deck deck = new Deck();
		Hand hand = new Hand();
		
		
		
		//Set 1 (RANK 30)
		Tile t12 = new Tile(10, 'R');
		Tile t13 = new Tile(10, 'G');
		Tile t14 = new Tile(10, 'B');
		
		hand.addTile(t12);
		hand.addTile(t13);
		hand.addTile(t14);

		
		Meld highestMeld = hand.getHighestMeld();
		
		assertTrue(highestMeld.getMeldRank() == 30);
	}
	
	@Test
	public void getLowestMeld_True() {
		Deck deck = new Deck();
		Hand hand = new Hand();
		
		//Run 1 (RANK 20)
		Tile t1 = new Tile(2, 'R');
		Tile t2 = new Tile(3, 'R');
		Tile t3 = new Tile(4, 'R');
		Tile t4 = new Tile(5, 'R');
		Tile t5 = new Tile(6, 'R');
		
		//Run 2 (RANK 18)
		Tile t6 = new Tile(5, 'B');
		Tile t7 = new Tile(6, 'B');
		Tile t8 = new Tile(7, 'B');

		//Set 1 (RANK 24)
		Tile t9 = new Tile(8, 'R');
		Tile t10 = new Tile(8, 'G');
		Tile t11 = new Tile(8, 'O');
		
		//Set 1 (RANK 40)
		Tile t12 = new Tile(10, 'R');
		Tile t13 = new Tile(10, 'G');
		Tile t14 = new Tile(10, 'B');
		Tile t15 = new Tile(10, 'O');
		
		hand.addTile(t1);
		hand.addTile(t2);
		hand.addTile(t3);
		hand.addTile(t4);
		hand.addTile(t5);
		hand.addTile(t6);
		hand.addTile(t7);
		hand.addTile(t8);
		hand.addTile(t9);
		hand.addTile(t10);
		hand.addTile(t11);
		hand.addTile(t12);
		hand.addTile(t13);
		hand.addTile(t14);
		hand.addTile(t15);
		
		Meld lowestMeld = hand.getLowestMeld();
		
		assertTrue(lowestMeld.getMeldRank() == 18);
	}
	
	
	@Test
	public void initialPlay_True() {
		
	}
}
