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

	public void drawEntities(SpriteBatch sb,int camX, int camY){
		for(int i =0; i<list.size(); i++){
			if(onScreen(list.get(i),camX,camY)){
				list.get(i).draw(sb,camX,camY);
			}
		}
	}
	
	private boolean onScreen(Entity e, int x, int y){
		if(e.getX()>=x-1 && e.getX()<x+20)
			if(e.getY() >=y && e.getY()<=y+15){
				return true;
			}
		return false;
	}
	
	public Player getPlayer(){
		return p;
	}
	
	public int moveRight(){
		for(int i = 0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX()+1, p.getAbsY(), p.getAbsWidth(), p.getAbsHeight()))
					return -1;
		}
		return p.moveRight();
	}
	public int moveLeft(){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX()-1, p.getAbsY(), p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveLeft();
	}
	public int moveUp(){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX(), p.getAbsY()-1, p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveUp();
	}
	public int moveDown(){
		for(int i =0; i<list.size(); i++){
			if(!list.get(i).equals(p))
				if(list.get(i).contains(p.getAbsX(), p.getAbsY()+1, p.getAbsWidth(), p.getAbsHeight()))
						return -1;
		}
		return p.moveDown();
	}
	
	
}
