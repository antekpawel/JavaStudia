package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = new HBox();
			Scene scene = new Scene(root,350,200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Label zrodlo = new Label("Zród³o:");
			Label wynikOperacjiTekst = new Label("Wynik operacji:");
			
			TextField wynikOperacji = new TextField("");
			wynikOperacji.setEditable(false);
			wynikOperacji.getStyleClass().add("my-field");
			
			TextField input = new TextField();
			input.getStyleClass().add("my-field");
					
			VBox left_vbox = new VBox(10);
			
			Label operacja = new Label("Operacja:");
			
			RadioButton r1 = new RadioButton("kodowanie");
			RadioButton r2 = new RadioButton("dekodowanie");
			
			VBox kopiuj_vbox = new VBox(10);
			Button kopiuj = new Button("Kopiuj");
			kopiuj_vbox.getStyleClass().add("kopiuj");
			kopiuj_vbox.getChildren().add(kopiuj);
			
			kopiuj.setOnAction(
					event -> {
							if (!wynikOperacji.getText().isEmpty()) input.setText(wynikOperacji.getText());
						}
					
					);
			
			Button wykonaj = new Button("Wykonaj");
			wykonaj.setOnAction(
					event -> {
							if (!input.getText().isEmpty()) {
								
								if (r1.isSelected()){
									wynikOperacji.setText(encode(input.getText()));
								}
								
								if (r2.isSelected()) {
									wynikOperacji.setText(decode(input.getText()));
								}
							}
						}
					
					);
			
			
			left_vbox.getChildren().addAll(zrodlo, input, kopiuj_vbox, 
					wynikOperacjiTekst, wynikOperacji, wykonaj);
			root.getChildren().add(left_vbox);

			ToggleGroup  tgroup = new ToggleGroup();
			r1.setToggleGroup(tgroup);
			r2.setToggleGroup(tgroup);
			r1.setSelected(true);
			
			VBox right_vbox = new VBox(10);
			
			right_vbox.getChildren().addAll(operacja, r1, r2);
			root.getChildren().add(right_vbox);

			primaryStage.setTitle("Zadanie 1");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public String encode(String text) {
		int licznik = 0;
		char kontener = text.charAt(0);
		String wynik = "";
		
		for (char ch: text.toCharArray()) {
			if (ch == kontener) {
				licznik++;
			}
			else {
				
				wynik += (kontener + String.valueOf(licznik) + ",");
				
				kontener = ch;
				licznik = 1;
			}
		}
		wynik += (kontener + String.valueOf(licznik));
		return wynik;
	}
	
	public String decode(String text) {
		
		String wynik = "";
		char pisanie;
		String kontener = "";
		int ilosc = 0;
		
		while (!text.isEmpty()) {
			
			kontener += text.charAt(0);
			
			if (text.charAt(0) == ','){
				kontener = kontener.replace(",", "");
				pisanie = kontener.charAt(0);
				kontener = kontener.substring(1);
				
				for (int i = 0; i < Integer.parseInt(kontener); i++) {
					wynik += (pisanie);
				}
			
				kontener = "";
			}
			
			text = text.substring(1);
		}
		kontener = kontener.replace(",", "");
		pisanie = kontener.charAt(0);
		kontener = kontener.substring(1);
		
		for (int i = 0; i < Integer.parseInt(kontener); i++) {
			wynik += (pisanie);
		}
		
		return wynik;
	}
}
