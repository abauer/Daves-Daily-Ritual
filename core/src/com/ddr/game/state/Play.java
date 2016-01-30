package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.ddr.game.DavesDailyRitual;
import com.ddr.game.handlers.GameStateManager;

public class Play extends GameState {
	
	public Play(GameStateManager gsm){
		super(gsm);
	}
	
	public void handleInput(){
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
	            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){}
//	                sprite.translateX(-1f);
//	            else
//	                sprite.translateX(-10.0f);
	        }
	        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
	            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){}
//	                sprite.translateX(1f);
//	            else
//	                sprite.translateX(10.0f);
	        }
	}
	
	public void update(float dt){}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(DavesDailyRitual.getSprite("city.png",1,1), 1*16, 1*16+32);
		sb.end();
		
	}
	
	public void dispose(){}
	
}
