package startingScene;


import java.util.ArrayList;
import java.util.List;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.image.ImageView;
import memoryDs.holes;
//import memoryDs.hole;
import memoryDs.operate;


public class startingSceneController  {

    @FXML
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
    void enterHole(ActionEvent event) {
    	holeList.add(new holes(Integer.parseInt(holeStartAdress.getText()) , Integer.parseInt(holeSize.getText())));
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



}
