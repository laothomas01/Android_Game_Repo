package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import java.util.Vector;

public class Enemy extends Game_Object {

    public Enemy(float x_position, float y_position, float enemy_speed, float enemy_damage, float enemy_size, float enemy_health) {
        x = x_position;
        y = y_position;
        current_health = enemy_health;
        current_size = enemy_size;
        current_damage = enemy_damage;
        current_speed = enemy_speed;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(texture, 0, 0, (int) current_size, (int) current_size);
        dx = 0;
        dy = 0;
    }
//
//    Sprite sprite;
//    Texture tex;
//    boolean remove;
//    static boolean hit;
//    float rageTimer;
//    float rageWaitTime;
//    boolean rage;
//    float rageDurationTimer;
//    float rageDurationTime;
//    int prevSize;
//    float prevSpeed;
//    boolean wait_for_rage_duration_timer;
//
//    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
//        x = posX;
//        y = posY;
//        speed = enemy_speed;
//        damage = enemy_damage;
//        size = enemy_size;
//        health = enemy_health;
//        tex = new Texture(Gdx.files.internal("circle.png"));
//        sprite = new Sprite(tex, 0, 0, 20, 20);
//        remove = false;
//        hit = false;
//        rageTimer = 0;
//        rageWaitTime = 5f;
//        rageDurationTimer = 0;
//        rageDurationTime = 5f;
//        prevSize = getSize();
//        wait_for_rage_duration_timer = false;
//        prevSpeed = getSpeed();
//
//    }
//
//    public void update(float dt, Player player, SpriteBatch batch) {
////
////        basic_enemy_AI(player, dt);
////
////        if (this.getHealth() <= 0) {
////            remove = true;
////        }
////
////        /**
////         * if i get angry after waiting too long
//         *  -my anger should increase my stats
//         *  -how long should i stay angry?
//         *  -after X amount of seconds staying angry, I should chill out.
//         */
//
//
//
//        if (!wait_for_rage_duration_timer) {
//            if (rageTimer > rageWaitTime) {
//                rageTimer = 0;
//                rage = true;
//                wait_for_rage_duration_timer = true;
//                setSpeed(100);
//            } else {
//                rageTimer += dt;
//            }
//
//        } else {
//            if (rageDurationTimer > rageDurationTime) {
//                rageDurationTimer = 0;
//                rage = false;
//                wait_for_rage_duration_timer = false;
//                setSpeed(-100);
//            } else {
//                rageDurationTimer += dt;
//            }
//        }
//
//        draw(batch);
//
//    }
//
//    public void draw(Batch batch) {
//
//
//        batch.begin();
//        sprite.setScale(getSize(), getSize());
//        sprite.setPosition(getXPosition(), getYPosition());
//        if (rage == true) {
//            batch.setColor(Color.FIREBRICK);
//        }
//        if (rage == false) {
//            batch.setColor(Color.WHITE);
//        }
//
//
//        batch.draw(tex, getXPosition(), getYPosition(), (getSize() * 2), (getSize() * 2));
//        batch.end();
//    }
//
//    public void basic_enemy_AI(Player player, float dt) {
//        Vector2 enemy_direction = new Vector2();
//        dx = (player.getXPosition() + 20) - (this.getXPosition() + 20);
//        dy = (player.getYPosition() + 20) - (this.getYPosition() + 20);
//
//        enemy_direction.x = dx;
//        enemy_direction.y = dy;
//
//        enemy_direction.nor();
//
//        x += enemy_direction.x * speed * dt;
//        y += enemy_direction.y * speed * dt;
//
//
//    }
//
//    boolean isHit() {
//        return hit;
//    }
//
//    public float getXPosition() {
//        return x;
//    }
//
//    public float getYPosition() {
//        return y;
//    }
//
//    public void setSize(int s) {
//        size = size + s;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public Sprite getSprite() {
//        return sprite;
//    }
//
//    public Texture getTex() {
//        return tex;
//    }
//
//    public float getHealth() {
//        return health;
//    }
//
//    public void takeDamage(float damage) {
//        this.health -= damage;
//    }
//
//    public boolean shouldRemove() {
//        return remove;
//    }
//
//    public void setRemove(boolean r) {
//        remove = r;
//    }
//
//    public void setSpeed(float s) {
//        speed = speed + s;
//    }
//
//
//    public float getSpeed() {
//        return speed;
//    }
//
//    public float getRadians() {
//        return radians;
//    }
//
//    public void resetSpeed(float s) {
//        speed = s;
//    }
//
//    public void resetSize(int s) {
//        size = s;
//    }


}
