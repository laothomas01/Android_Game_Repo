package com.hackslash.game.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackslash.game.model.*;

/**
 * This class will be used to display game objects and UI
 */
public class game_object_view {
    private Player player;
    private SpriteBatch spriteBatch;


    public game_object_view(Player p) {
        player = p;
        spriteBatch = new SpriteBatch();
    }

    public void draw_player(Batch batch) {
        batch.begin();
        player.getSprite().setPosition(player.getPosition().x, player.getPosition().y);
        player.getSprite().draw(batch);
        batch.end();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }


}
