package com.chrisreading.address;

import java.io.IOException;

import com.chrisreading.address.model.Person;
import com.chrisreading.address.view.PersonOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/**
	 * List of persons in the application
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		
		initRootLayout();
		showPersonOverview();
		
		// sample data
		personData.add(new Person("Chris", "Reading"));
		personData.add(new Person("James", "Buchanan"));
		personData.add(new Person("Walter", "White"));
		personData.add(new Person("Haedyn", "Kennedy"));
		personData.add(new Person("Barrack", "Obama"));
	}
	
	/**
	 * Returns the data as an observable list of Persons.
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}
	
	/**
	 * Initializes the root layout
	 */
	public void initRootLayout() {
		try {
			// load rootlayout from it's file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// show the scene containing rootlayout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shows person overview inside rootlayout
	 */
	public void showPersonOverview() {
		try {
			// load person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			// set person overview to center of root layout
			rootLayout.setCenter(personOverview);
			
			// give the controller access to the main app
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
