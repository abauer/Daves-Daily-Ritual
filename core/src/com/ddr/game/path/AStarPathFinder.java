package com.ddr.game.path;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm
 * to determine a path. 
 * 
 */
public class AStarPathFinder implements PathFinder {
	/** The set of nodes that have been searched through */
	private ArrayList closed = new ArrayList();
	/** The set of nodes that we do not yet consider fully searched */
	private SortedList open = new SortedList();
	
	/** The map being searched */
	private NodeManager map;
	/** The maximum depth of search we're willing to accept before giving up */
	private int maxSearchDistance;
	
	/** The heuristic we're applying to determine which nodes to search first */
	private AStarHeuristic heuristic;
	
	/**
	 * Create a path finder with the default heuristic - closest to target.
	 * 
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(NodeManager map, int maxSearchDistance) {
		this(map, maxSearchDistance, new ClosestHeuristic());
	}

	/**
	 * Create a path finder 
	 * 
	 * @param heuristic The heuristic used to determine the search order of the map
	 * @param map The map to be searched
	 * @param maxSearchDistance The maximum depth we'll search before giving up
	 * @param allowDiagMovement True if the search should try diaganol movement
	 */
	public AStarPathFinder(NodeManager map, int maxSearchDistance, AStarHeuristic heuristic) {
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
	}
	
	/**
	 * @see PathFinder#findPath(Mover, int, int, int, int)
	 */
	public Path findPath(Mover mover, int sx, int sy, int tx, int ty) {
		// easy first check, if the destination is blocked, we can't get there
//		if (map.blocked(mover, tx, ty)) {
//			System.out.println("destination blocked");
//			return null;
//		}
		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and it'e're already there
//		System.out.println("setup: clear lists");
		closed.clear();
		open.clear();
//		System.out.println("setup: create Mover node");
		Node z = map.newMoverNode(sx, sy);
		Node zombie = z;
		zombie.cost = 0;
		zombie.depth = 0;
//		System.out.println("setup: create Target node");
		Node player = map.newMoverNode(tx,ty);
		Node target = player;
		target.cost = 0;
		target.depth = 0;
//		System.out.println("setup: add mover to open list");
		open.add(zombie); 
		target.parent = null;
		// while we haven'n't exceeded our max search depth
		int maxDepth = 0;
//		System.out.println("find dem nodes");
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// pull out the first node in our open list, this is determined to 
			// be the most likely to be the next step based on our heuristic
			Node current = getFirstInOpen();
//			System.out.println("find: get most likely node");
			if (map.isClosestNode(current,tx,ty)) {//found exit
//				System.out.println("i think we found a target");
				break;
			}
//			System.out.println("find: manage lists, now closed");
			removeFromOpen(current);
			addToClosed(current);
			
			// search through all the neighbors of the current node evaluating
			// them as next steps
//			System.out.println("find: iterate over neighbors");
			Node[] neis = map.getNeighbors(current); 
			for(int i = 0; i<neis.length; i++){
				int xp = neis[i].x;
				int yp = neis[i].y;
//				System.out.println("nei: ["+xp+", "+yp+"]");
//				if (isValidLocation(mover,sx,sy,xp,yp)) {
					// the cost to get to this node is cost the current plus the movement
					// cost to reach this node. Note that the heursitic value is only used
					// in the sorted open list
//					System.out.println("nei: calc next cost");
					float nextStepCost = current.cost + getMovementCost(mover, current.x, current.y, xp, yp);
					Node neighbour = neis[i];
//						map.pathFinderVisited(xp, yp); //for debug
					
					// if the new cost we've determined for this node is lower than 
//					 it has been previously makes sure the node hasn'e've
					// determined that there might have been a better path to get to
					// this node so it needs to be re-evaluated
//					System.out.println("nei: determine if it should be less");
					if (nextStepCost < neighbour.cost) {
//						System.out.println("nei: new shorter path, fix lists");
						if (inOpenList(neighbour)) {
							removeFromOpen(neighbour);
						}
						if (inClosedList(neighbour)) {
							removeFromClosed(neighbour);
						}
					}
					
					// if the node hasn't already been processed and discarded then

					// reset it's cost to our current cost and add it as a next possible

					// step (i.e. to the open list)
//					System.out.println("nei: have we already done this node?");
					if (!inOpenList(neighbour) && !(inClosedList(neighbour))) {
//						System.out.println("nei: no - add to open list");
						neighbour.cost = nextStepCost;
						neighbour.heuristic = getHeuristicCost(mover, xp, yp, tx, ty);
						maxDepth = Math.max(maxDepth, neighbour.setParent(current));
						addToOpen(neighbour);
					}
				}
//				System.out.println("find: keep going till found player");
			}
//		}

