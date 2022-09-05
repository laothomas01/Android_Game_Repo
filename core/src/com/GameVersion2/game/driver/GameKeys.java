package com.GameVersion2.game.driver;

/**
 * This will be for desktop platform
 */
class GameKeys {
    //if false, key is released. if true, key is pressed
    private static boolean[] keys;
    //previous state of keys
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 10;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int ESC = 4;
    public static final int ENTER = 5;
    public static final int W = 6;
    public static final int A = 7;
    public static final int S = 8;
    public static final int D = 9;


    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public static boolean isDown(int k) {
        return keys[k];
    }

    public static boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}
