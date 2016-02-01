package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ddr.game.Sprite;
import com.ddr.game.handlers.GameStateManager;

public class Menu extends GameState {

	int select = 0;
	BitmapFont font9;
	public Menu(GameStateManager gsm){
		super(gsm);
		cam.setToOrtho(false, 640, 480);
		cam.update();
		FileHandle f = Gdx.files.internal("postnote.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(f);
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 20;
		font9 = generator.generateFont(parameter);
		generator.dispose();
		float r = 82f;
		float g = 70f;
		float b = 24f;
		font9.setColor(r/255.0f, g/255.0f, b/255.0f, 1f);
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			switch(select){
				case 0: gsm.popState();
						gsm.pushState(GameStateManager.PLAY); break;
				case 1: gsm.pushState(GameStateManager.OPTIONS); break;
				case 2: System.exit(0); break;	
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
		else select%=3;
//		System.out.println("["+x+", "+y+"]");
	}
	
	public void update(float dt){
		handleInput(dt);
	}
	
	public void render(){
		cam.setToOrtho(false, 640, 480);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(new Texture("MainMenu.png"),0,0);
		switch(select){
			case 0: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 195,201); break;
			case 1: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 197,184); break;
			case 2: sb.draw(Sprite.getSprite(Sprite.newsprite, 120), 199,167); break;
		}
		sb.draw(Sprite.getSprite(Sprite.newsprite,128),640-(3)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640-(1)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640-(2)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,138),640-(3)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640-(1)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640-(2)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		
		font9.draw(sb, "WSAD to move\n      Space \n    to select", 640-3*32+5, 480-5);
		sb.end();
	}
	
	public void dispose(){}
	
}
