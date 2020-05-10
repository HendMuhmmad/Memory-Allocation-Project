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
    	
    	segment seg = new segment(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
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
    	try {
		
    	segment seg = new segment(segmentName.getText(),Integer.parseInt(segmentSize.getText()));
    	segmentList.add(seg);
    	if(oper.isType())
    	{
    		System.out.println("segment_list from algorithm before first fit");
    		System.out.println(segmentList);
    		 boolean checksegments=oper.FirstFit (processName.getText(), segmentList);
    		 System.out.println("segment_list from algorithm after first fit");
     		System.out.println(segmentList);
    		
            if(checksegments==false){
                System.out.println("there's no enough space2");
                segmentList.clear();  
              }
              else {
            	  System.out.println("segment_list from algorithm before allocate");
          		System.out.println(segmentList); 
                  oper.AllocateFirstFit(processName.getText() ,segmentList);
                  System.out.println("segment_list from algorithm after allocate");
          		System.out.println(segmentList);
                  draw_memory.draw_process(processName.getText(), segmentList);
                  //oper.AllocateBestFit(processname ,holeList ,segmentList);
                  System.out.println("segId   " + "segBase   " + "    segLimit");
                  for(segment op :segmentList){
                      System.out.println(op.getSegmentName()+"         "+ op.getSegmentBase()+"        "+op.getSegmentLimit());

                  }


                  System.out.println("holes after alloc and before deallocation");
                 // Collections.sort(holeList, new sortByBase());
                  System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                  for(holes op :oper.getHolesList()){
                  System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());

                  }
                
                  
              }

    	}

    	else
    	{
    		boolean checksegments =oper.BestFit(processName.getText(), segmentList);
    		 if(checksegments==false){
                 System.out.println("there's no enough space2");
                 segmentList.clear();  
               }
               else {             	 
                   oper.AllocateBestFit(processName.getText() ,segmentList);
                   draw_memory.draw_process(processName.getText(), segmentList);
                   //oper.AllocateBestFit(processname ,holeList ,segmentList);
                   System.out.println("segId   " + "segBase   " + "    segLimit");
                   for(segment op :segmentList){
                       System.out.println(op.getSegmentName()+"         "+ op.getSegmentBase()+"        "+op.getSegmentLimit());
                   }
                   System.out.println("holes after alloc and before deallocation");
                   System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                   for(holes op :oper.getHolesList()){
                   System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());
                   }
                   
                   
               }
    	
    	
    	
    	
    	}
  	
    		FXMLLoader tab_loader = new FXMLLoader(getClass().getResource("/segmentTab/segementTab.fxml"));
    		Tab new_tab;
    		new_tab = tab_loader.load();
    		segmentTableController controller = tab_loader.getController(); 		     
    	    

    		controller.setSegments(FXCollections.observableArrayList(segmentList));
    		controller.updateTable();
    		new_tab.setText(processName.getText());
    		tabPane.getTabs().add(new_tab);
    		tabPane.getSelectionModel().select(new_tab);
    	  	segmentName.clear();
        	segmentSize.clear();
        	processName.clear();
        	numOfSegments.clear();
        	segmentList.clear();
        //	oper.clearSegments();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		

    	//alocate
		    	
	    /*	if(full)
	    	{

	    	}
	    	else {

<<<<<<< HEAD

	    	}*/

	    	

	    	
	    	segmentName.setDisable(true);
			segmentSize.setDisable(true);
			segmentLabel.setDisable(true);


		    
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
