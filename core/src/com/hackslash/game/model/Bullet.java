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

    Vector2 position;
    Sprite sprite;
    Texture tex;
    int radius;
    boolean bullet_hit;
    float lifeSpan;
    float maxLifespan;
    float damage;
    Vector2 bulletDirection;


    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        position = new Vector2(x, y);
        speed = 15f;
        radius = 15;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, radius, radius);
        bullet_hit = false;
        lifeSpan = 0;
        maxLifespan = 3;
        damage = 1;
        bulletDirection = new Vector2();
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
    public void update(float dt, Enemy e) {
        Vector2 pos = e.getEnemyPosition();
        bulletDirection.x = (pos.x + 20) - (this.getXPosition() + 20);
        bulletDirection.y = (pos.y + 20) - (this.getYPosition() + 20);

        getBulletPosition().x += bulletDirection.x + getBulletSpeed() * dt;
        getBulletPosition().y += bulletDirection.y + getBulletSpeed() * dt;

        float sumRadius = this.getRadius() + e.getEnemySize();
        float distance = Vector2.dst(pos.x, pos.y, getBulletPosition().x, getBulletPosition().y);
        if (distance < sumRadius) {
            bullet_hit = true;
        }

        lifeSpan += dt;

    }


    public boolean hasHit() {
        return bullet_hit;
    }

    public float getLifeSpan() {
        return lifeSpan;
    }

    public float getMaxLifespan() {
        return maxLifespan;
    }

    public void setLifeSpan(float l)
    {
        lifeSpan = l;
    }
    public int getRadius() {
        return radius;
    }

    public float getDamage() {
        return damage;
    }

    public Vector2 getBulletPosition() {
        return position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getXPosition() {
        return position.x;
    }

    public float getYPosition() {
        return position.y;
    }

    public float getBulletSpeed() {
        return speed;
    }

}
