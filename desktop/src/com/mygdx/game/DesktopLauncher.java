package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.hackslash.game.driver.hack_and_slash;
import com.hackslash.game.driver.hack_and_slashVersion2;
import com.hackslashVersion2.game.driver.main;
import com.mygdx.game.DesktopLauncher;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setWindowedMode(800, 600);
        config.setTitle("My GDX Game");
        new Lwjgl3Application(new main(),config);
    }
}
