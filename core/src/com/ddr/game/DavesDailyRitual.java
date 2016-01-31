package com.ddr.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ddr.game.handlers.GameStateManager;

public class DavesDailyRitual extends ApplicationAdapter {
	SpriteBatch sb;
	TextureRegion region;
	OrthographicCamera cam;
	
	public static int WIDTH = 640*2;
	public static int HEIGHT = 480*2;
	public static int SCALE = 1;
	public static String TITLE = "Daves Daily Ritual";
	
	public static final float STEP = 1/60f;
	private float accum;
	private int count =0;
	public static GameStateManager gsm;
	
	
	public OrthographicCamera getCamera(){return cam;}
	public SpriteBatch getSpriteBatch() {return sb;}
	
	@Override
	public void create () {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 240);
		sb = new SpriteBatch();
		gsm = new GameStateManager(this);
	}
	
	
	
	@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while(accum>=STEP){
			accum-= STEP;
			count++;
			gsm.update(STEP);
			gsm.render();
		}
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		sb.begin();
//		for(int i = 0; i<37; i++)
//			for(int j = 0; j<28; j++)
//				sb.draw(getSprite("city.png",i,j), i*16, j*16);
//		sb.end();
	}
}
