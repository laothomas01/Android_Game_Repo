package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class Player extends GameObject {

    Vector2 player_position;
    float playerHealth;
    Sprite sprite;
    Texture tex;
    float shoot_timer = 0;
    float shoot_cooldown = 2;
    float enemy_detection_radius = 300f;
    //    float circleCenterX;
//    float circleCenterY;
    float circleCenterRadius;
    //    Actor actor;

    public Player() {
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        size = 25;

        /**
         * useful for vector functions
         */
        player_position = new Vector2(x, y);
        /**
         * with delta time, we move at speed * Number of units per second even if the frame rate drops.
         */
        speed = 300f;

        //player health for health bar
        playerHealth = 500f;

        tex = new Texture(Gdx.files.internal("square.png"));
        sprite = new Sprite(tex, 0, 0, 20, 20);
        circleCenterRadius = 100;

    }

    public Player(float posX, float posY, int player_size, float player_speed) {
        x = posX;
        y = posY;
        size = player_size;
        speed = player_speed;
    }


//    public void update(float dt) {
//        boundingCircle.set(player_position.x + 9, player_position.y + 6, 100f);
//    }

    public void draw(Batch batch) {
        batch.begin();
        sprite.setPosition(player_position.x, player_position.y);
        sprite.draw(batch);
        batch.end();

    }

    public float getXPosition() {
        return player_position.x;
    }


    public float getYPosition() {
        return player_position.y;
    }

    public float getPlayerSpeed() {
        return speed;
    }

    public Vector2 getPlayerPosition() {
        return player_position;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setXPosition(float dx) {
        player_position.x = dx;
    }

    public void setYPosition(float dy) {
        player_position.y = dy;
    }

    public float getPlayerHealth() {
        return playerHealth;

    }


    public Sprite getSprite() {
        return sprite;
    }

    public void dispose() {
        tex.dispose();
    }

    public void shootBullets(Enemy e, float dt, ArrayList<Bullet> bullets, Batch batch) {
        shoot_timer += dt;

        if (detectEnemy(e)) {
            if (shoot_timer >= shoot_cooldown) {
                bullets.add(new Bullet(this.getXPosition(), this.getYPosition()));
                shoot_timer = 0;
            } else {
                shoot_timer += dt;
            }

            for (Bullet b : bullets) {
                b.draw(batch);
                b.update(dt, e);
            }
        } else {
            System.out.println("ENEMY NOT DETECTED!");
        }

//        for (Bullet bullet : bullets) {
//            bullet.draw(batch);
//            bullet.update(dt, 100, 100);
//        }

    }

    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(this.getXPosition(), this.getYPosition(), e.getXPosition(), e.getYPosition()) <= enemy_detection_radius) {
            return true;
        }
        return false;
    }


    //    public Enemy getClosestEnemy() {
////        Enemy closestEnemy = null;
////        float cloestEnemyDist = -1;
////        ArrayList<Enemy> list_of_enemies = new ArrayList<Enemy>();
//
//    }


}
