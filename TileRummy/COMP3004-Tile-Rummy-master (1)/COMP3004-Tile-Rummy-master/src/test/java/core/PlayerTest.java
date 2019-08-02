package core;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest{

    @Test
    public void canDrawTile(){

        Deck deck = new Deck();

        ArrayList<Tile> hand = new ArrayList<Tile>();

        Tile t1 = new Tile(1, 'R');
        Tile t2 = new Tile(2, 'O');

        hand.add(t1);
        hand.add(t2);

        hand.add(deck.draw());

        assertTrue(hand.size() == 3);

    }

    @Test
    public void returnTrueSize(){
        Deck deck = new Deck();
        Player player = new Player(deck);
       // ArrayList<Tile> hand = new ArrayList<Tile>();

        System.out.println(player.handSize());
        assertTrue(player.handSize() == 14);
    }
}