package com.hackslash.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.hackslash.game.model.GameObject;

public class PlayerView extends GameObjectView {
    //    public PlayerView() {
//
//    }
//
    public void draw_player(Batch batch, GameObject p) {
        batch.begin();
        batch.draw(p.getTexture(), p.getPosition().x, p.getPosition().y, p.getCurrent_size(), p.getCurrent_size());
        batch.end();
    }
}
