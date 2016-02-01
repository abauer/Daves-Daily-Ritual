package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class Obstacle extends Entity{
	
	protected boolean xoffset = false;
	protected boolean yoffset = false;
	
	public Obstacle(Obstacle e){
		this.x = e.x;
		this.y = e.y;
		this.width = e.width;
		this.height = e.height;
		this.id = e.id;
		this.xoffset = e.xoffset;
		this.yoffset = e.yoffset;
	}
	
	public Obstacle(int x, int y, int width, int height,int tlid){
		super();
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
				id[j*width+i]=(short)(tlid+i+j*Sprite.SHEETWIDTH);
	}
	
	public Obstacle(int x, int y, int width, int height,short[] tlid,boolean xoffset, boolean yoffset){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
		this.id = tlid;
	}
	
	public void draw(SpriteBatch sb,int abscamx, int abscamy) {
		int ox = 0;
		int oy = 0;
		if(xoffset) ox = 16;
		if(yoffset)	oy = 16;
		for(int i =0; i<width; i++)
			for(int j=0; j<height; j++){
					sb.draw(Sprite.getSprite(Sprite.newsprite,id[j*width+i]),(x+i)*Sprite.SIZE+ox-abscamx,(15-1-y-j)*Sprite.SIZE-oy+abscamy);
			}
	}
	
	public int getAbsX() {int t = x*Sprite.SIZE; return (xoffset)?t+Sprite.SIZE/2 : t;}
	public int getAbsY() {int t = y*Sprite.SIZE; return (yoffset)?t+Sprite.SIZE/2 : t;}
	
	public boolean entityContainsPoint(int absx, int absy){
//		System.out.println("check your stuff");
		return (absx>= getAbsX()&&
				absx< getAbsX()+getAbsWidth()&&
				absy>= getAbsY()&&
				absy< getAbsY()+getAbsHeight());
	}
	
	
	public boolean entityContainsRect(int absx, int absy, int abswidth, int absheight){
//		return (Math.abs((absx+abswidth/2)-(getAbsX()+getAbsWidth()/2)) < (abswidth+getAbsWidth())/2)
//				&& (Math.abs((absy+absheight/2)-(getAbsY()+getAbsHeight()/2)) < (absheight+getAbsHeight())/2);
		return (getAbsX() < absx + abswidth &&
	   getAbsX() + getAbsWidth() > absx &&
	   getAbsY() < absy + absheight &&
	   getAbsHeight() + getAbsY() > absy);
		
//		return (entityContainsPoint(absx,absy)||
//				entityContainsPoint(absx+abswidth,absy)||
//				entityContainsPoint(absx,absy+absheight)||
//				entityContainsPoint(absx+abswidth,absy+absheight));
	}
	
	public boolean entityContainsEntity(Entity e){
		return entityContainsRect(e.getAbsX(),e.getAbsY(),e.getAbsWidth(),e.getAbsHeight());
	}

}
