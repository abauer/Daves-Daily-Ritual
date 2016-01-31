package com.ddr.game.path;

import java.util.ArrayList;

import com.ddr.game.Sprite;
import com.ddr.game.entity.Entity;
import com.ddr.game.entity.EntityManager;

public class NodeManager {
	
	ArrayList<Node> allNodes;
	Entity[] blocks;
	
	public NodeManager(Node[] n,int[][] ks){
		allNodes = new ArrayList<Node>();
		for(int i = 0; i<n.length; i++){
			allNodes.add(n[i]);
		}
		
		for(int i = 0; i<ks.length;i++){
			for(int j = 0; j<ks[i].length; j++){
				allNodes.get(i).linkNode(allNodes.get(j));
			}
		}
	}
	
	public void fillEntity(EntityManager em){
		blocks = new Entity[em.getList().size()];
		for(int i =0; i<em.getList().size(); i++){
			Entity e = em.getList().get(i);
			if(e.getType()!=1&&e.getType()!=3){
				blocks[i] = e;
			}
		}
	}
	
	public boolean blocked(Mover m, int x, int y){
		int cx=(int)Math.round((x)/Sprite.SIZE);
		int cy=(int)Math.round((y)/Sprite.SIZE);
		
		for(int i = 0; i<blocks.length; i++){
			if(blocks[i].getX()==cx&&blocks[i].getY()==cy)
				return true;
		}
		
		return false;		
	}
	
	public Node newPlayerNode(int x, int y){
		return null;
	}
	
	public boolean isClosestNode(Node n, int x, int y){
		int cx=(int)Math.round((x)/Sprite.SIZE);
		int cy=(int)Math.round((y)/Sprite.SIZE);
		
		int ox=(int)Math.round((n.x)/Sprite.SIZE);
		int oy=(int)Math.round((n.y)/Sprite.SIZE);
		return(cx==ox && cy==oy);
	}
	
	public float getCost(Mover mover, int sx, int sy, int tx, int ty){
		return 1f;
	}
}
