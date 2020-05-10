package startingScene;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private boolean valid;
    
    @FXML
    private Label choiceLabel;

    @FXML
    private Pane imgpane;
  
    RequiredFieldValidator validator =new RequiredFieldValidator();
	NumberValidator n_validator = new NumberValidator();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		validator.setMessage("Required Field !");
		n_validator.setMessage("Only Numbers Allowed !");
		memorySize.getValidators().addAll(validator,n_validator);
		holeStartAdress.getValidators().addAll(validator,n_validator);
		holeSize.getValidators().addAll(validator,n_validator);
		image = new ImageView("/startingScene/tech_brush_by_dayzee_by_dariadz_d8xnsi6-pre.jpg");
		image.setBlendMode(BlendMode.SRC_OVER);
		image.setPreserveRatio(false);
		image.setOpacity(0.45);
		image.setManaged(true);
		imgpane.getChildren().add(image);
		memorySize.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal)
		    {
		    	valid = memorySize.validate();
		    }

		});
		holeStartAdress.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal)
		    {
		    	valid = holeStartAdress.validate();
		    }

		});
		holeSize.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal)
		    {
		    	valid = holeSize.validate();
		    }

		});
	}

    @FXML
    void enterHole(ActionEvent event) {
        if(valid)
        {
          	int holeStart= Integer.parseInt(holeStartAdress.getText());
        	int holeLimit= Integer.parseInt(holeSize.getText());
        	holeList.add(new holes(holeStart,holeLimit));
        	holeStartAdress.clear();
        	holeSize.clear();
        	memorySize.setDisable(true);     	
        }
 
    }
 
    @FXML
    void start(ActionEvent event) {
    	oper = new operate();
    	if(firstFiit.isSelected())
		{
			oper.setType(true);
			valid = true;
			choiceLabel.setVisible(false);
		}
		else if (bestFit.isSelected())
		{
			oper.setType(false);
			valid = true;
			choiceLabel.setVisible(false);
		}
		else
		{
			valid = false;
			choiceLabel.setVisible(true);
		}
    	if(valid)
    	{
    		
        	holeList.add(new holes(Integer.parseInt(holeStartAdress.getText()) , Integer.parseInt(holeSize.getText())));       
        	oper.setMemorySize(Integer.parseInt(memorySize.getText()));
        	oper.setholes(holeList);
        	if(oper.SizeOfHoles() <=oper.getMemorySize())
        	{        	        		
        		Main.showMainScene(oper);
        		
        	}
        	else
        	{
        		
        		Main.showAlertStage("Exceeded Memory Limit");
        	}
        	
        	holeList.clear();    		
    	}     
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
