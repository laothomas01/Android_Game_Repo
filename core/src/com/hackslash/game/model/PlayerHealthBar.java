package com.hackslash.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayerHealthBar {

    Player player;
    Color cl;

    public PlayerHealthBar(Player player){
        this.player = player;
        cl = Color.GREEN;
    }

    public void draw(ShapeRenderer sr, int size) {
        sr.setColor(cl);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(250, 100, size, 50);
        sr.end();
    }

    public void changeColor(Color newCL){
        this.cl = newCL;
    }

}
