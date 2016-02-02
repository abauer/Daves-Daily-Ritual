package com.ddr.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.ddr.game.entity.CoolObject;
import com.ddr.game.entity.Obstacle;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;
import com.ddr.game.entity.Zombie;
import com.ddr.game.path.Node;
import com.ddr.game.path.NodeManager;

public class LevelManager {
	
	private final int LEVELSIZE=64;
	private int count;
	Player p;
	
	public LevelManager(){
		count = 0;
	}
	
	public Level nextLevel(){
		count++;
		return nextLevel(count);
	}
	
	public Level nextLevel(int count){
		p = getPlayer(count);
		short[] l;
		switch(count){
			case 1: l = LevelDefs.oneL; /*18,15,17,37*/
			default: l = LevelDefs.oneL;
		}

		return new Level(l,LEVELSIZE,p,getBeginNote(count),getNodes(count),getWalls(count),getObstacles(count),getZombies(count),getCoolObjects(count));
	}
	
	private Player getPlayer(int c){
		switch(c){
			case 1: return new Player(22,39,new short[]{100,101,102,103,104,105,106,107});
			//x,y frames
			default: return getPlayer(1);
		}
	}
	
	private String getBeginNote(int count){
		return "Press Space to Continue";
	}
	
	private Wall[] getWalls(int c){
		switch(c){
			case 1: return createWallsfromFile(LevelDefs.oneL,new short[]{1,10,11,79});
			default: return getWalls(1);
		}
	}
	
	private Obstacle[] getObstacles(int c){
		switch(c){
			case 1: return new Obstacle[] {new Obstacle(20,39,2,3,37),new Obstacle(21,16,2,1,new short[]{80,81},true,false)};
			default: return getObstacles(1);
		}
	}
	
	private NodeManager getNodes(int c){
		switch(c){
			case 1: return new NodeManager(	
					new int[] {20,24,20,20,22,24,22,26},
					new int[] {17,17,20,22,22,20,20,22},
					new int[][]{{1,2},{0,5},{0,6,3},{2,4},{3,4,6,7},{1,6,7},{2,5,4},{6,5,7}});
					/*
					new Node(20,17,0),new Node(24,17,1),new Node(20,20,2),new Node(20,22,3),new Node(22,22,4),new Node(24,20,5),new Node(22,20,6),new Node(26,22,7)
					}, new int[][]{{1,2},{0,5},{0,6,3},{2,4},{3,4,6,7},{1,6,7},{2,5,4},{6,5,7}});*/
			default: return getNodes(1);
		}
	}
	
	private Zombie[] getZombies(int c){
		switch(c){
			case 1: return new Zombie[] {new Zombie(20,20,new short[]{90,91,92,93,94,95,96,97},p)};//,new Zombie(20,22,new short[]{90,91,92,93,94,95,96,97},p)};
			//x,y,frames, p
			default: return getZombies(1);
		}
	}
	
	private CoolObject[] getCoolObjects(int c){// x,y,w,h,time,objective,
		switch(c){
			case 1: return new CoolObject[]{new CoolObject(28,41,1,1,"Knock on \nJohn's Door","Wake up John!",getSound("knocking.mp3")),
											new CoolObject(21,34,1,1,"Brush your \nteeth","Brush Brush \nBrush!",getSound("teeth.mp3")),
											new CoolObject(29,37,2,1,"Feed the fish","Take care \nof your pets!",getSound("fish.mp3")),
											new CoolObject(22,28,2,1,"Cook some \nfood","You look \nhungry!",getSound("cooking.mp3")),
											new CoolObject(29,23,3,6,"Eat your \nfood","You're still \nhungry!",getSound("eating.mp3")),
											new CoolObject(20,45,2,2,"Time for work","Start the car!",getSound("eating.mp3"))};
			//x,y,w,h,
			default: return getCoolObjects(1);
		}
	}
	
	private Music getSound(String s){
//		return Gdx.audio.newSound(Gdx.files.internal(s));
		return Gdx.audio.newMusic(Gdx.files.internal(s));
	}
	
	private Wall[] createWallsfromFile(short[] f,short[] ignore){ //10,11,1
		int n = 0;
		outerloop:
		for(int i = 0; i<f.length; i++){
			for(int j = 0; j<ignore.length; j++)
				if(f[i]==ignore[j]){
					continue outerloop;
				}
			n++;
		}
		Wall w[] = new Wall[n];
		n=0;
		outerloop:
		for(int i = 0; i<f.length; i++){
			for(int j=0; j<ignore.length; j++)
				if(f[i]==ignore[j])
					continue outerloop;
			w[n]=new Wall(i%LEVELSIZE,i/LEVELSIZE,1,1,0);
	//				System.out.println("new wall ["+i%LEVELSIZE+", "+i/LEVELSIZE+"]");
			n++;
		}
	//	System.out.println("len: "+w.length);
		return w;
	}
}
