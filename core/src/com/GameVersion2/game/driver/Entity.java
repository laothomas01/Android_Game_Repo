package com.GameVersion2.game.driver;

/**
 * An Entity is an object that can be transformed, scaled, or rotated
 */
class Entity {
    //physics2D manager
    Physics2D physics;
    //graphics2D manager
    Graphics2D graphics;

    public Entity() {
        //give your game objects a physics2D component
        physics = new Physics2D();
        graphics = new Graphics2D();
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
    }

    public void shoot(Entity target, float dt) {

    }


}
