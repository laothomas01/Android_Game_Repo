package com.hackslash.game.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.hackslash.game.model.*;

public class game_view {
    player player;

    public game_view() {
        player = new player();
    }

    public void draw_player(Batch batch) {
        batch.begin();
        player.getSprite().setPosition(player.getPosition().x, player.getPosition().y);
        player.getSprite().draw(batch);
        batch.end();
    }


}
