package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {

    Sprite sprite;
    Texture tex;
    boolean remove;
    static boolean hit;
    float rageAgainTimer;
    float rageWaitTime;
    boolean rage;
    float rageDurationTimer;
    float rageDurationTime;
    float prevSize;
    float prevSpeed;

    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
        x = posX;
        y = posY;
        speed = enemy_speed;
        damage = enemy_damage;
        size = enemy_size;
        health = enemy_health;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, 20, 20);
        remove = false;
        hit = false;
        rageAgainTimer = 0;
        rageWaitTime = 15f;
        rageDurationTimer = 0;
        rageDurationTime = 2f;
        prevSize = getSize();
        prevSpeed = getSpeed();

    }

    public void update(float dt, Player player, SpriteBatch batch) {

        basic_enemy_AI(player, dt);

        if (this.getHealth() <= 0) {
            remove = true;
        }

        /**
         * if i get angry after waiting too long
         *  -my anger should increase my stats
         *  -how long should i stay angry?
         *  -after X amount of seconds staying angry, I should chill out.
         */
        if (rageAgainTimer > rageWaitTime) {
            rageAgainTimer = 0;
            rage = true;
            setSize(5);
            setSpeed(7);

        } else {
            rageAgainTimer += dt;
        }


        draw(batch);

//        System.out.println("RAGE:" + rage);
    }

    public void draw(Batch batch) {


        batch.begin();
        sprite.setScale(getSize(), getSize());
        sprite.setPosition(getXPosition(), getYPosition());
        batch.setColor(Color.WHITE);
        if (rage == true) {
            batch.setColor(Color.FIREBRICK);
        }


        batch.draw(tex, getXPosition(), getYPosition(), (getSize() * 2), (getSize() * 2));

        batch.end();
    }

    public void basic_enemy_AI(Player player, float dt) {
        Vector2 enemy_direction = new Vector2();
        dx = (player.getXPosition() + 20) - (this.getXPosition() + 20);
        dy = (player.getYPosition() + 20) - (this.getYPosition() + 20);

        enemy_direction.x = dx;
        enemy_direction.y = dy;

        enemy_direction.nor();

        x += enemy_direction.x * speed * dt;
        y += enemy_direction.y * speed * dt;


    }

    boolean isHit() {
        return hit;
    }

    public float getXPosition() {
        return x;
    }

    public float getYPosition() {
        return y;
    }

    public void setSize(float s) {
        size += s;
    }

    public int getSize() {
        return size;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTex() {
        return tex;
    }

    public float getHealth() {
        return health;
    }

    public void takeDamage(float damage) {
        this.health -= damage;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void setRemove(boolean r) {
        remove = r;
    }

    public void setSpeed(float s) {
        speed += s;
    }


    public float getSpeed() {
        return speed;
    }

    public float getRadians() {
        return radians;
    }


}
