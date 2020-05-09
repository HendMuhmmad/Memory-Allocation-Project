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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import memoryDs.hole;


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
    @FXML
    private Pane imgpane;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//image.fitWidthProperty().bind(imgpane.widthProperty());
		//image.fitHeightProperty().bind(imgpane.heightProperty());
		//image.autosize();
		//image.fitWidthProperty().
		//imgpane.setPrefSize(mainHbox.getPrefWidth()/2, mainHbox.getPrefHeight());
		image = new ImageView("/startingScene/tech_brush_by_dayzee_by_dariadz_d8xnsi6-pre.jpg");
		image.setBlendMode(BlendMode.SRC_OVER);
		image.setPreserveRatio(false);
		image.setOpacity(0.45);
		image.setManaged(true);
		imgpane.getChildren().add(image);
	}



}
