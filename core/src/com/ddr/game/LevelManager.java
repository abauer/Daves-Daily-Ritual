package com.ddr.game;

import com.ddr.game.entity.Obstacle;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;
import com.ddr.game.entity.Zombie;

public class LevelManager {
	
	private int count;
	Player p;
	
	public LevelManager(){
		count = 1;
	}
	
	public Level nextLevel(){
		p = getPlayer(count);
		switch(count){
			case 1:return new Level(LevelDefs.oneL,32,p,getWalls(count),getObstacles(count),getZombies(count));
			default: return new Level(LevelDefs.oneL,32,p,getWalls(1),getObstacles(1),getZombies(1));
		}
	}
	
	private Player getPlayer(int c){
		switch(c){
			case 1: return new Player(1,1,new short[]{10,2,36,16});
			default: return new Player(1,1,new short[]{10,2,36,16});
		}
	}
	
	private Wall[] getWalls(int c){
		switch(c){
			case 1: return new Wall[]{new Wall(0,0,10,1,0),new Wall(0,1,1,26,0),new Wall(1,26,6,1,0),new Wall(6,23,1,3,0),new Wall(7,23,10,1,0),
				new Wall(16,5,1,18,0),new Wall(10,5,6,1,0),new Wall(9,1,1,5,0)};
			default: return new Wall[]{new Wall(0,0,10,1,0),new Wall(0,1,1,26,0),new Wall(1,26,6,1,0),new Wall(6,23,1,3,0),new Wall(6,23,10,1,0),
				new Wall(16,5,1,17,0),new Wall(10,5,6,1,0),new Wall(9,1,1,5,0)};
		}
	}
	
	private Obstacle[] getObstacles(int c){
		switch(c){
			case 1: return new Obstacle[] {new Obstacle(2,2,2,2,25)};
			default: return new Obstacle[] {new Obstacle(2,2,2,2,25)};
		}
	}
	
	private Zombie[] getZombies(int c){
		switch(c){
			case 1: return new Zombie[] {new Zombie(7,17,new short[]{23},p),new Zombie(2,17,new short[]{23},p)};
			default: return new Zombie[] {new Zombie(7,17,new short[]{23},p),new Zombie(2,17,new short[]{23},p)};
		}
	}
}
