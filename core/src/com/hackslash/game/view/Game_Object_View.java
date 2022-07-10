package com.hackslash.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
    SpriteBatch batch;
    Stage stage;
    Touchpad touchpad;
    Touchpad.TouchpadStyle touchpadStyle;
    Skin skin;
    Drawable touchBackground;
    Drawable touchKnob;
    //Healthbar UI
    ShapeRenderer sr;
    Color clr;
    Sprite sprite;
    Texture tex;

    public Game_Object_View() {
//        bullet_sprite_batch = new SpriteBatch();
//        enemy_sprite_batch = new SpriteBatch();
//        player_sprite_batch = new SpriteBatch();
    }


//    public void confirm_detection(Game_Object a, Game_Object b) {
//
//        a.getSpriteBatch().begin();
//
//        if (a.hasCollided(a, b)) {
//
//            a.getSpriteBatch().setColor(Color.GREEN);
//        } else {
//            a.getSpriteBatch().setColor(Color.WHITE);
//        }
//        a.getSpriteBatch().end();
//
//        b.getSpriteBatch().begin();
//        if (!b.hasCollided(a, b)) {
//            b.getSpriteBatch().setColor(Color.WHITE);
//        } else {
//            b.getSpriteBatch().setColor(Color.RED);
//        }
//
//        b.getSpriteBatch().end();
//
//    }


}
