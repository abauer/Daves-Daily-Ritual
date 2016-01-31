package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class Player extends Obstacle {
	
	protected int xvel = 2;
	protected int yvel = 2;
	protected int absx = 0;
	protected int absy = 0;
	protected int maxframes = 1;
	protected int framecount = 0;
	
	public Player(Player p){
		super(p);
		this.xvel = p.xvel;
		this.yvel = p.yvel;
		this.absx = p.absx;
		this.absy = p.absy;
		this.maxframes = p.maxframes;
		this.framecount = p.framecount;
	}
	
	public Player(int x, int y, short[] tlid) {
		super(x, y, 1, 1, tlid[0]);
		absx = x*Sprite.SIZE;
		absy = y*Sprite.SIZE;
		id=tlid;
		this.maxframes = Math.max(1,tlid.length);
	}
	
	public Player(int x, int y, short[] tlid,boolean xoffset, boolean yoffset){
		super(x, y, 1, 1, tlid[0]);
		absx = x*Sprite.SIZE;
		absy = y*Sprite.SIZE;
		id=tlid;
		this.maxframes = Math.max(1,tlid.length);
	}
	
	public void draw(SpriteBatch sb,int abscamx,int abscamy){
		int tx = absx-abscamx;
		int ty = (14)*Sprite.SIZE-absy+abscamy;
		sb.draw(Sprite.getSprite(Sprite.cities,id[framecount%maxframes]),tx,ty);
	}
	
	public int moveLeft(){
		x=(absx-xvel)/Sprite.SIZE;
		framecount = 1;
		return absx-=xvel;}
	
	public int moveRight(){
		x=(absx+xvel)/Sprite.SIZE;
		framecount = 2;
		return absx+=xvel;}
	
	public int moveUp(){
		y=(absy-yvel)/Sprite.SIZE;
		framecount = 3;
		return absy-=yvel;}
	
	public int moveDown(){
		y=(absy+yvel)/Sprite.SIZE;
		framecount = 0;
		return absy+=yvel;}
	
	public int getAbsX() {return absx;}
	public int getAbsY() {return absy;}
	public int getXvel() {return xvel;}
	public int getYvel() {return yvel;}
}
