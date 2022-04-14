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
    Vector2 enemy_direction;


    public Enemy() {
        x = Gdx.graphics.getWidth() / 4;
        y = Gdx.graphics.getHeight() / 4;
        position = new Vector2(x, y);
        enemy_direction = new Vector2();
        radius = 20;
        size = radius;
        speed = 100;
        damage = 1;
        health = 1;
    }

    public void update(float dt, Player player) {
        basic_enemy_AI(player, dt);
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.circle(position.x, position.y, size);
        sr.end();

    }

    public void basic_enemy_AI(Player player, float dt) {


        /**
         * two different ways to have the enemy or any object follow another object in the most basic form
         */
        Vector2 playerPos = new Vector2(player.getPlayerPosition());
        Vector2 direction = new Vector2();
        direction.x = (playerPos.x + 40) - (getEnemyPosition().x + 40);
        direction.y = (playerPos.y + 40) - (getEnemyPosition().y + 40);
        direction.nor();
        getEnemyPosition().x += direction.x * 3;
        getEnemyPosition().y += direction.y * 3;

//        Vector2 playerPos = player.getPlayerPosition();
//
//        float angle = MathUtils.atan2(playerPos.y - position.y, playerPos.x - position.x) * MathUtils.radiansToDegrees;
//        getEnemyPosition().x += MathUtils.cos(angle) * speed * dt;
//        getEnemyPosition().y += MathUtils.sin(angle) * speed * dt;

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


}
