package com.ddr.game;

public class Level {
	static short one[]={0,1,2,3,4,5,6,7,8,9,10};
	public static Level ONE = new Level(one);
	
	private short id[];
	private static final short SIZE = 128;
	
	public Level(){
		id = new short[SIZE*SIZE];
		for(int i = 0; i<SIZE*SIZE; i++){
			id[i]=(short) (i%(19*19));
		}
	}
	
	public Level(short x[]){
		id = new short[SIZE*SIZE];
		for(int i = 0; i<SIZE*SIZE; i++){
			if(i<x.length)
				id[i]=x[i];
			else
				id[i]=19;
		}
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
	
}
