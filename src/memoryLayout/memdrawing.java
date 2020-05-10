package memoryLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.jfoenix.controls.JFXButton;

import memoryDs.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mainScene.mainSceneController;


public class memdrawing 
{
	public AnchorPane anchorpane;
	public float memory_size;
	Map<String,ArrayList<segment>> map; 
	float index;
	private operate oper;
	private mainSceneController controller;
	public void setOper(operate oper) {
		this.oper = oper;
	}
	public operate getOper() {
		return oper;
	}
public memdrawing(AnchorPane _achorpane , float _memory_size,operate _oper,mainSceneController _controller) 
	{
		anchorpane=_achorpane;
		memory_size=_memory_size;
		oper = _oper;
		controller =_controller;
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
	
	public Pane draw_partition (String Process_name,String _segmentname,float base,float limit,String color)
	{
		 VBox new_segment =new VBox();
		 
		 //hbox for label of start index
		 HBox segment_start =new HBox();
		 segment_start.getChildren().add(new Label(Float.toString(base)));;
		 segment_start.setAlignment(Pos.TOP_LEFT);
		
		 //hbox for label of segment name
		 HBox segment_name =new HBox();
		 Label p_name =new Label(Process_name);
		 Label seg_name = new Label(_segmentname); 
		 segment_name.getChildren().addAll(p_name,seg_name);
		 segment_name.setAlignment(Pos.CENTER);
		 
		 //hbox for label of end index 
		 float end = base+limit-1;
		 HBox segment_end =new HBox();
		 segment_end.getChildren().add(new Label(Float.toString(end)));
		 segment_end.setStyle("-fx-text-fill: #ffffff;");
		 segment_end.setAlignment(Pos.BOTTOM_LEFT);
		
		 //layout and positioning
		 new_segment.getChildren().add(segment_name);
		 new_segment.setLayoutX(0);
		 new_segment.setLayoutY(base*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefHeight(limit*anchorpane.getHeight()/memory_size);
		 new_segment.setPrefWidth(anchorpane.getWidth());
		 System.out.println(anchorpane.getWidth());
		
		 //style of the partition	 
		 new_segment.setStyle( "-fx-border-style: solid;"
		                     + "-fx-border-width: 2;"
		                     + "-fx-border-radius: 5;"
		                     + "-fx-border-color:"+ color +";");
		
		 //adding new box to memory layout
		 anchorpane.getChildren().add(new_segment);
		 System.out.println(randomColor());
		 
		 ////clear button
		 JFXButton clear = new JFXButton("clear");
		 HBox segment_delete =new HBox();
		 segment_delete.getChildren().add(clear);
		 segment_delete.setAlignment(Pos.CENTER);
		 clear.setOnAction(e->
		 {
			deallocate_memorylayout(new_segment);   
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
		  
		 //click
		 	new_segment.setOnMouseClicked(e->
		 	{
		 		new_segment.getChildren().clear();
		 		new_segment.getChildren().add(segment_delete);
		 	});
		 
		 return new_segment;
	}
		
	public void draw_process(String process_name,ArrayList<segment> segmentList )
	{
		ArrayList<segment> new_list = new ArrayList<segment>();
		new_list.addAll(segmentList);
		
		String color = randomColor();
		System.out.println("process before drawing"+ process_name);
		System.out.println("process_size "+new_list.size() );
		for(segment s : new_list) {
			Pane g =draw_partition(process_name, s.getSegmentName(), s.getSegmentBase(), s.getSegmentLimit(), color);
			System.out.println("process");
		  	System.out.println(s.getSegmentBase());
			System.out.println(s.getSegmentLimit());
			s.setBox(g);	
			System.out.println(g.getHeight());
			System.out.println(g);
		}
		
		System.out.println("process after drawing"+ process_name);
		System.out.println("process_size "+new_list.size() );
		System.out.println(new_list);
		map.put(process_name, new_list);
		
	}

	public void draw_holes (ArrayList<holes> holes_list)
	{
		index=0;
		ArrayList<segment> list =new ArrayList<segment>();
		
		Collections.sort(holes_list, new sortByBase());
		for(holes h: holes_list)
		{
			if(index < h.getBase()) 
			{
				segment used =new segment("", 0);
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
			segment used =new segment("", 0);
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
		
		for(segment a : list) {
			System.out.println("list items before calling draw process");
			System.out.println(a.getSegmentBase());
		}
		draw_process("USED", list);
		
		
	}

	public void deallocate_memorylayout(Pane clicked_box) 
	{
		Set set = map.entrySet();
		 Iterator itr = set.iterator();
		 boolean flag =false;
		 segment seg = null ;
		 while(itr.hasNext()) 
		 {
			Map.Entry entry=(Map.Entry) itr.next();
			ArrayList<segment> f_list = (ArrayList<segment>) entry.getValue();
			System.out.println("size");
			System.out.println(f_list.size());
			System.out.println("name");
			System.out.println(entry.getKey());
			
			for(segment s : f_list) 
			{
				System.out.println("current segment:"+clicked_box+"tested segment"+s.getBox());
				if(clicked_box==s.getBox())
					{flag=true;
					seg=s;	
					break;}
			}
		 
			if(flag) 
			{
				flag=false;
				//oper.deallocate();
				System.out.println("list size");
				System.out.println(f_list.size());
				if(entry.getKey()=="used") {
					anchorpane.getChildren().remove(clicked_box);
					f_list.remove(seg);
					
				ArrayList<segment> temp = new ArrayList<segment>();
				temp.add(seg);
				oper.DeallocateProcess(temp);
					
				}
				else {
					for(segment s : f_list) 
				{
					System.out.println("segment");
					System.out.println(s.getBox());
					anchorpane.getChildren().remove(s.getBox());
					
				}
					oper.DeallocateProcess(f_list );
					
					controller.deleteTab((String)entry.getKey());
				
					map.remove(entry.getKey(), entry.getValue());
				}
				
				break;
				
			}
		 }
	}

}

