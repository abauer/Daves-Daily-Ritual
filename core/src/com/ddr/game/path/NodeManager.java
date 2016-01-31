package com.ddr.game.path;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	public void displayNodes(SpriteBatch sb,int abscamx, int abscamy){
		for(Node n:allNodes){
//			sb.draw(Sprite.getSprite(Sprite.newsprite, 1), n.x-abscamx,(15-1)*Sprite.SIZE-n.y+abscamy);
//			sb.draw(Sprite.getSprite(Sprite.newsprite,57),(x)*Sprite.SIZE-abscamx,(15-1-y)*Sprite.SIZE+abscamy);
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
	
	public Node newPlayerNode(int x, int y){
		Node p = new Node(x,y);
		Node l = new Node(0,0);
		float distance = p.distance(l);
		for(int i = 0; i<allNodes.size(); i++){
			Node n = allNodes.get(i);
			if(p.distance(n)<distance)
				l = n;
		}
		if(l.x==0&&l.y==0){
			System.out.println("this is null");
			return null;
		}
		p.linkNode(l);
		allNodes.add(p);
		return p;
	}
	
	public boolean isClosestNode(Node n, int x, int y){//modify so not grid?
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
