package com.GameVersion2.game.Entities;

import com.badlogic.gdx.graphics.Color;

public class Enemy extends Entity {


    //enemy type
    int type;
    //----------enemy type values-----------
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int LARGE = 2;
    // -------------------------------


    public Enemy(float x, float y, int t) {
        type = t;
        getPhysics().setPosition(x, y);
        if (getType() == SMALL) {
            getPhysics().setSpriteWidth(10f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(70f);
            getPhysics().setMoveSpeed(0f);
            getGraphics().getColor().set(Color.RED);
        } else if (getType() == MEDIUM) {
            getPhysics().setSpriteWidth(12f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(50f);
            getPhysics().setMoveSpeed(0f);
            getGraphics().getColor().set(Color.RED);
        } else if (getType() == LARGE) {
            getPhysics().setSpriteWidth(20f);
            getPhysics().setSpriteHeight(getPhysics().getSpriteWidth());
//            getPhysics().setMoveSpeed(10);
            getPhysics().setMoveSpeed(0);
            getGraphics().getColor().set(Color.RED);
        }
        getPhysics().setDirectionVector(1, 1);
    }


    public int getType() {
        return type;
    }


    public String toString() {
        return "\n--------------------------------\n" +
                "ENEMY:" + this.hashCode() +
                "\n" +
                "POSITION:" + this.getPhysics().getPosition()
                + "\n"
                + "DIRECTION VECTOR:" + this.getPhysics().getMovementDirection() +
                "\n" +
                "MOVE SPEED:"
                + this.getPhysics().getMoveSpeed() +
                "\n" + "SIZE:" + this.getPhysics().getSpriteWidth() + "," + this.getPhysics().getSpriteHeight() + "\n" +
                "-------------------------------------" + "\n";
    }

}
