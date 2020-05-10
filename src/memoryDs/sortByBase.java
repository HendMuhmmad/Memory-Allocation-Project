package memoryDs;

import java.util.Comparator;

public class sortByBase implements Comparator<holes>  {
	public int compare(holes h1 ,holes h2)
	{
		if(h1.getBase()>h2.getBase()) {return 1;}
		else if (h1.getBase()<h2.getBase()) {return -1;}
		return 0;
    }

}
