package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.GameVersion2.game.driver.GameStateManager;
import com.GameVersion2.game.Managers.AppManager;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setWindowedMode(AppManager.getLocalViewPortWidth(), AppManager.getLocalViewPortHeight());
        config.setTitle("My GDX Game");
        new Lwjgl3Application(new GameStateManager(), config);
    }
}
