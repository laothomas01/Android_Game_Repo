package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Queue;

public class Player extends GameObject {

    Vector2 player_position;
    float playerHealth;
    Sprite sprite;
    Texture tex;
    float shoot_timer = 0;
    float find_new_enemy_timer = 0;
    float shoot_cooldown = 0.5f;
    float enemy_detection_radius = 200f;
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

    public void shootBullets(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets, float dt, SpriteBatch batch) {
//        ArrayList<Bullet> removeBullets = new ArrayList<>();
//        ArrayList<Enemy> removeEnemies = new ArrayList<>();
//        if (shoot_timer >= shoot_cooldown) {
//            bullets.add(new Bullet(this.getXPosition(), this.getYPosition()));
//            shoot_timer = 0;
//        } else {
//            for (Bullet b : bullets) {
//                b.draw(batch);
//                for (Enemy e : enemies) {
//                    b.update(dt, e);
//                }
//            }
//            shoot_timer += dt;
//        }
////        for (int i = 0; i < enemies.size(); i++) {
//            if (detectEnemy(enemies.get(i))) {
//
//            }
//            if (detectEnemy(enemies.get(i))) {
//                for (Bullet b : bullets) {
//                    b.draw(batch);
//                    b.update(dt, enemies.get(i));
//                    if (b.hasHit()) {
//                        removeBullets.add(b);
//                        b.getSprite().getTexture().dispose();
//                    }
//                    if (b.getLifeSpan() >= 3) {
//                        removeBullets.add(b);
//                        b.getSprite().getTexture().dispose();
//                    }
//                }
//                if (enemies.get(i).getHealth() != 0) {
//
////                    bullets.removeAll(removeBullets);
//
//                }
//
//            }
    }
//        enemies.removeAll(removeEnemies);


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


    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(this.getXPosition(), this.getYPosition(), e.getXPosition(), e.getYPosition()) <= enemy_detection_radius) {
            return true;
        }
        return false;
    }


}
