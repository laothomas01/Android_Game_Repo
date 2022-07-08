package com.hackslash.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackslash.game.model.*;

/**
 * Textures are draw with rectangular geometry
 */
public class Game_Object_View {

    //used to send each texture's rectangle to the GPU(is this related to computer hardware or a completely different acronym??)
    // all at once
    private SpriteBatch spriteBatch;

    public Game_Object_View() {
        spriteBatch = new SpriteBatch();
    }

    public void draw_player(Batch batch, Game_Object p) {
        batch.begin();
        batch.draw(p.getTexture(), p.getPosition().x, p.getPosition().y, p.getCurrent_size() / 2f, p.getCurrent_size());
        batch.end();
    }

    public void draw_enemy(Batch batch, Game_Object e) {
        batch.begin();

        batch.draw(e.getTexture(), e.getPosition().x, e.getPosition().y, e.getCurrent_size(), e.getCurrent_size());

        batch.end();
    }

    public void draw_bullets(Batch batch, Game_Object b) {
        batch.begin();
        batch.draw(b.getTexture(), b.getPosition().x, b.getPosition().y, b.getCurrent_size(), b.getCurrent_size());
        batch.end();
    }

    public void confirm_detection(SpriteBatch batch, Game_Object a) {
        batch.begin();
        batch.setColor(Color.GREEN);
        batch.end();

    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }


}
