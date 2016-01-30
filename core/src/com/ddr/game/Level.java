package com.ddr.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.entity.EntityManager;
import com.ddr.game.entity.Player;

public class Level {
	static short one[]={0,1,2,3,4,5,6,7,8,9,10};
	public static Level ONE = new Level(one);
	public Player player;
	
	private short id[];
	private static final short SIZE = 128;
	private EntityManager em;
	
	public Level(){
		em = new EntityManager();
		id = new short[SIZE*SIZE];
		for(int i = 0; i<SIZE*SIZE; i++){
			id[i]=(short) (i%(19*19));
		}
	}
	
	public Level(short x[]){
		em = new EntityManager();
		player = new Player(90,90,1,1,10);
		em.loadLevelOne(player);
		id = new short[SIZE*SIZE];
		for(int i = 0; i<SIZE*SIZE; i++){
			if(i<x.length)
				id[i]=x[i];
			else
				id[i]=19;
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public short getId(int i){
		return id[i];
	}
	
	public short getId(int x, int y){
		int temp = y*SIZE+x;
		if(temp<0||temp>=id.length)
			return 19;
		return id[y*SIZE+x];
	}
	
	public void drawEntities(SpriteBatch sb, int x, int y){
		em.drawEntities(sb, x, y);
	}
}
