package com.ddr.game.path;

import com.ddr.game.Sprite;

public class Node {
	
	int x, y; //xy in px format
	Node[] nei;
	int count;
	float cost;
	public Node parent;
	/** The heuristic cost of this node */
	public float heuristic;
	/** The search depth of this node */
	private int depth;
	
	public Node(int x, int y){
		this.x = x*Sprite.SIZE;
		this.y = y*Sprite.SIZE;
		count = 0;
		cost = 0;
	}
	
	public void linkNode(Node n){
//		System.out.println("there are "+count+"nodes");
		Node[] temp = new Node[count];
		for(int i = 0; i<count; i++){
			temp[i]=nei[i];
		}
		count++;
		nei = new Node[count];
		for(int i = 0; i<count-1; i++)
			nei[i]=temp[i];
		nei[count-1]=n;
	}
	
	public float distance(Node n){
		int dx = this.x-n.x;
		int dy = this.y-n.y;
		return (float) Math.sqrt(dx*dx+dy*dy);
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
