package com.hackslash.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hackslash.game.model.*;

/**
 * This class will be used to display game objects and UI
 */
public class game_object_view {
    private player player;
    private SpriteBatch spriteBatch;


    public game_object_view() {
        player = new player();
        spriteBatch = new SpriteBatch();
    }

    public void draw_player(Batch batch) {
        batch.begin();
        player.getSprite().setPosition(player.getX(), player.getY());
        player.getSprite().draw(batch);
        batch.end();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }



}
