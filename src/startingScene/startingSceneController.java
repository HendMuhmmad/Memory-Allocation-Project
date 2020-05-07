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
import memoryDs.hole;


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
    private List<hole> holes = new ArrayList<hole>();

    @FXML
    void enterHole(ActionEvent event) {
    	hole h = new hole(Integer.parseInt(holeStartAdress.getText()),Integer.parseInt(holeSize.getText()));
    	holes.add(h);

    }

    @FXML
    void start(ActionEvent event) {
    	
        Main.showMainScene();
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
