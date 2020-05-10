package startingScene;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;

import memoryDs.holes;
//import memoryDs.hole;
import memoryDs.operate;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class startingSceneController implements Initializable {
	
	@FXML
    private HBox mainHbox;
	
    private ImageView image;
    @FXML
    private JFXCheckBox firstFiit;

    @FXML
    private JFXCheckBox bestFit;
    
    @FXML
    private JFXTextField memorySize;

    @FXML
    private JFXTextField holeStartAdress;

    @FXML
    private JFXButton done;

    @FXML
    private JFXTextField holeSize;

    @FXML
    private JFXButton enterHole;

    ArrayList<holes> holeList = new ArrayList<holes> ();
    operate oper;

   

    @FXML
    private Pane imgpane;
  


    @FXML
    void enterHole(ActionEvent event) {
    	holeList.add(new holes(Integer.parseInt(holeStartAdress.getText()) , Integer.parseInt(holeSize.getText())));
    	holeStartAdress.clear();
    	holeSize.clear();
    
    }
 
    @FXML
    void start(ActionEvent event) {
    	holeList.add(new holes(Integer.parseInt(holeStartAdress.getText()) , Integer.parseInt(holeSize.getText())));       
    	oper = new operate(Integer.parseInt(memorySize.getText()));
    	oper.setholes(holeList);
    	if(oper.SizeOfHoles() <=oper.getMemorySize())
    	{
    		if(firstFiit.isSelected())
    		{
    			oper.setType(true);
    		}
    		else if (bestFit.isSelected())
    		{
    			oper.setType(false);
    		}
    			
    		Main.showMainScene(oper);
    		
    	}
    	else
    	{
    		
    		Main.showAlertStage("Exceeded Memory Limit");
    	}
    	
    	holeList.clear();       
    }

    @FXML
    void bestfitChecked(ActionEvent event) {
    	firstFiit.setSelected(false);

    }

    @FXML
    void firstFitChecked(ActionEvent event) {
    	bestFit.setSelected(false);

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		image = new ImageView("/startingScene/tech_brush_by_dayzee_by_dariadz_d8xnsi6-pre.jpg");
		image.setBlendMode(BlendMode.SRC_OVER);
		image.setPreserveRatio(false);
		image.setOpacity(0.45);
		image.setManaged(true);
		imgpane.getChildren().add(image);
	}



}
