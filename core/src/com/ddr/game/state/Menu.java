package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ddr.game.DavesDailyRitual;
import com.ddr.game.handlers.GameStateManager;

public class Menu extends GameState {
	
	public Menu(GameStateManager gsm){
		super(gsm);
	}
	
	public void handleInput(){}
	
	public void update(float dt){}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(DavesDailyRitual.getSprite("city.png",9,1), 1*16, 1*16+32);
		sb.end();
		
	}
	
	public void dispose(){}
	
}
