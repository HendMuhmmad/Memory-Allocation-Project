package memoryDs;

import java.util.Collections;
import java.util.Comparator;


public class holes {
    
    private float base;
    private float limit;
    private float end;

    public holes(float start , float size){
         this.base = start;
         this.limit = size;
         this.end = start+size-1;

    }
    

    public holes() {
        this.base = 0;
        this.end = 0;
        this.limit = 0;
    }
    

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }
   
    
}
   
    
    class sortByLimit implements Comparator<holes>  {
		public int compare(holes h1 ,holes h2)
		{
			if(h1.getLimit()>h2.getLimit()) {return 1;}
			else if (h1.getLimit()<h2.getLimit()) {return -1;}
			return 0;
	    }

    }
