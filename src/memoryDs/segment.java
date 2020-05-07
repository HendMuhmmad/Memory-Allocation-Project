package memoryDs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class segment {
	private SimpleStringProperty  segmentName;
	private SimpleIntegerProperty segmentBase;
	private SimpleIntegerProperty SegmentLimit;

	//CONSTRUCTOR
	public segment(String segmentName, int segmentLimit) {
		this.segmentName = new SimpleStringProperty(segmentName);
		this.SegmentLimit = new SimpleIntegerProperty(segmentLimit);
		this.segmentBase = new SimpleIntegerProperty(0);
		
	}

	public String getSegmentName() {
		return segmentName.get();
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = new SimpleStringProperty(segmentName);
	}

	public int getSegmentBase() {
		return segmentBase.get();
	}

	public void setSegmentBase(int segmentBase) {
		this.segmentBase = new SimpleIntegerProperty(segmentBase);
	}

	public int getSegmentLimit() {
		return SegmentLimit.get();
	}

	public void setSegmentLimit(int segmentLimit) {
		SegmentLimit = new SimpleIntegerProperty(segmentLimit);
	}

	
}
