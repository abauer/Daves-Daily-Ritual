package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;
import com.ddr.game.state.LV1;

public class Wall extends Obstacle{

	public Wall(int x, int y, int width, int height, int tlid) {
		super(x, y, width, height, tlid);
		id=new short[1];
		id[0]=(short)tlid;
	}
	
	public void draw(SpriteBatch sb,int camX, int camY) {
		int ox = 0;
		int oy = 0;
		if(xoffset)
			ox = 16;
		if(yoffset)
			oy = 16;
		for(int i =0; i<width; i++)
			for(int j=0; j<height; j++){
					sb.draw(Sprite.getSprite(Entity.entities,0),(x+i-camX)*Sprite.SIZE+ox,(15-1-y-j+camY)*Sprite.SIZE-oy);
			}
	}
}
