package com.ddr.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite {
	public static int SIZE=32;
	public static int SHEETWIDTH=27;
	public static Texture tiles = new Texture("tile32.png");
	public static Texture cities = new Texture("city32.png");
	
	public static TextureRegion getSprite(Texture s, int n){
		return getSprite(s,n%Sprite.SHEETWIDTH,n/Sprite.SHEETWIDTH);
	}
	
	public static TextureRegion getSprite(Texture s, int x, int y){
		return new TextureRegion(s, x*Sprite.SIZE, y*Sprite.SIZE, Sprite.SIZE, Sprite.SIZE);
	}
}
