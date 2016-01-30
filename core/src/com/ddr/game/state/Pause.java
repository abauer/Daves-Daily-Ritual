package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.ddr.game.handlers.GameStateManager;

public class Pause extends GameState{
	
	private BitmapFont font = new BitmapFont();
	ShapeRenderer sr = new ShapeRenderer();
	
	public Pause(GameStateManager gsm) {
		super(gsm);
	}

	public void handleInput() {
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			int x = Gdx.input.getX()/2;
			int y = Gdx.input.getY()/2;
			if(x<40&&y<15){
				gsm.popState();
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gsm.popState();
			return;
        }
	}

	public void update(float dt) {
		handleInput();
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
		font.draw(sb,"Paused",0,480/2-10);
		sb.end();
		
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		sr.rect(0, 225, 40, 15);
		sr.end();
	}

	public void dispose() {
	}

}