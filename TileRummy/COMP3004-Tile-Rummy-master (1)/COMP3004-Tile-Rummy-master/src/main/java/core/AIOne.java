//REAL
package core;
import java.util.ArrayList;
public class AIOne implements Player{
	
	Hand hand;
	boolean hasPlayedFirstTurn = false;
	
	public AIOne() {
		hand = new Hand();
	}
	
	public AIOne(Deck d) {
		hand = new Hand(d);
	}
	
	public void play(ArrayList<Meld> table, Deck d, ArrayList<Player> plist) {
		boolean playedTurn = false;
		
		System.out.println("");
		System.out.println("------- Player One's Turn -------");
		
		//First Turn 30+ Point Play
		if (!hasPlayedFirstTurn) {
			while (true) {
				Meld meld = hand.getHighestMeld();
				for(Tile t: hand.getHand()) {
					if (t.getRank() == 0){
						if (meld.getMeldType() == "RUN") {
								if (meld.getMeld().get(meld.getNumTiles() - 1).getRank() < 13) {
									t.setRank((meld.getMeld().get(meld.getNumTiles() - 1).getRank()) + 1);
									t.setColor((meld.getMeld().get(meld.getNumTiles() -1).getColor()));
									meld.addTile(t);
								}
								else if (meld.getMeld().get(0).getRank() > 1) {
									t.setRank((meld.getMeld().get(0).getRank()) - 1);
									t.setColor(meld.getMeld().get(0).getColor());
									
									ArrayList<Tile> copyMeld = new ArrayList<Tile>(meld.getMeld());
									copyMeld.add(0, t);
									meld.setMeld(copyMeld);
									
								}
							}
						else if (meld.getMeldType() == "SET") {
							if(meld.getNumTiles() > 3) {
								break;
							}
							else {
								ArrayList<String> colors = new ArrayList<String>();
								String c1 = "R";
								String c2 = "G";
								String c3 = "B";
								String c4 = "O";
								colors.add(c1);
								colors.add(c2);
								colors.add(c3);
								colors.add(c4);
								
								ArrayList<String> colors2 = new ArrayList<String>(colors);
								
								for(Tile tile : meld.getMeld()) {
									for(String s: colors) {
										if(tile.getColor() == s.charAt(0)) {
											colors2.remove(s);
										}
									}
								}
								t.setColor(colors2.get(0).charAt(0));
								t.setRank(meld.getMeld().get(0).getRank());
							}
								
						}
					}
				}
			
				//HAND HAS NO MELDS TO PLAY
				if(meld.getNumTiles() == 0) {
					System.out.println("Player One Draws a Tile.");
					hand.addTile(d.draw());
					break;
				}
				
				if(meld.getMeldRank() >= 30) {
					for(int i = 0; i < table.size(); i++){
						
						Meld tableMeld = table.get(i);
						
						if (tableMeld.getMeldType() == meld.getMeldType()) {
							if (meld.getMeldType() == "RUN") {
								if(tableMeld.getMeld().get(0).getColor() == meld.getMeld().get(0).getColor()) {
									if(meld.getMeld().get(0).getRank() - tableMeld.getMeld().get((table.get(i).getMeld().size()) - 1).getRank() == 1) {
										hand.play(table.get(i), meld);
										System.out.print("Player One plays Meld: ");
										meld.displayMeld();
										hasPlayedFirstTurn = true;
										break;
									} else if(meld.getMeld().get(meld.getNumTiles() - 1).getRank() - tableMeld.getMeld().get(0).getRank() == -1) {
										hand.playBeg(table.get(i), meld);
										System.out.print("Player One plays Meld: ");
										meld.displayMeld();
										hasPlayedFirstTurn = true;
										break;
									} else {
										continue;
									}
								} else {
									continue;
								}
							} else if (meld.getMeldType() == "SET") {
								hand.play(table, meld);
								System.out.print("Player One plays Meld: ");
								meld.displayMeld();
								hasPlayedFirstTurn = true;
								break;
							} else {
								System.out.println("ERROR: MELD TYPE NOT SET OR RUN.");
							}
						} else {
							continue;
						}
					}
					if(hasPlayedFirstTurn)
						break;
					
					hand.play(table, meld);
					System.out.print("Player One plays Meld: ");
					meld.displayMeld();
					hasPlayedFirstTurn = true;
					break;
					
				} else {
					System.out.println("Player One draws a Tile.");
					hand.addTile(d.draw());
					break;
				}
			}
		} else {
			while (true) {
				Meld meld = hand.getHighestMeld();
				for(Tile t: hand.getHand()) {
					if (t.getRank() == 0){
						if (meld.getMeldType() == "RUN") {
								if (meld.getMeld().get(meld.getNumTiles() - 1).getRank() < 13) {
									t.setRank((meld.getMeld().get(meld.getNumTiles() - 1).getRank()) + 1);
									t.setColor((meld.getMeld().get(meld.getNumTiles() -1).getColor()));
									meld.addTile(t);
								}
								else if (meld.getMeld().get(0).getRank() > 1) {
									t.setRank((meld.getMeld().get(0).getRank()) - 1);
									t.setColor(meld.getMeld().get(0).getColor());
									
									ArrayList<Tile> copyMeld = new ArrayList<Tile>(meld.getMeld());
									copyMeld.add(0, t);
									meld.setMeld(copyMeld);
									
								}
							}
						else if (meld.getMeldType() == "SET") {
							if(meld.getNumTiles() > 3) {
								break;
							}
							else {
								ArrayList<String> colors = new ArrayList<String>();
								String c1 = "R";
								String c2 = "G";
								String c3 = "B";
								String c4 = "O";
								colors.add(c1);
								colors.add(c2);
								colors.add(c3);
								colors.add(c4);
								
								ArrayList<String> colors2 = new ArrayList<String>(colors);
								
								for(Tile tile : meld.getMeld()) {
									for(String s: colors) {
										if(tile.getColor() == s.charAt(0)) {
											colors2.remove(s);
										}
									}
								}
								t.setColor(colors2.get(0).charAt(0));
								t.setRank(meld.getMeld().get(0).getRank());
							}
								
						}
					}
				}
				//CHECK IF MELD EMPTY
				if(meld.getNumTiles() == 0) {

					
					//Play Individual Tiles on Table if Possible
					ArrayList<Tile> removeTiles = new ArrayList<Tile>();
					for(Tile t: hand.getHand()) {
						boolean nextTile = false;
						for(Meld m: table) {
							if(m.getMeldType() == "RUN") {
								if(t.getColor() != m.getMeld().get(0).getColor())
									continue;
								for(int w = 0; w < m.getNumTiles(); w++) {
									if(t.getRank() - m.getMeld().get(w).getRank() == 0) {
										nextTile = true;
										break;
									}
									if(t.getRank() - m.getMeld().get(w).getRank() == -1) {
										m.getMeld().add(w, t);
										removeTiles.add(t);
										System.out.print("Player One Plays Tile: ");
										playedTurn = true;
										t.displayTile();
										System.out.println("");
										nextTile = true;
										break;
									}
								}
								if(nextTile)
									break;
								
								if(t.getRank() - m.getMeld().get(m.getNumTiles() - 1).getRank() == 1) {
									m.getMeld().add(t);
									removeTiles.add(t);
									System.out.print("Player One Plays Tile: ");
									playedTurn = true;
									t.displayTile();
									System.out.println("");
									nextTile = true;
									break;
								}
							} else if(m.getMeldType() == "SET") {
								if(m.getNumTiles() > 3)
									continue;
								
								if(t.getRank() != m.getMeld().get(0).getRank())
									continue;
								
								boolean colorExists = false;
								
								for(Tile t2: m.getMeld()) {
									if(t.getColor() == t2.getColor())
										colorExists = true;
								}
								
								if(colorExists) {
									continue;
								} else {
									m.getMeld().add(t);
									removeTiles.add(t);
									System.out.print("Player One Plays Tile: ");
									playedTurn = true;
									t.displayTile();
									System.out.println("");
									break;
								}
								
							} else {
								continue;
							}
						}
					}
					
					hand.getHand().removeAll(removeTiles);
					
					if(!playedTurn) {
						System.out.println("Player One draws a Tile.");
						hand.addTile(d.draw());
					}
					break;
				}
					for(int i = 0; i < table.size(); i++){
						Meld tableMeld = table.get(i);
						if (tableMeld.getMeldType() == meld.getMeldType()) {
							if (meld.getMeldType() == "RUN") {
								if(tableMeld.getMeld().get(0).getColor() == meld.getMeld().get(0).getColor()) {
									if(meld.getMeld().get(0).getRank() - tableMeld.getMeld().get((table.get(i).getMeld().size()) - 1).getRank() == 1) {
										hand.play(table.get(i), meld);
										playedTurn = true;
										System.out.print("Player One plays Meld: ");
										meld.displayMeld();
										break;
									} else if(meld.getMeld().get(meld.getNumTiles() - 1).getRank() - tableMeld.getMeld().get(0).getRank() == -1) {
										hand.playBeg(table.get(i), meld);
										System.out.print("Player One plays Meld: ");
										meld.displayMeld();
										playedTurn = true;
										break;
									} else {
										hand.play(table, meld);
										System.out.print("Player One plays Meld: ");
										meld.displayMeld();
										playedTurn = true;
										break;
									}
								} else {
									continue;
								}
							} else if (meld.getMeldType() == "SET") {
								hand.play(table, meld);
								System.out.print("Player One plays Meld: ");
								meld.displayMeld();
								playedTurn = true;
								break;
							} else {
								System.out.println("ERROR: MELD TYPE NOT SET OR RUN.");
							}
						} else {
							continue;
						}
					}
					
					if(!playedTurn) {	
						hand.play(table, meld);
						System.out.print("Player One plays Meld - ");
						playedTurn = true;
						meld.displayMeld();
					}
			}
		} 

	}
	
	public int handSize(){
        return hand.getNumTiles();
    }

	public void displayHand() {
		// TODO Auto-generated method stub
		hand.displayHand();
	}

	public Hand getHand() {
		// TODO Auto-generated method stub
		return hand;
	}
}


