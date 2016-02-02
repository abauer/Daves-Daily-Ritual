package com.ddr.game.path;

public class Node implements Comparable{
	
	int x, y; //xy in px format
	int[] nei;
	int len;
	int cap;
	float cost;
	public Node parent;
	/** The heuristic cost of this node */
	public float heuristic;
	/** The search depth of this node */
	public int depth;
	public int id;
	
	public Node(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id; 
//		System.out.println("created node ("+id+") at ["+this.x+", "+this.y+"]");
		cost = 0;
		len = 0;
		cap = 1;
		nei = new int[cap];
	}
		
	public void linkNode(int link){
//		System.out.println("len:"+len +"array len"+nei.length);
		len+=1;
//		System.out.println("len:"+len+ "cap:"+cap);
		if(len>cap){
			int[] temp = new int[len-1];
			for(int i = 0; i<len-1; i++ )
				temp[i]=nei[i];
			cap*=2;
			nei = new int[cap];
			for(int i = 0; i<len-1; i++)
				nei[i] = temp[i];
		}
//		System.out.println("len:"+len+ "cap:"+cap);
		nei[len-1]=link;
	}
	
	private void listNodes(){
		for(int index = 0; index<nei.length; index++){
			System.out.println("this node ["+id+"] is linked with "+nei[index]);
		}
	}
	
	public void unlink(Node n){
		for(int i=0; i<len; i++){
			if(n.id == nei[i]){
				nei[i]=nei[len-1];
				len--;
				return;
			}
		}
	}
	
	public float distance(Node n){
		if(n==null)
			return Float.MAX_VALUE;
		int dx = this.x-n.x;
		int dy = this.y-n.y;
		return (float) (Math.sqrt((dx*dx)+(dy*dy)));
	}
	
	public int[] getNeighbors(){
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

	public void destroy() {
		nei = null;
		
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
