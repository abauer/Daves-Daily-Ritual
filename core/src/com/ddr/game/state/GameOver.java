package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ddr.game.handlers.GameStateManager;

public class GameOver extends GameState{
	
	private BitmapFont font = new BitmapFont();
	ShapeRenderer sr = new ShapeRenderer();
	
	public GameOver(GameStateManager gsm) {
		super(gsm);
	}

	public void handleInput(float dt) {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int x = Gdx.input.getX()/2;
			int y = Gdx.input.getY()/2;
			if(x<40&&y>225){
				gsm.setState(GameStateManager.MENU);
			}
		}
	}

	public void update(float dt) {
		handleInput(dt);
	}

	public void render() {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
		cam.setToOrtho(false,320,240);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
//		sb.setColor(Color.CLEAR);
//		sb.draw(DavesDailyRitual.getSprite("city.png",9,1), 1*16, 1*16+32);
		
//		sb.setColor(Color.BLUE);
		font.draw(sb,"You Lose",0,480/2-10);
		sb.end();
		
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.RED);
		sr.rect(0, 0, 40, 15);
		sr.end();
	}

	public void dispose() {
	}

}
