package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,512,256);
			
			Canvas canvas = new Canvas(512, 256);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);
			
			Image image = new Image(getClass().getResourceAsStream("lenna256px.png"));
			int height = (int)image.getHeight();
			int width = (int)image.getWidth();
			gc.drawImage(image, 0, 0);
			
			PixelReader reader = image.getPixelReader();
			WritableImage dstImage = new WritableImage(width, height);
			PixelWriter writer = dstImage.getPixelWriter();
			
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Color color = reader.getColor(i, j);
					writer.setColor(i, j, Color.color(
							color.getRed(), 
							color.getGreen(), 
							0//color.getBlue()
							));
				}
			}
			gc.drawImage(dstImage, 256, 0);
			gc.setStroke(Color.BLUE);
			gc.setLineWidth(3);
			gc.strokeLine(6, 6, 6, 250);
			gc.strokeLine(6, 6, 500, 6);
			gc.strokeLine(0, 250, 500, 250);
			gc.strokeLine(500, 0, 500, 250);
			
			canvas.setOnMouseClicked(event ->{
				System.out.println("x = " + event.getX() +
						", y = " + event.getY());
			});
			
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
