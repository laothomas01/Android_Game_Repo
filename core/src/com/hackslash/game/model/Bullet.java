package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Bullet extends GameObject {

    //    Vector2 position;
    Sprite sprite;
    Texture tex;
    //    boolean bullet_hit;
    float lifeSpan;
    float maxLife;
    //    float damage;
//    Vector2 bulletDirection;
    boolean remove;


    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        speed = 300f;
        size = 15;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, size, size);
        lifeSpan = 0;
        maxLife = 3;
//        bullet_hit = false;
//        lifeSpan = 0;
//        maxLifespan = 10;
        damage = 1;
//        bulletDirection = new Vector2();
        remove = false;
//        dx = x + 20;
//        dy = y + 20;

    }

    public void draw(Batch batch) {
        batch.begin();
        sprite.setPosition(getXPosition(), getYPosition());
        sprite.setColor(Color.RED);
        sprite.draw(batch);
        batch.end();
    }

    /**
     * Bullet will target 1 enemy at a time
     */
    public void update(float dt) {
        x += 50 * dt;
        y += 50 * dt;
        lifeSpan += dt;
        if (lifeSpan > maxLife) {
            remove = true;
        }
//        lifeSpan += dt;
//        if (lifeSpan > maxLife) {
//            remove = true;
//        }
//        x += dx * dt;
//        y += dy * dt;
//        dx = (dt * getBulletPosition().x) * speed;
//        dy = (dt * getBulletPosition().y) * speed;
//        getBulletPosition().x += dx;
//        getBulletPosition().y += dy;


//        Vector2 pos = e.getEnemyPosition();
//        bulletDirection.x = (pos.x + 20) - (this.getXPosition() + 20);
//        bulletDirection.y = (pos.y + 20) - (this.getYPosition() + 20);
//        lifeSpan += dt;
//
//        getBulletPosition().x += bulletDirection.x * getBulletSpeed() * dt;
//        getBulletPosition().y += bulletDirection.y * getBulletSpeed() * dt;
//
//        float sumRadius = this.getRadius() + e.getEnemySize();
//        float distance = Vector2.dst(pos.x, pos.y, getBulletPosition().x, getBulletPosition().y);
//        if (distance < sumRadius) {
//            bullet_hit = true;
//            remove = true;
//        } else if (lifeSpan > maxLifespan) {
//            remove = true;
//        }


    }


    //    public boolean hasHit() {
//        return bullet_hit;
//    }
//
//    public boolean getRemove() {
//        return remove;
//    }
//
//    public float getLifeSpan() {
//        return lifeSpan;
//    }
//
//    public float getMaxLifespan() {
//        return maxLifespan;
//    }
//
//    public void setLifeSpan(float l) {
//        lifeSpan = l;
//    }
//
//
//    public int getRadius() {
//        return radius;
//    }
//
    public boolean shouldRemove() {
        return remove;
    }

    public int getSize() {
        return size;
    }

    public float getDamage() {
        return damage;
    }

    //
//    public Vector2 getBulletPosition() {
//        return position;
//    }
//
//    public void set_pos(float x, float y) {
//        getBulletPosition().x = x;
//        getBulletPosition().y = y;
//    }
//
    public void set_pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getXPosition() {
        return x;
    }

    public float getYPosition() {
        return y;
    }

    public float getBulletSpeed() {
        return speed;
    }

}