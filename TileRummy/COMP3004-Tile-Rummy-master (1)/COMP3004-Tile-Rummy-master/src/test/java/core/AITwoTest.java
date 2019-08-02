package core;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//10a: p1 can play one meld on its first turn

//10c: p1 can play one meld on a subsequent turn
//10d: p1 can play several melds on a subsequent turn
//10e: p1 draws on first turn
//10f: p1 draws on a subsequent turn

public class AITwoTest {

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
    
//    This AI	player	p2 plays	its	initial	30	points only	if	another	player	has	already	
//    played	tiles	on	the	table.
//    If it	can	play	all	its	tiles	(possibly	using	some	tiles	already	on	the	table),	it	
//    does.	
    @Test 
    public void winsInOneTurn_True() {
    	Deck deck = new Deck();
    	Game game = new Game();
    	AIOne AIOne = new AIOne(deck);
    	AITwo AITwo = new AITwo(deck);
    	
    	ArrayList<Meld> table = new ArrayList<Meld>();

    	while(AIOne.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AIOne.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	
    	AIOne.hand.addTile(new Tile(10, 'R'));
    	AIOne.hand.addTile(new Tile(10, 'O'));
    	AIOne.hand.addTile(new Tile(10, 'G'));
    	AIOne.hand.addTile(new Tile(1, 'O'));
    	AIOne.hand.addTile(new Tile(7, 'G'));
    	
    	//{R10, O10, G10}
    	AIOne.play(table, deck);
    	
    	while(AITwo.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AITwo.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	
    	AITwo.hand.addTile(new Tile(10, 'R'));
    	AITwo.hand.addTile(new Tile(10, 'O'));
    	AITwo.hand.addTile(new Tile(10, 'G'));
    	AITwo.hand.addTile(new Tile(1, 'R'));
    	AITwo.hand.addTile(new Tile(2, 'R'));
    	AITwo.hand.addTile(new Tile(3, 'R'));
    	AITwo.hand.addTile(new Tile(4, 'R'));

    	if(table.isEmpty() == false) {
    		while(AITwo.hand.getHand().isEmpty() == false){
    			AITwo.play(table, deck);
    		}
    	}
    	
    
    	assertTrue(AITwo.hand.getHand().isEmpty());
    	
    }
    
//    Otherwise,	each	turn,	this AI	player	plays only	the	tiles	of	its	hand	that	
//    require	using	tiles	on	the	table	to	make	melds.	That	is,	if	it	can’t	win	this	turn,	
//    this AI	player	keeps	in	its	hand	all	melds	that	do	NOT	require	the	use	of	tiles	
//    already	on	the	table.
    @Test 
    public void playNonWinningTurn() {
    	Deck deck = new Deck();
    	AIOne AIOne = new AIOne(deck);
    	AITwo AITwo = new AITwo(deck);
    	Meld meld = new Meld();

    	ArrayList<Meld> table = new ArrayList<Meld>();
    	
    	//existing meld on table
    	while(AIOne.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AIOne.hand.getHand().remove(i);
    		i++;
    	} 	
    	
    	//AIOne plays {G6 G7 G8 G9 G10 }

    	AIOne.hand.addTile(new Tile(6, 'G'));
    	AIOne.hand.addTile(new Tile(7, 'G'));
    	AIOne.hand.addTile(new Tile(8, 'G'));
    	AIOne.hand.addTile(new Tile(9, 'G'));
    	AIOne.hand.addTile(new Tile(10, 'G'));
    	AIOne.hand.addTile(new Tile(1, 'O'));
    	AIOne.hand.addTile(new Tile(4, 'R'));
    	
    	AIOne.play(table, deck);
    	
//    	set.add(new Tile(1, 'G'));
//    	set.add(new Tile(2, 'G'));
//    	set.add(new Tile(3, 'G'));
//    	
//    	meld.addTiles(set);
//    	
//    	table.add(meld);
    	
    	while(AITwo.hand.getHand().isEmpty() == false) {
    		int i = 0;
    		AITwo.hand.getHand().remove(i);
    		i++;
    	} 	
 	
    	//Because AITwo can't win in one turn, this hand should ONLY 
    	//play G11 and G12 to add onto {G6, G7, G8, G9, G10}
    	//but it play's {R9, R10, R11} instead. In this test case, it should only
    	//play tiles that can add onto existing melds. All independent melds must 
    	//remain in hand
    	AITwo.hand.addTile(new Tile(9, 'R'));
    	AITwo.hand.addTile(new Tile(9, 'O'));
    	AITwo.hand.addTile(new Tile(9, 'G'));
    	AITwo.hand.addTile(new Tile(9, 'B'));
    	AITwo.hand.addTile(new Tile(11, 'G'));
    	AITwo.hand.addTile(new Tile(12, 'G'));
    	AITwo.hand.addTile(new Tile(2, 'R'));
    	AITwo.hand.addTile(new Tile(11, 'R'));
    	AITwo.hand.addTile(new Tile(4, 'O'));

  
    	if(table.isEmpty() == false) {
    		AITwo.play(table, deck);
    		AITwo.play(table, deck);
    		//To show which tiles are left in the hand
    	  	for(int i = 0; i < AITwo.hand.getHand().size(); i++) {
        		System.out.println(AITwo.hand.getHand().get(i).getRank());
        	}
        	
    	}
    	//There should only be 3 tiles remaining -> {2, 11, 4}
    	assertTrue(AITwo.hand.getHand().size() == 3);
    
    }
}