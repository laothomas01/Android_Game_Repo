package com.hackslash.game.view;

import com.badlogic.gdx.Game;
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
//    private SpriteBatch bullet_sprite_batch;
//    private SpriteBatch enemy_sprite_batch;
//    private SpriteBatch player_sprite_batch;

//    public SpriteBatch getBullet_sprite_batch() {
//        return bullet_sprite_batch;
//    }
//
//    public SpriteBatch getEnemy_sprite_batch() {
//        return enemy_sprite_batch;
//    }
//
//    public SpriteBatch getPlayer_sprite_batch() {
//        return player_sprite_batch;
//    }

    public Game_Object_View() {
//        bullet_sprite_batch = new SpriteBatch();
//        enemy_sprite_batch = new SpriteBatch();
//        player_sprite_batch = new SpriteBatch();
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

    public void confirm_detection(Game_Object a, Game_Object b) {

        a.getSpriteBatch().begin();

        if (a.hasCollided(a, b)) {

            a.getSpriteBatch().setColor(Color.GREEN);
        } else {
            a.getSpriteBatch().setColor(Color.WHITE);
        }
        a.getSpriteBatch().end();

        b.getSpriteBatch().begin();
        if (!b.hasCollided(a, b)) {
            b.getSpriteBatch().setColor(Color.WHITE);
        } else {
            b.getSpriteBatch().setColor(Color.RED);
        }

        b.getSpriteBatch().end();

    }


}
