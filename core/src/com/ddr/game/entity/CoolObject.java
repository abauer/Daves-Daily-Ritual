package com.ddr.game.entity;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;
import com.ddr.game.state.Play;

public class CoolObject extends Obstacle{

	boolean first = true;
	boolean complete = false;
	int framecount = 200;
	String objectText;
	String pauseText;
	Music effect;
	long soundid;
	public CoolObject(int x, int y, int width, int height, String objectText,String pauseText,Music effect) {
		super(x, y, width, height, 0);
		type=2;
		this.objectText = objectText;
		this.pauseText = pauseText;
		id[0] = 0;
		this.effect = effect;
	}
	
	public void draw(SpriteBatch sb, int abscamx, int abscamy){
		super.draw(sb, abscamx, abscamy);
		if(!first&&!complete){
			//paper to draw on
			//sb.draw(Sprite.getSprite(Sprite.newsprite,57),(x)*Sprite.SIZE-abscamx,(15-1-y)*Sprite.SIZE+abscamy);
			
			if(Play.paused){ //first time interact, game gets paused
				Play.font9.draw(sb, pauseText, 640-5*32, 480-5);
			}
			else{// always occurs, after first interact
				Play.font9.draw(sb, objectText, 640-5*32, 480-5);
			}

			//progressbar gets drawn
			float progress = (200.0f-framecount)/200.0f;
			int frame = (int) (progress/.125);
//			System.out.println("frame: "+frame+" P: "+progress);
//			
			sb.draw(Sprite.getSprite(Sprite.newsprite,110+frame),0,(15-1)*Sprite.SIZE);
			
			Play.font9.draw(sb, framecount+" 200", 640-5*32, 480-25);
		}
	}
	
	public void interact(){
		if(first){ //first time interact
			first = false;
			Play.paused=true;
		}
		else if(!complete){//everytime they interact
			framecount--;
//			System.out.println(framecount+" more!");
//			if(soundid==0)
//				soundid = 
//			else{
//				effect.
//				effect.play(soundid);
//			}
			if(!effect.isPlaying())
				effect.play();
			if(framecount<=0)
				complete = true;
		}
	}
	
	public boolean getComplete(){
		return complete;
	}

}
