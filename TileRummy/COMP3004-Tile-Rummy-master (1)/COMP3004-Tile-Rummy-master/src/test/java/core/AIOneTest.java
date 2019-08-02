package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//10a: p1 can play one meld on its first turn

//10c: p1 can play one meld on a subsequent turn
//10d: p1 can play several melds on a subsequent turn
//10e: p1 draws on first turn
//10f: p1 draws on a subsequent turn

public class AIOneTest {

//    Hand hand;
//    Deck d = new Deck();
//    public AIOneTest(Deck d) {hand = new Hand(d);}


    @Test
    public void canPlayMeld(){

        Deck deck = new Deck();
        Hand hand = new Hand(deck);
        Meld meld = hand.getHighestMeld();
        AIOne AI = new AIOne(deck);

        ArrayList<Meld> p1 = new ArrayList<Meld>();
        ArrayList<Meld> table = new ArrayList<Meld>();
        Tile t1 = new Tile(1,'R');
        Tile t2 = new Tile(2,'R');
        Tile t3 = new Tile(3,'R');
        Tile t4 = new Tile(4,'R');

        meld.addTile(t1);
        meld.addTile(t2);
        meld.addTile(t3);
        meld.addTile(t4);

        hand.play(table, meld);
       // System.out.println(table.size());
        assertTrue(table.size() == 1);
    }
    
    @Test 
    public void winsInOneTurn_True() {
    	Deck deck = new Deck();
    	AIOne AI = new AIOne(deck);
    	Meld meld = new Meld();

    	ArrayList<Tile> set = new ArrayList<Tile>();
    	ArrayList<Meld> table = new ArrayList<Meld>();
    	
    	set.add(new Tile(5, 'O'));
    	set.add(new Tile(5, 'R'));
    	set.add(new Tile(5, 'B'));
    	
    	meld.addTiles(set);
    	meld.setMeldType("SET");
    	
    	table.add(meld);
    	
    
    	while(AI.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AI.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	AI.hand.addTile(new Tile(10, 'R'));
    	AI.hand.addTile(new Tile(10, 'O'));
    	AI.hand.addTile(new Tile(10, 'G'));
    	AI.hand.addTile(new Tile(1, 'R'));
    	AI.hand.addTile(new Tile(2, 'R'));
    	AI.hand.addTile(new Tile(3, 'R'));
    	AI.hand.addTile(new Tile(4, 'R'));
    	AI.hand.addTile(new Tile(5, 'G'));
    	
    	AI.hand.displayHand();
    	AI.play(table, deck);
    	AI.play(table, deck);
    	
    	System.out.println(AI.hand.getNumTiles());
    	assertTrue(AI.hand.getNumTiles() == 0);
    }

    
//10e: p1 draws on first turn
    
    
    @Test
    public void drawFirst(){

        ArrayList<Tile> AI = new ArrayList<Tile>();
        Deck deck = new Deck();
        Hand hand = new Hand();

        AI.addAll(deck.getHand());

        AI.add(deck.draw());

        assertTrue(AI.size() == 15);
    }

    @Test
    public void drawSubsequentFirst(){

        ArrayList<Tile> AI = new ArrayList<Tile>();
        Deck deck = new Deck();
        Hand hand = new Hand();

        AI.addAll(deck.getHand());

        AI.add(deck.draw());

        assertTrue(AI.size() == 15);
    }
    
  
}


