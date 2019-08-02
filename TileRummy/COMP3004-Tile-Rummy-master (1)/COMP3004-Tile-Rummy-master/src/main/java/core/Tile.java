package core;
import java.util.*;

public class Tile implements Comparable<Tile>{
	private int rank;
	private char color;

	private boolean isJoker;
	
	public Tile() {
		this.rank = 0;
		this.color = 'J';
		this.isJoker = true;
	}

	
	public Tile(int r, char c) {
		this.rank = r;
		this.color = c;
		this.isJoker = false;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int r) { //Only Jokers to be able to change Rank
		this.rank = r;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char c) { //Only Jokers to be able to change Color 
		this.color = c;
	}
	
	public Tile getJoker() {
		Tile joker = new Tile(0, 'J');
		return joker;
	}

	
	public void displayTile() { //toString 
		System.out.print(this.color + "" + this.rank);
	}

	public int compareTo(Tile other) {
		return this.getRank() - other.getRank();
	}
	
	public boolean isJoker() {
		return isJoker;
	}
	
	public void manipulateJoker() {
		if(isJoker) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Change rank to: ");
			int newRank = scanner.nextInt();
			this.rank = newRank;
			
			System.out.println("Change colour to: ");
			String temp = scanner.next();					
			char newColour = temp.charAt(0);
			this.color = newColour;
		}
	}
}