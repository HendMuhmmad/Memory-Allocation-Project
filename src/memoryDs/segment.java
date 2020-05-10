package memoryDs;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.Pane;

public class segment {
	private SimpleStringProperty  segmentName;
	private SimpleFloatProperty segmentBase;
	private SimpleFloatProperty SegmentLimit;
	private Pane box;
	//CONSTRUCTOR
	public Pane getBox() {
		return box;
	}
	public void setBox(Pane box) {
		this.box = box;
	}

	public segment(String segmentName, float segmentLimit) {

		this.segmentName = new SimpleStringProperty(segmentName);
		this.SegmentLimit = new SimpleFloatProperty(segmentLimit);

		this.segmentBase = new SimpleFloatProperty(0);	

		this.segmentBase = new SimpleFloatProperty(0);
		

	
		

	}
	public segment(String segmentName, int segmentLimit,int segmentBase) {

		this.segmentName = new SimpleStringProperty(segmentName);
		this.SegmentLimit = new SimpleFloatProperty(segmentLimit);

		this.segmentBase = new SimpleFloatProperty(0);	

		this.segmentBase = new SimpleFloatProperty(0);
		

		this.segmentBase = new SimpleFloatProperty(segmentBase);
		

	}

	public String getSegmentName() {
		return segmentName.get();
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = new SimpleStringProperty(segmentName);
	}

	public float getSegmentBase() {
		return segmentBase.get();
	}

	public void setSegmentBase(float segmentBase) {
		this.segmentBase = new SimpleFloatProperty(segmentBase);
	}

	public float getSegmentLimit() {
		return SegmentLimit.get();
	}

	public void setSegmentLimit(float segmentLimit) {
		SegmentLimit = new SimpleFloatProperty(segmentLimit);
	}
}
