package com.hackslash.GAME_VERSION1.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.GAME_VERSION1.model.GameObject;

public class PlayerView extends GameObjectView {
    //    public PlayerView() {
//
//    }
//
    public void draw_player(Batch batch, GameObject p) {
        batch.begin();
        batch.draw(p.getTexture(), p.getPosition().x, p.getPosition().y, p.getCurrent_size()/2, p.getCurrent_size());
        batch.end();
    }
}
