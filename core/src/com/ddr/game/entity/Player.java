package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class Player extends Obstacle {
	
	protected int xvel = 2;
	protected int yvel = 2;
	protected double absx = 0;
	protected double absy = 0;
	protected int maxframes = 1;
	protected int direction = 0;
	
	public Player(Player p){
		super(p);
		this.xvel = p.xvel;
		this.yvel = p.yvel;
		this.absx = p.absx;
		this.absy = p.absy;
		this.maxframes = p.maxframes;
		this.type = p.type;
	}
	
	public Player(int x, int y, short[] tlid) {
		super(x, y, 1, 1, tlid[0]);
		absx = x*Sprite.SIZE;
		absy = y*Sprite.SIZE;
		id=tlid;
		this.maxframes = Math.max(1,tlid.length);
		this.type = 3;
	}
	
	public void draw(SpriteBatch sb,int abscamx,int abscamy){
		int tx = (int) Math.round(absx-abscamx);
		int ty = (int) Math.round((14)*Sprite.SIZE-absy+abscamy);
		sb.draw(Sprite.getSprite(Sprite.newsprite,id[direction%maxframes]),tx,ty);
	}
	
	public void move(int[] a){
		if(a[0]==1){
			if(a[1]==1){
				absx+=xvel*0.70710678118654752440084436210485;
				absy+=yvel*0.70710678118654752440084436210485;
			}
			if(a[1]==0){
				absx+=xvel;
			}
			if(a[1]==-1){
				absx+=xvel*0.70710678118654752440084436210485;
				absy-=yvel*0.70710678118654752440084436210485;
			}
		}
		else if(a[0]==0){
			if(a[1]==1){
				absy+=yvel;
			}
			if(a[1]==0){
//				return;
			}
			if(a[1]==-1){
				absy-=yvel;
			}			
		}
		else if(a[0]==-1){
			if(a[1]==1){
				absx-=xvel*0.70710678118654752440084436210485;
				absy+=yvel*0.70710678118654752440084436210485;
			}
			if(a[1]==0){
				absx-=xvel;
			}
			if(a[1]==-1){
				absx-=xvel*0.70710678118654752440084436210485;
				absy-=yvel*0.70710678118654752440084436210485;
//				System.out.println("Walk this way");
			}			
		}
//		else
//			return;
		
		y=(int)Math.round((absy)/Sprite.SIZE);
		x=(int)Math.round((absx)/Sprite.SIZE);
	}
	
	public int getAbsX() {return (int) Math.round(absx);}
	public int getAbsY() {return (int) Math.round(absy);}
	public int getXvel() {return xvel;}
	public int getYvel() {return yvel;}
	
	public int getAbsWidth() {return 24;}
	public int getAbsHeight() {return 24;}
}
