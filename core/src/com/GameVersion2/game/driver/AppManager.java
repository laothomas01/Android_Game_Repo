package com.GameVersion2.game.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Class will be created for the following features:
 * -handling toggling of settings
 * -saving and load data
 * -manages global viewport
 * -manages local viewport
 */
public class AppManager {


    static int localViewPortWidth = 1000;
    static int localViewPortHeight = 700;
    static int globalViewPortHeight = 1400;
    static int globalViewPortWidth = 2000;

    public static int getGetGlobalViewPortHeight() {
        return globalViewPortHeight;
    }

    public static void setGetGlobalViewPortHeight(int height) {
        globalViewPortHeight = height;
    }

    public static int getGlobalViewPortWidth() {
        return globalViewPortWidth;
    }

    public static void setGlobalViewPortWidth(int width) {
        globalViewPortWidth = width;
    }

    public static int getLocalViewPortWidth() {
        return localViewPortWidth;
    }

    public static int getLocalViewPortHeight() {
        return localViewPortHeight;
    }

    public static void setLocalViewPortWidth(int width) {
        localViewPortWidth = width;
    }

    public static void setLocalViewPortHeight(int height) {
        localViewPortHeight = localViewPortHeight;
    }


}
