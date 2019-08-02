package core;

import java.util.ArrayList;

import javafx.scene.control.Label;

public interface Player {

	void displayHand();
	public void play(ArrayList<Meld> table, Deck d, ArrayList<Player> plist);
	public int handSize();
	public void winMessage();
	public Hand getHand();
}
