package com.ddr.game.entity;

import com.badlogic.gdx.audio.Music;

public class AnimatedCoolObject extends CoolObject{

	short nextid = 0;
	
	public AnimatedCoolObject(int x, int y, int width, int height, short id,String objectText, String pauseText, Music effect) {
		super(x, y, width, height, objectText, pauseText, effect);
	}
	
	protected void actionCompleted(){
		super.actionCompleted();
		id[0]=this.nextid;
	}

}
