package com.gdx.orphanrpg;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.gdx.orphanrpg.ORPG;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("OrphanageRPG");
		config.setWindowedMode(1920, 1080);
		config.useVsync(true);
		config.setResizable(false);
		while (true) {
			new Lwjgl3Application(new ORPG(), config);
		}
	}
}
