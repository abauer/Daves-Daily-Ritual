package com.ddr.game;

import com.ddr.game.entity.Entity;
import com.ddr.game.entity.Player;
import com.ddr.game.entity.Wall;

public class LevelDefs {
	public static short one[]={171,203,203,203,203,203,203,203,203,172,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,151,203,203,203,203,203,203,172,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,96,96,96,96,96,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,171,202,202,202,202,202,202,96,96,202,199,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			176,96,96,96,96,96,175,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
			198,202,202,202,202,202,199,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19};
	
	public static Entity[] oneE = {new Wall(0,0,10,1,0),new Wall(0,1,1,26,0),new Wall(1,26,6,1,0),new Wall(6,23,1,3,0),new Wall(6,23,10,1,0),
								new Wall(16,5,1,17,0),new Wall(10,5,6,1,0),new Wall(9,1,1,5,0)};
	public static Player oneP = new Player(1,1,1,1,10);
	
}
