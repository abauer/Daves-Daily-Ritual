package com.ddr.game.path;

import com.ddr.game.path.Path.Step;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(NodeManager map, Mover mover, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
//		Step[] s = mover.getLastSteps();
//		for(int i = 0; i<s.length; i++){
//			if(s[i]!=null)
//				if(s[i].getX()==x&&s[i].getY()==y){
//					System.out.println("added Heuristic");
//					result+=.5;
//				}
//		}
		
		return result;
	}

}
