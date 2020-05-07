package segmentTab;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import memoryDs.segment;

public class segmentTableController implements Initializable {

    @FXML
    private static TableView<segment> segmentTable;

    @FXML
    private TableColumn<segment,String> name;

    @FXML
    private TableColumn<segment,Integer> base;

    @FXML
    private TableColumn<segment,Integer> limit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		name.setCellValueFactory(new PropertyValueFactory<>("segmentName"));
		base.setCellValueFactory(new PropertyValueFactory<>("segmentBase"));
		limit.setCellValueFactory(new PropertyValueFactory<>("segmentLimit"));			
	}
	    

}