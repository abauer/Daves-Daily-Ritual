package com.ddr.game.entity;

public class CoolObject extends Obstacle{

	boolean first = true;
	int framecount = 200;
	
	public CoolObject(int x, int y, int width, int height, int tlid) {
		super(x, y, width, height, tlid);
		type=2;
	}
	
	public void interact(){
		if(first){
			System.out.println("Brush your teeth");
			first = false;
		}
		else{
			framecount--;
			System.out.println(framecount+" more!");
		}
	}

}
