package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.state.LV1;

public class Player extends Obstacle {
	
	private int xvel = 1;
	private int yvel = 1;
	private int absx = 0;
	private int absy = 0;
	
	
	public Player(int x, int y, int width, int height, int tlid) {
		super(x, y, width, height, tlid);
		absx = x*SPRITEWIDTH;
		absy = y*SPRITEWIDTH;
		id[0]=(short) tlid;
	}
	
	public Player(int x, int y, int width, int height, int tlid, boolean xoffset, boolean yoffset){
		super(x, y, width, height, tlid);
		absx = x*SPRITEWIDTH;
		absy = y*SPRITEWIDTH;
		id[0]=(short) tlid;
	}
	
	public void draw(SpriteBatch sb,int camX,int camY){//this might be 1,1 off because abs are diff than tile
		sb.draw(LV1.getSprite(Entity.entities,id[0]),absx-camX*SPRITEWIDTH,(15+camY)*SPRITEWIDTH-absy);
	}
	
	public int moveLeft(){ 
		x=(absx-1)/SPRITEWIDTH; 
		return absx-=xvel;}
	
	public int moveRight(){
		x=(absx+1)/SPRITEWIDTH; 
		return absx+=xvel;}
	
	public int moveUp(){
		y=(absy-1)/SPRITEWIDTH; 
		return absy-=yvel;}
	
	public int moveDown(){
		y=(absy+1)/SPRITEWIDTH; 
		return absy+=yvel;}
	
	public int getAbsX() {return absx;}
	public int getAbsY() {return absy;}
	
}
