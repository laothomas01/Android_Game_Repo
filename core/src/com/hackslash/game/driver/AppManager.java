package com.hackslash.game.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Class will be created for the following features:
 * -handling toggling of settings
 * -saving and load data
 * -managing screen size
 */
public class AppManager {


    static int screenWidth = 800;
    static int screenHeight = 600;


    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }


    public static void setScreenWidth(int width) {
        screenWidth = width;
    }

    public static void setScreenHeight(int height) {
        screenHeight = height;
    }

}
