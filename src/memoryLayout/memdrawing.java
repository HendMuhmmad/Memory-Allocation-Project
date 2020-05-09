package memoryLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.jfoenix.controls.JFXButton;

import memoryDs.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class memdrawing 
{
	public AnchorPane anchorpane;
	public int memory_size;
	Map<String,ArrayList<segments>> map; 
	int index;
	public memdrawing(AnchorPane _achorpane , int _memory_size) 
	{
		anchorpane=_achorpane;
		memory_size=_memory_size;
		map =new HashMap<String,ArrayList<segments>>();
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
	
	public Pane draw_partition (String Process_name,String _segmentname,int base,int limit,String color)
	{
		 VBox new_segment =new VBox();
		 
		 //hbox for label of start index
		 HBox segment_start =new HBox();
		 segment_start.getChildren().add(new Label(Integer.toString(base)));
		// segment_start.setStyle("-fx-font-size:"+ 0.01*anchorpane.getHeight()+ ";");
		 segment_start.setAlignment(Pos.TOP_LEFT);
		
		 //hbox for label of segment name
		 HBox segment_name =new HBox();
		 Label p_name =new Label(Process_name);
		 Label seg_name = new Label(_segmentname); 
		 
		 segment_name.getChildren().addAll(p_name,seg_name);
		// segment_name.setStyle("-fx-font-size:"+ 0.014*anchorpane.getHeight()+ ";");
		 segment_name.setAlignment(Pos.CENTER);
		 
		 //hbox for label of end index 
		 int end = base+limit-1;
		 HBox segment_end =new HBox();
		 segment_end.getChildren().add(new Label(Integer.toString(end)));
		 //segment_end.setStyle("-fx-font-size:"+ 0.01*anchorpane.getHeight()+ ";");
		 segment_end.setAlignment(Pos.BOTTOM_LEFT);
		 //add the data of partition
		 //new_segment.setTop(segment_start);
		 //new_segment.setBottom(segment_end);
		// new_segment.setCenter(segment_name);
		new_segment.getChildren().add(segment_name);
		 new_segment.setLayoutX(0);
		 new_segment.setLayoutY(base*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefHeight(limit*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefWidth(anchorpane.getWidth());
		System.out.println(anchorpane.getWidth());
		
		while(anchorpane.getHeight()==0) {System.out.println(anchorpane);}
		System.out.println(anchorpane.getHeight());
		System.out.println(anchorpane);
		 //style of the partition	 
		 new_segment.setStyle( "-fx-border-style: solid;"
		                     + "-fx-border-width: 2;"
		                     + "-fx-border-radius: 5;"
		                     + "-fx-border-color:"+ color +";");
		 anchorpane.getChildren().add(new_segment);
		 System.out.println(randomColor());
		 
		 ////clear button
		 JFXButton clear = new JFXButton("clear");
		 HBox segment_delete =new HBox();
		 segment_delete.getChildren().add(clear);
		 segment_delete.setAlignment(Pos.CENTER);
		 clear.setOnAction(e->
		 {
			 anchorpane.getChildren().remove(new_segment);
		 });
		 
		 // hover 
		 new_segment.hoverProperty().addListener((ov, oldValue, newValue) -> {
			    if (newValue) {
			    	new_segment.getChildren().clear();
			    	new_segment.getChildren().addAll(segment_start,segment_end);
			    } else {
			    	new_segment.getChildren().clear();
			    	new_segment.getChildren().add(segment_name);
			    	
			    }
			});
		 
		 	new_segment.setOnMouseClicked(e->
		 	{
		 		new_segment.getChildren().clear();
		 		new_segment.getChildren().add(segment_delete);
		 	});
		 /*new_segment.onMouseClickedProperty().addListener((ov, oldValue, newValue) -> {
			    if (newValue) {
			    	new_segment.getChildren().removeAll();
			    	new_segment.getChildren().addAll(segment_start,segment_end);
			    } else {
			    	new_segment.getChildren().add(segment_name);
			    	//seg_name.setText("Not Hovered");
			    }
			});*/
		 
		 return new_segment;
	
	
	}
		
	public void draw_process(String process_name,ArrayList<segments> segmentList )
	{
		String color = randomColor();
		segmentList.forEach((segment)->
		{
			  Platform.runLater(() -> {
				    
				  	Pane g =draw_partition(process_name, segment.getSegmentName(), segment.getSegmentBase(), segment.getSegmentLimit(), color);
					System.out.println("process");
				  	System.out.println(segment.getSegmentBase());
					System.out.println(segment.getSegmentLimit());
					segment.setBox(g);	
					System.out.println(g.getHeight());
					System.out.println(g);
			    });
			
		
		});
		
		map.put(process_name, segmentList);
		
	}

	public void draw_holes (ArrayList<holes> holes_list)
	{
		index=0;
		ArrayList<segments> list =new ArrayList<segments>();
		
		
		for(holes h: holes_list)
		{
			if(index < h.getBase()) 
			{
				segments used =new segments("used", 0);
				used.setSegmentLimit(h.getBase()-index);
				used.setSegmentBase(index);
				list.add(used);
				
				
				System.out.println("base");
				System.out.println(used.getSegmentBase());
				System.out.println("limit" );
				System.out.println( used.getSegmentLimit());
			}
			index=h.getBase()+h.getLimit();
		}
		
		if(index != memory_size)
		{
			segments used =new segments("used", 0);
			used.setSegmentLimit(memory_size-index);
			used.setSegmentBase(index);	
			list.add(used);
			
			System.out.println("base");
			System.out.println(used.getSegmentBase());
			System.out.println("limit" );
			System.out.println(used.getSegmentLimit());
		}
	
		System.out.println("size");
		System.out.println(list.size());
		
		for(segments a : list) {
			System.out.println("list items before calling draw process");
			System.out.println(a.getSegmentBase());
		}
		draw_process("used", list);
		
		
	}
}

