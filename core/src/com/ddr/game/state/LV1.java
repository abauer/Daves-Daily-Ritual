package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.ddr.game.DavesDailyRitual;
import com.ddr.game.Level;
import com.ddr.game.Sprite;
import com.ddr.game.handlers.GameStateManager;

public class LV1 extends GameState {
	
	private Level currentLevel = Level.ONE;
	private int abscamX = 0;//90*32;
	private int abscamY = 0;//90*32;
	private int camX = 0;
	private int camY = 0;
	
	private float camxaccel = 0;
	private float camyaccel = 0;
	private float maxcamaccel = 1.0f;
	private float camxvel = 0;
	private float camyvel = 0;
	
	public LV1(GameStateManager gsm){
		super(gsm);
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)&&dt==DavesDailyRitual.BIGSTEP){
			gsm.pushState(GameStateManager.PAUSE);
			return;
        }
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			currentLevel.em.moveLeft(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	currentLevel.em.moveRight(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){

        	currentLevel.em.moveUp(2);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	currentLevel.em.moveDown(2);
        }
        updateCam();
	}
	
	private void updateCam(){
		int x = currentLevel.em.getPlayer().getAbsX()-640/2;
		int y = currentLevel.em.getPlayer().getAbsY()-480/2;
		
//		System.out.println("cam: ["+abscamX+", "+abscamY+"] p: "+(x+640/2)+", "+(y+480/2));
//		System.out.println("acce: ["+camxaccel+", "+camyaccel+"] vel: "+camxvel+", "+camyvel+"");
		
		if(abscamX<x){//left
				camxaccel+=.05;
		}
		else if(abscamX>x){
				camxaccel-=.05;
		}
		if(abscamY<y){//up
				camyaccel+=.05;
		}
		else if(abscamY>y){//down
				camyaccel-=.05;
		}
    	
		if(camxaccel>=maxcamaccel)
			camxaccel=maxcamaccel;
		if(camyaccel>=maxcamaccel)
			camyaccel=maxcamaccel;
		if(camxaccel<=-maxcamaccel)
			camxaccel=-maxcamaccel;
		if(camyaccel<=-maxcamaccel)
			camyaccel=-maxcamaccel;
		
		camxvel += camxaccel;
		camyvel += camyaccel;
		
		if(camxvel>=1)
			camxvel = 1;
		if(camyvel>=1)
			camyvel = 1;
		if(camxvel<=-1)
			camxvel = -1;
		if(camyvel<=-1)
			camyvel = -1;
		
		abscamX+=camxvel;
		abscamY+=camyvel;
		
		if(abscamX<0){
			abscamX=0;
		}
		if(abscamX>(currentLevel.size-20)*Sprite.SIZE){
			abscamX=(currentLevel.size-20)*Sprite.SIZE;
		}
		if(abscamY<0){
			abscamY=0;
		}
		if(abscamY>(currentLevel.size-15)*Sprite.SIZE){
			abscamY=(currentLevel.size-15)*Sprite.SIZE;
		}
		
		if(abscamX==x){
			camxaccel=0;
			camxvel=0;
		}
		if(abscamY==y){
			camyaccel=0;
			camyvel=0;
			abscamY=y;
		}
		
		camX = (int)(abscamX/Sprite.SIZE);
        camY = (int)(abscamY/Sprite.SIZE);
	}
	
	public void update(float dt){
		handleInput(dt);
	}
	
	public void render(){
		cam.setToOrtho(false, 640, 480);
		sb.setProjectionMatrix(cam.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		for(int i = 0; i <22; i++)
			for(int j = 0; j<17; j++){
				int id = currentLevel.getId(camX-1+i,camY-1+j);
				sb.draw(Sprite.getSprite(Sprite.tiles,id),(i-1)*Sprite.SIZE-abscamX%Sprite.SIZE,(15-j)*Sprite.SIZE+abscamY%Sprite.SIZE);
			}
		currentLevel.drawEntities(sb, abscamX, abscamY);
		sb.end();
	}
	
	public void dispose(){}
	
}
