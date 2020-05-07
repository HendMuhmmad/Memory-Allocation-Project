package segmentTab;


import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import memoryDs.segment;

public class segmentTableController {

    @FXML
    private  TableView<segment> segmentTable;

    @FXML
    private  TableColumn<segment,String> name;

    @FXML
    private  TableColumn<segment,Integer> base;

    @FXML
    private  TableColumn<segment,Integer> limit;

    private ObservableList<segment> segments = FXCollections.observableArrayList();
    
	public  void updateTable()
	{
		name.setCellValueFactory(new PropertyValueFactory<>("segmentName"));
		base.setCellValueFactory(new PropertyValueFactory<>("segmentBase"));
		limit.setCellValueFactory(new PropertyValueFactory<>("segmentLimit"));	
		segmentTable.setItems(segments);
		
	}
	public  void setSegmentList(List<segment> segs)
	{
		segments.addAll(segs);
		
	}

	    

}