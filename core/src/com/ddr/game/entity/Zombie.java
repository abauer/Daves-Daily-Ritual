package com.ddr.game.entity;

import com.ddr.game.DavesDailyRitual;
import com.ddr.game.handlers.GameStateManager;

public class Zombie extends Player{
	
	Player target;
	
	public Zombie(Zombie z){
		super(z);
		this.target= z.target;
		this.type = z.type;
		
	}
	
	public Zombie(int x, int y, short[] tlid, Player p) {
		super(x, y, tlid);
		xvel=1;
		yvel=1;
		target = p;
		type = 1;
	}
	
	public void move(int[] a){
		super.move(a);
		if(entityContainsEntity(target)){
			DavesDailyRitual.gsm.setState(GameStateManager.GOVER);
		}
	}
	
	public int[] askMove(){//better selection of 8 dir and then do frames somehow and detection of objects
		int vector[] = new int[2];
		vector[0]=vector[1]=0;
//		System.out.println("z: ["+absx+", "+absy+"] P:"+target.absx+", "+target.absy);
		if(target.absx>absx)
			vector[0]=xvel;
		else if(target.absx<absx)
			vector[0]=-xvel;
		if(target.absy>absy)
			vector[1]=yvel;
		else if(target.absy<absy)
			vector[1]=-yvel;
		
		if(vector[0]==1){
			if(vector[1]==1){
				direction=7;
			}
			if(vector[1]==0){
				direction=0;
			}
			if(vector[1]==-1){
				direction=1;
			}
		}
		else if(vector[0]==0){
			if(vector[1]==1){
				direction=6;
			}
			if(vector[1]==-1){
				direction=2;
			}			
		}
		else if(vector[0]==-1){
			if(vector[1]==1){
				direction=5;
			}
			if(vector[1]==0){
				direction=4;
			}
			if(vector[1]==-1){
				direction=3;
			}			
		}
//		System.out.println("asking ["+value[0]+", "+value[1]+"]");
		return vector;
	}
}
