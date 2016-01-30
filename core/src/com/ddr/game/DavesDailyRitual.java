package com.ddr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DavesDailyRitual extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;
	
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 4;
	public static String TITLE = "Daves Daily Ritual";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("city.png");
		
	}
	
	private TextureRegion getSprite(String s, int x, int y){
		return new TextureRegion(img, x*17, y*17, 16, 16);
	}
	
	public static int SPRITEWIDTH;
	private TextureRegion getSprite(String s, int n){
		return getSprite(s,n%SPRITEWIDTH,n/SPRITEWIDTH);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(int i = 0; i<37; i++)
			for(int j = 0; j<28; j++)
				batch.draw(getSprite("city.png",i,j), i*16, j*16);
		batch.end();
	}
}
