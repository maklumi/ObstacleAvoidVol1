package com.obstacle.avoid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.obstacle.avoid.ObstacleAvoidGame;
import com.obstacle.avoid.config.GameConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameConfig.INSTANCE.getWIDTH();
		config.height = (int) GameConfig.INSTANCE.getHEIGHT();
		new LwjglApplication(new ObstacleAvoidGame(), config);
	}
}
