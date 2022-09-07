package com.GameVersion2.game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

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

    JsonReader json = new JsonReader();

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

    /**
     * Deserializing objects from JSON
     */
    public static JsonValue loadJsonFile(String jsonFile) {
        JsonReader json = new JsonReader();
        JsonValue objectGraph = json.parse(Gdx.files.internal(jsonFile));
        return objectGraph;
    }

    public static JsonValue getJsonObject(int index, JsonValue objectGraph) {
        return objectGraph.get(index);
    }

    public static JsonValue getJsonObject(String objectName, JsonValue objectGraph) {
        return objectGraph.get(objectName);
    }



}
