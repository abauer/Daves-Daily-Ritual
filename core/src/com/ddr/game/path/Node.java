package com.ddr.game.path;

import java.util.Random;

import com.ddr.game.Sprite;

public class Node implements Comparable{
	
	int x, y; //xy in px format
	Node[] nei;
	float cost;
	public Node parent;
	/** The heuristic cost of this node */
	public float heuristic;
	/** The search depth of this node */
	public int depth;
	private Random r;
	private int id;
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		r = new Random();
		id = r.nextInt();
//		System.out.println("created node ("+id+") at ["+this.x+", "+this.y+"]");
		cost = 0;
		nei = new Node[]{};
		
	}
	
	public Node(int x, int y,int a){
		this.x = x*Sprite.SIZE;
		this.y = y*Sprite.SIZE;
		r = new Random();
		id = r.nextInt();
//		System.out.println("created node ("+id+") at ["+this.x+", "+this.y+"]");
		cost = 0;
		nei = new Node[]{};
		
	}
	
	public void linkNode(Node n){
//		System.out.println("there are "+count+"nodes");
		Node[] temp = new Node[nei.length]; //create temp buffer
		int i;
		for(i = 0; i<nei.length; i++){ //copy to temp buffer
			temp[i]=nei[i];
		}
		i++;					//increase number of nodes linked
		nei = new Node[i];      //create bigger list for linked nodes
		for(i = 0; i<nei.length-1; i++) //link all of old list
			nei[i]=temp[i];
//		System.out.println("created link from "+id+" to "+n.id);
		nei[nei.length-1]=n; //link the new node
//		listNodes();
	}
	
	private void listNodes(){
		for(int index = 0; index<nei.length; index++){
			System.out.println("this node ["+id+"] is linked with "+nei[index].id);
		}
	}
	
	public void unlink(Node n){
//		System.out.println("attempting to unlink ("+this.id+") from ("+n.id+") my nodes are");
//		listNodes();
//		System.out.println("\n");
		
		boolean flag=false;
		Node[] temp = new Node[nei.length]; //create temp buffer
		for(int i = 0; i<nei.length; i++){ //copy all to temp buffer
			if(n==nei[i] || n.id == nei[i].id){
//				System.out.println("found a match");
				flag = true;
			}
			temp[i]=nei[i];
		}
		if(!flag){
			System.out.println("quitting because no match");
			return;
		}
		nei = new Node[temp.length-1]; //create smaller list for linked nodes
		int index = 0;
		for(int i = 0; i<temp.length; i++){ //link all that we keep
			if(!(n==temp[i])){
				
				nei[index]=temp[i];
//				System.out.println("restroing link between ("+this.id+") and ("+nei[index].id+")");
				index++;
				continue;
			}
//			System.out.println("successful unlinking");
		}
//		listNodes();
//		System.out.println("quit unlinking");
	}
	
	
	public void destroy(){
		for(int i = 0; i<nei.length;i++){
			nei[i].unlink(this);
		}
		nei= new Node[]{};
//		System.out.println("still linked to -->");
//		listNodes();
//		System.out.println("<--still linked to ");
	}
	
	public float distance(Node n){
		if(n==null)
			return Float.MAX_VALUE;
		int dx = this.x-n.x;
		int dy = this.y-n.y;
		return (float) (Math.sqrt((dx*dx)+(dy*dy)));
	}
	
	public Node[] getNeighbors(){
		return nei;
	}
	
	/**
	 * Set the parent of this node
	 * 
	 * @param parent The parent node which lead us to this node
	 * @return The depth we have no reached in searching
	 */
	public int setParent(Node parent) {
		depth = parent.depth + 1;
		this.parent = parent;
		
		return depth;
	}
	
	public int compareTo(Object other) {
		Node o = (Node) other;
		float f = heuristic + cost;
		float of = o.heuristic + o.cost;
		
		if (f < of) {
			return -1;
		} else if (f > of) {
			return 1;
		} else {
			return 0;
		}
	}

//	private class Node implements Comparable {
//		/** The x coordinate of the node */
//		private int x;
//		/** The y coordinate of the node */
//		private int y;
//		/** The path cost for this node */
//		private float cost;
//		/** The parent of this node, how we reached it in the search */
//		private Node parent;
//		/** The heuristic cost of this node */
//		private float heuristic;
//		/** The search depth of this node */
//		private int depth;
//		
//		/**
//		 * Create a new node
//		 * 
//		 * @param x The x coordinate of the node
//		 * @param y The y coordinate of the node
//		 */
//		public Node(int x, int y) {
//			this.x = x;
//			this.y = y;
//		}
//		
//		
//		
//		/**
//		 * @see Comparable#compareTo(Object)
//		 */
//		public int compareTo(Object other) {
//			Node o = (Node) other;
//			
//			float f = heuristic + cost;
//			float of = o.heuristic + o.cost;
//			
//			if (f < of) {
//				return -1;
//			} else if (f > of) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}
//	}
}
