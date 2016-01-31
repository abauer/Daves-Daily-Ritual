package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.ddr.game.Level;
import com.ddr.game.LevelManager;
import com.ddr.game.Sprite;
import com.ddr.game.handlers.GameStateManager;

public class Play extends GameState {
	
	private LevelManager lm;
	private Level currentLevel;
	private int abscamX = 0;//90*32;
	private int abscamY = 0;//90*32;
	private int camX = 0;
	private int camY = 0;
	
	private float camxaccel = 0f;
	private float camyaccel = 0f;
	private float maxcamaccel = 1.0f;
	private float camxvel = 0f;
	private float camyvel = 0f;
	private float maxcamvel = 2.0f;
	
	private boolean paused = false;	
	
	public Play(GameStateManager gsm){
		super(gsm);
		lm = new LevelManager();
		currentLevel = lm.nextLevel();
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gsm.pushState(GameStateManager.PAUSE);
			return;
        }
		int[] a = new int[2];
		a[0]=a[1]=0;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			a[0]-=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	a[0]+=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        	a[1]-=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	a[1]+=1;
        }
        currentLevel.em.movePlayer(a);
        updateCam();
	}
	
	private void updateCam(){
		int x = currentLevel.em.getPlayer().getAbsX()-640/2;
		int y = currentLevel.em.getPlayer().getAbsY()-480/2;
		
//		System.out.println("cam: ["+abscamX+", "+abscamY+"] p: "+(x+640/2)+", "+(y+480/2));
		
		float accelinc=.33f;
		
		if(abscamX<x){//left
				camxaccel+=accelinc;
				if(camxaccel<0)
					camxaccel=0;
		}
		else if(abscamX>x){
				camxaccel-=accelinc;
				if(camxaccel>0)
					camxaccel=0;
//				System.out.println("here");
		}
		if(abscamY<y){//up
				camyaccel+=accelinc;
				if(camyaccel<0)
					camyaccel=0;
		}
		else if(abscamY>y){//down
				camyaccel-=accelinc;
				if(camyaccel>0)
					camyaccel=0;
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
		
		if(camxvel>=maxcamvel)
			camxvel = maxcamvel;
		if(camyvel>=maxcamvel)
			camyvel = maxcamvel;
		if(camxvel<=-maxcamvel)
			camxvel = -maxcamvel;
		if(camyvel<=-maxcamvel)
			camyvel = -maxcamvel;
		
//		System.out.println("acce: ["+camxaccel*-1+", "+camyaccel+"] vel: "+camxvel+", "+camyvel+"");
		
		abscamX+=Math.round(camxvel);
		abscamY+=Math.round(camyvel);
		
		if(abscamX<0){
			abscamX=0;
//			System.out.println("lesx");
		}
		else if(abscamX>(currentLevel.size-20)*Sprite.SIZE){
			abscamX=(currentLevel.size-20)*Sprite.SIZE;
//			System.out.println("morex");
		}
		if(abscamY<0){
			abscamY=0;
//			System.out.println("lesy");
		}
		else if(abscamY>(currentLevel.size-15)*Sprite.SIZE){
			abscamY=(currentLevel.size-15)*Sprite.SIZE;
//			System.out.println("morey");
		}
		
		if(abscamX-x<2){
			camxaccel=0;
			camxvel=0;
			abscamX=x;
//			System.out.println("caught player x");
		}
		if(abscamY-y<2){
			camyaccel=0;
			camyvel=0;
			abscamY=y;
//			System.out.println("caught player y");
//			abscamY=y;
		}
		
		camX = (int)(abscamX/Sprite.SIZE);
        camY = (int)(abscamY/Sprite.SIZE);
	}
	
	public void update(float dt){
		if(!paused){
			handleInput(dt);
			currentLevel.em.tick();
		}
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