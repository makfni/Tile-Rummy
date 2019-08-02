//101015537
package core;

import java.util.*;

public class Deck {

	private static char[] colors = {'R', 'G', 'B', 'O'};
	private static int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
	
	private ArrayList<Tile> deck; 

	//Creates a standard Deck of 104 tiles
	public Deck() {
		deck = new ArrayList<Tile>();
		
		for(int i = 0; i < 2; i++) {
			for(int c = 0; c < colors.length; c++ ) {
				for(int v = 0; v < values.length; v++) {
					Tile tile = new Tile(values[v], colors[c]);
					deck.add(tile);
				}
			}
			//Create and Add Joker Tile
			Tile tile = new Tile();
			deck.add(tile);
		}
	}
	
	//Get Number of Tiles currently in Deck
	public int getNumTiles() {
		return deck.size();
	}
	
	public ArrayList<Tile> getDeck(){
		return deck;
	}
	
	//Shuffles the Deck
	public void shuffle() {
		Collections.shuffle(deck);
	}

	//Draws Tile from Top of Deck 
	public Tile draw() {
		Tile tile;
		if(getNumTiles() > 0) {
			tile = deck.get(getNumTiles() - 1);
			deck.remove(tile);
			return tile;
		}

		System.out.println("Deck Empty. No Tile Returned...");
		return null;
	}

	//Draws 14 Tiles from Top of Deck
	public ArrayList<Tile> getHand() {
		ArrayList<Tile> hand = new ArrayList<Tile>();
		for(int i = 0; i < 14; i ++) {
			hand.add(i, draw());
		}
		return hand;
	}

	//Displays Current Deck
	public void displayDeck() {
		System.out.print("{");
		for(int i = 0; i < getNumTiles(); i++) {
			deck.get(i).displayTile();
			System.out.print(" ");
		}
		System.out.print("}");
		System.out.println("");
	}
}