package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {

    //
//    Vector2 position;
    Sprite sprite;
    Texture tex;
    boolean remove;
    static boolean hit;
//    boolean enemy_hit;
//    boolean enemy_dead;

    public Enemy() {
//        x = Gdx.graphics.getWidth() / 4;
//        y = Gdx.graphics.getHeight() / 4;
//        position = new Vector2(x, y);
//
//        size = 20;
//        speed = 200;
//        damage = 1;
//        health = 1;
//        tex = new Texture(Gdx.files.internal("circle.png"));
//        sprite = new Sprite(tex, 0, 0, size, size);
    }

    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
        x = posX;
        y = posY;
//        position = new Vector2(x, y);
        speed = enemy_speed;
        damage = enemy_damage;
        size = enemy_size;
        health = enemy_health;
//
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
//        /**
//         * two different ways to have the enemy or any object follow another object in the most basic form
//         */
//        Vector2 playerPos = player()
        Vector2 enemy_direction = new Vector2();
        dx = (player.getXPosition() + 20) - (this.getXPosition() + 20);
        dy = (player.getYPosition() + 20) - (this.getYPosition() + 20);

        enemy_direction.x = dx;
        enemy_direction.y = dy;

        enemy_direction.nor();

        x += enemy_direction.x * speed * dt;
        y += enemy_direction.y * speed * dt;


//        dx = (player.getXPosition() + 20) - (x + 20);
//        dy = (player.getYPosition() + 20) - (y + 20);
//
//        x += dx * speed * dt;
//        y += dy * speed * dt;
//        enemy_direction.x = (playerPos.x + 20) - (getEnemyPosition().x + 20);
//        enemy_direction.y = (playerPos.y + 20) - (getEnemyPosition().y + 20);
//        enemy_direction.nor();
//        getEnemyPosition().x += enemy_direction.x * speed * dt;
//        getEnemyPosition().y += enemy_direction.y * speed * dt;
    }

    boolean isHit() {
        return hit;
    }
//    public boolean isHit() {
//        return enemy_hit;
//    }
//
//    public boolean isDead() {
//        return enemy_dead;
//    }
//
//    public void setHealth(float damage) {
//
//        health -= damage;
//        if (health < 0) {
//            health = 0;
//        }
//    }

    //    public void set_is_hit(boolean h) {
//        enemy_hit = h;
//    }
//
//    public void set_is_dead(boolean d) {
//        d = enemy_dead;
//    }
//
    public float getXPosition() {
        return x;
    }

    public float getYPosition() {
        return y;
    }

    //    public Vector2 getEnemyPosition() {
//        return position;
//    }
//
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


}
