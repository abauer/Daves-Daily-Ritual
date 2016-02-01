package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ddr.game.handlers.GameStateManager;

public class Pause extends GameState{
	
	public Pause(GameStateManager gsm) {
		super(gsm);
	}

	public void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gsm.popState();
			return;
        }
	}

	public void update(float dt) {
		handleInput(dt);
	}

	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(new Texture("pause.png"),0,0);
		sb.end();
	}
	public void dispose() {
	}

}
