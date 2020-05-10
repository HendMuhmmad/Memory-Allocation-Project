package mainScene;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import memoryDs.holes;
import memoryDs.operate;
import memoryDs.segment;
import memoryLayout.memdrawing;
import segmentTab.segmentTableController;


public class mainSceneController {
	
	@FXML
	private AnchorPane memoryAnchorPane;

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
   
    private ArrayList<segment> segmentList = new  ArrayList<segment>();
    
    
    private memdrawing draw_memory ;
   
    public TabPane getTabPane() {
		return tabPane;
	}
    public memdrawing getDraw_memory() {
		return draw_memory;
	}
    
    public void setDraw_memory(memdrawing draw_memory) {
		this.draw_memory = draw_memory;
	}
    
    public void setOper(operate p)
    {
    	this.oper = p; 
     draw_memory= new memdrawing(memoryAnchorPane, oper.getMemorySize(),oper, this);
   
     Platform.runLater(() -> { draw_memory.draw_holes(oper.getHolesList());});
  

   
    }

    public operate getOper() {
		return oper;
	}

	@FXML
    void nextSegment(ActionEvent event) {
    	
    	segment seg = new segment(segmentName.getText(),Float.parseFloat(segmentSize.getText()));
    	segmentList.add(seg);
    	segmentName.clear();
    	segmentSize.clear();
    }

    @FXML
    void showSegmentsEntry(ActionEvent event) {
    	segmentName.setDisable(false);
		segmentSize.setDisable(false);
		segmentLabel.setDisable(false);

    }
    @FXML
    void enterProcess(ActionEvent event) {
    	boolean checksegments;
    	try {
		
    	segment seg = new segment(segmentName.getText(),Float.parseFloat(segmentSize.getText()));
    	segmentList.add(seg);
    	if(oper.isType())
    	{
    		
    		checksegments=oper.FirstFit (processName.getText(), segmentList);
    		 
    		
            if(checksegments==false)
              {
               Main.showAlertStage(" For "+processName.getText());
                segmentList.clear();  
              }
              else 
              {
                  oper.AllocateFirstFit(processName.getText() ,segmentList);
                  draw_memory.draw_process(processName.getText(), segmentList);
              }

    	}
    	else
    	{
    		 checksegments =oper.BestFit(processName.getText(), segmentList);
    		 if(checksegments==false)
    		   {
    			 Main.showAlertStage("For"+processName.getText());
                 segmentList.clear();  
               }
               else {             	 
                   oper.AllocateBestFit(processName.getText() ,segmentList);
                   draw_memory.draw_process(processName.getText(), segmentList);
                    }
    	
    	}
  	
    		if(checksegments) 
    		{FXMLLoader tab_loader = new FXMLLoader(getClass().getResource("/segmentTab/segementTab.fxml"));
    		Tab new_tab;
    		new_tab = tab_loader.load();
    		segmentTableController controller = tab_loader.getController(); 		     
    		controller.setSegments(FXCollections.observableArrayList(segmentList));
    		controller.updateTable();
    		new_tab.setText(processName.getText());
    		tabPane.getTabs().add(new_tab);
    		tabPane.getSelectionModel().select(new_tab);
    		}
    		segmentName.clear();
        	segmentSize.clear();
        	processName.clear();
        	numOfSegments.clear();
        	segmentList.clear();
     
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	    	
	    	segmentName.setDisable(true);
			segmentSize.setDisable(true);
			segmentLabel.setDisable(true);
		    
		} 
    public void deleteTab(String tabName)
    {
    	List<Tab> temp=tabPane.getTabs();
    	for(Tab t : temp) 
    		{
    			if(t.getText().equals(tabName)) 
    			{
    				tabPane.getTabs().remove(t);
    				break;
    			}
    		}
    }

    @FXML
    void goBack(ActionEvent event) {
    	Main.showStartingScene();

    }
}
