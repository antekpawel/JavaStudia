package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Label  lblCharacter = new Label("Posta�");
			lblCharacter.setMinWidth(200);
			lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
			
			TextField txtCharacter = new TextField();
			txtCharacter.setMinWidth(200);
			txtCharacter.setPromptText("Wprowadz posta�");
			
			Label  lblActor = new Label("Aktor");
			lblActor.setMinWidth(200);
			lblActor.setAlignment(Pos.BOTTOM_RIGHT);
			
			TextField txtActor = new TextField();
			txtActor.setMinWidth(200);
			txtActor.setPromptText("Wprowadz aktora");
			
			Label lblRole1 = new Label("Posta�");
			Label lblRole2 = new Label();
			Label lblRole3 = new Label("Grana przez");
			Label lblRole4 = new Label();
			
			HBox hboxChatracter = new HBox(20, lblCharacter, txtCharacter);
			hboxChatracter.setPadding(new Insets(10));
			
			HBox hboxActor = new HBox(20, lblActor, txtActor);
			hboxActor.setPadding(new Insets(10));
			
			HBox hboxRole = new HBox(20, lblRole1, lblRole2, lblRole3, lblRole4);
			hboxRole.setPadding(new Insets(10));
			
			VBox root = new VBox(
					10, hboxChatracter, hboxActor, hboxRole);
			
			lblRole2.textProperty().bind(
					txtCharacter.textProperty());
			
			lblRole4.textProperty().bind(
					txtActor.textProperty());
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
