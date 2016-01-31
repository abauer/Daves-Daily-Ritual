package com.ddr.game.path;

import com.ddr.game.entity.Entity;
import com.ddr.game.entity.EntityManager;

public class Map implements TileBasedMap{

	public int x,y,width,height;
	EntityManager em;
	
	public Map(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void init(EntityManager eme){
		this.em = eme;
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
			if(e.getType()!=3){
				if(e.entityContainsPoint(x, y))
					return true;
			}
		}	
		return false;
	}

	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}
}
