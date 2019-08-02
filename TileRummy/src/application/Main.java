package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	
		
		 
    @Override
    public void start(Stage primaryStage) {
    	
 	try {
 		FXMLLoader loader = new FXMLLoader(Main.class.getResource("/application/StartPage.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		StartPageController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		dialogStage.setMaximized(true);
        dialogStage.show();
 		
	   	}catch(Exception e){
 		e.printStackTrace();
 	}
		
    }

    

    public static void main(String[] args) {
        launch(args);
    }
    
   
    
    
    
}