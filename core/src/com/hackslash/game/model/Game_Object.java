package com.hackslash.game.model;

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

    /**
     * x and y positions
     */
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

    Texture texture;

    float health;

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

}
