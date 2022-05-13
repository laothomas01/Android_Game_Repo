package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Player extends GameObject {

    float MAX_BULLETS = 1;
    float playerHealth;
    Sprite sprite;
    Texture tex;
    float fireTimer;
    float fireTime;
    boolean remove;


    float enemy_detection_radius = 500f;
    ArrayList<Bullet> bullets;

    public Player() {

        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        size = 25;
        speed = 300f;
        playerHealth = 1000f;

        tex = new Texture(Gdx.files.internal("square.png"));
        sprite = new Sprite(tex, 0, 0, 20, 20);
        bullets = new ArrayList<Bullet>();
        fireTimer = 0;
        fireTime = 1f;
    }

    public Player(float posX, float posY, int player_size, float player_speed) {
        x = posX;
        y = posY;
        size = player_size;
        speed = player_speed;
    }

    public void update(float dt, Batch batch, Enemy e) {

        if (detectEnemy(e)) {
            if (!e.isHit()) {

                if (fireTimer > fireTime) {
                    fireTimer = 0;
                    radians = MathUtils.atan2(e.getYPosition() - y, e.getXPosition() - x);
                    loadBullets();

                } else {
                    fireTimer += dt;
                    fireBullets(dt, batch);
                }
            }


        }


    }

    public void loadBullets() {
        if (bullets.size() == MAX_BULLETS) {
            return;
        }
        bullets.add(new Bullet(x, y, radians));
    }


    public void fireBullets(float dt, Batch batch) {

        if (!bullets.isEmpty()) {
            bullets.get(0).draw(batch);
            bullets.get(0).update(dt);
            if (bullets.get(0).shouldRemove()) {
                bullets.remove(0);
            }
        }

    }


    public void draw(Batch batch) {
        batch.begin();
        sprite.setPosition(x, y);
        sprite.draw(batch);
        batch.end();

    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public float getXPosition() {
        return x;
    }


    public float getYPosition() {
        return y;
    }

    public float getPlayerSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setXPosition(float dx) {
        x = dx;
    }

    public void setYPosition(float dy) {
        y = dy;
    }

    public float getPlayerHealth() {
        return playerHealth;

    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(this.getXPosition(), this.getYPosition(), e.getXPosition(), e.getYPosition()) < enemy_detection_radius) {
            return true;
        }
        return false;

    }

    public boolean shouldRemove() {
        return remove;
    }

    public void takeDamage(float h) {
        playerHealth -= h;
    }


}
