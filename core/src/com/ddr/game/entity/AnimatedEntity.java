package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class AnimatedEntity extends Obstacle{
	
	int framecount;
	int maxframes;
	boolean animate = false;
	
	public AnimatedEntity(AnimatedEntity e){
		super(e);
		this.framecount = e.framecount;
		this.maxframes = e.maxframes;
		this.animate = e.animate;
	}
	
	public AnimatedEntity(int x, int y,short[] tlid,int frames){
		super(x,y,1,1,tlid[0]);
		framecount=0;
		maxframes = frames;
		id=tlid;
	}
	public AnimatedEntity(int x, int y,short[] tlid,int frames,boolean xoffset, boolean yoffset){
		super(x,y,1,1,tlid[0],xoffset,yoffset);
		framecount=0;
		maxframes = frames;
		id=tlid;
	}
	
	
	public void draw(SpriteBatch sb, int abscamx, int abscamy) {
		if(animate)
			framecount= (framecount+1)%maxframes;
		int ox = 0;
		int oy = 0;
		if(xoffset)
			ox = 16;
		if(yoffset)
			oy = 16;
		sb.draw(Sprite.getSprite(Sprite.cities,id[framecount]),(x)*Sprite.SIZE+ox+abscamx,(15-1-y)*Sprite.SIZE-oy-abscamy);
	}
	
	public void start(){
		animate = true;
	}
	
	public void stop(){
		animate = false;
	}
//
//	@Override
//	public boolean contains(int absx, int absy) {
//		return false;
//	}
//
//	@Override
//	public boolean contains(int absx, int absy, int abswidth, int absheight) {
//		return false;
//	}
//
//	@Override
//	public boolean contains(Entity e) {
//		return false;
//	}

}
