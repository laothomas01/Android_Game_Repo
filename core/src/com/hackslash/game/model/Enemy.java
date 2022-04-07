package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Enemy extends GameObject {

    int radius;

    Vector2 enemy_position;
    Vector2 enemy_direction;

    public Enemy() {
        x = Gdx.graphics.getWidth() / 4;
        y = Gdx.graphics.getHeight() / 4;
        enemy_position = new Vector2(x, y);
        enemy_direction = new Vector2();
        radius = 20;
        size = radius;
        speed = 100;
    }

    public void update(float dt, Player player) {
        basic_enemy_AI(player, dt);
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        sr.circle(enemy_position.x, enemy_position.y, size);
        sr.end();

    }

    public void basic_enemy_AI(Player player, float dt) {
//        enemy_position.x += 4;
//        float distance1 = enemy_position.dst(player.player_position);
//        float distance2 = player.player_position.dst(enemy_position);
//        System.out.println("DISTANCE:" + distance1);
//        enemy_direction.x = (player.player_position.x - enemy_position.x);
//        enemy_direction.y = (player.player_position.y - enemy_position.y);
//        enemy_direction = player.player_position.sub(enemy_position);
//        enemy_direction = player.getPlayerPosition().sub(this.getEnemyPosition());
        Vector2 playerPos = new Vector2(player.getPlayerPosition());
        enemy_direction = playerPos.sub(enemy_position);
        enemy_direction.nor();
        enemy_position = enemy_position.add(enemy_direction.x * speed * dt, enemy_direction.y * speed * dt);


//        enemy_direction = new Vector2();
//        enemy_direction.x = (playerPos.x) - (enemy_position.y);
//        enemy_direction.y = (playerPos.y) - (enemy_position.y);
//        enemy_position.nor();
//        enemy_position = enemy_position.add(enemy_direction).add(1, 1);
//        this.getEnemyPosition().x += 1 + enemy_direction.x;
//        this.getEnemyPosition().y += enemy_direction.y;

//        System.out.println(playerPos);

//        enemy_direction.sub(playerPos);
//        enemy_direction.nor();
//        enemy_position.add(enemy_direction.add(speed * dt, speed * dt));
//        enemy_position += enemy_direction.add(speed * dt, speed * dt);
//        enemy_direction.x = (player.player_position
//        if (distance1 == 150)
//        {
//
//        }
//        if (distance1 != distance2) {
//            enemy_position.x += 0;
//            enemy_position.y += 0;
//        } else {
//            enemy_position.x += 1;
//            enemy_position.y += 1;
//        }


//        else {
//            enemy_position.x = enemy_position.x;
//            enemy_position.y = enemy_position.y;
//        }
    }

    public float getXPosition() {
        return enemy_position.x;
    }

    public float getYPosition() {
        return enemy_position.y;
    }

    public Vector2 getEnemyPosition() {
        return enemy_position;
    }


}
