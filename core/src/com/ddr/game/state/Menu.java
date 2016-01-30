package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.*;
import com.ddr.game.handlers.GameStateManager;

public class Menu extends GameState {
	
	private BitmapFont font = new BitmapFont();
	ShapeRenderer sr = new ShapeRenderer();
	
	public Menu(GameStateManager gsm){
		super(gsm);
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int x = Gdx.input.getX()/2;
			int y = Gdx.input.getY()/2;
			
			if(x>320-40&&y<15){
				gsm.popState();
				gsm.pushState(GameStateManager.LV1);
			}
			
		}
	}
	
	public void update(float dt){
		handleInput(dt);
	}
	
	public void render(){
//		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
//		sb.setColor(Color.CLEAR);
//		sb.draw(DavesDailyRitual.getSprite("city.png",9,1), 1*16, 1*16+32);
		
//		sb.setColor(Color.BLUE);
		
		
		font.draw(sb,"Play",640/2-40,480/2-15);
		font.draw(sb, "Highscore",640/4,480/4+25);
		font.draw(sb,"Options",640/4,480/4-25);
		font.draw(sb, "Exit",640/4,480/4-100);
		
		sb.end();
		
		cam.update();
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(1, 1, 0, 1);
		sr.rect(280, 225, 40, 15);
		sr.end();	
	}
	
	public void dispose(){}
	
}
