package mainScene;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import memoryDs.segment;
import segmentTab.segmentTableController;


public class mainSceneController {

    @FXML
    private JFXTextField processName;
    @FXML
    private TabPane tabPane;

    @FXML
    private JFXTextField segmentSize;

    @FXML
    private JFXTextField numOfSegments;

    @FXML
    private JFXButton enterProcess;

    @FXML
    private JFXTextField segmentName;
    
    @FXML
    private Label segmentLabel;
    private static Stage error_window;
    private boolean full = false;
    private List<segment> segments = new ArrayList<segment>();

    @FXML
    void nextSegment(ActionEvent event) {
    	segment s = new segment(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
    	segments.add(s);
    	segmentName.clear();
    	segmentSize.clear();
    }

    @FXML
    void showSegmentsEntry(ActionEvent event) {
    	segmentName.setVisible(true);
		segmentSize.setVisible(true);
		segmentLabel.setVisible(true);

    }
    @FXML
    void enterProcess(ActionEvent event) {
		segment s = new segment(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
    	segments.add(s);
    	//alocate
		    	
	    	if(full)
	    	{
	        	try {
	    			FXMLLoader loader= new FXMLLoader(getClass().getResource("/noPlaceError/error.fxml"));	
	        		VBox errorVbox = (VBox)loader.load();
	    		    error_window = new Stage();
	    		    error_window.initModality(Modality.APPLICATION_MODAL);
	    		    //error_window.setTitle("");
	    	        Scene scene = new Scene(errorVbox);
	    	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    	        error_window.setScene(scene);
	    	        error_window.show();            
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	else {
	    		
			try {
				FXMLLoader tab_loader = new FXMLLoader(getClass().getResource("/segmentTab/segementTab.fxml"));
				Tab new_tab;
				new_tab = tab_loader.load();
				segmentTableController controller = tab_loader.getController(); 		     
			    controller.setSegmentList(segments);
				controller.updateTable();
				new_tab.setText(processName.getText());
				tabPane.getTabs().add(new_tab);
				tabPane.getSelectionModel().select(new_tab);
			  	segmentName.clear();
		    	segmentSize.clear();
		    	processName.clear();
		    	numOfSegments.clear();
		    	segments.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    	}
		    
		} 

    	
    
    
    @FXML
    void goBack(ActionEvent event) {
    	Main.showStartingScene();

    }
    
    public static void closeErrorBox()
    {
    	error_window.close();
    }

}
