package com.ddr.game.path;

import com.ddr.game.entity.Entity;
import com.ddr.game.entity.EntityManager;

public class Map implements TileBasedMap{

	int x,y,width,height;
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
		if(em == null){
			System.out.println("maybe you just quit");
		}
		if(em.getList()==null){
			System.out.println("this was the problem");
			return;
		}
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
				ecks[ecks.length-1-i]=e.getX();
				wye[wye.length-1-i]=e.getY();
			}
		}
		return false;
	}

	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		return 1;
	}
}
