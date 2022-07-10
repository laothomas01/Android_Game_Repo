package com.hackslash.game.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.game.model.Game_Object;

public class Player_View {
    public void draw_player(Batch batch, Game_Object p) {
        batch.begin();
        batch.draw(p.getTexture(), p.getPosition().x, p.getPosition().y, p.getCurrent_size() / 2f, p.getCurrent_size());
        batch.end();
    }

}
