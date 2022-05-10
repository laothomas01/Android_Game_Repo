package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Bullet extends GameObject {

    //    Vector2 position;
    Sprite sprite;
    Texture tex;
    boolean hit;
    float lifeSpan;
    float maxLife;
    //    float damage;
    boolean remove;
    int radius;


    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
        speed = 350f;
        size = 15;
        radius = 15;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, size, size);
        lifeSpan = 0;
        maxLife = 3;
        damage = 1;
        remove = false;
        hit = false;
        //  x direction
        dx = MathUtils.cos(radians) * speed;
        //y direction
        dy = MathUtils.sin(radians) * speed;
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

        x += dx * dt;
        y += dy * dt;

        lifeSpan += dt;
        if (lifeSpan > maxLife) {
            remove = true;
        }


//        basic_bullet_ai(dt, e);


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

    public void basic_bullet_ai(float dt) {

//        x += speed * dt;
//        y += speed * dt;
//        dx += (e.getXPosition() + 20) - (this.getXPosition() + 20);
//        dy += (e.getYPosition() + 20) - (this.getYPosition() + 20);
//
//        x += dx * getBulletSpeed() * dt;
//        y += dy * getBulletSpeed() * dt;

//        direction.x = (e.getXPosition() + 20) - (this.getXPosition() + 20);
//        direction.y = (e.getYPosition() + 20) - (this.getYPosition() + 20);
//
//        x += direction.x * getBulletSpeed() * dt;
//        y += direction.y * getBulletSpeed() * dt;
//
//        float distance = Vector2.dst(e.getXPosition(), e.getYPosition(), this.getXPosition(), this.getYPosition());
//        float sumRadius = this.getRadius() + e.getSize();
//        if (distance < sumRadius) {
//            // do something
//            remove = true;
//
////            enemy.set_is_hit(true);
//        }
        //        for (int i = 0; i < e.size(); i++) {
//            dx = (e.get(i).getXPosition() + 20) - (x + 20);
//            dy = (e.get(i).getYPosition() + 20) - (y + 20);
//            x += dx * speed * dt;
//            y += dy * speed * dt;
//            float total_radius = size + e.get(i).getSize();
//            float distance = Vector2.dst(e.get(i).getXPosition(), e.get(i).getYPosition(), x, y);
//            if (distance < total_radius) {
//                hit = true;
//                remove = true;
//            }
//        }
    }


    public boolean hasHit() {
        return hit;
    }

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
    public int getRadius() {
        return radius;
    }

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


    public boolean intersect(Enemy e) {
        float total_radius = size + e.getSize();
        float distance = Vector2.dst(e.getXPosition(), e.getYPosition(), x, y);
        if (distance < total_radius) {
            remove = true;
            return true;
        }
        return false;
    }

}