package com.ddr.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.entity.CoolObject;
import com.ddr.game.entity.EntityManager;
import com.ddr.game.entity.Obstacle;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;
import com.ddr.game.entity.Zombie;
import com.ddr.game.path.Map;

public class Level {
	
	private short id[];
	public short size = 1;
	public EntityManager em;
	public Map m;
//	public Level(){
//		em = new EntityManager();
//		id = new short[size*size];
//		for(int i = 0; i<size*size; i++){
//			id[i]=(short) (i%(19*19));
//		}
//	}
	
	public Level(short ids[],int size,Player p, int x, int y, int width, int height,
			Wall[] w,Obstacle[] o, Zombie[] z,CoolObject[] c){
		this.size = (short) size;
		if(size<1)
			this.size = (short)(size = 1);
		m = new Map(x,y,width,height);
		em = new EntityManager(m);
		em.loadLevel(p,w,o,z,c);
		id = new short[size*size];
		for(int i = 0; i<size*size; i++){
			if(i<ids.length)
				id[i]=ids[i];
			else
				id[i]=19;
		}
	}
	
	public Player getPlayer(){
		return em.getPlayer();
	}
	
	public short getId(int i){
		return id[i];
	}
	
	public short getId(int x, int y){
		int temp = y*size+x;
		if(temp<0||temp>=id.length)
			return 19;
		return id[y*size+x];
	}
	
	public void drawEntities(SpriteBatch sb, int camx, int camy){
		em.drawEntities(sb, (camx/Sprite.SIZE),(camy/Sprite.SIZE),camx, camy);
	}
}
