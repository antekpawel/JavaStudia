package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primatyStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primatyStage = primaryStage;
		mainWindow();
	}
	
	public void mainWindow() {
		FXMLLoader loader = new FXMLLoader(
				Main.class.getResource("/view/MainWindowView.fxml"));
		try {
			AnchorPane pane = loader.load();
			primatyStage.setMinWidth(400);
			primatyStage.setMinHeight(300);
			Scene scene = new Scene(pane);
			MainWindowController mainWindowController =
					loader.getController();
			mainWindowController.setMain(this);
			primatyStage.setScene(scene);
			primatyStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
