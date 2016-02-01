package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ddr.game.Sprite;
import com.ddr.game.handlers.GameStateManager;

public class Menu extends GameState {

	int select = 0;
	int x=0;
	int y=0;
		
	public Menu(GameStateManager gsm){
		super(gsm);
		cam.setToOrtho(false, 640, 480);
		cam.update();
		x=200;
		y=200;
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			switch(select){
				case 0: gsm.popState();
						gsm.pushState(GameStateManager.LV1); break;
				case 1: gsm.pushState(GameStateManager.OPTIONS); break;
				case 2: System.exit(0); break;		
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			select--;
//			y++;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			select++;
//			y--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
//			select++;
//			x--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
//			select++;
//			x++;
		}
		if(select<0) select = 0;
		else select%=3;
//		System.out.println("["+x+", "+y+"]");
	}
	
	public void update(float dt){
		handleInput(dt);
	}
	
	public void render(){
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(new Texture("MainMenu.png"),0,0);
		switch(select){
			case 0: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 195,201); break;
			case 1: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 197,184); break;
			case 2: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 199,167); break;
		}
		sb.end();
	}
	
	public void dispose(){}
	
}
