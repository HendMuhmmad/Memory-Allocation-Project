package memoryDs;

import java.util.Collections;
import java.util.Comparator;


public class holes {
    
    private int base;
    private int limit;
    private int end;

    public holes(int start , int size){
         this.base = start;
         this.limit = size;
         this.end = start+size-1;

    }
    

    public holes() {
        this.base = 0 ;
        this.end = 0;
        this.limit = 0;
    }
    

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
   
    
}
    class sortByBase implements Comparator<holes>  {
		public int compare(holes h1 ,holes h2)
		{
			if(h1.getBase()>h2.getBase()) {return 1;}
			else if (h1.getBase()<h2.getBase()) {return -1;}
			return 0;
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

