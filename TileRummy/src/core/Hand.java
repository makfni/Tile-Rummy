//101015537
package core;
import java.util.*;

public class Hand{
	
	private ArrayList<Tile> hand;
	//private ArrayList< Meld > meldsInHand = new ArrayList< Meld >();
	
	public Hand(Deck d) {
		hand = d.getHand();
	}
	
	public Hand() {
		hand = new ArrayList<Tile>();
	}
	
	public Hand(Hand h){
		hand = h.hand;
	}
	
	//Getters and Setters
	public ArrayList<Tile> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Tile> hand) {
		this.hand = hand;
	}
	
	
	//Methods
	public void addTile(Tile t) {
		hand.add(t);
	}
	
	
	public void removeTile(Tile t) {
		hand.remove(t);
	}
	
	public int getNumTiles() {
		return hand.size();
	}
	
	public void sortHand() {
		//Creation of Color ArrayLists
		ArrayList<Tile> redTiles = new ArrayList<Tile>();
		ArrayList<Tile> greenTiles = new ArrayList<Tile>();
		ArrayList<Tile> blueTiles = new ArrayList<Tile>();
		ArrayList<Tile> orangeTiles = new ArrayList<Tile>();
				
		//Separate Tiles into their Respective Colored Arrays
		for(Tile t: hand) {
			if(t.getColor() == 'R') {
				redTiles.add(t);
			} else if(t.getColor() == 'G') {
				greenTiles.add(t);
			} else if(t.getColor() == 'B') {
				blueTiles.add(t);
			} else {
				orangeTiles.add(t);
			}
		}
				
		//Sort ColoredArrays by Tile Rank
		Collections.sort(redTiles);
		Collections.sort(greenTiles);
		Collections.sort(blueTiles);
		Collections.sort(orangeTiles);
		
		ArrayList<Tile> tmpHand = new ArrayList<Tile>();
		tmpHand.addAll(redTiles);
		tmpHand.addAll(greenTiles);
		tmpHand.addAll(blueTiles);
		tmpHand.addAll(orangeTiles);
		
		hand = tmpHand;

	}
	
	public void displayHand() {
		sortHand();
		System.out.print("{");
		for(Tile t : hand) {
			t.displayTile();
			System.out.print(" ");
		}
		System.out.println("}");
		System.out.println("");
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Hand Play() method corresponding to USER INPUT. 
	 * User inputting "{01, 02, 03}" as String plays meld {01, 02, 03}
	 * into corresponding passed in tableMeld
	 */
	public void play(Meld tableMeld, String str) {

		//Parse String
		str = str.replaceAll("\\{", "").replaceAll("\\}",""); // Removes Brackets in String
		String[] stringArray = str.split( ", " ); // Converts String to a String Array
//		for(int i = 0; i < stringArray.length; i++) {
//			if(stringArray[i] == "J0"){
//				Joker joker = new Joker();
//				stringArray[i] = joker.changeJoker();
//				
//			}
//		}
		
		//For Each Tile add Tile into Meld and remove Tile from Hand 
		for(String s: stringArray) {
			char color = s.charAt(0);
			int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
			for(Tile t : hand) {
				if(t.getColor() == color && t.getRank() == value) {
					tableMeld.addTile(t);
					removeTile(t);
					break;
				}
			}
		}
	}
	
	/*
	 * Hand Play() method dealing with ACTUAL MELDS
	 * Passed in meld are played into corresponding passed in tableMeld
	 */
	public void play(Meld tableMeld, Meld meld) {
		//For Each Tile add Tile into Meld and remove Tile from Hand
		for(Tile t: meld.getMeld()) {
			tableMeld.addTile(t);
			removeTile(t);
			//break;
		}
	}
	
	/*
	 * Hand Play() method dealing with ACTUAL MELDS
	 * Passed in meld are played into corresponding passed in tableMeld
	 */
	public void playBeg(Meld tableMeld, Meld meld) {
		Meld temp = meld;		
		
		//For Each Tile add Tile into Meld and remove Tile from Hand	
		for(Tile t: tableMeld.getMeld()) {
			temp.addTile(t);
			removeTile(t);
			//break;
		}
		tableMeld = temp;		
	}
	
	public void play(ArrayList<Meld> table , String str) {
		Meld tableMeld = new Meld();
		//Parse String
		str = str.replaceAll("\\{", "").replaceAll("\\}",""); // Removes Brackets in String
		String[] stringArray = str.split( ", " ); // Converts String to a String Array
		
		//For Each Tile add Tile into Meld and remove Tile from Hand 
		for(String s: stringArray) {
			char color = s.charAt(0);
			int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
			for(Tile t : hand) {
				if(t.getColor() == color && t.getRank() == value) {
					tableMeld.addTile(t);
					removeTile(t);
					break;
				}
			}
		}
		if(tableMeld.getMeld().get(0).getColor() != tableMeld.getMeld().get(1).getColor() && tableMeld.getMeld().get(0).getRank() - tableMeld.getMeld().get(1).getRank() == 0) {
			tableMeld.setMeldType("SET");
		} else if (tableMeld.getMeld().get(0).getColor() == tableMeld.getMeld().get(1).getColor() && tableMeld.getMeld().get(0).getRank() - tableMeld.getMeld().get(1).getRank() == -1) {
			tableMeld.setMeldType("RUN");
		} else {
			System.out.println("PLAYER PLAYED INVALID MELD. GAME WILL NOT WORK PROPERLY.");
		}
		table.add(tableMeld);
	}	
	
	/*
	 * Hand Play() method dealing with ACTUAL MELDS
	 * Passed in meld are 
	 */
	public void play(ArrayList< Meld > table, Meld meld) {
		table.add(meld);
		for(Tile t: meld.getMeld()) {
			removeTile(t);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	//	MELDS
	/////////////////////////////////////////////////////////////////////////
	
	/*
	 * Returns a 2D ArrayList
	 * Showing all possible sets being held within the hand
	 * @@@ No Melds/Tiles are removed from Hand @@@@
	 */
	public ArrayList< Meld > getSets() {
		
		ArrayList< Meld > setMelds = new ArrayList< Meld >();

		
		
		for(int i = 0; i < hand.size(); i++) {
		
			Meld setMeld = new Meld();
			setMeld.setMeldType("SET");
			ArrayList<Character> currColor = new ArrayList<Character>();
			
			setMeld.addTile(hand.get(i));
			currColor.add(hand.get(i).getColor());
			
			boolean rankExists = false;
			
			for(Meld m: setMelds) {
				for(Tile t: m.getMeld()) {
					if(hand.get(i).getRank() == t.getRank())
						rankExists = true;
				}
			}
			
			if(rankExists)
				continue;
			
			for(int j = i; j < hand.size(); j++) {
				
				if(hand.get(i) == hand.get(j))
					continue;
				
				if(hand.get(i).getRank() == hand.get(j).getRank()) {
					boolean pass = true;
					
					for(int k = 0; k < currColor.size(); k++) {
						if(hand.get(j).getColor() == currColor.get(k)) {
							pass = false;
							break;
						}
					}
					
					if(pass) {
						setMeld.addTile(hand.get(j));
						currColor.add(hand.get(j).getColor());
					}
				}
			}
			
			if(setMeld.getMeld().size() > 2) {
				setMelds.add(setMeld);
			}
		}
		return setMelds;
	}
	
	/*
	 * Returns a 2D ArrayList
	 * Showing all possible runs being held within the hand
	 * @@@ No Melds/Tiles are removed from Hand @@@@
	 */
	public ArrayList< Meld > getRuns() {
		//Return This
		ArrayList< Meld > runMelds = new ArrayList< Meld >();
		
		//Creation of Color ArrayLists
		ArrayList<Tile> redTiles = new ArrayList<Tile>();
		ArrayList<Tile> greenTiles = new ArrayList<Tile>();
		ArrayList<Tile> blueTiles = new ArrayList<Tile>();
		ArrayList<Tile> orangeTiles = new ArrayList<Tile>();
		
		//Separate Tiles into their Respective Colored Arrays
		for(Tile t: hand) {
			if(t.getColor() == 'R') {
				redTiles.add(t);
			} else if(t.getColor() == 'G') {
				greenTiles.add(t);
			} else if(t.getColor() == 'B') {
				blueTiles.add(t);
			} else {
				orangeTiles.add(t);
			}
		}
		
		//Sort ColoredArrays by Tile Rank
		Collections.sort(redTiles);
		Collections.sort(greenTiles);
		Collections.sort(blueTiles);
		Collections.sort(orangeTiles);
		
		//For each Colored Array
		for(int i = 0; i < 4; i++) {
			
			//Variables
			boolean reachedEnd = false;
			int startingFromLastTile = 0;
			ArrayList<Tile> colorArray;
			
			//Set specific current colorArray
			if(i == 0) {
				colorArray = redTiles;
			} else if(i == 1) {
				colorArray = greenTiles;
			} else if(i == 2) {
				colorArray = blueTiles;
			} else {
				colorArray = orangeTiles;
			}
			
			//Keep running until end of specific current colorArray reached
			while(true) {
				Meld runMeld = new Meld();
				runMeld.setMeldType("RUN");
				//If specific current colorArray holds less than 3 tiles move onto next colorArray
				if(colorArray.size() < 3) {
					break;
				}
				
				runMeld.addTile(colorArray.get(startingFromLastTile));
				
				//Loop through all elements in current colorArray 
				for(int j = (startingFromLastTile + 1); j < colorArray.size(); j++) {
					//If Same TILE continue;
					if(colorArray.get(j).getRank() == colorArray.get(j-1).getRank()) {
						if(j+1 > colorArray.size()-1) {
							reachedEnd = true;
							break;
						}
						continue;
					}
					if(colorArray.get(j).getRank() - colorArray.get(j-1).getRank() == 1 ) {
						runMeld.addTile(colorArray.get(j));
						//Reached End of Current colorArray. Break out of For Loop
						if(j == colorArray.size()-1) {
							reachedEnd = true;
							break;
						}
						continue;
					} else {
						startingFromLastTile = j;
						//Reached End of Current colorArray. Break out of For Loop
						if(j == colorArray.size()-1) {
							reachedEnd = true;
						}
						break;
					}
				}
				
				//Valid Meld must hold 3+ Tiles
				if(runMeld.getMeld().size() > 2) {
					runMelds.add(runMeld);
				}
				
				//Break out of While Loop
				if(reachedEnd) {
					break;
				}
			}
		}
		
		return runMelds;
	}
	
	/*
	 * Returns a 2D ArrayList
	 * Showing all possible MELDS being held within the hand
	 * @@@ No Melds/Tiles are removed from Hand @@@@
	 */
	public ArrayList< Meld > getAllMelds(){
		//Return This
		ArrayList< Meld > melds = new ArrayList< Meld >();
		ArrayList< Meld > setMelds = getSets();
		ArrayList< Meld > runMelds = getRuns();
		
		for(Meld m : setMelds) {
			melds.add(m);
		}
		
		for(Meld m : runMelds) {
			melds.add(m);
		}
		return melds;
	}
	
	/*
	 * Returns the highest Meld being held by hand
	 * using method getAllMelds()
	 */
	public Meld getHighestMeld() {
		ArrayList< Meld > melds = getAllMelds();
		Meld meld = new Meld();
		
		int hiMeldRank = 0;
		for(Meld m: melds) {
			if(m.getMeldRank() > hiMeldRank) {
				meld = m;
				hiMeldRank = m.getMeldRank();
			}
		}
		return meld;
	}
	
	/*
	 * Returns the lowest Meld being held by hand
	 * using method getAllMelds()
	 */
	public Meld getLowestMeld() {
		ArrayList< Meld > melds = getAllMelds();
		Meld meld = new Meld();
		
		int loMeldRank = 1000;
		for(Meld m: melds) {
			if(m.getMeldRank() < loMeldRank) {
				meld = m;
				loMeldRank = m.getMeldRank();
			}
		}
		return meld;
	}
}
