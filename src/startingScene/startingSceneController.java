package startingScene;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class startingSceneController  implements Initializable  {

    @FXML
    private ImageView image;
    
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
    void enterHole(ActionEvent event) {

    }

    @FXML
    void start(ActionEvent event) {
        Main.showMainScene();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}

}
