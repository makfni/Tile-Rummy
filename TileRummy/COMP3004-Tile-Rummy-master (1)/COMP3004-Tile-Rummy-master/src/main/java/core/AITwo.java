package core;
import java.util.ArrayList;
public class AITwo implements Player{
	
	Hand hand;
	boolean hasPlayedFirstTurn = false;
	boolean playedTurn2;

	public AITwo() {
		hand = new Hand();
	}
	
	public AITwo(Deck d) {
		hand = new Hand(d);
	}
	
	public void play(ArrayList<Meld> table, Deck d, ArrayList<Player> plist) {
		
		boolean playedTurn = false;
		playedTurn2 = false;
		
		System.out.println("");
		System.out.println("------- Player Two's Turn -------");
		
		//First Turn 30+ Point Play
		if (!hasPlayedFirstTurn) {
			if (table.size() != 0) {
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
						System.out.println("Player Two Draws a Tile.");
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
											System.out.print("Player Two plays Meld: ");
											meld.displayMeld();
											hasPlayedFirstTurn = true;
											break;
										} else if(meld.getMeld().get(meld.getNumTiles() - 1).getRank() - tableMeld.getMeld().get(0).getRank() == -1) {
											hand.playBeg(table.get(i), meld);
											System.out.print("Player Two plays Meld: ");
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
									System.out.print("Player Two plays Meld: ");
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
						System.out.print("Player Two plays Meld: ");
						meld.displayMeld();
						hasPlayedFirstTurn = true;
						break;
						
					} else {
						System.out.println("Player Two draws a Tile.");
						hand.addTile(d.draw());
						break;
					}
				}
			} else {
				System.out.println("Player Two draws a Tile.");
				hand.addTile(d.draw());
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
					Hand passInHand = new Hand();
					ArrayList<Tile> passInHand_Hand = new ArrayList<Tile>(hand.getHand());
					passInHand.setHand(passInHand_Hand);
					
					playIndividualTiles(table, passInHand);
					if(!playedTurn2) {
						System.out.println("Player Two draws a Tile.");
						hand.addTile(d.draw());
					}
					break;
				}
				
				//WINNING IN ONE TURN /////////////////////////////////////////////////
				Hand hand2 = new Hand();
				ArrayList<Tile> Hand2_Hand = new ArrayList<Tile>(hand.getHand());
				hand2.setHand(Hand2_Hand);
				
				ArrayList< Meld > winInOneTurn = new ArrayList < Meld >();
				//Add Melds into WinningHand
				while(hand2.getHighestMeld().getNumTiles() != 0) {
					//Add Current Meld into winInOneTurn
					winInOneTurn.add(hand2.getHighestMeld());
					ArrayList<Tile> removeTiles = new ArrayList<Tile>();
					
					//What Tiles need to be removed
					for(Tile t: hand2.getHighestMeld().getMeld()) {
						removeTiles.add(t);
					}
					
					//Remove tiles
					for(Tile t: removeTiles) {
						hand2.removeTile(t);
					}
				}
				
				//Add Individual Tiles into WinningHand
				Meld remainingTiles = new Meld();
				remainingTiles.addTiles(hand2.getHand());
				
				Meld tempTiles = new Meld();
				
				for(Tile t: remainingTiles.getMeld()) {
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
									tempTiles.addTile(t);
									nextTile = true;
									break;
								}
							}
							if(nextTile)
								break;
							
							if(t.getRank() - m.getMeld().get(m.getNumTiles() - 1).getRank() == 1) {
								tempTiles.addTile(t);
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
								tempTiles.addTile(t);
								break;
							}
							
						} else {
							continue;
						}
					}
				}
				
				Boolean allTilesCanBePlayed = false;
				if(tempTiles.getNumTiles() == remainingTiles.getNumTiles()) {
					allTilesCanBePlayed = true;
				}
				
				if(allTilesCanBePlayed) {
					playedTurn2 = true;
					winInOneTurn.add(remainingTiles);
					
					//PLAY WINNING HAND
					for(int mIndex = 0; mIndex < winInOneTurn.size(); mIndex++) {
						if(mIndex == winInOneTurn.size() - 1) { //PLAY ALL THE SINGLE TILES
							Meld m = winInOneTurn.get(mIndex);
							Hand passHand = new Hand();
							for(Tile t: m.getMeld()) {
								passHand.addTile(t);
							}
							playIndividualTiles(table, passHand);
						} else { //PLAY ALL THE MELDS
							Meld meld2 = winInOneTurn.get(mIndex);
							for(int i = 0; i < table.size(); i++){
								Meld tableMeld = table.get(i);
								if (tableMeld.getMeldType() == meld2.getMeldType()) {
									if (meld2.getMeldType() == "RUN") {
										if(tableMeld.getMeld().get(0).getColor() == meld2.getMeld().get(0).getColor()) {
											if(meld2.getMeld().get(0).getRank() - tableMeld.getMeld().get((table.get(i).getMeld().size()) - 1).getRank() == 1) {
												hand.play(table.get(i), meld2);
												playedTurn = true;
												System.out.print("Player Two plays Meld: ");
												meld2.displayMeld();
												break;
											} else if(meld2.getMeld().get(meld2.getNumTiles() - 1).getRank() - tableMeld.getMeld().get(0).getRank() == -1) {
												hand.playBeg(table.get(i), meld2);
												System.out.print("Player Two plays Meld: ");
												meld2.displayMeld();
												playedTurn = true;
												break;
											} else {
												hand.play(table, meld2);
												System.out.print("Player Two plays Meld: ");
												meld2.displayMeld();
												playedTurn = true;
												break;
											}
										} else {
											continue;
										}
									} else if (meld2.getMeldType() == "SET") {
										hand.play(table, meld2);
										System.out.print("Player Two plays Meld: ");
										meld2.displayMeld();
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
								hand.play(table, meld2);
								System.out.print("Player Two plays Meld - ");
								meld2.displayMeld();
							}
						}
					}
				} else {
					Hand passInHand = new Hand();
					ArrayList<Tile> passInHand_Hand = new ArrayList<Tile>(hand.getHand());
					passInHand.setHand(passInHand_Hand);
					
					playIndividualTiles(table, passInHand);
					
					if(!playedTurn2) {
						System.out.println("Player Two draws a Tile.");
						hand.addTile(d.draw());
					}
					break;
				}
				
			}
		}
		
	}
	
	public void playIndividualTiles(ArrayList< Meld > tble, Hand h) {
		//Play Individual Tiles on Table if Possible
		for(Tile t: h.getHand()) {
			boolean nextTile = false;
			for(Meld m: tble) {
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
							hand.removeTile(t);
							//removeTiles.add(t);
							System.out.print("Player Two Plays Tile: ");
							playedTurn2 = true;
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
						hand.removeTile(t);
						System.out.print("Player Two Plays Tile: ");
						playedTurn2 = true;
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
						hand.removeTile(t);
						System.out.print("Player Two Plays Tile: ");
						playedTurn2 = true;
						t.displayTile();
						System.out.println("");
						break;
					}
					
				} else {
					continue;
				}
			}
		}
	}
	
	public Hand getHand() {
		// TODO Auto-generated method stub
		return hand;
	}
	
	public int handSize(){
        return hand.getNumTiles();
    }
	
	public void displayHand() {
		// TODO Auto-generated method stub
		hand.displayHand();
	}
}

