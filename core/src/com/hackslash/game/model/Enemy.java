package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Enemy extends GameObject {

    int radius;

    Vector2 position;


    /**
     * Basic Enemy constructor with basic attributes
     */
    public Enemy() {
        x = Gdx.graphics.getWidth() / 4;
        y = Gdx.graphics.getHeight() / 4;
        position = new Vector2(x, y);
        radius = 20;
        size = radius;
        speed = 100;
        damage = 1;
        health = 1;

    }

    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, float enemy_size, float enemy_health) {
        x = posX;
        y = posY;
        position = new Vector2(x, y);
        speed = enemy_speed;
        damage = enemy_damage;
        size = enemy_size;
        health = enemy_health;
    }

    public void update(float dt, Player player) {
        basic_enemy_AI(player, dt);
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(getXPosition(), getYPosition(), getEnemySize());
        sr.end();
    }

    public void basic_enemy_AI(Player player, float dt) {


        /**
         * two different ways to have the enemy or any object follow another object in the most basic form
         */
        Vector2 playerPos = player.getPlayerPosition();
        Vector2 enemy_direction = new Vector2();
        enemy_direction.x = (playerPos.x + 20) - (getEnemyPosition().x + 20);
        enemy_direction.y = (playerPos.y + 20) - (getEnemyPosition().y + 20);
        enemy_direction.nor();
        getEnemyPosition().x += enemy_direction.x * speed * dt;
        getEnemyPosition().y += enemy_direction.y * speed * dt;
    }

    public float getXPosition() {
        return position.x;
    }

    public float getYPosition() {
        return position.y;
    }

    public Vector2 getEnemyPosition() {
        return position;
    }

    public void setEnemySize(float size) {
        this.size = size;
    }

    public float getEnemySize() {
        return size;
    }
}
