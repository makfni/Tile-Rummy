package core;
import java.util.*;

public class Meld {
	private ArrayList<Tile> meld;
	private int meldRank;
	private String meldType = "NONE";

	//Constructor
	public Meld() {
		meld = new ArrayList<Tile>();
		meldRank = 0;
		setMeldType("NULL");		
	}

	//Getters and Setters
	public ArrayList<Tile> getMeld() {
		return meld;
	}

	public void setMeld(ArrayList<Tile> meld) {
		this.meld = meld;
	}

	public int getMeldRank() {
		return meldRank;
	}

	public void setMeldRank(int totalMeldRank) {
		this.meldRank += totalMeldRank;
	}
	
	//Methods
	public void addTile(Tile t) {
		meld.add(t);
		setMeldRank(t.getRank());
	}
	
	public void addTiles( ArrayList<Tile> meldToAdd ) {
		setMeld(meldToAdd);
		
		int value = 0;
		for(int i = 0; i < meldToAdd.size(); i++) {
			value += meldToAdd.get(i).getRank();
		}
		setMeldRank(value);
	}
	
	public int getNumTiles() {
		return meld.size();
	}
	
	
	public void displayMeld() {
		System.out.print("{");
		for(Tile t : meld) {
			t.displayTile();
			System.out.print(" ");
		}
		System.out.println("}");
		System.out.println("");
	}

	public String getMeldType() {
		return meldType;
	}

	public void setMeldType(String meldType) {
		this.meldType = meldType;
	}
}
