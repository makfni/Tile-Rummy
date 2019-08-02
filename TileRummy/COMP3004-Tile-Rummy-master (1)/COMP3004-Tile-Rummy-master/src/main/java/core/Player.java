package core;

import java.util.ArrayList;

public interface Player {

	void displayHand();
	public void play(ArrayList<Meld> table, Deck d, ArrayList<Player> plist);
	public int handSize();
}
