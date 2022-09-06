package com.GameVersion2.game.Managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

/**
 * This will be for desktop platform
 */
//Handles when gameplay begins, pauses and ends
public class GameInputProcessor extends InputAdapter {
    public boolean keyDown(int k) {
        if (k == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if (k == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if (k == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if (k == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if (k == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if (k == Input.Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESC, true);
        }

        if (k == Input.Keys.W) {
            GameKeys.setKey(GameKeys.W, true);
        }
        if (k == Input.Keys.A) {
            GameKeys.setKey(GameKeys.A, true);
        }
        if (k == Input.Keys.S) {
            GameKeys.setKey(GameKeys.S, true);
        }
        if (k == Input.Keys.D) {
            GameKeys.setKey(GameKeys.D, true);
        }

        return true;
    }

    public boolean keyUp(int k) {
        if (k == Input.Keys.UP) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if (k == Input.Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if (k == Input.Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if (k == Input.Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if (k == Input.Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if (k == Input.Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESC, false);
        }
        if (k == Input.Keys.W) {
            GameKeys.setKey(GameKeys.W, false);
        }
        if (k == Input.Keys.A) {
            GameKeys.setKey(GameKeys.A, false);
        }
        if (k == Input.Keys.S) {
            GameKeys.setKey(GameKeys.S, false);
        }
        if (k == Input.Keys.D) {
            GameKeys.setKey(GameKeys.D, false);
        }

        return true;
    }

    /**
     * This will be for desktop platform
     */
    public static class GameKeys {
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
}
