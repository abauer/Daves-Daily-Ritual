package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ddr.game.Level;
import com.ddr.game.handlers.GameStateManager;

public class LV1 extends GameState {
	
	private Level currentLevel = Level.ONE;
	private static Texture tiles = new Texture("tile.png");
	private int abscamX = 90*32;
	private int abscamY = 90*32;
	private int camX = 0;
	private int camY = 0;
	
	private int camxaccel = 0;
	private int camyaccel = 0;
	private int camxvel = 0;
	private int camyvel = 0;
	
	public LV1(GameStateManager gsm){
		super(gsm);
	}
	
	public void handleInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//			if(camX>=0&&abscamX>=2)
//				abscamX-=2;
			currentLevel.getPlayer().moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//        	if(camX<128-(20))
//				abscamX+=2;
        	currentLevel.getPlayer().moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//			if(camY>=0&&abscamY>=2)
//				abscamY-=2;
        	currentLevel.getPlayer().moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//        	if(camY<128-(15))
//				abscamY+=2;
        	currentLevel.getPlayer().moveDown();
        }
        camX = (int)(abscamX/32);
        camY = (int)(abscamY/32);
	}
	
	public void update(float dt){
		handleInput();
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		for(int i = 0; i <22; i++)
			for(int j = 0; j<17; j++){
				int id = currentLevel.getId(camX-1+i,camY-1+j);
				sb.draw(getSprite(tiles,id),(i-1)*SPRITEWIDTH-abscamX%SPRITEWIDTH,(15-j)*SPRITEWIDTH+abscamY%SPRITEWIDTH);
			}
		currentLevel.drawEntities(sb, camX, camY);
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
