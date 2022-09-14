package com.GameVersion2.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class ExpDrop extends Entity {

    public ExpDrop(float x, float y) {
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(5, 5);
        this.getPhysics().setPosition(x, y);
        this.getGraphics().getSprite().setRotation(45);
        graphics.setColor(Color.GREEN);
    }

    public ExpDrop(Vector2 pos) {
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(5, 5);
        this.getPhysics().setPosition(pos);
        this.getGraphics().getSprite().setRotation(45);
        graphics.setColor(Color.GREEN);
    }
}
