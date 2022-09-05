package com.GameVersion2.game.driver;

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
}
