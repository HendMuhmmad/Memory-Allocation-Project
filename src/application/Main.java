package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private  Stage primaryStage;
	private static  BorderPane mainLayout;

	
	@Override
	public void start(Stage primaryStage)
	{
		this.setPrimaryStage(primaryStage);
		mainLayout=new BorderPane();
	    Scene scene = new Scene(mainLayout,836,564);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());		  
		primaryStage.setScene(scene);
		primaryStage.show();
		showStartingScene();
	}
	
	public static void showStartingScene() 
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/startingScene/startingScene.fxml"));
		    HBox startingScene  = loader.load();
		    mainLayout.setCenter(startingScene);
		  		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void showMainScene()
	{
		try {
			mainLayout.setCenter( FXMLLoader.load(Main.class.getResource("/mainScene/mainScene.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
