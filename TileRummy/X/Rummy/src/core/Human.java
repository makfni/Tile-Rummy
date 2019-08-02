package core;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Label;

public class Human implements Player{
    private Hand hand;
    //Initialize hand
    public Human(Deck deck){
        hand = new Hand(deck);
    }
    
    public Human() {
    	hand = new Hand();
    }
    	
    //Getter
    public Hand getHand(){
        return hand;
    }

    

    public void play(ArrayList<Meld> table, Deck deck, ArrayList<Player> plist) {		
		boolean turnEnded = false;
		while(!turnEnded) {
			System.out.println("--- Human Player's Turn ---");
			System.out.println("1 - Play");
			System.out.println("2 - Draw");
			
			//Player Input
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int selection = scanner.nextInt();
			switch(selection) {
			case 1:
				while(true){
					//Check if hand contains a Joker
					for(Tile t: hand.getHand()) {
						if(t.getRank() == 0) {
							System.out.println("Would you like to manipulate a joker? \n" +
									"1 - Yes \n" +
									"2 - No");
							int nestSwitch = scanner.nextInt();
							switch(nestSwitch) {
							case 1:
								t.manipulateJoker();
								System.out.println("Updated hand:");
								break;
							case 2:
								break;
							}							
						}
					}
				
					getHand().displayHand();
					System.out.println("");
					System.out.println("Enter Table Meld number to play into. (0) to play into new Table Meld Number: ");
					int meldAtPos = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Enter Meld to play ( EX: {R1, R2, R3} ): ");
					String stringMeld = scanner.nextLine();
					
					if(meldAtPos == 0) {
						hand.play(table, stringMeld);
					} else {
						hand.play(table.get(meldAtPos - 1), stringMeld);
					}
					
					System.out.println("Human Player plays meld: " + stringMeld);
					System.out.println("Do you want to play an additional Meld?");
					System.out.println("1 - Play");
					System.out.println("0 - End Turn");
					int selection2 = scanner.nextInt();
					if(selection2 == 0) {
						turnEnded = true;
						break;
					}
				}
				break;
			case 2:
				System.out.println("Human Player draws a Tile.");
				drawTile(deck);
				turnEnded = true;
				break;
			default:
				throw new IllegalArgumentException("Unknown selection: " + selection);
			}
		}	
	}
    
    //display hand
    public void displayHand(){
       hand.displayHand();
    }
    
    public boolean handContains(Tile tile) {
    	if(getHand().getHand().contains(tile)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    //draw a tile from deck to player hand
    public void drawTile(Deck deck){
        if(deck.getNumTiles() != 0) {
            hand.addTile(deck.draw());
        }
    }

    //hand size
    public int handSize(){
        return hand.getNumTiles();
    }
    
    public void winMessage() {
		// TODO Auto-generated method stub
		System.out.println("Human Player WON GAME !");
	}
}



