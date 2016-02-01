package com.ddr.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;
import com.ddr.game.path.AStarPathFinder;
import com.ddr.game.path.NodeManager;
import com.ddr.game.state.Play;

public class EntityManager {
	
	private ArrayList<Entity> list;
	Player p;
	public AStarPathFinder aspf;
	NodeManager m;
	public CoolObject[] actions;
	
	public EntityManager(NodeManager m){
		list = new ArrayList<Entity>();
		p = new Player(0,0,new short[]{1});
		list.add(p);
		this.m = m;
		aspf = new AStarPathFinder(m, 40);
	}
	
	public void loadLevel(Player p,Wall w[],Obstacle[] o,Zombie[] z,CoolObject[] c){
		for(int i =0; i<list.size(); i+=0){
			list.remove(0);
		}
		this.p=p;
		list.add(this.p);
		for(int i =0; i<o.length; i++)
			list.add(o[i]);
		for(int i =0; i<w.length; i++){
			list.add(w[i]);
		}
//		System.out.println("len: "+w.length);
		for(int i =0; i<z.length; i++)
			list.add(z[i]);
		actions = new CoolObject[c.length];
		for(int i =0; i<c.length; i++){
			list.add(c[i]);
			actions[i]=c[i];
		}
		m.fillEntity(this);
	}

	public boolean drawEntities(SpriteBatch sb,int camX, int camY,int abscamx,int abscamy){		
		for(int i =0; i<list.size(); i++){
			if(onScreen(list.get(i),camX,camY)){
				list.get(i).draw(sb,abscamx,abscamy);
			}
		}
		sb.draw(Sprite.getSprite(Sprite.newsprite,128),640-(3)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640-(1)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640-(2)*Sprite.SIZE,480-(1)*Sprite.SIZE);
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,138),640-(3)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640-(1)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640-(2)*Sprite.SIZE,480-(2)*Sprite.SIZE);
		
		for(int i = 0; i<actions.length; i++){
			CoolObject c = actions[i];
			if(!c.getComplete()){
//				if(c.getClosestDistance((int)vector[0],(int)vector[1])<10.0)
				Play.font9.draw(sb, c.getText(), 640-3*32+5, 480-5);
				return false;
			}
		}
		//no more actions
		return true;
		
//		m.displayNodes(sb, abscamx, abscamy);
	}
	
	private boolean onScreen(Entity e, int x, int y){
//		if(e.getX()>=x-2 && e.getX()<x+22)
//			if(e.getY() >=y-1 && e.getY()<=y+16){
//				return true;
//			}
//		return false;
		return true;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public void tick(){
		for(int i = 0; i<list.size(); i++){
			if(list.get(i).getType() == 1){
				Zombie z = (Zombie) list.get(i);
				z.move(moveZombie(z,z.askMove(aspf,m)));
			}
		}
	}
	
	public void interact(){
		double vector[]=new double[2];
//		System.out.println("player: ["+p.absx+", "+p.absy+"] dir"+p.direction);
		switch(p.direction){
			case 0: vector[0]=p.absx+24;vector[1]=p.absy; break;
			case 1: vector[0]=p.absx+24;vector[1]=p.absy-24; break;
			case 2: vector[0]=p.absx;vector[1]=p.absy-24; break;
			case 3: vector[0]=p.absx-24;vector[1]=p.absy-24; break;
			case 4: vector[0]=p.absx-24;vector[1]=p.absy; break;
			case 5: vector[0]=p.absx-24;vector[1]=p.absy+24; break;
			case 6: vector[0]=p.absx;vector[1]=p.absy+24; break;
			case 7: vector[0]=p.absx+24;vector[1]=p.absy+24; break;
		}
//		System.out.println("attempt: ["+vector[0]+", "+vector[1]+"]");
		for(int i = 0; i<actions.length; i++){
			CoolObject c = actions[i];
			if(!c.getComplete()){
				if(c.getClosestDistance((int)vector[0],(int)vector[1])<10.0)
						c.interact();
				break;
			}
		}
	}
	
	private int[] moveZombie(Zombie z, int[] a){
		for(int i = 0; i<list.size(); i++){
			Entity e = list.get(i);
			if(e.equals(p)||e.equals(z)){
//				System.out.println("skipped object ");
				continue;
			}
//			if(e.type==1)
//				System.out.print("ZOMBIE: ");
//			System.out.println("e["+e.getAbsX()+", "+e.getAbsY()+", "+e.getAbsWidth()+", "+e.getAbsHeight()+
//							"] z "+z.getAbsX()+", "+z.getAbsY()+", "+z.getAbsWidth()+", "+z.getAbsHeight());
			if(e.entityContainsRect(z.getAbsX()+a[0], z.getAbsY(), z.getAbsWidth(), z.getAbsHeight())){
				a[0]=0;
			}
			if(e.entityContainsRect(z.getAbsX(), z.getAbsY()+a[1], z.getAbsWidth(), z.getAbsHeight())){
				a[1]=0;
			}
		}	
		return a;
	}
	
	public void movePlayer(int[] vector){
		if(vector[0]==1){
			if(vector[1]==1){
				p.direction=7;
			}
			if(vector[1]==0){
				p.direction=0;
			}
			if(vector[1]==-1){
				p.direction=1;
			}
		}
		else if(vector[0]==0){
			if(vector[1]==1){
				p.direction=6;
			}
			if(vector[1]==-1){
				p.direction=2;
			}			
		}
		else if(vector[0]==-1){
			if(vector[1]==1){
				p.direction=5;
			}
			if(vector[1]==0){
				p.direction=4;
			}
			if(vector[1]==-1){
				p.direction=3;
			}			
		}
		
		for(int i = 0; i<list.size(); i++){
			Entity e = list.get(i);
			if(e.equals(p)){
				continue;
			}
			if(e.entityContainsRect(p.getAbsX()+vector[0]*p.xvel, p.getAbsY(), p.getAbsWidth(), p.getAbsHeight())){
				vector[0]=0;
			}
			if(e.entityContainsRect(p.getAbsX(), p.getAbsY()+vector[1]*p.yvel, p.getAbsWidth(), p.getAbsHeight())){
				vector[1]=0;
			}
		}	
		p.move(vector);
	}
	
	public ArrayList<Entity> getList(){
		return list;
	}
}
