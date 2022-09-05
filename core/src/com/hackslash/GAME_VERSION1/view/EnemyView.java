package com.hackslash.GAME_VERSION1.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.GAME_VERSION1.model.GameObject;

public class EnemyView extends GameObjectView {
    //    public EnemyView() {
//
//    }
    public void draw_enemy(Batch batch, GameObject e) {

        batch.begin();
        batch.draw(e.getTexture(), e.getPosition().x, e.getPosition().y, e.getCurrent_size(), e.getCurrent_size());
        batch.setColor(Color.LIME);
        batch.end();

    }
}
