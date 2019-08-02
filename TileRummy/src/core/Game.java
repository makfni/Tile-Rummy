package core;

import java.util.*;

public class Game {
	int numPlayers;
	Scanner scanner;
	boolean wonGame = false;
	
	ArrayList< Meld > table;
	Deck deck;
	
	ArrayList< Player > players = new ArrayList< Player >();
	
	boolean gameWon = false;
	boolean riggedGame = false;
	
	static int interval;
	static Timer timer;
	
	public Game(){
		
	}
	
	public void run() {
		rigging();
		init();
		runGame();
	}
	
	public void rigging() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select Game Start\n"
				+ "(1) Normal Game Mode\n"
				+ "(2) Setup/Rigged Game Mode\n");
		int gameModeChoice = scanner.nextInt();
		if(gameModeChoice == 2) {
			riggedGame = true;
			numPlayers = 4;
			Human h = new Human();
			AIOne aiOne = new AIOne();
			AITwo aiTwo = new AITwo();
			AIThree aiThree = new AIThree();
			
					for(int i = 0; i < 4; i++) {
						Scanner scanner2 = new Scanner(System.in);
						System.out.println("ENTER DESIRED HAND ({R1, R2, R3})");
						String str = scanner2.nextLine();
						
						str = str.replaceAll("\\{", "").replaceAll("\\}",""); // Removes Brackets in String
						String[] stringArray = str.split( ", " ); // Converts String to a String Array
						//System.out.println(stringArray[0].charAt(0));
						
						Hand hand = new Hand();
						for(String s: stringArray) {
							char color = s.charAt(0);
							int value = Integer.parseInt(s.replaceAll("[\\D]", ""));
							Tile t = new Tile(value, color);
							hand.addTile(t);
						}
						if(i == 0) {
							h.getHand().setHand(hand.getHand());
							players.add(h);
						} else if (i == 1) {
							aiOne.getHand().setHand(hand.getHand());
							players.add(aiOne);
						} else if (i == 2) {
							aiTwo.getHand().setHand(hand.getHand());
							players.add(aiTwo);
						} else {
							aiThree.getHand().setHand(hand.getHand());
							players.add(aiThree);
						}
					}
		} else {
			
		}
		
		for(Player p : players) {
			p.displayHand();
		}
	}
	
	public void init() {
		//Initialize Table
				table = new ArrayList< Meld >();

				//Initialize and Shuffle Deck
				deck = new Deck();
				deck.shuffle();
				
				if(!riggedGame) {
					scanner = new Scanner(System.in);
					System.out.println("How many Players are playing? (2 - 4)\n");
					numPlayers = scanner.nextInt();
				
					for(int i = 1; i < numPlayers + 1; i++) {
						
						System.out.println("Set Player " + i + " as (H)uman or (A)I?\n");
						char playerType = scanner.next().charAt(0);
						if(playerType == 'H'){
							Human human = new Human(deck);
							players.add(human);
						} else if (playerType == 'A'){
							System.out.println("What Strategy would you like this AI to have?\n"
									+ "(1) Strategy One\n"
									+ "(2) Strategy Two\n"
									+ "(3) Strategy Three\n");
							int aiType = scanner.nextInt();
							switch(aiType) {
							case 1:
								AIOne aiOne = new AIOne(deck);
								players.add(aiOne);
								break;
							case 2:
								AITwo aiTwo = new AITwo(deck);
								players.add(aiTwo);
								break;
							case 3:
								AIThree aiThree = new AIThree(deck);
								players.add(aiThree);
								break;
							}
						}
					}
				}
	}
	
	public void runGame() {
		while(true && !wonGame) {
			if(deck.getNumTiles() == 0) {
				System.out.println("DECK OUT OF TILES");
				break;
			}
				
			displayInfo(); //Display Table
			playerTurn();
			
		}
	}
	
	public void playerTurn() {
		for(int i = 0; i < numPlayers; i++) {
			players.get(i).play(table, deck, players);
			if(players.get(i).handSize() == 0) {
				wonGame = true;
				players.get(i).winMessage();
				break;
			}
		}
	}
	
	/*
	 * https://stackoverflow.com/questions/14393423/how-to-make-a-countdown-timer-in-java
	 */
	public void timer() {
	    String secs = "120";
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    interval = 120;
	    System.out.println(secs);
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	            System.out.println(setInterval());

	        }
	    }, delay, period);
		
	}
	
	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	
	public void displayInfo() {
		int i = 1;
		System.out.println("");
		System.out.println("-------TABLE-------");
		for(Meld m: table ) {
			System.out.print(i);
			m.displayMeld();
			i++;
		}
		System.out.println("-------------------");
		
		for(int j = 0; j < numPlayers; j++) {
			players.get(j).displayHand();
		}
		
		/*
		//----------------------------------------------
		System.out.println("-------------------");
		System.out.print("Human Player's Hand: ");
		player.displayHand();
		
		
		
		System.out.print("Player One's Hand: ");
		aiOne.hand.displayHand();
		
		System.out.print("Player Two's Hand: ");
		aiTwo.hand.displayHand();
		
		System.out.print("Player Three's Hand: ");
		aiThree.hand.displayHand();
		*/
	}
}