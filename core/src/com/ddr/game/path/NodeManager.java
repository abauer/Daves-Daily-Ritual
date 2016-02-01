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
//			System.out.println("node at ["+n.x+","+n.y+"]");
			sb.draw(Sprite.getSprite(Sprite.newsprite, 122), n.x-abscamx,(15-1)*Sprite.SIZE-n.y+abscamy);
			// should I draw lines?
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
	
//	public Node newPlayerNode(int x, int y){
//		Node p = new Node(x,y);
//		Node l = new Node(0,0);
//		float distance = p.distance(l);
//		for(Node n : allNodes){
//			if(p.distance(n)<distance)
//				l = n;
//		}
//		if(l.x==0&&l.y==0){
//			System.out.println("this is null");
//			return null;
//		}
//		p.linkNode(l);
//		l.linkNode(p);
//		allNodes.add(p);
//		return p;
//	}
	
	//write Node dispose method
	public Node newMoverNode(int x, int y){ //link to closest 2 nodes
		Node p = new Node(x,y);//create new mover node
		Node l[]=new Node[2]; //create array of 2 closest nodes
//		for(int i = 0; i<l.length; i++){//init new nodes and distances
//			l[i] = new Node(0,0);
//			distance[i] = p.distance(l[i]);
//		}
//		System.out.println("setup: find closest");
		for(int i = 0; i<allNodes.size(); i++){ //for every node we have, check if closest
			Node n = allNodes.get(i); //get a node
//			System.out.println("setup: prev distances ["+p.distance(l[0])+", "+p.distance(l[1])+"]");
			for(int j = 0;j<l.length; j++){ //check against all our current closest nodes
				if(p.distance(n)<p.distance(l[j])){ //is it closer?
//					System.out.println("setup: prev distance = "+p.distance(l[j])+" new distance "+p.distance(n));
					if(p.distance(n)<2){//too close, shouldn't be a possible node
//						System.out.println("setup: ontop of this node, throw it out");
						break;
					}
//					System.out.println("setup: new closest node:["+n.x+", "+n.y+"]");
					for(int k=j;k<l.length-1;k++){//shift all current "closer nodes" to the right
						l[k+1]=l[k];
					}
					l[j]=n;//set it in its place
					break;//stop checking our close nodes, grab a new node
				}
			}
		}
		for(int i = 0; i<l.length; i++){//link our nodes
			Node n = l[i];
			if(n.x==0&&n.y==0){
				System.out.println("we have a null");
				return null;
			}
//			System.out.println("setup: link nodes: ["+n.x+", "+n.y+"]");
			p.linkNode(n);
			n.linkNode(p);
		}
//		allNodes.add(p);
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
