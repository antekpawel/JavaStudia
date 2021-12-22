package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Person;

public class MainWindowController {
	private Main main;
	private Person person = new Person(0, "", "");
	
	@FXML private Button button;
	@FXML private TextField field;
	@FXML private Label label;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void handleButton() {
		System.out.println("Wciœniêto przycisk.");
		person.setFirstName(field.getText());
		label.setText(person.getFirstName());
		field.clear();
	}
}
