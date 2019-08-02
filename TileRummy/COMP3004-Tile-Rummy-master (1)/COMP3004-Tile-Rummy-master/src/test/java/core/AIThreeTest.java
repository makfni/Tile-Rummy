package core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//10a: p1 can play one meld on its first turn

//10c: p1 can play one meld on a subsequent turn
//10d: p1 can play several melds on a subsequent turn
//10e: p1 draws on first turn
//10f: p1 draws on a subsequent turn

public class AIThreeTest {

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
     //   System.out.println(table.size());
        assertTrue(table.size() == 1);
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
    
    @Test 
    public void winsInOneTurn_True() {
    	Deck deck = new Deck();
    	AIThree AIThree = new AIThree(deck);
    	int p = 3;
    	int p1 = 3;
    	int p2 = 3;

    	ArrayList<Meld> table = new ArrayList<Meld>();
    	
    	

    	while(AIThree.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AIThree.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	AIThree.hand.addTile(new Tile(10, 'R'));
    	AIThree.hand.addTile(new Tile(10, 'O'));
    	AIThree.hand.addTile(new Tile(10, 'G'));
    	AIThree.hand.addTile(new Tile(1, 'R'));
    	AIThree.hand.addTile(new Tile(2, 'R'));
    	AIThree.hand.addTile(new Tile(3, 'R'));
    	AIThree.hand.addTile(new Tile(4, 'R'));

    	
    	AIThree.play(table, deck, p, p1, p2);
    	AIThree.play(table, deck, p, p1, p2);
 
    	assertTrue(AIThree.hand.getHand().isEmpty() == true);
    }
    
    
    @Test 
    public void DoesNotWinOnFirstTurn() {
    	Deck deck = new Deck();
    	AIOne AIOne = new AIOne(deck);
    	AITwo AITwo = new AITwo(deck);
    	AIThree AIThree = new AIThree(deck);
    	int p = 10;

    	ArrayList<Meld> table = new ArrayList<Meld>();
    	
    	while(AIOne.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AIOne.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	
    	AIOne.hand.addTile(new Tile(10, 'R'));
    	AIOne.hand.addTile(new Tile(10, 'O'));
    	AIOne.hand.addTile(new Tile(10, 'G'));
    	AIOne.hand.addTile(new Tile(1, 'R'));
    	AIOne.hand.addTile(new Tile(2, 'R'));
    	AIOne.hand.addTile(new Tile(3, 'R'));
    	AIOne.hand.addTile(new Tile(3, 'O'));
    	AIOne.hand.addTile(new Tile(5, 'B'));
    	AIOne.hand.addTile(new Tile(7, 'O'));
    	AIOne.hand.addTile(new Tile(12, 'O'));
    	
    	//{R10, O10, G10}
    	//7 CARDS REMAINING 
    	AIOne.play(table, deck);
    	
    	while(AITwo.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AITwo.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	
    	AITwo.hand.addTile(new Tile(11, 'R'));
    	AITwo.hand.addTile(new Tile(11, 'O'));
    	AITwo.hand.addTile(new Tile(11, 'G'));
    	AITwo.hand.addTile(new Tile(1, 'R'));
    	AITwo.hand.addTile(new Tile(2, 'R'));
    	AITwo.hand.addTile(new Tile(3, 'R'));
    	AITwo.hand.addTile(new Tile(3, 'O'));
    	AITwo.hand.addTile(new Tile(5, 'B'));
    	AITwo.hand.addTile(new Tile(7, 'O'));
    	AITwo.hand.addTile(new Tile(12, 'O'));
    	
    	//{R11, O11, G11}
    	//7 CARDS REMAINING 
    	AITwo.play(table, deck);
    	
    	while(AIThree.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AIThree.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	//HAND SIZE: 8
    	//SHOULD ONLY PLAY G7 AND 10B
    	//NOT G1,G2,G3,G4
    	AIThree.hand.addTile(new Tile(12, 'R'));
    	AIThree.hand.addTile(new Tile(12, 'G'));
    	AIThree.hand.addTile(new Tile(12, 'O'));
    	AIThree.hand.addTile(new Tile(11, 'B'));
    	AIThree.hand.addTile(new Tile(10, 'B'));
    	AIThree.hand.addTile(new Tile(9, 'O'));
    	AIThree.hand.addTile(new Tile(1, 'G'));
    	AIThree.hand.addTile(new Tile(7, 'B'));
    	AIThree.hand.addTile(new Tile(2, 'G'));
    	AIThree.hand.addTile(new Tile(3, 'G'));
    	AIThree.hand.addTile(new Tile(4, 'G'));
    	AIThree.hand.addTile(new Tile(6, 'B'));
    	

//    	If	no	other	player	has	3	fewer	tiles	than	p3,	then p3 plays only	the	tiles	
//    	of	its	hand	that	require	using	tiles	on	the	table	to	make	melds	(as	in
//    	Strategy.
//    	Else p3 plays	all	the	tiles	it	can.
    	AIThree.play(table, deck, p, AIOne.hand.getHand().size(), AITwo.hand.getHand().size());
    	AIThree.play(table, deck, p, AIOne.hand.getHand().size(), AITwo.hand.getHand().size());

    	assertTrue(AIThree.hand.getHand().size() == 7);
    	
    }
}