package com.ddr.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.state.LV1;

public abstract class Entity {
	
	protected final int SPRITEWIDTH = LV1.SPRITEWIDTH;
	protected final int SHEETWIDTH = 30; //TODO FIX THIS NUMBER
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected short id[];
	public static Texture entities = new Texture("city32.png");
	
	public Entity(){}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getAbsX() {return x*SPRITEWIDTH;}
	public int getAbsY() {return y*SPRITEWIDTH;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getAbsWidth() {return width*SPRITEWIDTH;}
	public int getAbsHeight() {return height*SPRITEWIDTH;}
	
	public abstract void draw(SpriteBatch sb,int camX,int camY);
	public abstract boolean contains(int absx, int absy);
	public abstract boolean contains(int absx, int absy, int abswidth, int absheight);
	public abstract boolean contains(Entity e);
}
