package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;
import com.ddr.game.state.LV1;

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
	
	public void draw(SpriteBatch sb,int camX,int camY){
		sb.draw(Sprite.getSprite(Entity.entities,id[0]),absx-(camX)*Sprite.SIZE,(15+camY-1)*Sprite.SIZE-absy);
	}
	
	public int moveLeft(){
		x=(absx-1)/Sprite.SIZE; 
		return absx-=xvel;}
	
	public int moveRight(){
		x=(absx+1)/Sprite.SIZE; 
		return absx+=xvel;}
	
	public int moveUp(){
		y=(absy-1)/Sprite.SIZE; 
		return absy-=yvel;}
	
	public int moveDown(){
		y=(absy+1)/Sprite.SIZE; 
		return absy+=yvel;}
	
	public int getAbsX() {return absx;}
	public int getAbsY() {return absy;}
	
}
