package com.cubesquare.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cubesquare.controlador.CubeSquare;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1920;
		config.height=1080;
		config.addIcon("cubo16x16.png", Files.FileType.Local);
		config.addIcon("cubo32x32.png", Files.FileType.Local);
		new LwjglApplication(new CubeSquare(), config);
	}
}
