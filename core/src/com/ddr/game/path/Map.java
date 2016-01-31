package com.ddr.game.path;

import com.ddr.game.entity.Entity;
import com.ddr.game.entity.EntityManager;

public class Map implements TileBasedMap{

	public int x,y,width,height;
	EntityManager em;
	int ecks[];
	int wye[];
	
	public Map(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init(EntityManager eme){
		this.em = eme;
		int count = 0;
		for(int i =0; i<em.getList().size(); i++){
			Entity e = em.getList().get(i);
			if(e.getType()!=3){
				count++;
			}
		}
		ecks = new int[count];
		wye = new int[count];
		count=0;
		for(int i =0; i<em.getList().size(); i++){
			Entity e = em.getList().get(i);
			if(e.getType()!=1&&e.getType()!=3){
				ecks[i] = e.getX();
				wye[i] = e.getY();
			}
		}
	}
	
	public int getWidthInTiles() {
		return width;
	}

	public int getHeightInTiles() {
		return height;
	}

	public void pathFinderVisited(int x, int y) {
		
	}

	public boolean blocked(Mover mover, int x, int y) {
		for(int i =0; i<em.getList().size(); i++){
			Entity e = em.getList().get(i);
			if(e.getType()==1){
				if(e.getX()-this.x==x&&e.getY()-this.y==y)
					return true;
			}
		}
		
		for(int i = 0; i<ecks.length; i++){
			if(ecks[i]-this.x==x && wye[i]-this.y==y)
				return true;
		}
		
		return false;
	}

	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}
}