		// since we'e've run out of search 
		
//		System.out.println("unlink Player");
		
		
		// there was no path. Just return null
		
		if (target.parent == null) {
//			System.out.println("find: was no path");
			map.destroyNode(z);
			map.destroyNode(player);
			return null;
		}
		
		// At this point we've definitely found a path so we can uses the parent
		// references of the nodes to find out way from the target location back
		// to the start recording the nodes on the way.
//		System.out.println("find: create path");
		Path path = new Path();
		while (target != zombie) {
			path.prependStep(target.x, target.y);
			target = target.parent;
		}
		path.prependStep(sx,sy);
		
		// thats it, we have our path 
		
//		System.out.println("unlink Zombie");
		map.destroyNode(z);
		map.destroyNode(player);
		return path;
	}

	/**
	 * Get the first element from the open list. This is the next
	 * one to be searched.
	 * 
	 * @return The first element in the open list
	 */
	protected Node getFirstInOpen() {
		return (Node) open.first();
	}
	
	/**
	 * Add a node to the open list
	 * 
	 * @param node The node to be added to the open list
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}
	
	/**
	 * Check if a node is in the open list
	 * 
	 * @param node The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}
	
	/**
	 * Remove a node from the open list
	 * 
	 * @param node The node to remove from the open list
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
	
	/**
	 * Add a node to the closed list
	 * 
	 * @param node The node to add to the closed list
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}
	
	/**
	 * Check if the node supplied is in the closed list
	 * 
	 * @param node The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}
	
	/**
	 * Remove a node from the closed list
	 * 
	 * @param node The node to remove from the closed list
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}
	
	/**
	 * Check if a given location is valid for the supplied mover
	 * 
	 * @param mover The mover that would hold a given location
	 * @param sx The starting x coordinate
	 * @param sy The starting y coordinate
	 * @param x The x coordinate of the location to check
	 * @param y The y coordinate of the location to check
	 * @return True if the location is valid for the given mover
	 */
//	protected boolean isValidLocation(Mover mover, int sx, int sy, int x, int y) {
//		boolean invalid = (x < 0) || (y < 0) || (x >= map.getWidthInTiles()) || (y >= map.getHeightInTiles());
//		
//		if ((!invalid) && ((sx != x) || (sy != y))) {
//			invalid = map.blocked(mover, x, y);
//		}
//		
//		return !invalid;
//	}
	
	/**
	 * Get the cost to move through a given location
	 * 
	 * @param mover The entity that is being moved
	 * @param sx The x coordinate of the tile whose cost is being determined
	 * @param sy The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The cost of movement through the given tile
	 */
	public float getMovementCost(Mover mover, int sx, int sy, int tx, int ty) {
		return map.getCost(mover, sx, sy, tx, ty);
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which 
	 * order the locations are processed.
	 * 
	 * @param mover The entity that is being moved
	 * @param x The x coordinate of the tile whose cost is being determined
	 * @param y The y coordiante of the tile whose cost is being determined
	 * @param tx The x coordinate of the target location
	 * @param ty The y coordinate of the target location
	 * @return The heuristic cost assigned to the tile
	 */
	public float getHeuristicCost(Mover mover, int x, int y, int tx, int ty) {
		return heuristic.getCost(map, mover, x, y, tx, ty);
	}
	
	/**
	 * A simple sorted list
	 *
	 */
	private class SortedList {
		/** The list of elements */
		private ArrayList list = new ArrayList();
		
		/**
		 * Retrieve the first element from the list
		 *  
		 * @return The first element from the list
		 */
		public Object first() {
			return list.get(0);
		}
		
		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}
		
		/**
		 * Add an element to the list - causes sorting
		 * 
		 * @param o The element to add
		 */
		public void add(Object o) {
			list.add(o);
			Collections.sort(list);
		}
		
		/**
		 * Remove an element from the list
		 * 
		 * @param o The element to remove
		 */
		public void remove(Object o) {
			list.remove(o);
		}
	
		/**
		 * Get the number of elements in the list
		 * 
		 * @return The number of element in the list
 		 */
		public int size() {
			return list.size();
		}
		
		/**
		 * Check if an element is in the list
		 * 
		 * @param o The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(Object o) {
			return list.contains(o);
		}
	}
}
