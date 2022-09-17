package com.GameVersion2.game.Entities;

import com.GameVersion2.game.Util.Graphics2D;
import com.GameVersion2.game.Util.Physics2D;

/**
 * An Entity is an object that can be transformed, scaled, or rotated
 */


public class Entity {

    //physics manager
    Physics2D physics;
    //graphics manage
    Graphics2D graphics;

    /**
     * Non-Graphic or Physics Fields
     */
    //------------------------------------------------------------------
    //max time an entity can stay around
    float maxLifeSpan = 2f;
    //timer to see how long it takes before expiration sets in
    float lifeSpanTimer = 0;


    //----------------------------------------------------
    public Entity() {
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

        //-------------------if not in viewport, do not draw. handle later--

        //@TODO implement handling of graphics rendering when entities are not in viewport
        //-----------------------------------------------------------------
        this.getGraphics().drawSprite();
        this.getPhysics().move(dt);
    }

    //override it
    public void shoot(Entity target, float dt) {

    }

    //life span timer has went over max life span
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
