package com.hackslash.game.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackslash.game.model.*;

/**
 * This class will be used to display game objects
 */
public class game_object_view {
    private SpriteBatch spriteBatch;

    public game_object_view() {
        spriteBatch = new SpriteBatch();
    }

    public void draw_player(Batch batch, Player p) {
        batch.begin();
        p.getSprite().setPosition(p.getPosition().x, p.getPosition().y);
        p.getSprite().draw(batch);
        batch.end();
    }

    public void draw_enemy(Batch batch, Enemy e) {
        batch.begin();
        e.getSprite().setPosition(e.getPosition().x, e.getPosition().y);
        e.getSprite().draw(batch);
        batch.end();
    }

    public void draw_bullets(Batch batch, Bullet b) {
        batch.begin();
        b.getSprite().setPosition(b.getPosition().x, b.getPosition().y);
        b.getSprite().draw(batch);
        batch.end();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }


}
