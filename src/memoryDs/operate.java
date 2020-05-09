
package memoryDs;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class operate {
    private int memorySize;
    private boolean type; //true first fit   false  best fit
    private ArrayList<holes> holesList = new ArrayList<holes>();
    private ObservableList<segment> segments =FXCollections.observableArrayList();
    
    public ArrayList<holes> getHolesList() {
		return holesList;
	}
    public ObservableList<segment> getObservableList()
    {
    	return segments;
    }
	public void setholes(ArrayList<holes> holes) {
		this.holesList.addAll(holes);
	}
    
    public operate(int size){
        this.memorySize =size;
    }
    
    public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public operate(){
        this.memorySize=0;
    }
    
    public int SizeOfHoles(){
        int size = 0;
        for(holes a : holesList){
            size = size + a.getLimit();
        }
        
        return size;
    }
    
    public void ArrangeHoles(){
        Collections.sort(holesList, new sortByBase());    
       for(int i= 0 ;i<holesList.size() ; i++)
        { 
            //System.out.println("d5lt gwa el for");
            int new_limit_hole = 0;
            if(holesList.size()-i!=1){
                if((holesList.get(i).getBase() + holesList.get(i).getLimit()) == holesList.get(i+1).getBase())
                    {
                        //System.out.println("d5lt gwa el if");
                        new_limit_hole = new_limit_hole + holesList.get(i).getLimit() ;
                        //System.out.println("limit hole " + new_limit_hole);
                        holesList.get(i).setLimit(new_limit_hole + holesList.get(i+1).getLimit());
                        holesList.get(i).setEnd(holesList.get(i).getBase()+holesList.get(i).getLimit()-1);
                        holesList.remove(i+1);
                        i--;	
                    }
                
            }
           // System.out.println(holesList.size());
        }
    }
    
    public int SearchInHolesList(int segmentBase , int segmentEnd){
        boolean up = false;
        boolean down = false;
        int result;
        
        for(int index=0 ; index<holesList.size() ; index++){
            if(segmentBase == (holesList.get(index).getLimit()+holesList.get(index).getBase())){
                up = true;
            }
            if(segmentEnd == holesList.get(index).getBase()){
                down = true;
            }
        }
        
        if(up==true && down==false){
            result=0;
            return result;
        }
        else if(up==false && down==true){
            result=1;
            return result;
        }
        else if(up==true && down==true){
            result=2;
            return result;
        }
        else if(up==false && down==false){
            result=3;
            return result;
        }
        return 4;
    } 
    
    public void DeallocateProcess(String ProcessName, ArrayList<segments> segmentList){
        Collections.sort(holesList, new sortByBase());
        int result;
        int BaseHole = 0;
        int EndHole;
        int limitofholes=0;
        for(int i=0; i<segmentList.size(); i++){
            
            result= SearchInHolesList(segmentList.get(i).getSegmentBase()
                    ,(segmentList.get(i).getSegmentBase()+segmentList.get(i).getSegmentLimit())
                    );
            
            //System.out.println("result " + result);
            if(result==0){
                for(int index=0 ; index<holesList.size() ; index++){
                    if(segmentList.get(i).getSegmentBase()== (holesList.get(index).getBase()+holesList.get(index).getLimit())){
                        holesList.get(index).setLimit(holesList.get(index).getLimit()+segmentList.get(i).getSegmentLimit());
                        holesList.get(index).setEnd(holesList.get(index).getBase()+holesList.get(index).getLimit()-1);
                        break;
                    }
                }
                Collections.sort(holesList, new sortByBase());
            }
            else if(result==1){
                for(int index=0 ; index<holesList.size() ; index++){
                    if(segmentList.get(i).getSegmentBase()+segmentList.get(i).getSegmentLimit()== holesList.get(index).getBase()){
                        holesList.get(index).setLimit(holesList.get(index).getLimit()+segmentList.get(i).getSegmentLimit());
                        holesList.get(index).setBase(segmentList.get(i).getSegmentBase());
                        holesList.get(index).setEnd(holesList.get(index).getBase()+holesList.get(index).getLimit()-1);
                        break;
                    }
                }   
                Collections.sort(holesList, new sortByBase());
            }
            else if(result==2){
                
                for(int index=0 ; index<holesList.size() ; index++){
                    if(segmentList.get(i).getSegmentBase()== (holesList.get(index).getBase()+holesList.get(index).getLimit())){
                        BaseHole = holesList.get(index).getBase();
                        //System.out.println("base hole "+BaseHole);
                        limitofholes = limitofholes+ holesList.get(index).getLimit(); 
                        //System.out.println("limitof holes "+limitofholes);
                        holesList.remove(holesList.get(index));
                    }
                    if(segmentList.get(i).getSegmentBase()+segmentList.get(i).getSegmentLimit()== holesList.get(index).getBase()){
                        EndHole = holesList.get(index).getBase()+holesList.get(index).getLimit() -1 ;
                        limitofholes = limitofholes+ holesList.get(index).getLimit();
                        //System.out.println("limitof holes "+limitofholes);
                        holesList.remove(holesList.get(index));    
                    }
                    
                }
                holesList.add(new holes(BaseHole  , segmentList.get(i).getSegmentLimit() + limitofholes));
                Collections.sort(holesList, new sortByBase());
            }
            else if(result==3){
                holesList.add(new holes(segmentList.get(i).getSegmentBase() , segmentList.get(i).getSegmentLimit()));
                
                Collections.sort(holesList, new sortByBase());
//                
//                for(int index=0 ; index<holesList.size();index++){
//                    System.out.println("base" +holesList.get(index).getBase());
//                    System.out.println("limit" +holesList.get(index).getLimit());
//                    
//                }
                
            }
            
            
        }
    }
    
    public void AllocateFirstFit(String ProcessName, ArrayList<segments> segmentList){
        
        for(int i=0 ; i<segmentList.size() ; i++){
            
            int index=0;
            while(index< holesList.size()){
                if(segmentList.get(i).getSegmentLimit() <= holesList.get(index).getLimit()){
                    segmentList.get(i).setSegmentBase(holesList.get(index).getBase());
                    segments.add(new segment(segmentList.get(i).getSegmentName(),segmentList.get(i).getSegmentLimit(),segmentList.get(i).getSegmentBase()));
                    if(segmentList.get(i).getSegmentLimit() == holesList.get(index).getLimit()){
                        holesList.remove(index);
                    }
                    else if(segmentList.get(i).getSegmentLimit() < holesList.get(index).getLimit()){
                        holesList.get(index).setBase(segmentList.get(i).getSegmentBase()+segmentList.get(i).getSegmentLimit());
                        holesList.get(index).setLimit(holesList.get(index).getLimit()-segmentList.get(i).getSegmentLimit());
                    }
                    break;
                }
                index++;
            }
        }
    }
    
    public void AllocateBestFit(String ProcessName, ArrayList<segments> segmentList){
        Collections.sort(holesList, new sortByLimit());
        for(int i=0 ; i<segmentList.size() ; i++){
            Collections.sort(holesList, new sortByLimit());
            int index=0;
            while(index< holesList.size()){
                if(segmentList.get(i).getSegmentLimit() <= holesList.get(index).getLimit()){
                    segmentList.get(i).setSegmentBase(holesList.get(index).getBase());
                    segments.add(new segment(segmentList.get(i).getSegmentName(),segmentList.get(i).getSegmentLimit(),segmentList.get(i).getSegmentBase()));
                    if(segmentList.get(i).getSegmentLimit() == holesList.get(index).getLimit()){
                        holesList.remove(index);
                    }
                    else if(segmentList.get(i).getSegmentLimit() < holesList.get(index).getLimit()){
                        holesList.get(index).setBase(segmentList.get(i).getSegmentBase()+segmentList.get(i).getSegmentLimit());
                        holesList.get(index).setLimit(holesList.get(index).getLimit()-segmentList.get(i).getSegmentLimit());
                    }
                    break;
                }
                index++;
            }
        }
    }
    
    public void FirstFit(String ProcessName  , ArrayList<segments> segmentList){
        Collections.sort(holesList, new sortByBase());
        int sizeofsegment=0;
        int sizeofholes=0;
        for(segments s: segmentList){
            sizeofsegment += s.getSegmentLimit();
        }
        
        for(holes s: holesList){
        sizeofholes += s.getLimit();
        }
       // System.out.println(sizeofsegment);
        boolean flag = false;
        int counter=0;
        int numofelements =0;
        
        ArrayList<Integer> savedholes = new ArrayList<Integer> (); 
        ArrayList<Integer> savedholesrem = new ArrayList<Integer> (); 
        
        if(sizeofsegment > sizeofholes){
        	System.out.println(sizeofsegment);
        	System.out.println(sizeofholes);
        	System.out.println(holesList);
            System.out.println("there's no enough space");
        }
        else{
            for(int i=0 ; i<segmentList.size(); i++){
                int index=0;
                boolean exit = false;
                int remaining;
                while(index < holesList.size()){
                    if(segmentList.get(i).getSegmentLimit()<= holesList.get(index).getLimit()){
                       
                        for(int k=0 ; k<savedholes.size() ; k++){
                            //System.out.println("haha");
                           // System.out.println("saved "+ savedholes.get(k));
                            if(savedholes.get(k)== holesList.get(index).getBase()){
                                if(segmentList.get(i).getSegmentLimit()<= savedholesrem.get(k))
                                {
                                    //holesList.get(index).setBase(segmentList.get(i).getSegmentLimit()+holesList.get(index).getBase());
                                  //  System.out.println("d5lt gwa el savedrem");
                                    numofelements++;
                                    
                                    
                                     remaining = savedholesrem.get(k) - segmentList.get(i).getSegmentLimit();
                                     savedholesrem.set(k,remaining );
                                     if(remaining == 0 ){
                                     //   System.out.println("abo shklk");
                                    }
//                                     System.out.println("savedrem"+ savedholesrem.get(0));
//                                     System.out.println("rem "+ remaining);
                                     counter++;
                                                            
                                    //counter=0;
                                }

                                else{
                                    counter++;
                                    break;
                                }
                                
                            }
                        }
                        if(counter==0){
                            
                           // System.out.println("holesList "+ holesList.get(index).getLimit());         
                            remaining = holesList.get(index).getLimit() - segmentList.get(i).getSegmentLimit();
                           // System.out.println("rem "+ remaining);
                            numofelements++;
                           // System.out.println("d5lt gwa el counter =0");
                            savedholes.add(holesList.get(index).getBase());
                            savedholesrem.add(remaining);
                            exit=true;
                        }
                        else {
                         //   System.out.println("d5lt gwa el else");
                             counter=0;
                          
                        }
                       

                    }
                    if(exit==true){
                        break;
                    }
                    index++;
                }

            }
            
            if(numofelements!=segmentList.size()){
                System.out.println("there's no enough space2");
                segmentList.clear();
               // deallocate segments of refused process;
                
            }
            else{
                //the allocation function 
                
                AllocateFirstFit(ProcessName,segmentList);
                
                System.out.println("segId   " + "segLimit   " + "segBase");
                for(segments op :segmentList){
                    System.out.println(op.getSegmentName()+"        "+op.getSegmentLimit()+"            "+ op.getSegmentBase());
            
                }
                
                
                    System.out.println("holes before allocation");
                System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                for(holes op :holesList){
                System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());

                }
                
                DeallocateProcess(ProcessName ,segmentList);  //Gui httshaaaal
                System.out.println("holes after allocation");
                
                System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                for(holes op :holesList){
                System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());

            }
                segmentList.clear(); //bt7sl m3 nhayetm kol process ma3da a5r process
                
            }
        }
    }
    
    public void BestFit(String ProcessName , ArrayList<segments> segmentList ){
        Collections.sort(holesList, new sortByLimit());
        int sizeofsegment=0;
        int sizeofholes=0;
        for(segments s: segmentList){
            sizeofsegment += s.getSegmentLimit();
        }
        
        for(holes s: holesList){
        sizeofholes += s.getLimit();
        }
       // System.out.println(sizeofsegment);
        boolean flag = false;
        int counter=0;
        int numofelements =0;
        
        ArrayList<Integer> savedholes = new ArrayList<Integer> (); 
        ArrayList<Integer> savedholesrem = new ArrayList<Integer> (); 
        
        if(sizeofsegment > sizeofholes){
        	System.out.println(sizeofsegment);
        	System.out.println(sizeofholes);
        	System.out.println(sizeofsegment);
        	System.out.println(holesList);
            System.out.println("there's no enough space");
        }
        else{
            for(int i=0 ; i<segmentList.size(); i++){
                int index=0;
                boolean exit = false;
                int remaining;
                while(index < holesList.size()){
                    if(segmentList.get(i).getSegmentLimit()<= holesList.get(index).getLimit()){
                       
                        for(int k=0 ; k<savedholes.size() ; k++){
                            //System.out.println("haha");
                           // System.out.println("saved "+ savedholes.get(k));
                            if(savedholes.get(k)== holesList.get(index).getBase()){
                                if(segmentList.get(i).getSegmentLimit()<= savedholesrem.get(k))
                                {
                                    //holesList.get(index).setBase(segmentList.get(i).getSegmentLimit()+holesList.get(index).getBase());
                                  //  System.out.println("d5lt gwa el savedrem");
                                    numofelements++;
                                    
                                    
                                     remaining = savedholesrem.get(k) - segmentList.get(i).getSegmentLimit();
                                     savedholesrem.set(k,remaining );
                                     if(remaining == 0 ){
                                     //   System.out.println("abo shklk");
                                    }
//                                     System.out.println("savedrem"+ savedholesrem.get(0));
//                                     System.out.println("rem "+ remaining);
                                     exit = true ;
                                     counter++;
                                                            
                                    //counter=0;
                                }

                                else{
                                    counter++;
                                    break;
                                }
                                
                            }
                        }
                        if(counter==0){
                            
                           // System.out.println("holesList "+ holesList.get(index).getLimit());         
                            remaining = holesList.get(index).getLimit() - segmentList.get(i).getSegmentLimit();
                           // System.out.println("rem "+ remaining);
                            numofelements++;
                           // System.out.println("d5lt gwa el counter =0");
                            savedholes.add(holesList.get(index).getBase());
                            savedholesrem.add(remaining);
                            exit=true;
                        }
                        else {
                         //   System.out.println("d5lt gwa el else");
                             counter=0;
                          
                        }
                       

                    }
                    if(exit==true){
                        break;
                    }
                    index++;
                }

            }
            
            if(numofelements!=segmentList.size()){
                System.out.println("there's no enough space2");
                segmentList.clear();
               // deallocate segments of refused process;
                
            }
            else{
                //the allocation function 
                
                AllocateBestFit(ProcessName,segmentList);
                System.out.println("segId   " + "segLimit   " + "segBase");
                for(segments op :segmentList){
                    System.out.println(op.getSegmentName()+"        "+op.getSegmentLimit()+"            "+ op.getSegmentBase());
            
                }
                

                System.out.println("holes after alloc and before deallocation");
                Collections.sort(holesList, new sortByBase());
                System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                for(holes op :holesList){
                System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());

                }
                
                DeallocateProcess(ProcessName,segmentList);  //Gui httshaaaal
                System.out.println("holes after deallocation");
                
                System.out.println("holeBase   " + "holeLimit   " + "holeEnd");
                for(holes op :holesList){
                System.out.println(op.getBase()+"           "+op.getLimit()+"            "+op.getEnd());

                }
                segmentList.clear(); //bt7sl m3 nhayetm kol process ma3da a5r process
                
            }
        }
    }

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}    
	
	public void clearSegments()
	{
		segments.clear();
	}

    
}
