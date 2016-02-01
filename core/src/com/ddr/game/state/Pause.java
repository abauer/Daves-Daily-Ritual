package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.ddr.game.Sprite;
import com.ddr.game.handlers.GameStateManager;

public class Pause extends GameState{
	
	int select = 0;
	int x,y;
	
	public Pause(GameStateManager gsm) {
		super(gsm);
		x=y=200;
	}

	public void handleInput(float dt) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gsm.popState();
			return;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			if(select==0){
				gsm.popState();
				return;
			}
			if(select==1){
				gsm.popState();
				gsm.popState();
				System.exit(0);
			}
        }
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
			select--;
//			y++;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
			select++;
//			y--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
//			x--;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
//			x++;
		}
		if(select<0) select = 0;
		else select%=2;
//		System.out.println("["+x+", "+y+"]");
	}

	public void update(float dt) {
		handleInput(dt);
	}

	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		sb.draw(new Texture("pause.png"),0,0);
		sb.draw(Sprite.getSprite(Sprite.newsprite,128), Sprite.SIZE, 480-(0)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 180);
		sb.draw(Sprite.getSprite(Sprite.newsprite,121),0,(15-1)*Sprite.SIZE);
		switch(select){
			case 0: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 196,186); break;
			case 1: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 199,167); break;
		}
		sb.end();
	}
	public void dispose() {
	}

}
