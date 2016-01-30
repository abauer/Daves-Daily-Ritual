package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.state.Play;

public class Obstacle extends Entity{
	
	protected boolean xoffset = false;
	protected boolean yoffset = false;
	
	public Obstacle(int x, int y, int width, int height,int tlid){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		id = new short[width*height];
		for(int i =0; i<width; i++)
			for(int j = 0; j<height; j++)
				id[j*width+i]=(short)(tlid+i+j*SHEETWIDTH);
	}
	
	public Obstacle(int x, int y, int width, int height,int tlid,boolean xoffset, boolean yoffset){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		id = new short[width*height];
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		for(int i =0; i<width; i++)
			for(int j = 0; j<height; j++)
				id[j*width+i]=(short)(tlid+i+j*SHEETWIDTH);
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		int ox = 0;
		int oy = 0;
		if(xoffset)
			ox = 16;
		if(yoffset)
			oy = 16;
		
		for(int i =0; i<width; i++)
			for(int j=0; j<height; j++){
					sb.draw(Play.getSprite(Entity.entities,id[j*width+i]),x*SPRITEWIDTH+ox,(15-y)*SPRITEWIDTH-oy);
			}
	}
	
	public boolean contains(int absx, int absy){
		if(absx>this.x*SPRITEWIDTH && absx< this.x*SPRITEWIDTH+width*SPRITEWIDTH)
			if(absy>this.y*SPRITEWIDTH && absx< this.y*SPRITEWIDTH+height*SPRITEWIDTH)
				return true;
		return false;
	}
	
	public boolean contains(int absx, int absy, int abswidth, int absheight){
		return (contains(absx,absy)||contains(absx+width,absy)||
				contains(absx,absy+height)||contains(absx+width,absy+height));
	}
	
	public boolean contains(Entity e){
		return contains(e.getAbsX(),e.getAbsY(),e.getAbsWidth(),e.getAbsHeight());
	}

}
