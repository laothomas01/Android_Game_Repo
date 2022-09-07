package com.GameVersion2.game.Entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends Entity {

    int type = 0;
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;

    boolean remove = false;

    public Enemy() {

    }

    //better constructor
    public Enemy(float x, float y, int t) {
        getPhysics().setPosition(x, y);
        if (getType() == SMALL) {
            getPhysics().setSpriteWidth(10f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
            getPhysics().setMoveSpeed(MathUtils.random(0, 0));
        } else if (getType() == MEDIUM) {
            getPhysics().setSpriteWidth(12f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
            getPhysics().setMoveSpeed(MathUtils.random(100, 120));
        } else if (getType() == LARGE) {
            getPhysics().setSpriteWidth(20f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
            getPhysics().setMoveSpeed(MathUtils.random(70, 80));
        }
        getPhysics().setDirectionVector(1, 1);
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }
//    public Enemy() {

//        this.getPhysics().setPosition(1, 0);
//        this.getPhysics().setDirectionVector(1, 1);
//        this.getPhysics().setSpriteSize(10f, 10f);
//        graphics.setColor(Color.RED);
//        this.getPhysics().setMoveSpeed(40f);
//    }

//    public Enemy(Vector2 position, float width, float height, float speed) {
//        this.getPhysics().setPosition(position);
//        this.getPhysics().setSpriteSize(width, height);
//        this.getPhysics().setMoveSpeed(speed);
//        graphics.setColor(Color.RED);
//    }
//
//    public Enemy(float x, float y, float width, float height, float speed) {
//        this.getPhysics().setPosition(x, y);
//        this.getPhysics().setSpriteSize(width, height);
//        this.getPhysics().setMoveSpeed(speed);
//        graphics.setColor(Color.RED);
//}

    public void Update(float dt) {
        update(dt);
        if (this.lifeSpanTimer < 0) {
            remove = true;
        } else {
            this.lifeSpanTimer -= dt;
        }
        this.getPhysics().move(dt);
    }


    public String toString() {
        return "ENEMY:\n" + "POSITION:" + this.getPhysics().getPosition() + "\nDIRECTION VECTOR: " + this.getPhysics().getDirectionVector() + "\nMOVE SPEED: " + this.getPhysics().getMoveSpeed()
                + "\n" + "SIZE:" + this.getPhysics().getSpriteWidth() + "," + this.getPhysics().getSpriteHeight();
    }

}
