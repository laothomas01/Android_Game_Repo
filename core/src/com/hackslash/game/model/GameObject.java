package com.hackslash.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
    GameObject() {

    }


    //enumerator used to help developers indicate which game object they are using
    enum OBJECT_TYPE {ENEMY, PLAYER, EXP, BULLET}

    OBJECT_TYPE object = null;

    public String getObjectType() {
        return object.name();
    }

    float x, y, dx, dy, radians, speed, size;
    Vector2 position, velocity;

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
        getSprite().setPosition(position.x, position.y);
    }

    public void setPosition(float x, float y) {
        Vector2 new_position = new Vector2(x, y);
        setPosition(new_position);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y) {
        setVelocity(new Vector2(x, y));
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }


    Sprite sprite;

    SpriteBatch batch;

    Texture img;


    //    //game object x and y position
//    float x;
//    float y;
//    //x and y directions
//    float dx;
//    float dy;
//    //game object angle but in radians
//    float radians;
//
//
//    float speed;
//
//
//    int size;
//
//    Vector2 position;
//    Vector2 velocity;
//    Vector2 direction;
//
//
//    Sprite sprite;
//
//
//    //used to make sure objects on screen dont stay around too long
//    float lifeSpan;
//    float maxLifeSpan;
//    SpriteBatch spriteBatch;
//
//    public SpriteBatch getSpriteBatch() {
//        return spriteBatch;
//    }
//
//    boolean remove;
//
//    public float getLifeSpan() {
//        return lifeSpan;
//    }
//
//    public void setLifeSpan(float lifeSpan) {
//        this.lifeSpan = lifeSpan;
//    }
//
//    public float getMaxLifeSpan() {
//        return maxLifeSpan;
//    }
//
//    public void setMaxLifeSpan(float maxLifeSpan) {
//        this.maxLifeSpan = maxLifeSpan;
//    }
//
//    public float getCurrentLifeSpan() {
//        return currentLifeSpan;
//    }
//
//    public void setCurrentLifeSpan(float currentLifeSpan) {
//        this.currentLifeSpan = currentLifeSpan;
//    }
//
//    float currentLifeSpan;
//
//
//    public void setX(float x) {
//        this.x = x;
//    }
//
//    public void setY(float y) {
//        this.y = y;
//    }
//
//    public void setVelocity(Vector2 velocity) {
//        this.velocity = velocity;
//    }
//
//    public Vector2 getDirection() {
//        return direction;
//    }
//
//    public void setDirection(Vector2 direction) {
//        this.direction = direction;
//    }
//
//    public float getCurrent_health() {
//        return current_health;
//    }
//
//    public void setCurrent_health(float current_health) {
//        this.current_health = current_health;
//    }
//
//    public float getCurrent_damage() {
//        return current_damage;
//    }
//
//    public void setCurrent_damage(float current_damage) {
//        this.current_damage = current_damage;
//    }
//
//    public float getCurrent_speed() {
//        return current_speed;
//    }
//
//    public void setCurrent_speed(float current_speed) {
//        this.current_speed = current_speed;
//    }
//
//    Texture texture;
//
//    float health;
//    float current_health;
//    float current_damage;
//
//    public void setCurrent_size(int current_size) {
//        this.current_size = current_size;
//    }
//
    public float getCurrentSize() {
        return size;
    }
//
//    float current_speed;
//    //    float current_size;
//    int current_size;
//
//
//    float damage;
//
//    public SpriteBatch getSpriteBatch() {
//        return batch;
//    }
//
//    public float getX() {
//        return x;
//    }
//
//    public void set_X_Position(float x) {
//        this.position.x = x;
//    }
//
//    public float getY() {
//        return y;
//    }
//
//    public void set_Y_Position(float y) {
//        this.position.y = y;
//    }
//
//    public float getDx() {
//        return dx;
//    }
//
//    public void setDx(float dx) {
//        this.dx = dx;
//    }
//
//    public float getDy() {
//        return dy;
//    }
//
//    public void setDy(float dy) {
//        this.dy = dy;
//    }
//
//    public float getRadians() {
//        return radians;
//    }
//
//    public void setRadians(float radians) {
//        this.radians = radians;
//    }
//
//    public float getSpeed() {
//        return speed;
//    }
//
//
////    public float getSize() {
////        return size;
////    }
//
////    public void setSize(float size) {
////        this.size = size;
////    }
//
//    public Vector2 getPosition() {
//        return position;
//    }
//
//    public void setPosition(Vector2 position) {
//        this.position = position;
//    }
//
//    public Sprite getSprite() {
//        return sprite;
//    }
//
//    public void setSprite(Sprite sprite) {
//        this.sprite = sprite;
//    }
//
//    public Texture getTexture() {
//        return texture;
//    }
//
//    public void setTexture(Texture texture) {
//        this.texture = texture;
//    }
//
//    public float getHealth() {
//        return health;
//    }
//
//    public void setHealth(float health) {
//        this.health = health;
//    }
//
//    public float getDamage() {
//        return damage;
//    }
//
//    public void setDamage(float damage) {
//        this.damage = damage;
//    }
//
//    public Vector2 getVelocity() {
//        return velocity;
//    }
//
//
//    public void set_Velocity(float dx, float dy) {
//        this.getVelocity().set(dx, dy);
//    }
//
//
//    public float get_dx() {
//        return dx;
//    }
//
//    public float get_dy() {
//        return dy;
//    }
//
//    public void set_dx(float x_direction) {
//        dx = x_direction;
//    }
//
//    public void set_dy(float y_direction) {
//        dy = y_direction;
//    }

    /**
     * circle and rectangle edge detection
     *
     * @param a Circle
     * @param b Rectangle
     * @return
     */
    //this should be a GAME OBJECT CONTROLLER method
    //refactor later!
    //This method can be refactored into only having to take 1 paremeter, checking the type of game object and doing the same calculations.


}

