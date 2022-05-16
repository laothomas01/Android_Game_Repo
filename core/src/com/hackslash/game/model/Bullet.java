package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject {
    Sprite sprite;
    Texture tex;
    boolean hit;
    float lifeSpan;
    float maxLife;
    boolean remove;
    int radius;

    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;
        speed = 300f;
        size = 15;
        radius = 15;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, size, size);
        lifeSpan = 0;
        maxLife = 5;
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
    }

    public boolean hasHit() {
        return hit;
    }

    public int getRadius() {
        return radius;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public int getSize() {
        return size;
    }

    public float getDamage() {
        return damage;
    }

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
