package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import core.AIOne;
import core.AIThree;
import core.AITwo;
import core.Deck;
import core.Hand;
import core.Human;
import core.Meld;
import core.Player;
import core.Tile;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartPageController implements Initializable {

	private Stage dialogStage;

	private boolean okClicked = false;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	ArrayList<Meld> table;
	Deck deck;

	ArrayList<Player> players = new ArrayList<Player>();

	boolean wonGame = false;
	boolean gameWon = false;
	boolean riggedGame = false;

	Stage dialogStagee = new Stage();

	ObservableList<String> noofplayerslist = FXCollections.observableArrayList(
			"2", "3", "4");

	@FXML
	private Label dragleb;

	@FXML
	private GridPane GridMain;

	@FXML
	private GridPane gamemodegrid;
	@FXML
	private GridPane Normalgamegrid;

	@FXML
	private JFXComboBox noofplayerscombo;

	@FXML
	void MinimizeWindow() {
		dialogStage.setIconified(true);
		;
	}

	@FXML
	void exitSystem() {
		dialogStage.close();
		System.exit(0);
	}

	@FXML
	void gotonomalgame() {
		gamemodegrid.setVisible(false);
		Normalgamegrid.setVisible(true);
	}

	@FXML
	void gobacktostart() {
		gamemodegrid.setVisible(true);
		Normalgamegrid.setVisible(false);
	}

	@FXML
	private ButtonBar playerone;

	@FXML
	private ButtonBar playertwo;

	@FXML
	private ButtonBar playerthree;

	@FXML
	private ButtonBar playerfour;

	@FXML
	private JFXRadioButton p1human;
	@FXML
	private JFXRadioButton p1ai1;
	@FXML
	private JFXRadioButton p1ai2;
	@FXML
	private JFXRadioButton p1ai3;

	@FXML
	private JFXRadioButton p2human;
	@FXML
	private JFXRadioButton p2ai1;
	@FXML
	private JFXRadioButton p2ai2;
	@FXML
	private JFXRadioButton p2ai3;

	@FXML
	private JFXRadioButton p3human;
	@FXML
	private JFXRadioButton p3ai1;
	@FXML
	private JFXRadioButton p3ai2;
	@FXML
	private JFXRadioButton p3ai3;

	@FXML
	private JFXRadioButton p4human;
	@FXML
	private JFXRadioButton p4ai1;
	@FXML
	private JFXRadioButton p4ai2;
	@FXML
	private JFXRadioButton p4ai3;

	final ToggleGroup group = new ToggleGroup();
	final ToggleGroup group2 = new ToggleGroup();
	final ToggleGroup group3 = new ToggleGroup();
	final ToggleGroup group4 = new ToggleGroup();

	private double xOffset = 0;
	private double yOffset = 0;

	ObservableList<String> playerslist = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		noofplayerscombo.setItems(noofplayerslist);

		p1human.setUserData("H");
		p1ai1.setUserData("1");
		p1ai2.setUserData("2");
		p1ai3.setUserData("3");

		p2human.setUserData("H");
		p2ai1.setUserData("1");
		p2ai2.setUserData("2");
		p2ai3.setUserData("3");

		p3human.setUserData("H");
		p3ai1.setUserData("1");
		p3ai2.setUserData("2");
		p3ai3.setUserData("3");

		p4human.setUserData("H");
		p4ai1.setUserData("1");
		p4ai2.setUserData("2");
		p4ai3.setUserData("3");

		p1human.setToggleGroup(group);
		p1ai1.setToggleGroup(group);
		p1ai2.setToggleGroup(group);
		p1ai3.setToggleGroup(group);

		p2human.setToggleGroup(group2);
		p2ai1.setToggleGroup(group2);
		p2ai2.setToggleGroup(group2);
		p2ai3.setToggleGroup(group2);

		p3human.setToggleGroup(group3);
		p3ai1.setToggleGroup(group3);
		p3ai2.setToggleGroup(group3);
		p3ai3.setToggleGroup(group3);

		p4human.setToggleGroup(group4);
		p4ai1.setToggleGroup(group4);
		p4ai2.setToggleGroup(group4);
		p4ai3.setToggleGroup(group4);

		dragleb.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		dragleb.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				dialogStage.setX(event.getScreenX() - xOffset);
				dialogStage.setY(event.getScreenY() - yOffset);
			}
		});

		dragleb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {

						if (dialogStage.isMaximized()) {
							dialogStage.setMaximized(false);

						} else {
							dialogStage.setMaximized(true);

						}

					}
				}
			}
		});

		noofplayerscombo.setOnAction(new EventHandler() {
			@Override
			public void handle(Event arg0) {

				if (noofplayerscombo.getValue().equals("2")) {
					playerone.setVisible(true);
					playertwo.setVisible(true);

					playerthree.setVisible(false);
					playerfour.setVisible(false);

				} else if (noofplayerscombo.getValue().equals("3")) {
					playerone.setVisible(true);
					playertwo.setVisible(true);

					playerthree.setVisible(true);
					playerfour.setVisible(false);

				} else if (noofplayerscombo.getValue().equals("4")) {
					playerone.setVisible(true);
					playertwo.setVisible(true);

					playerthree.setVisible(true);
					playerfour.setVisible(true);

				}

			}

		});

	}

	@FXML
	private GridPane gridcardinhard;

	@FXML
	void loadplayers() {

		Alert alertl1 = new Alert(AlertType.WARNING);
		alertl1.initStyle(StageStyle.UTILITY);

		playerslistview.getItems().clear();
		playerslistview.setItems(playerslist);

		int playesNum = Integer
				.parseInt(noofplayerscombo.getValue().toString());

		table = new ArrayList<Meld>();
		// Initialize and Shuffle Deck
		deck = new Deck();
		deck.shuffle();
		vboxcardsplayer.getChildren().clear();
		players.clear();

		if (group.getSelectedToggle().getUserData().toString().equals("H")) {
			Human human = new Human(deck);
			players.add(human);
		} else if (group.getSelectedToggle().getUserData().toString()
				.equals("1")) {
			AIOne AI = new AIOne(deck);
			players.add(AI);
		} else if (group.getSelectedToggle().getUserData().toString()
				.equals("2")) {
			AITwo AI = new AITwo(deck);
			players.add(AI);
		} else if (group.getSelectedToggle().getUserData().toString()
				.equals("3")) {
			AIThree AI = new AIThree(deck);
			players.add(AI);
		}

		if (playesNum == 2
				&& (group.getSelectedToggle() == null || group2
						.getSelectedToggle() == null)) {
			alertl1.setContentText("Select 2 players");
			alertl1.show();
			return;
		} else if (playesNum == 2) {
			if (group2.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}
		}
		if (playesNum == 3
				&& (group.getSelectedToggle() == null
						|| group2.getSelectedToggle() == null || group3
						.getSelectedToggle() == null)) {
			alertl1.setContentText("Select 3 players");
			alertl1.show();
			return;
		} else if (playesNum == 3) {
			if (group2.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}

			if (group3.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}

		}
		if (playesNum == 4
				&& (group.getSelectedToggle() == null
						|| group2.getSelectedToggle() == null
						|| group3.getSelectedToggle() == null || group4
						.getSelectedToggle() == null)) {
			alertl1.setContentText("Select 4 players");
			alertl1.show();
			return;
		} else if (playesNum == 4) {
			if (group2.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group2.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}

			if (group3.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group3.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}

			if (group4.getSelectedToggle().getUserData().toString().equals("H")) {
				Human human = new Human(deck);
				players.add(human);
			} else if (group4.getSelectedToggle().getUserData().toString()
					.equals("1")) {
				AIOne AI = new AIOne(deck);
				players.add(AI);
			} else if (group4.getSelectedToggle().getUserData().toString()
					.equals("2")) {
				AITwo AI = new AITwo(deck);
				players.add(AI);
			} else if (group4.getSelectedToggle().getUserData().toString()
					.equals("3")) {
				AIThree AI = new AIThree(deck);
				players.add(AI);
			}
		}
		playerslistview.setItems(playerslist);
		playerslistview.getSelectionModel().select(0);
		
		
		
		
		
		
		
		thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {

                    	
                    	
                    	incrementCount();
						

                    
                    
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                    
                    
                    
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

		
		
		
		
			
	}

	Thread thread;
	@FXML
	private VBox vboxcardsplayer;
	@FXML
	
	private VBox vboxcardsMeld;

	@FXML
	private ListView playerslistview;

	@FXML
	private Label playerhand;
	
    private int count = 0;

	 private void incrementCount() {
		 Alert alertl1= new Alert(AlertType.WARNING);
		 if(deck.getNumTiles() == 0) {
			 System.err.println("DECK OF TILES");
				thread.stop();
			 alertl1.setContentText("DECK OUT OF TILES");
				alertl1.show();
				return;
			}
			playerslistview.getItems().clear();
			playerslistview.setItems(playerslist);
		 
			vboxcardsplayer.getChildren().clear();
			
			if(!vboxcardsMeld.getChildren().isEmpty()){
			vboxcardsMeld.getChildren().clear();
			}
		
		 if(wonGame){
			 System.err.println("DECK OF TILES");
				thread.stop();
			 alertl1.setContentText("DECK OUT OF TILES");
				alertl1.show();
			return;
		 }
		 displayInfo();
			//playerTurn();
		 
		 for(int i = 0; i < players.size(); i++) {
				hand = players.get(i).getHand().getHand();
				playerhand.setText(" Player "+(i)+" {");
				for (Tile t : hand) {
					playerhand.setText(playerhand.getText() + "  " + t.getColor()
							+ t.getRank());
				}
				playerhand.setText(playerhand.getText() + "  }");
				System.out.println("Player "+(i));
				players.get(i).play(table, deck, players);
				if(players.get(i).handSize() == 0) {
					wonGame = true;
					System.out.println("Player "+i+" won the game");
					players.get(i).winMessage();
					break;
				}
			}
		 
	        
	    }
	

	ArrayList<Tile> hand;
	
	public void displayInfo() {
		playerslistview.getItems().clear();
		playerslistview.setItems(playerslist);

		HBox Player = null;
		Label l = null;

		String tile = "";
		ImageView imageView;
		int i = 0;
		System.out.println("-------TABLE-------");
		for(Meld m: table ) {
			//hand = players.get(j - 1).getHand().getHand();
			Player = new HBox();
			l = new Label("Meld " + (i) + "    ");
			l.setMinSize(30, 60);
			l.setAlignment(Pos.CENTER);
			l.setFont(Font.font("TAHOMA", 14));
			Player.getChildren().add(l);

			for (Tile t : m.getMeld()) {

				tile = t.getColor() + "" + t.getRank();
				imageView = new ImageView(new Image("/images/Cropped/" + tile
						+ ".png"));
				imageView.setFitHeight(60);
				imageView.setFitWidth(30);
				imageView.setUserData(t.getColor() + "-" + t.getRank());
				Player.getChildren().add(imageView);

			}
			vboxcardsMeld.getChildren().add(Player);
			i++;
		}
		
		
		for (int j = 1; j <= players.size(); j++) {
			playerslist.add("Player " + (j));
			players.get(j - 1).displayHand();

			hand = players.get(j - 1).getHand().getHand();
			Player = new HBox();
			l = new Label("Player " + (j) + "    ");
			l.setMinSize(30, 60);
			l.setAlignment(Pos.CENTER);
			l.setFont(Font.font("TAHOMA", 14));
			Player.getChildren().add(l);

			for (Tile t : hand) {

				tile = t.getColor() + "" + t.getRank();
				imageView = new ImageView(new Image("/images/Cropped/" + tile
						+ ".png"));
				imageView.setFitHeight(60);
				imageView.setFitWidth(30);
				imageView.setUserData(t.getColor() + "-" + t.getRank());
				Player.getChildren().add(imageView);

			}
			vboxcardsplayer.getChildren().add(Player);
		}
		
		
		
	}
	
	public void playerTurn() {
		for(int i = 0; i < players.size(); i++) {
			hand = players.get(i).getHand().getHand();
			playerhand.setText(" Player "+(i)+" {");
			for (Tile t : hand) {
				playerhand.setText(playerhand.getText() + "  " + t.getColor()
						+ t.getRank());
			}
			playerhand.setText(playerhand.getText() + "  }");
			System.out.println("Player "+(i));
			players.get(i).play(table, deck, players);
			if(players.get(i).handSize() == 0) {
				wonGame = true;
				players.get(i).winMessage();
				break;
			}
		}
	}

}
