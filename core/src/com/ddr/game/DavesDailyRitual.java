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
	
	public static int WIDTH = 640;
	public static int HEIGHT = 480;
	public static int SCALE = 2;
	public static String TITLE = "Daves Daily Ritual";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("City.png");
		
	}
	
	
	
	private TextureRegion getSprite(String s, int x, int y){
		return new TextureRegion(img, x*17, y*17, x*17+16, y*17+16);
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(getSprite("City.png",0,0), 0, 0);
		batch.end();
	}
}
