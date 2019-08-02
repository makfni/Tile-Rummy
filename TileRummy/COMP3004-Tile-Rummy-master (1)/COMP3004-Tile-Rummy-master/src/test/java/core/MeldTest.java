package core;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class MeldTest {
	
	@Test
	public void addTileToMeld_True() {
		Tile tile = new Tile(4,'G');
		Meld meld = new Meld();
		meld.addTile(tile);
		assertTrue(meld.getNumTiles() == 1);
	}
	
	@Test
	public void addTilesToMeld_True() {
						
		Tile tile1 = new Tile(4,'G');
		Tile tile2 = new Tile(4,'R');
		Tile tile3 = new Tile(4,'O');
		
		ArrayList<Tile> meldToAdd = new ArrayList<Tile>();
		
		meldToAdd.add(tile1);
		meldToAdd.add(tile2);
		meldToAdd.add(tile3);
		
		Meld meld = new Meld();
		meld.addTiles(meldToAdd);
		assertTrue(meld.getNumTiles() == 3);
	}
	
	@Test
	public void getMeldSize() {
		Tile tile1 = new Tile(1,'R');
		Tile tile2 = new Tile(2,'R');
		Tile tile3 = new Tile(3,'R');
		Tile tile4 = new Tile(4,'R');
		Tile tile5 = new Tile(5,'R');
		Tile tile6 = new Tile(6,'R');
		
		ArrayList<Tile> meldToAdd = new ArrayList<Tile>();
		
		meldToAdd.add(tile1);
		meldToAdd.add(tile2);
		meldToAdd.add(tile3);
		meldToAdd.add(tile4);
		meldToAdd.add(tile5);
		meldToAdd.add(tile6);
				
		Meld meld = new Meld();
		meld.addTiles(meldToAdd);
		assertTrue(meld.getNumTiles() == 6);
	}
	
	@Test
	public void getMeldRank() {
		Tile tile1 = new Tile(1,'R');
		Tile tile2 = new Tile(2,'R');
		Tile tile3 = new Tile(3,'R');
		Tile tile4 = new Tile(4,'R');
		Tile tile5 = new Tile(5,'R');
		Tile tile6 = new Tile(6,'R');
		
		ArrayList<Tile> meldToAdd = new ArrayList<Tile>();
		
		meldToAdd.add(tile1);
		meldToAdd.add(tile2);
		meldToAdd.add(tile3);
		meldToAdd.add(tile4);
		meldToAdd.add(tile5);
		meldToAdd.add(tile6);
		
		Meld meld = new Meld();
		meld.addTiles(meldToAdd);
		assertTrue(meld.getMeldRank() == 21);
	}
	
	@Test
	public void printMeld() {
		Tile tile1 = new Tile(4,'G');
		Tile tile2 = new Tile(4,'R');
		Tile tile3 = new Tile(4,'O');
		
		ArrayList<Tile> meldToAdd = new ArrayList<Tile>();
		
		meldToAdd.add(tile1);
		meldToAdd.add(tile2);
		meldToAdd.add(tile3);
		
		Meld meld = new Meld();
		meld.addTiles(meldToAdd);
		meld.displayMeld();
	}
	
	@Test
	public void setMeldTypeAndGetMeldType() {
		Meld meld = new Meld();
		meld.setMeldType("SET");
		assertTrue(meld.getMeldType() == "SET");
		
		meld.setMeldType("RUN");
		assertTrue(meld.getMeldType() == "RUN");
	}
}
