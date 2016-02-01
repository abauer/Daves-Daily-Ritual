package com.ddr.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.entity.CoolObject;
import com.ddr.game.entity.EntityManager;
import com.ddr.game.entity.Obstacle;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;
import com.ddr.game.entity.Zombie;
import com.ddr.game.path.Map;
import com.ddr.game.path.NodeManager;
import com.ddr.game.state.Play;

public class Level {
	
	private short id[];
	public short size = 1;
	public EntityManager em;
	public Map m;
	String note;
//	public Level(){
//		em = new EntityManager();
//		id = new short[size*size];
//		for(int i = 0; i<size*size; i++){
//			id[i]=(short) (i%(19*19));
//		}
//	}
	
	public Level(short ids[],int size,Player p, String s, NodeManager m,
			Wall[] w,Obstacle[] o, Zombie[] z,CoolObject[] c){
		this.size = (short) size;
		if(size<1)
			this.size = (short)(size = 1);
		em = new EntityManager(m);
		em.loadLevel(p,w,o,z,c);
		id = new short[size*size];
		for(int i = 0; i<size*size; i++){
			if(i<ids.length)
				id[i]=ids[i];
			else
				id[i]=19;
		}
		note = s;
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
	
	public boolean drawEntities(SpriteBatch sb, int camx, int camy){
		return em.drawEntities(sb, (camx/Sprite.SIZE),(camy/Sprite.SIZE),camx, camy);
	}
	
	public void displayNote(SpriteBatch sb){
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,128),640/2-(3)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2-(2)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2-(1)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2-(0)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2+(1)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2+(1)*Sprite.SIZE,480/2-(1)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,128),640/2+(2)*Sprite.SIZE,480/2-(0)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 270);
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2-(2)*Sprite.SIZE,480/2-(2)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 90);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(2)*Sprite.SIZE,480/2-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(1)*Sprite.SIZE,480/2-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(0)*Sprite.SIZE,480/2-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2+(1)*Sprite.SIZE,480/2-(2)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2+(2)*Sprite.SIZE,480/2-(1)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 270);
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2-(2)*Sprite.SIZE,480/2-(3)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 90);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(2)*Sprite.SIZE,480/2-(3)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(1)*Sprite.SIZE,480/2-(3)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2-(0)*Sprite.SIZE,480/2-(3)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,137),640/2+(1)*Sprite.SIZE,480/2-(3)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,129),640/2+(2)*Sprite.SIZE,480/2-(2)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 270);
		
		sb.draw(Sprite.getSprite(Sprite.newsprite,138),640/2-(3)*Sprite.SIZE,480/2-(4)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640/2-(2)*Sprite.SIZE,480/2-(4)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640/2-(1)*Sprite.SIZE,480/2-(4)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640/2-(0)*Sprite.SIZE,480/2-(4)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,139),640/2+(1)*Sprite.SIZE,480/2-(4)*Sprite.SIZE);
		sb.draw(Sprite.getSprite(Sprite.newsprite,138),640/2+(3)*Sprite.SIZE,480/2-(4)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 90);
		
//		sb.draw(Sprite.getSprite(Sprite.newsprite,128), Sprite.SIZE, 480/2-(0)*Sprite.SIZE, 0, 0, Sprite.SIZE, Sprite.SIZE, 1, 1, 180);
		
		Play.font9.draw(sb,note,640/2-50,480/2-24);
	}
}
