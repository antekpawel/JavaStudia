package application;
	
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Group root = new Group();
			Scene scene = new Scene(root,400,400, Color.GRAY);
			
			//Czerwona linia 
			Line redLine = new Line(10, 10, 200, 10);
			redLine.setStroke(Color.RED);
			redLine.setStrokeWidth(10);
			redLine.setStrokeLineCap(StrokeLineCap.ROUND);
			root.getChildren().add(redLine);
			
			//Niebieska linia
			Line blueLine = new Line(10, 40, 200, 40);
			blueLine.setStroke(Color.BLUE);
			blueLine.setStrokeWidth(10);
			blueLine.setStrokeLineCap(StrokeLineCap.BUTT);
			blueLine.setStrokeDashOffset(45);
			blueLine.getStrokeDashArray().addAll(30d, 15d, 5d, 30d);
			
			root.getChildren().add(blueLine);

			//Suwak powiazany ze StrokeDashOffset
			Slider slider = new Slider(0, 80, 45);
			slider.setLayoutX(10);
			slider.setLayoutY(70);
			slider.setOrientation(Orientation.HORIZONTAL);
			blueLine.strokeDashOffsetProperty().bind(slider.valueProperty());
			
			root.getChildren().add(slider);

			//CubicCurve
			CubicCurve cubicCurve = new CubicCurve(
					50, 75, 	//punkt startowy
					80, -25, 	//punkt kontrolny
					110, 175, 	//punkt kontrolny 2
					140, 75 	//punkt koncowy
					); 
			cubicCurve.setStrokeWidth(3);
			cubicCurve.setTranslateX(220);
			cubicCurve.setTranslateY(-40);
			cubicCurve.setFill(Color.TRANSPARENT);
			cubicCurve.setStroke(Color.ANTIQUEWHITE);
			root.getChildren().add(cubicCurve);
			
			//Rysowanie po sciezce
			Path path = new Path();
			path.setStrokeWidth(5);
			MoveTo moveTo = new MoveTo(50, 150);
			QuadCurveTo quadCurveTo = new QuadCurveTo();
			quadCurveTo.setX(150);
			quadCurveTo.setY(150);
			quadCurveTo.setControlX(100);
			quadCurveTo.setControlY(50);
			LineTo lineTo1 = new LineTo(50, 150);
			LineTo lineTo2 = new LineTo(100, 275);
			LineTo lineTo3 = new LineTo(150, 150);
			path.getElements().addAll(moveTo, quadCurveTo, lineTo1, lineTo2, lineTo3);
			
			root.getChildren().add(path);
			
			//Donut z dwoch elips
			Ellipse bigEllipse = new Ellipse(
					100, 100, //œrodek
					50, //promien x
					36 //promien y
					);
			Ellipse smallEllipse = new Ellipse(
					100, 100, //œrodek
					17, //promien x
					12 //promien y
					);
			Shape donut = Path.subtract(bigEllipse, smallEllipse);
			donut.setTranslateX(200);
			donut.setFill(Color.rgb(255, 200, 0));
			donut.setStroke(Color.BLACK);
			donut.setStrokeWidth(2);
			//efekt cienia
			DropShadow dropShadow = new DropShadow(
					5, 
					12.0, 12.0,
					Color.rgb(200, 200, 200)
					);
			donut.setEffect(dropShadow);
			
			root.getChildren().add(donut);
			
			//Wypelnienie gradientowe linii
			Rectangle roundRect = new Rectangle(
					250, 250, 	//polozenie
					100, 70 	//wysokosc, szerokosc
					);
			roundRect.setArcHeight(20);
			roundRect.setArcWidth(20);
			root.getChildren().add(roundRect);
			
			LinearGradient lgradient = new LinearGradient(
					0, 0, 	//poczatek
					0, 1, 	//koniec
					true,			//czy proporcjonalne
					CycleMethod.REPEAT, 	//powtarzanie kolorow
					new Stop(0, Color.RED),	//kolor poczatkowy
					new Stop(0.5, Color.YELLOW),
					new Stop(1, Color.BLUE)	//kolor koncowy
					);
			roundRect.setFill(lgradient);
			
			//Gradient radialny
			Ellipse ellipse1 = new Ellipse(
					200, 300, //œrodek
					70, //promien x
					70 //promien y
					);
			RadialGradient rgradient = new RadialGradient(
					0, 0,
					0.5, 0.5, 	//centrum
					0.75,		//promien
					true,
					CycleMethod.NO_CYCLE,
					new Stop(0, Color.YELLOW),
					new Stop(1, Color.BLACK)
					);
			ellipse1.setFill(rgradient);
			
			root.getChildren().add(ellipse1);
			
			
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
