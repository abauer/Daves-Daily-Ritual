package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ddr.game.DavesDailyRitual;
import com.ddr.game.Level;
import com.ddr.game.handlers.GameStateManager;

public class Play extends GameState {
	
	private Level currentLevel = Level.ONE;
	private Texture tiles;
	private int abscamX = 0;
	private int abscamY = 0;
	private int camX = 0;
	private int camY = 0;
	
	
	public Play(GameStateManager gsm){
		super(gsm);
		tiles = new Texture("tile.png");
	}
	
	public void handleInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if(camX-1>=0)
				camX--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	if(camX+1<108)
				camX++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			if(camY-1>=0)
				camY--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	if(camY+1<113)
				camY++;
        }
	}
	
	public void update(float dt){
		handleInput();
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		for(int i = 0; i <22; i++)
			for(int j = 0; j<17; j++){
				int id = currentLevel.getId(camX-2+i,camY-2+j);
				sb.draw(getSprite(tiles,id),(i-1)*SPRITEWIDTH,(16-j)*SPRITEWIDTH);
			}
		sb.end();
	}
	
	public static TextureRegion getSprite(Texture s, int x, int y){
		return new TextureRegion(s, x*SPRITEWIDTH, y*SPRITEWIDTH, SPRITEWIDTH, SPRITEWIDTH);
	}
	
	public static int SPRITEWIDTH=32;
	public static TextureRegion getSprite(Texture s, int n){
		return getSprite(s,n%SPRITEWIDTH,n/SPRITEWIDTH);
	}
	
	public void dispose(){}
	
}
