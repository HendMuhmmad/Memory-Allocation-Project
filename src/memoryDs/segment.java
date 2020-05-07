package memoryDs;

public class segment {
	private String segmentName;
	private int segmentBase;
	private int SegmentLimit;

	//CONSTRUCTOR
	public segment(String segmentName, int segmentLimit) {
		this.segmentName = segmentName;
		this.SegmentLimit = segmentLimit;
		this.segmentBase = 0;
		
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public int getSegmentBase() {
		return segmentBase;
	}

	public void setSegmentBase(int segmentBase) {
		this.segmentBase = segmentBase;
	}

	public int getSegmentLimit() {
		return SegmentLimit;
	}

	public void setSegmentLimit(int segmentLimit) {
		SegmentLimit = segmentLimit;
	}


}
