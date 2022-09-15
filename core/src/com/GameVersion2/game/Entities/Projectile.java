package com.GameVersion2.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {

    Projectile() {
        this.getPhysics().setSpriteSize(10f, 10f);
        this.getPhysics().setDirectionVector(1, 1);
        this.getPhysics().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.getPhysics().setMoveSpeed(250f);
        graphics.setColor(Color.WHITE);
    }

    Projectile(Vector2 newPosition, Vector2 newDirection, float newSpeed, float newWidth, float newHeight) {
        this.getPhysics().setPosition(newPosition);
        this.getPhysics().setDirectionVector(newDirection);
        this.getPhysics().setMoveSpeed(newSpeed);
        this.getPhysics().setSpriteSize(newWidth, newHeight);
        graphics.setColor(Color.GREEN);
    }


    public String toString() {
        return " BULLET " + this.hashCode() + "    POSITION   " + this.getPhysics().getPosition().toString() + "   HEADING VECTOR    " + this.getPhysics().getDirectionVector();
    }




}
