package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
				id[j*width+i]=(short)(tlid+i+j*SHEETWIDTH);
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
				id[j*width+i]=(short)(tlid+i+j*SHEETWIDTH);
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
					sb.draw(LV1.getSprite(Entity.entities,id[j*width+i]),(x+i-camX)*SPRITEWIDTH+ox,(15-1-y-j+camY)*SPRITEWIDTH-oy);
			}
	}
	
	public int getAbsX() {int t = x*SPRITEWIDTH; return (xoffset)?t+SPRITEWIDTH/2 : t;}
	public int getAbsY() {int t = y*SPRITEWIDTH; return (yoffset)?t+SPRITEWIDTH/2 : t;}
	
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
