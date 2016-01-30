package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;
import com.ddr.game.state.LV1;

public class Obstacle extends Entity{
	
	protected boolean xoffset = false;
	protected boolean yoffset = false;
	
	public Obstacle(int x, int y, int width, int height,int tlid){
		this.x = x;
		this.y = y;
		this.width = Math.max(1,width);
		this.height = Math.max(1, height);
		if(width*height>=1)
			id = new short[width*height];
		else
			id = new short[1];
		for(int i =0; i<width; i++)
			for(int j = 0; j<height; j++)
				id[j*width+i]=(short)(tlid+i+j*Sprite.SIZE);
	}
	
	public Obstacle(int x, int y, int width, int height,int tlid,boolean xoffset, boolean yoffset){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		if(width*height>=1)
			id = new short[width*height];
		else
			id = new short[1];
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		for(int i =0; i<width; i++)
			for(int j = 0; j<height; j++)
				id[j*width+i]=(short)(tlid+i+j*Sprite.SIZE);
	}
	
	public void draw(SpriteBatch sb,int camX, int camY) {
		int ox = 0;
		int oy = 0;
		if(xoffset)
			ox = 16;
		if(yoffset)
			oy = 16;
		for(int i =0; i<width; i++)
			for(int j=0; j<height; j++){
					sb.draw(Sprite.getSprite(Entity.entities,id[j*width+i]),(x+i-camX)*Sprite.SIZE+ox,(15-1-y-j+camY)*Sprite.SIZE-oy);
			}
	}
	
	public int getAbsX() {int t = x*Sprite.SIZE; return (xoffset)?t+Sprite.SIZE/2 : t;}
	public int getAbsY() {int t = y*Sprite.SIZE; return (yoffset)?t+Sprite.SIZE/2 : t;}
	
	public boolean contains(int absx, int absy){
		if(absx>=getAbsX() && absx< getAbsX()+getAbsWidth())
			if(absy>=getAbsY() && absy< getAbsY()+getAbsHeight())
				return true;
		return false;
	}
	
	public boolean contains(int absx, int absy, int abswidth, int absheight){
		return (contains(absx,absy)||contains(absx+abswidth,absy)||
				contains(absx,absy+absheight)||contains(absx+abswidth,absy+absheight));
	}
	
	public boolean contains(Entity e){
		return contains(e.getAbsX(),e.getAbsY(),e.getAbsWidth(),e.getAbsHeight());
	}

}
