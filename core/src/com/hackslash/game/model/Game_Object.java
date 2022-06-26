package com.hackslash.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Game_Object {
    //    /**
//     * MOVEMENT
//     * x: x coordinate
//     * y: y coordinate
//     * dx: x traveling direction
//     * dy: y traveling direction
//     * <p>
//     * Enemy Shape: circles
//     * Player Shape: square
//     */
//
//    float x;
//    float y;
//
//    float dx;
//    float dy;
//    float radians;
//    float speed;
//    int size;
//    float damage;
//    float health;
//
//    Vector2 position;
//
    Game_Object() {
    }

    enum OBJECT_TYPE {ENEMY, PLAYER, EXP, BULLET}

    OBJECT_TYPE object = null;

    public String getObjectType() {
        return object.name();
    }


    /**
     * x and y positions
     */
    // x and y positions
    float x;
    float y;
    /**
     * direction
     */
    float dx;
    float dy;
    /**
     * angle
     */

    float radians;


    float speed;

    float size;

    Vector2 position;
    Vector2 velocity;
    Vector2 direction;


    Sprite sprite;


    float lifeSpan;
    float maxLifeSpan;

    boolean remove;

    public float getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(float lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public float getMaxLifeSpan() {
        return maxLifeSpan;
    }

    public void setMaxLifeSpan(float maxLifeSpan) {
        this.maxLifeSpan = maxLifeSpan;
    }

    public float getCurrentLifeSpan() {
        return currentLifeSpan;
    }

    public void setCurrentLifeSpan(float currentLifeSpan) {
        this.currentLifeSpan = currentLifeSpan;
    }

    float currentLifeSpan;


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public float getCurrent_health() {
        return current_health;
    }

    public void setCurrent_health(float current_health) {
        this.current_health = current_health;
    }

    public float getCurrent_damage() {
        return current_damage;
    }

    public void setCurrent_damage(float current_damage) {
        this.current_damage = current_damage;
    }

    public float getCurrent_speed() {
        return current_speed;
    }

    public void setCurrent_speed(float current_speed) {
        this.current_speed = current_speed;
    }

    public float getCurrent_size() {
        return current_size;
    }

    public void setCurrent_size(float current_size) {
        this.current_size = current_size;
    }

    Texture texture;

    float health;
    float current_health;
    float current_damage;
    float current_speed;
    float current_size;

    float damage;

    public float getX() {
        return x;
    }

    public void set_X_Position(float x) {
        this.position.x = x;
    }

    public float getY() {
        return y;
    }

    public void set_Y_Position(float y) {
        this.position.y = y;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float get_X_Velocity() {
        return getVelocity().x;
    }

    public void set_Velocity(float dx, float dy) {
//        dx = getDx();
//        dy = getDy();
        this.getVelocity().set(dx, dy);
    }

    public float get_Y_Velocity() {
        return getVelocity().y;
    }

    /**
     * directions
     */
    public float get_dx() {
        return dx;
    }

    public float get_dy() {
        return dy;
    }

    public void set_dx(float x_direction) {
        dx = x_direction;
    }

    public void set_dy(float y_direction) {
        dy = y_direction;
    }

    /**
     * circle and rectangle edge detection
     *
     * @param a Circle
     * @param b Rectangle
     * @return
     */
    public boolean hasCollided(Game_Object a, Game_Object b) {
        //CIRCLE ON CIRCLE SHAPE DETECTION
        if ((a.getObjectType().equals("ENEMY") && b.getObjectType().equals("BULLET")) || (a.getObjectType().equals("BULLET") && b.getObjectType().equals("ENEMY"))) {
            float dist = Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
            float total_radius = (a.getCurrent_size() - 20f) + (b.getCurrent_size() - 20f);
            if (dist <= total_radius) {
                return true;
            } else {
                return false;
            }
        }
        //SQUARE OR RECTANGLE ON CIRCLE SHAPE DETECTION
        if ((a.getObjectType().equals("PLAYER") && b.getObjectType().equals("ENEMY")) || (a.getObjectType().equals("PLAYER") && b.getObjectType().equals("ENEMY"))) {
            //Edge Detection//
            float testX = a.getPosition().x;
            float testY = a.getPosition().y;
            if (a.getPosition().x < b.getPosition().x) {
                testX = b.getPosition().x; // left edge
            } else if (a.getPosition().x > b.getPosition().x + b.getSprite().getWidth()) {
                testX = b.getPosition().x + b.getSprite().getWidth(); // right edge
            }

            if (a.getPosition().y < b.getPosition().y) {
                testY = b.getPosition().y; // top edge
            } else if (a.getPosition().y > b.getPosition().y + b.getSprite().getHeight()) {
                testY = b.getPosition().y + b.getSprite().getHeight(); // bottom edge
            }

            //  Collision Detection: use Pythagorean Theorem using circle's center

            //  and the two edge we found above
            float distX = a.getPosition().x - testX;
            float distY = a.getPosition().y - testY;
            //Pythagorean Theorem
            double distance = Math.sqrt((distX * distX) + (distY * distY));
            if (distance <= a.getSprite().getWidth()) {
                return true;
            } else {
                return false;
            }

        }


        return false;


    }

}
