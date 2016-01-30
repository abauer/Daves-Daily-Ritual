package com.ddr.game.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.DavesDailyRitual;
import com.ddr.game.handlers.GameStateManager;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected DavesDailyRitual game;
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	
	protected GameState(GameStateManager gsm){
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
	}
	
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();

}
