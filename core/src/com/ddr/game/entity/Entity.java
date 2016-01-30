package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected short id[];
	
	public Entity(){}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getAbsX() {return x*Sprite.SIZE;}
	public int getAbsY() {return y*Sprite.SIZE;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getAbsWidth() {return width*Sprite.SIZE;}
	public int getAbsHeight() {return height*Sprite.SIZE;}
	
	public abstract void draw(SpriteBatch sb,int camX,int camY);
	public abstract boolean contains(int absx, int absy);
	public abstract boolean contains(int absx, int absy, int abswidth, int absheight);
	public abstract boolean contains(Entity e);
}
