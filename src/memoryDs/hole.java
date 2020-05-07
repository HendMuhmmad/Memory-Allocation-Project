package memoryDs;
import java.util.*;
public class hole    {
	private int base;
	private int limit;

	//CONSTRUCTOR
	public hole(int base, int limit) {
		this.base = base;
		this.limit = limit;
	}

	public hole()
	{
		this.base=0;
		this.limit=0;
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
}

class sortByBase implements Comparator<hole>  {
		public int compare(hole h1 ,hole h2)
		{
			if(h1.getBase()>h2.getBase()) {return 1;}
			else if (h1.getBase()<h2.getBase()) {return -1;}
			return 0;
	    }

}

class sortByLimit implements Comparator<hole>  {
		public int compare(hole h1 ,hole h2)
		{
			if(h1.getLimit()>h2.getLimit()) {return 1;}
			else if (h1.getLimit()<h2.getLimit()) {return -1;}
			return 0;
	    }

}



