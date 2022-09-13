package com.GameVersion2.game.Entities;

import com.badlogic.gdx.graphics.Color;

public class ExpDrop extends Entity {
    public ExpDrop() {
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(5, 5);
        this.getGraphics().getSprite().setRotation(45);
        graphics.setColor(Color.GREEN);
    }
}
