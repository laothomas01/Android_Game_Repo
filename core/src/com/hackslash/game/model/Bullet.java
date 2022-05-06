package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject {

    Vector2 position;
    Sprite sprite;
    Texture tex;
    int radius;
    boolean hit;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        position = new Vector2(x, y);
        speed = 1f;
        radius = 15;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, radius, radius);
        hit = false;
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
    public void update(float dt, Enemy enemy) {
        Vector2 enemy_pos = enemy.getEnemyPosition();
        Vector2 bullet_direction = new Vector2();

        bullet_direction.x = (enemy_pos.x + 20) - (this.getXPosition() + 20);
        bullet_direction.y = (enemy_pos.y + 20) - (this.getYPosition() + 20);

        getBulletPosition().x += bullet_direction.x * getBulletSpeed() * dt;
        getBulletPosition().y += bullet_direction.y * getBulletSpeed() * dt;

        float distance = Vector2.dst(enemy_pos.x, enemy_pos.y, getBulletPosition().x, getBulletPosition().y);
        float sumRadius = this.getRadius() + enemy.getEnemySize();
        if (distance < sumRadius) {
            hit = true;
        }
    }

    public boolean hasHit() {
        return hit;
    }

    public int getRadius() {
        return radius;
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
