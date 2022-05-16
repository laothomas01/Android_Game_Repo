package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {

    Sprite sprite;
    Texture tex;
    boolean remove;
    static boolean hit;

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
    }

    public void update(float dt, Player player) {
        basic_enemy_AI(player, dt);

        if (this.getHealth() <= 0) {
            remove = true;
        }
    }

    public void draw(Batch batch) {
        batch.begin();
        batch.setColor(Color.WHITE);
        sprite.setScale(getSize(), getSize());
        sprite.setPosition(getXPosition(), getYPosition());
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

    public void setSize(int s) {
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
