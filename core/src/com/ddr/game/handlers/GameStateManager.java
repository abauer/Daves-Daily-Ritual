package com.ddr.game.handlers;

import java.util.Stack;

import com.ddr.game.DavesDailyRitual;
import com.ddr.game.state.GameOver;
import com.ddr.game.state.GameState;
import com.ddr.game.state.LV1;
import com.ddr.game.state.Menu;
import com.ddr.game.state.Pause;

public class GameStateManager {

	public DavesDailyRitual game;
	private Stack<GameState> gameStates;
	public static final int LV1 = 912124;
	public static final int MENU = 314159;
	public static final int PAUSE = 12345;
	public static final int GOVER = 66666;
	
	public GameStateManager(DavesDailyRitual game){
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(LV1); //first screen
	}
	
	public DavesDailyRitual game() {return game;}
	
	public void handleInput(){
			
	}
	
	public void update(float dt){
		gameStates.peek().update(dt);
	}
	
	public void render(){
		gameStates.peek().render();
	}
	
	private GameState getState(int state){
		if(state == LV1) return new LV1(this);
		if(state == MENU) return new Menu(this);
		if(state == PAUSE) return new Pause(this);
		if(state == GOVER) return new GameOver(this);
		return null;
	}
	
	public void setState(int state){
		popState();
		pushState(state);
	}
	
	public void pushState(int state){
		gameStates.push(getState(state));
	}
	
	public void popState(){
		GameState g = gameStates.pop();
		g.dispose();
	}
}
