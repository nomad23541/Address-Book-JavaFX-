package com.chrisreading.address.view;

import com.chrisreading.address.MainApp;
import com.chrisreading.address.model.Person;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;
	
	// refrence to the main application
	private MainApp mainApp;
	
	/**
	 * Constructor
	 * is called before initialize() method
	 */
	public PersonOverviewController() {}
	
	/**
	 * Initializes the controller class, this method is
	 * called immediately after fxml file is loaded.
	 */
	@FXML
	private void initialize() {
		// initialize the person table with two columns
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// add the person list to the table
		personTable.setItems(mainApp.getPersonData());
	}

}
