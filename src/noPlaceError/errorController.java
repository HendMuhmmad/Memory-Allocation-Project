package noPlaceError;



import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class errorController {

    @FXML
    private Label warningText;



    @FXML
    void close(ActionEvent event) {
    	
    	Main.closeErrorBox();

    }
   public void setWarning(String messeage) {
    	
    	warningText.setText(messeage);

    }
    

}