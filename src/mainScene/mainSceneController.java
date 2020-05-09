package mainScene;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import memoryDs.operate;
import memoryDs.segment;
import memoryDs.segments;
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
   // private boolean full = false;
    private operate oper;
   
    private ArrayList<segments> segmentList = new  ArrayList<segments>();
    
    public void setOper(operate p)
    {
    	this.oper = p; 
    }

    public operate getOper() {
		return oper;
	}

	@FXML
    void nextSegment(ActionEvent event) {
    	
    	segments seg = new segments(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
    	segmentList.add(seg);
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
    	try {
		
    	segments seg = new segments(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
    	segmentList.add(seg);
    	if(oper.isType())
    	{
    		oper.FirstFit(processName.getText(), segmentList);
    		
    	}
    	else
    	{
    		oper.BestFit(processName.getText(), segmentList);
    	}
  	
    		FXMLLoader tab_loader = new FXMLLoader(getClass().getResource("/segmentTab/segementTab.fxml"));
    		Tab new_tab;
    		new_tab = tab_loader.load();
    		segmentTableController controller = tab_loader.getController(); 		     
    	    controller.setSegments(oper.getObservableList());
    		controller.updateTable();
    		new_tab.setText(processName.getText());
    		tabPane.getTabs().add(new_tab);
    		tabPane.getSelectionModel().select(new_tab);
    	  	segmentName.clear();
        	segmentSize.clear();
        	processName.clear();
        	numOfSegments.clear();
        	segmentList.clear();
        	oper.clearSegments();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		

    	//alocate
		    	
	    /*	if(full)
	    	{

	    	}
	    	else {


	    	}*/
		    
		} 
    public void deleteTab(String tabName)
    {
    	tabPane.getTabs().removeIf(n -> n.getText()==tabName);
    }

    	
    
    
    
    @FXML
    void goBack(ActionEvent event) {
    	Main.showStartingScene();

    }
    
  


}
