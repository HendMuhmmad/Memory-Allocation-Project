
package memoryDs;

import javafx.scene.layout.Pane;

public class segments {
    private String SegmentName;
    private int SegmentBase;
    private int SegmentLimit;

    private Pane box;
	//CONSTRUCTOR
	public Pane getBox() {
		return box;
	}
	public void setBox(Pane box) {
		this.box = box;
	}    
    
    public segments(String Name , int limit ){
        this.SegmentName = Name;
        this.SegmentLimit = limit;                
    }
    
    public segments(){
        this.SegmentName = null;
        this.SegmentLimit = 0;   
    }

    public String getSegmentName() {
        return SegmentName;
    }

    public void setSegmentName(String SegmentName) {
        this.SegmentName = SegmentName;
    }

    public int getSegmentBase() {
        return SegmentBase;
    }

    public void setSegmentBase(int SegmentBase) {
        this.SegmentBase = SegmentBase;
    }

    public int getSegmentLimit() {
        return SegmentLimit;
    }

    public void setSegmentLimit(int SegmentLimit) {
        this.SegmentLimit = SegmentLimit;
    }

    
    
    
    
}
