package com.GameVersion2.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity {


    int type;
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;


    boolean remove = false;


    //better constructor
    public Enemy(float x, float y, int t) {
        this.setState(State.ALIVE);
        type = t;
        getPhysics().setPosition(x, y);
        if (getType() == SMALL) {
            getPhysics().setSpriteWidth(10f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(70f);
            getPhysics().setMoveSpeed(0f);
        } else if (getType() == MEDIUM) {
            getPhysics().setSpriteWidth(12f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(50f);
            getPhysics().setMoveSpeed(0f);

            getGraphics().setColor(Color.YELLOW);
        } else if (getType() == LARGE) {
            getPhysics().setSpriteWidth(20f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(10);
            getPhysics().setMoveSpeed(0);

            getGraphics().setColor(Color.BROWN);
        }
        getPhysics().setDirectionVector(1, 1);
    }

    //this function doesnt work properly
    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

    public void Update(float dt) {
        update(dt);
        if (this.lifeSpanTimer < 0) {
            remove = true;
        } else {
            this.lifeSpanTimer -= dt;
        }
    }


    public String toString() {
        return "ENEMY:\n" + "POSITION:" + this.getPhysics().getPosition() + "\nDIRECTION VECTOR: " + this.getPhysics().getDirectionVector() + "\nMOVE SPEED: " + this.getPhysics().getMoveSpeed() + "\n" + "SIZE:" + this.getPhysics().getSpriteWidth() + "," + this.getPhysics().getSpriteHeight();
    }

}
