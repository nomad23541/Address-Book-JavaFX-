package com.chrisreading.address.view;

import com.chrisreading.address.MainApp;
import com.chrisreading.address.model.Person;
import com.chrisreading.address.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

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
		
		// clear person details
		showPersonDetails(null);
		
		// listen for selection changes and show the person's details when changed
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if(okClicked) 
			mainApp.getPersonData().add(tempPerson);
	}
	
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if(selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if(okClicked) 
				showPersonDetails(selectedPerson);
		} else {
			showNoSelectionAlert();
		}
	}
	
	/**
	 * Called when the user hits the delete button
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex >= 0)
			personTable.getItems().remove(selectedIndex);
		else {
			showNoSelectionAlert();
		}
	}
	
	/**
	 * Alert dialog to show if there is no selection
	 */
	private void showNoSelectionAlert() {
		// if nothing selected
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(mainApp.getPrimaryStage());
		alert.setTitle("No Selection");
		alert.setHeaderText("No Person Selected");
		alert.setContentText("Please select a person in the table.");
		alert.showAndWait();
	}
	
	private void showPersonDetails(Person person) {
		if(person != null) {
			// fill labels with info from person obj
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// this person is null, remove all text
			firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        streetLabel.setText("");
	        postalCodeLabel.setText("");
	        cityLabel.setText("");
	        birthdayLabel.setText("");
		}
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// add the person list to the table
		personTable.setItems(mainApp.getPersonData());
	}

}
