package noPlaceError;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import mainScene.mainSceneController;

public class errorController {

    @FXML
    private JFXTextField processName;

    @FXML
    void DeallocateProcess(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
    	
    	mainSceneController.closeErrorBox();

    }

}