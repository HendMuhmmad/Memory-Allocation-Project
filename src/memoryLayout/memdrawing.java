package memoryLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import memoryDs.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class memdrawing {
	public AnchorPane anchorpane;
	public int memory_size;
	Map<String,ArrayList<segment>> map; 
	int index;
	public memdrawing(AnchorPane _achorpane , int _memory_size) 
	{
		anchorpane=_achorpane;
		memory_size=_memory_size;
		map =new HashMap<String,ArrayList<segment>>();
	}
	
	public String randomColor() 
	{
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        String hex = String.format("#%02x%02x%02x", r, g, b);  
        return hex; 
	}
	
	Pane draw_partition (String Process_name,String _segmentname,int base,int limit,String color)
	{
		 BorderPane new_segment =new BorderPane();
		 
		 //hbox for label of start index
		 HBox segment_start =new HBox();
		 segment_start.getChildren().add(new Label(Integer.toString(base)));
		 segment_start.setStyle("-fx-font-size:"+ 0.01*anchorpane.getHeight()+ ";");
		 
		 //hbox for label of segment name
		 HBox segment_name =new HBox();
		 Label p_name =new Label(Process_name);
		 Label seg_name = new Label(_segmentname); 
		 
		 segment_name.getChildren().addAll(p_name,seg_name);
		 segment_name.setStyle("-fx-font-size:"+ 0.014*anchorpane.getHeight()+ ";");
		 segment_name.setAlignment(Pos.CENTER);
		 
		 //hbox for label of end index 
		 int end = base+limit-1;
		 HBox segment_end =new HBox();
		 segment_end.getChildren().add(new Label(Integer.toString(end)));
		 segment_end.setStyle("-fx-font-size:"+ 0.01*anchorpane.getHeight()+ ";");
		 
		 //add the data of partition
		 new_segment.setTop(segment_start);
		 new_segment.setBottom(segment_end);
		 new_segment.setCenter(segment_name);
		 //adjusting the position and width and height of partition
		 new_segment.setLayoutX(0);
		 new_segment.setLayoutY(base*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefHeight(limit*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefWidth(anchorpane.getWidth());
		 //style of the partition	 
		  new_segment.setStyle( "-fx-border-style: solid;"
		                     + "-fx-border-width: 2;"
		                     + "-fx-border-radius: 5;"
		                     + "-fx-border-color:"+ color +";");
		 anchorpane.getChildren().add(new_segment);
		 System.out.println(randomColor());
		 return new_segment;
	}
		
	void draw_process(String process_name,ArrayList<segment> list )
	{
		String color = randomColor();
		list.forEach((segment)->
		{
			segment.setBox(draw_partition(process_name, segment.getSegmentName(), segment.getSegmentBase(), segment.getSegmentLimit(), color));	
		});
		
		map.put(process_name, list);
		
	}

	void draw_holes (ArrayList<holes> holes)
	{
		index=0;
		ArrayList<segment> list =new ArrayList<segment>();
		segment used =new segment("used", 0);
		
		holes.forEach((hole)->
		{
			if(index < hole.getBase()) 
			{
				used.setSegmentLimit(hole.getBase()-index);
				used.setSegmentBase(index);
				list.add(used);
			}
			index=hole.getBase()+hole.getLimit();
		});
		
		if(index != memory_size)
		{
			used.setSegmentLimit(memory_size-index);
			used.setSegmentBase(index);	
			list.add(used);
		}
	
		draw_process("used", list);
	}

}
