package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.ddr.game.handlers.GameStateManager;

public class Menu extends GameState {
	
	int x,y,width,height;
	
	public Menu(GameStateManager gsm){
		super(gsm);
		cam.setToOrtho(false, 640, 480);
		cam.update();
//		x = ;
//		y = ;
//		width = ;
//		height = ;
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			if((x > 210) && (x < 260) && (480-y > 210) && (480-y < 225)){
				gsm.popState();
				gsm.pushState(GameStateManager.LV1);
				
			if ((x>220) && (x < 270) && (480 - y >180) && (480-y <195)) {
				System.exit(0);			
				}
				
			
			
			}
			
		}
	}
	

	
	public void update(float dt){
		handleInput(dt);
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		sb.draw(new Texture("MainMenu.png"),0,0);
		
		sb.end();
//		cam.update();
//		sr.setProjectionMatrix(cam.combined);
//		sr.begin(ShapeType.Line);
//		
//		sr.setColor(1, 1, 0, 1);
//		sr.rect(210, 210, 50, 15);
//		sr.rect(220, 195, 50, 15);
//		sr.rect(220, 180, 50, 15);
//		
//		sr.end();	
	}
	
	public void dispose(){}
	
}
