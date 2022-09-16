package com.GameVersion2.game.Entities;

import com.GameVersion2.game.Util.Graphics2D;
import com.GameVersion2.game.Util.Physics2D;

/**
 * An Entity is an object that can be transformed, scaled, or rotated
 */


public class Entity {
    //physics2D manager
    Physics2D physics;
    //graphics2D manager
    Graphics2D graphics;
    float maxLifeSpan = 2f;
    float lifeSpanTimer = 0;

    public enum State {DEAD, ALIVE}

    public State state = null;

    public String getState() {
        return this.state.toString();
    }

    public void setState(State s) {
        this.state = s;
    }

    public Entity() {
        //give your game objects a physics2D component
        physics = new Physics2D();

        graphics = new Graphics2D();
    }

    public float getMaxLifeSpan() {
        return this.maxLifeSpan;
    }

    public void setLifeSpanTimer(float t) {
        this.lifeSpanTimer = t;
    }

    public float getLifeSpanTimer() {
        return this.lifeSpanTimer;
    }

    public Graphics2D getGraphics() {
        return this.graphics;
    }

    public Physics2D getPhysics() {
        return this.physics;
    }


    public void update(float dt) {

        graphics.getSprite().setSize(this.getPhysics().getSpriteWidth(), this.getPhysics().getSpriteHeight());
        graphics.getSprite().setPosition(this.getPhysics().getPosition().x, this.getPhysics().getPosition().y);
        graphics.getSprite().setTexture(graphics.getTexture());
        graphics.getSprite().setColor(graphics.getColor());
        //if not in viewport, do not draw. handle later
        this.getGraphics().drawSprite();
        //can disable the speed of an object by not giving them speed
        this.getPhysics().move(dt);
    }

    //override it
    public void shoot(Entity target, float dt) {

    }

    public boolean lifeSpanExpired(float dt) {
        this.setLifeSpanTimer(this.getLifeSpanTimer() + dt);
        if (this.getLifeSpanTimer() > this.getMaxLifeSpan()) {
            this.setLifeSpanTimer(this.getLifeSpanTimer() - this.getMaxLifeSpan());
            System.out.println("    BULLET    " + this.hashCode() + " HAS EXPIRED ");
            return true;
        }
        return false;
    }


}
