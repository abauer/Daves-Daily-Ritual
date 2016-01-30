package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class Player extends Obstacle {
	
	private int xvel = 1;
	private int yvel = 1;
	private int absx = 0;
	private int absy = 0;
	
	
	public Player(int x, int y, int width, int height, int tlid) {
		super(x, y, width, height, tlid);
		absx = x*Sprite.SIZE;
		absy = y*Sprite.SIZE;
		id[0]=(short) tlid;
	}
	
	public Player(int x, int y, int width, int height, int tlid, boolean xoffset, boolean yoffset){
		super(x, y, width, height, tlid);
		absx = x*Sprite.SIZE;
		absy = y*Sprite.SIZE;
		id[0]=(short) tlid;
	}
	
	public void draw(SpriteBatch sb,int abscamx,int abscamy){
		int tx = absx-abscamx;
		int ty = (14)*Sprite.SIZE-absy+abscamy;
		sb.draw(Sprite.getSprite(Sprite.cities,id[0]),tx,ty);
	}
	
	public int moveLeft(int dis){
		x=(absx-dis)/Sprite.SIZE; 
		return absx-=xvel;}
	
	public int moveRight(int dis){
		x=(absx+dis)/Sprite.SIZE; 
		return absx+=xvel;}
	
	public int moveUp(int dis){
		y=(absy-dis)/Sprite.SIZE; 
		return absy-=yvel;}
	
	public int moveDown(int dis){
		y=(absy+dis)/Sprite.SIZE; 
		return absy+=yvel;}
	
	public int getAbsX() {return absx;}
	public int getAbsY() {return absy;}
	
}
