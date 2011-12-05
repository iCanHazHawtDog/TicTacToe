package edu.luc.tictactoe.networking;

/***
 * 
 * @author Paul Stasiuk
 * 
 * This is a class that takes in either a single coordinate, then transforms it to an x,y pair.
 *
 */

public class PositionInterpretor {
	public int x=0;
	public int y=0;
	
	
	
	public PositionInterpretor(int position){
		if(position==0){
			x=0;
			y=0;
		}if(position==1){
			x=0;
			y=1;
			
		}if(position==2){
			x=0;
			y=2;
			
		}if(position==3){
			x=1;
			y=0;
			
		}if(position==4){
			x=1;
			y=1;
			
		}if(position==5){
			x=1;
			y=2;
			
		}if(position==6){
			x=2;
			y=0;
			
		}if(position==7){
			x=2;
			y=1;
			
		}if(position==8){
			x=2;
			y=2;
			
		}
		
		
	}
	

}
