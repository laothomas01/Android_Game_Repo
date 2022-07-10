package com.hackslash.game.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.game.model.Game_Object;

public class Bullet_View {

    public void draw_bullets(Batch batch, Game_Object b) {

        batch.begin();
        batch.draw(b.getTexture(), b.getPosition().x, b.getPosition().y, b.getCurrent_size(), b.getCurrent_size());
        batch.end();

    }
}
