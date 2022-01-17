package application;
	
import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	Image image = new Image(getClass().getResourceAsStream("wisnie.jpg"));
	int height = (int)image.getHeight();
	int width = (int)image.getWidth();
	
	Canvas canvas = new Canvas(width + 6, height + 6);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	GraphicsContext gcTable;
	int iterTable = 0;
	
	ScrollPane sp = new ScrollPane(canvas);
	
	PixelReader reader = image.getPixelReader();
	WritableImage dstImage = new WritableImage(41, 41);
	PixelWriter writer = dstImage.getPixelWriter();
	
	ArrayList<Canvas> canvasTable = new ArrayList<Canvas>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// Main
			Group root = new Group();
			Scene scene = new Scene(root);
			GridPane mainHbox = new GridPane();
			mainHbox.getStyleClass().add("main");
			mainHbox.setPadding(new Insets(20, 20, 20, 20));
			mainHbox.setVgap(10);
			mainHbox.setHgap(10);
			
			// Right
			VBox vbox = new VBox(10);
			vbox.getStyleClass().add("vboxStyle");
			
			Button load = new Button("Wczytaj");
			Label toBigImage = new Label("");
			toBigImage.getStyleClass().add("labelStyle");
			Button clear = new Button("Czyœæ");
			Label label = new Label("Wycinki w kolejnoœci malej¹cej\nœredniej wartoœci sk³adowej czerwonej");
			label.getStyleClass().add("labelStyle");
			HBox buttonHbox = new HBox(10);
			buttonHbox.getStyleClass().add("hboxButtonStyle");
			
			buttonHbox.getChildren().add(load);
			buttonHbox.getChildren().add(clear);
			
			//Canvas table
			GridPane gridPane = new GridPane();
			gridPane.getStyleClass().add("table");
			gridPane.setPadding(new Insets(10, 10, 10, 10));
			gridPane.setGridLinesVisible(true);
			gridPane.setVgap(10);
			gridPane.setHgap(10);
			
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					canvasTable.add(new Canvas(41, 41));
					gridPane.add(canvasTable.get(canvasTable.size()-1), j, i);
				}
			}
			
			vbox.getChildren().addAll(toBigImage, buttonHbox, label, gridPane);

			// Left
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, width + 6, height + 6);
			
			gc.drawImage(image, 3, 3);
            
			mainHbox.add(canvas, 0, 0);
			mainHbox.add(vbox, 1, 0);
            
			root.getChildren().add(mainHbox);
			clearA();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Wybierz zdjêcie do analizy");
			
			File recordsDir = new File(System.getProperty("user.dir"), "src/application");
			if (! recordsDir.exists()) {
			    recordsDir.mkdirs();
			}
			fileChooser.setInitialDirectory(recordsDir);
			  
			load.setOnAction(
					event -> {
						File file = fileChooser.showOpenDialog(primaryStage);
						clearA();
						
						
			            if (file != null) {
			            	mainHbox.getChildren().remove(canvas);
			            	mainHbox.getChildren().remove(sp);
			            	
			            	image = new Image(file.toURI().toString());
			            	height = (int)image.getHeight();
			            	width = (int)image.getWidth();
			            	canvas = new Canvas(width + 6, height + 6);
			            	gc = canvas.getGraphicsContext2D();
			            	
			            	reader = image.getPixelReader();
			            	dstImage = new WritableImage(41, 41);
			            	writer = dstImage.getPixelWriter();
			            	
			            	gc.setFill(Color.BLACK);
			    			gc.fillRect(0, 0, width + 6, height + 6);
			    			
			    			gc.drawImage(image, 3, 3);
			    			
			            	if (height > 600 && width > 600) {
			            		sp = new ScrollPane(canvas);
			            		sp.maxHeight(600);
			            		sp.maxWidth(600);
			            		sp.setMaxSize(600, 600);
			        			gridPane.getStyleClass().add("scroll");
			            		mainHbox.add(sp, 0, 0);
			            		toBigImage.setText("Uwaga du¿e zdjêcie,\nu¿ywaj scrolla");
			            	}else {
			            		mainHbox.add(canvas, 0, 0);
			            		toBigImage.setText("");
			            	}
			            	
			            	canvas.setOnMouseClicked(event2 ->{
			            		copy(event2);
			            		
			    			});
			            	primaryStage.sizeToScene();
			            }
			            
						}
					
					);
			
			canvas.setOnMouseClicked(event ->{
				copy(event);
			});
			
			clear.setOnAction(event -> {
						clearA();	
						}
					);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Zadanie domowe 2");
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearA() {
		iterTable = 0;
		
		for (int i = 0; i < 25; i++) {
			gcTable = canvasTable.get(i).getGraphicsContext2D();
			gcTable.setFill(Color.WHITE);
			gcTable.fillRect(0, 0, 41, 41);
		}
	}
	
	public void copy(MouseEvent event) {	
		
			int h = (int)event.getY();
			int w = (int)event.getX();
			
			int frameSize = 22;
			boolean cursorInImage = (h > frameSize) && (h < height - frameSize) &&
					(w > frameSize) && (w < width - frameSize);
			
			if (cursorInImage){
				for (int i = 0; i < 41; i++) {
					for (int j = 0; j < 41; j++) {
						
						Color color = reader.getColor(i - 20 + w, j - 20 + h);
						
						writer.setColor(i, j, Color.color(
								color.getRed(), 
								color.getGreen(), 
								color.getBlue()
								));
					}
				}
				
				gcTable = canvasTable.get(iterTable).getGraphicsContext2D();
				gcTable.drawImage(dstImage, 0, 0);
				
				if (iterTable < 24) iterTable++;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
