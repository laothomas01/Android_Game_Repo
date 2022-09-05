package com.hackslash.GAME_VERSION1.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.GAME_VERSION1.model.GameObject;

public class BulletView extends GameObjectView {

//    public BulletView() {
//
//    }

    public void draw_bullets(Batch batch, GameObject b) {

        batch.begin();
        batch.draw(b.getTexture(), b.getPosition().x, b.getPosition().y, b.getCurrent_size(), b.getCurrent_size());
        batch.end();
    }
}
