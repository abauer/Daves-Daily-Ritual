package com.ddr.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.ddr.game.Level;
import com.ddr.game.LevelDefs;
import com.ddr.game.Sprite;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;
import com.ddr.game.entity.Zombie;
import com.ddr.game.handlers.GameStateManager;

public class LV1 extends GameState {
	
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
	
	private Player oneP;
	private Wall[] oneW;
	private Zombie[] oneZ;
	
	
	
	public LV1(GameStateManager gsm){
		super(gsm);
		oneP = new Player(1,1,new short[]{10,2,36,16});
		oneW = new Wall[] {new Wall(0,0,10,1,0),new Wall(0,1,1,26,0),new Wall(1,26,6,1,0),new Wall(6,23,1,3,0),new Wall(6,23,10,1,0),
							new Wall(16,5,1,17,0),new Wall(10,5,6,1,0),new Wall(9,1,1,5,0)};
		oneZ = new Zombie[] {new Zombie(7,17,new short[]{23},oneP),new Zombie(2,17,new short[]{23},oneP)};
		currentLevel = new Level(LevelDefs.oneL,32,oneP,oneW,oneZ);
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gsm.pushState(GameStateManager.PAUSE);
			return;
        }
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			currentLevel.em.moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	currentLevel.em.moveRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){

        	currentLevel.em.moveUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	currentLevel.em.moveDown();
        }
        updateCam();
	}
	
	private void updateCam(){
		int x = currentLevel.em.getPlayer().getAbsX()-640/2;
		int y = currentLevel.em.getPlayer().getAbsY()-480/2;
		
//		System.out.println("cam: ["+abscamX+", "+abscamY+"] p: "+(x+640/2)+", "+(y+480/2));
		
		
		if(abscamX<x){//left
				camxaccel+=.033;
				if(camxaccel<0)
					camxaccel=0;
		}
		else if(abscamX>x){
				camxaccel-=.033;
				if(camxaccel>0)
					camxaccel=0;
//				System.out.println("here");
		}
		if(abscamY<y){//up
				camyaccel+=.033;
				if(camyaccel<0)
					camyaccel=0;
		}
		else if(abscamY>y){//down
				camyaccel-=.033;
				if(camyaccel>0)
					camyaccel=0;
		}
    	
		if(camxaccel>=maxcamaccel)
			camxaccel=maxcamaccel;
		if(camyaccel>=maxcamaccel)
			camyaccel=maxcamaccel;
		if(camxaccel<=-maxcamaccel)
			camxaccel=-maxcamaccel;
		if(camyaccel<=-1.0*maxcamaccel)
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
		if(abscamX>(currentLevel.size-20)*Sprite.SIZE){
			abscamX=(currentLevel.size-20)*Sprite.SIZE;
//			System.out.println("morex");
		}
		if(abscamY<0){
			abscamY=0;
//			System.out.println("lesy");
		}
		if(abscamY>(currentLevel.size-15)*Sprite.SIZE){
			abscamY=(currentLevel.size-15)*Sprite.SIZE;
//			System.out.println("morey");
		}
		
		if(abscamX==x){
			camxaccel=0;
			camxvel=0;
//			System.out.println("caught player x");
		}
		if(abscamY==y){
			camyaccel=0;
			camyvel=0;
//			System.out.println("caught player y");
//			abscamY=y;
		}
		
		camX = (int)(abscamX/Sprite.SIZE);
        camY = (int)(abscamY/Sprite.SIZE);
	}
	
	public void update(float dt){
		handleInput(dt);
		currentLevel.em.tick();
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
