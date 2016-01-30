package com.ddr.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.state.Play;

public abstract class Entity {
	
	protected final int SPRITEWIDTH = Play.SPRITEWIDTH;
	protected final int SHEETWIDTH = 30; //TODO FIX THIS NUMBER
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected short id[];
	public static Texture entities = new Texture("");
	
	public Entity(){}
	
	public int getX() {return x;}
	public int getY() {return y;}
	public int getAbsX() {return x*SPRITEWIDTH;}
	public int getAbsY() {return y*SPRITEWIDTH;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getAbsWidth() {return width*SPRITEWIDTH;}
	public int getAbsHeight() {return height*SPRITEWIDTH;}
	
	public abstract void draw(SpriteBatch sb);
	
}
