package com.ddr.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	
	public ArrayList<Entity> list;
	
	public EntityManager(){
		list = new ArrayList<Entity>();
	}
	
	public void loadLevelOne(Player p){
		list.add(p);
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
				System.out.println("here");
				return true;
			}
		return false;
	}
}
