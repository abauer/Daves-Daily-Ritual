package com.ddr.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ddr.game.DavesDailyRitual;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = DavesDailyRitual.TITLE;
		config.width = DavesDailyRitual.WIDTH * DavesDailyRitual.SCALE;
		config.height = DavesDailyRitual.HEIGHT * DavesDailyRitual.SCALE;
		config.resizable = false;
		config.addIcon("icon_128.png", Files.FileType.Internal);
		config.addIcon("icon_32.png", Files.FileType.Internal);
		config.addIcon("icon_16.png", Files.FileType.Internal);

		new LwjglApplication(new DavesDailyRitual(), config);
	}
}
