package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Person;

public class MainWindowController {
	private Main main;
	private Stage primaryStage;
	private ObservableList<Person> personList = 
			FXCollections.observableArrayList();
	
	@FXML private TableView<Person> tableView;
	@FXML private TableColumn<Person, String> firstNameColumn;
	@FXML private TableColumn<Person, String> lastNameColumn;
	@FXML private TableColumn<Person, String> idColumn;
	
	private void setList() {
		personList.add(new Person("10", "Jan", "Kowalski"));
		personList.add(new Person("12", "Dzban", "Nowak"));
		personList.add(new Person("13", "Krzysztof", "Boryna"));
	}
	
	public void setMain(Main main) {
		this.main = main;
		setList();
		tableView.setItems(personList);
		
	}
	
	public void initialize() {
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("firstName")
				);
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("lastName")
				);
		idColumn.setCellValueFactory(
				new PropertyValueFactory<Person, String>("ID")
				);
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(ov, old_value, new_value) ->{
					System.out.println(new_value.getFirstName() + " " + new_value.getLastName());
				}
				);
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void closeStage() {
		primaryStage.close();
	
	}
}
