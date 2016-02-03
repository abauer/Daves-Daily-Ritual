package com.ddr.game.path;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ddr.game.Sprite;

public class NodeManager {
	
	ArrayList<Node> allNodes;
//	Entity[] blocks;
	ShapeRenderer sr;
	int count;
	
	public NodeManager(int[] ecks, int[] wye,int[][] links){
		count = 0;
		allNodes = new ArrayList<Node>();
		for(int i = 0; i<ecks.length; i++){
			createNode(ecks[i]*32,wye[i]*32,links[i]);
		}
		sr = new ShapeRenderer();
	}
	
	private Node createNode(int x,int y,int[] links){
		Node n = new Node(x,y,count);
		for(int i = 0; i<links.length; i++){
			n.linkNode(links[i]);
		}
		allNodes.add(n);
//		System.out.println("creating node "+count);
		count++;
		return n;
	}
	
	public void destroyNode(Node done){
//		System.out.println("destroying node "+count);
		for(Node n:allNodes){
			n.unlink(done);
		}
		done.destroy();
		if(!allNodes.remove(done))
			System.out.println("Bad error &&&&&&&&&&&&&&&&&&&&&&&&");;
	}
	
	public Node[] getNeighbors(Node loc){
		int[] t = loc.getNeighbors();
		Node[] nodes = new Node[loc.len];
		for(int i =0; i<nodes.length; i++){
			for(Node n: allNodes){
				if(n.id == t[i])
					nodes[i]=n;
			}
		}
		if(nodes == null || nodes.length==0){
			System.out.println("we have a problem "+loc.id);
		}
		return nodes;
	}
	
	public void displayNodes(SpriteBatch sb,int abscamx, int abscamy){
//		sr.setProjectionMatrix(sb.getProjectionMatrix());
		for(Node n:allNodes){
//			System.out.println("node at ["+n.x+","+n.y+"]");
			sb.draw(Sprite.getSprite(Sprite.newsprite, 122), n.x-abscamx,(15-1)*Sprite.SIZE-n.y+abscamy);
			// should I draw lines?
			sb.end();	
			Node end[] = getNeighbors(n);
			for(int i =0; i<n.len; i++){
				Node o = end[i];
				sr.begin(ShapeRenderer.ShapeType.Line);
				sr.setColor(Color.BLUE);
				sr.line(4*(n.x-abscamx),4*((15)*Sprite.SIZE-n.y+abscamy), 4*(o.x-abscamx),4*((15)*Sprite.SIZE-o.y+abscamy));
				sr.end();
			}
			sb.begin();
//			sb.draw(Sprite.getSprite(Sprite.newsprite,57),(x)*Sprite.SIZE-abscamx,(15-1-y)*Sprite.SIZE+abscamy);
		}
//		System.out.println("There are "+allNodes.size()+" nodes");
	}
	
//	public void fillEntity(EntityManager em){
//		blocks = new Entity[em.getList().size()];
//		for(int i =0; i<em.getList().size(); i++){
//			Entity e = em.getList().get(i);
//			if(e.getType()!=1&&e.getType()!=3){
//				blocks[i] = e;
//			}
//		}
//	}
	
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
		Node p = createNode(x,y,new int[]{});//create new mover node
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
					if(p.distance(n)<5){//too close, shouldn't be a possible node
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
			p.linkNode(n.id);
			n.linkNode(p.id);
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
