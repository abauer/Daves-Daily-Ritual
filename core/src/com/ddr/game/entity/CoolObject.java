package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.state.Play;

public class CoolObject extends Obstacle{

	boolean first = true;
	boolean complete = false;
	int framecount = 200;
	String objectText;
	String pauseText;
	
	public CoolObject(int x, int y, int width, int height, String objectText,String pauseText) {
		super(x, y, width, height, 0);
		type=2;
		this.objectText = objectText;
		this.pauseText = pauseText;
		id[0] = 1;
	}
	
	public void draw(SpriteBatch sb, int abscamx, int abscamy){
		super.draw(sb, abscamx, abscamy);
		if(!first&&!complete){
			//draw paper
			
			if(Play.paused){
				Play.font9.draw(sb, pauseText, 640-5*32, 480-5);
			}
			else{
				Play.font9.draw(sb, objectText, 640-5*32, 480-5);
			}
			Play.font9.draw(sb, framecount+" 200", 640-5*32, 480-25);
		}
	}
	
	public void interact(){
		if(first){
			first = false;
			Play.paused=true;
		}
		else if(!complete){
			framecount--;
//			System.out.println(framecount+" more!");
			if(framecount<=0)
				complete = true;
		}
	}

}
