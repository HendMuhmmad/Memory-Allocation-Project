package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainScene.mainSceneController;
import memoryDs.operate;
import noPlaceError.errorController;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private  Stage primaryStage;
	private static  Stage error_window;
	private static  BorderPane mainLayout;

	
	@Override
	public void start(Stage primaryStage)
	{
		this.setPrimaryStage(primaryStage);
		mainLayout=new BorderPane();
	    Scene scene = new Scene(mainLayout,923,637);
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
	public static void showMainScene(operate oper)
	{
		try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("/mainScene/mainScene.fxml"));	
    		BorderPane mainPane = loader.load();
    		mainSceneController c = loader.getController();
    		c.setOper(oper);
			mainLayout.setCenter(mainPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public static void showAlertStage(String message)
	{
    	try {
			FXMLLoader loader= new FXMLLoader(Main.class.getResource("/noPlaceError/error.fxml"));	
    		VBox errorVbox = (VBox)loader.load();
    		errorController c = loader.getController();
    		c.setWarning(message);
		    error_window = new Stage();
		    error_window.initModality(Modality.APPLICATION_MODAL);
		    //error_window.setTitle("");
	        Scene scene = new Scene(errorVbox);
	        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
	        error_window.setScene(scene);
	        error_window.show();            
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
    public static void closeErrorBox()
    {
    	error_window.close();
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
