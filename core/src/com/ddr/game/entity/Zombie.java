package com.ddr.game.entity;

import com.ddr.game.DavesDailyRitual;
import com.ddr.game.Sprite;
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
		absx+=a[0];
		absy+=a[1];
//		System.out.println("moving ["+a[0]+", "+a[1]+"]");
		y=(absy)/Sprite.SIZE;
		x=(absx)/Sprite.SIZE;
		if(entityContainsEntity(target)){
			DavesDailyRitual.gsm.setState(GameStateManager.GOVER);
		}
	}
	
	public int[] askMove(){//better selection of 8 dir and then do frames somehow and detection of objects
		int value[] = new int[2];
		value[0]=value[1]=0;
//		System.out.println("z: ["+absx+", "+absy+"] P:"+target.absx+", "+target.absy);
		if(target.absx>absx)
			value[0]=xvel;
		else if(target.absx<absx)
			value[0]=-xvel;
		if(target.absy>absy)
			value[1]=yvel;
		else if(target.absy<absy)
			value[1]=-yvel;
//		System.out.println("asking ["+value[0]+", "+value[1]+"]");
		return value;
	}
}
