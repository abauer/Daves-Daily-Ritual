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
		for(int i =0; i<id.length; i++)
			id[i] = 0;
		this.effect = effect;
	}
	
	public void draw(SpriteBatch sb, int abscamx, int abscamy){
		super.draw(sb, abscamx, abscamy);
		//paper to draw on
//		sb.draw(Sprite.getSprite(Sprite.newsprite,129), 640-(0)*Sprite.SIZE, 480-(1)*Sprite.SIZE+3, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 180);
//		sb.draw(Sprite.getSprite(Sprite.newsprite,129), 640-(1)*Sprite.SIZE, 480-(1)*Sprite.SIZE+3, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 180);
		if(!first&&!complete){
			sb.draw(Sprite.getSprite(Sprite.newsprite,128), Sprite.SIZE, 480-(0)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 180);
			float progress = (200.0f-framecount)/200.0f;
			int frame = (int) (progress/.125);
			sb.draw(Sprite.getSprite(Sprite.newsprite,110+frame),0,(15-1)*Sprite.SIZE);
		}
	}
	
	public void interact(){
		if(first){ //first time interact
			first = false;
//			Play.paused=true;
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
			if(framecount<=0){
				actionCompleted();
			}
		}
	}
	
	protected void actionCompleted(){
		complete = true;
	}
	
	public boolean getComplete(){
		return complete;
	}
	
	public float getClosestDistance(int tx, int ty){
		float dx = x*Sprite.SIZE-tx;
		float dy = y*Sprite.SIZE-ty;
		float dis = (float) Math.sqrt(dx*dx+dy*dy);
		
		for(int i = 0; i<width;i++){
			for(int j = 0; j<height; j++){
				dx=(x+i)*Sprite.SIZE-tx;//top left
				dy=(y+j)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i)*Sprite.SIZE+Sprite.SIZE/2-tx;//top center
				dy=(y+j)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i+1)*Sprite.SIZE-tx;//top right
				dy=(y+j)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i+1)*Sprite.SIZE-tx;//right center
				dy=(y+j)*Sprite.SIZE+Sprite.SIZE/2-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i+1)*Sprite.SIZE-tx;//bottom right
				dy=(y+j+1)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i)*Sprite.SIZE+Sprite.SIZE/2-tx;//bottom center
				dy=(y+j+1)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i)*Sprite.SIZE-tx;//bottom left
				dy=(y+j+1)*Sprite.SIZE-ty;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
				
				dx=(x+i)*Sprite.SIZE-tx;//left center
				dy=(y+j)*Sprite.SIZE-ty+Sprite.SIZE/2;
				dis = (dis<Math.sqrt(dx*dx+dy*dy)) ? dis: (float)Math.sqrt(dx*dx+dy*dy);
			}
		}	
		return dis;
	}
	
	public String getText(){
		if(!first&&!complete)
			return pauseText;
		else
			return objectText;
	}

}
