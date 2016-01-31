package com.ddr.game.entity;

import com.ddr.game.DavesDailyRitual;
import com.ddr.game.handlers.GameStateManager;
import com.ddr.game.path.AStarPathFinder;
import com.ddr.game.path.Map;
import com.ddr.game.path.Mover;
import com.ddr.game.path.Path;
import com.ddr.game.path.Path.Step;

public class Zombie extends Player implements Mover{
	
	Step approach[];
	int loop;
	Player target;
	
//	public Zombie(Zombie z){
//		super(z);
//		this.target= z.target;		
//	}
	
	public Zombie(int x, int y, short[] tlid, Player p) {
		super(x, y, tlid);
		xvel=1;
		yvel=1;
		target = p;
		type = 1;
		approach = new Step[25];
		loop = 0;
	}
	
	public void move(int[] a){
		super.move(a);
		if(entityContainsEntity(target)){
			DavesDailyRitual.gsm.setState(GameStateManager.GOVER);
		}
	}
	
	public int[] askMove(AStarPathFinder aspf,Map m){//better selection of 8 dir and then do frames somehow and detection of objects
		int vector[] = new int[2];
		vector[0]=vector[1]=0;
		Path p = aspf.findPath(this, getAbsX()-m.x, getAbsY()-m.y, target.getAbsX()-m.x, target.getAbsY()-m.y);
		if(p==null)
			return vector;
		approach[loop++%25] = p.getStep(1);
		System.out.println("self: ["+(getAbsX()-m.x)+", "+(getAbsY()-m.y)+"]");
		
		int tx = p.getStep(1).getX();
		int ty = p.getStep(1).getY();
		System.out.println("next: ["+tx+", "+ty+"]");
		vector[0]=(tx-getAbsX()+m.x);
		vector[1]=(ty-getAbsY()+m.y);
		if(vector[0]>0) vector[0]=1;
		if(vector[1]>0) vector[1]=1;
		if(vector[0]<0) vector[0]=-1;
		if(vector[1]<0) vector[1]=-1;
		System.out.println("diff: ["+vector[0]+", "+vector[1]+"]\n");
		
//		
////		System.out.println("z: ["+absx+", "+absy+"] P:"+target.absx+", "+target.absy);
//		if(target.absx>absx)
//			vector[0]=xvel;
//		else if(target.absx<absx)
//			vector[0]=-xvel;
//		if(target.absy>absy)
//			vector[1]=yvel;
//		else if(target.absy<absy)
//			vector[1]=-yvel;
//		
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

	public Step[] getLastSteps() {
		return approach;
	}
}
