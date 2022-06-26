package com.hackslash.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackslash.game.model.*;

/**
 * This class will be used to display game objects
 */
public class Game_Object_View {
    private SpriteBatch spriteBatch;

    public Game_Object_View() {
        spriteBatch = new SpriteBatch();
    }

    public void draw_player(Batch batch, Game_Object p) {
        batch.begin();
        p.getSprite().setPosition(p.getPosition().x, p.getPosition().y);
        p.getSprite().draw(batch);
        batch.end();
    }

    public void draw_enemy(Batch batch, Game_Object e) {
        batch.begin();
        e.getSprite().setPosition(e.getPosition().x, e.getPosition().y);
        e.getSprite().draw(batch);
        batch.end();
    }

    public void draw_bullets(Batch batch, Game_Object b) {
        batch.begin();
        b.getSprite().setPosition(b.getPosition().x, b.getPosition().y);
        b.getSprite().draw(batch);
        batch.end();
    }

    public void confirm_detection(SpriteBatch batch, Game_Object a, Game_Object b) {
        batch.begin();

        if (a.hasCollided(a, b)) {
            batch.setColor(Color.GREEN);
            batch.draw(a.getTexture(), a.getPosition().x, a.getPosition().y, a.getCurrent_size(), a.getCurrent_size());
            batch.draw(b.getTexture(), b.getPosition().x, b.getPosition().y, b.getCurrent_size(), b.getCurrent_size());

        }
        batch.end();

    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }


}
