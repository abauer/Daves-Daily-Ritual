package com.ddr.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ddr.game.Sprite;

public class Wall extends Obstacle{

	public Wall(int x, int y, int width, int height, int tlid) {
		super(x, y, width, height, tlid);
		id=new short[1];
		id[0]=(short)tlid;
	}
	
	public void draw(SpriteBatch sb,int abscamx, int abscamy) {
		if(id[0]!=0){
			int ox = 0;
			int oy = 0;
			if(xoffset)
				ox = 16;
			if(yoffset)
				oy = 16;
			for(int i =0; i<width; i++)
				for(int j=0; j<height; j++){
						int absx = (x+i)*Sprite.SIZE+ox-abscamx;
						int absy = (15-1-y-j)*Sprite.SIZE-oy+abscamy;
						sb.draw(Sprite.getSprite(Sprite.cities,0),absx,absy);
				}
		}
	}
}
