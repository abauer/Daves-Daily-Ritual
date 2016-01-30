package com.ddr.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	
	private ArrayList<Entity> list;
	Player p;
	
	public EntityManager(){
		list = new ArrayList<Entity>();
		p = new Player(0,0,0,1,1);
		list.add(p);
	}
	
	public void loadLevelOne(Player p){
		list.remove(this.p);
		this.p = p;
		list.add(p);
		list.add(new Obstacle(2, 2, 2, 2, 25,false,true));
	}
	
	public void loadLevel(Player p,Entity e[]){
		for(int i =0; i<list.size(); i+=0){
			list.remove(0);
		}
		this.p=p;
		list.add(p);
		for(int i =0; i<e.length; i++){
			list.add(e[i]);
		}
	}

	public void drawEntities(SpriteBatch sb,int camX, int camY,int abscamx,int abscamy){
		for(int i =0; i<list.size(); i++){
			if(onScreen(list.get(i),camX,camY)){
				list.get(i).draw(sb,abscamx,abscamy);
			}
		}
	}
	
	private boolean onScreen(Entity e, int x, int y){
		if(e.getX()>=x-2 && e.getX()<x+22)
			if(e.getY() >=y-1 && e.getY()<=y+16){
				return true;
			}
		return false;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public int moveRight(int dis){
		for(int i = 0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX()+dis, p.getAbsY(), p.getAbsWidth(), p.getAbsHeight()))
					return -1;
		}
		return p.moveRight(dis);
	}
	public int moveLeft(int dis){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX()-dis, p.getAbsY(), p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveLeft(dis);
	}
	public int moveUp(int dis){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX(), p.getAbsY()-dis, p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveUp(dis);
	}
	public int moveDown(int dis){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX(), p.getAbsY()+dis, p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveDown(dis);
	}
	
	
}
