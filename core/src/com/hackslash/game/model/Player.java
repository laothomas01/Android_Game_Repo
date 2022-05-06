package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class Player extends GameObject {

    Vector2 player_position;
    float playerHealth;
    Sprite sprite;
    Texture tex;
    float shoot_timer = 0;
    float shoot_cooldown = 5;
    float enemy_detection_radius = 300f;
    float circleCenterRadius;

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

    public void shootBullets(ArrayList<Enemy> enemies, float dt, ArrayList<Bullet> bullets, Batch batch, Queue<Enemy> enemyQueue) {
        shoot_timer += dt;
        ArrayList<Enemy> removeEnemies = new ArrayList<>();
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy i = iterator.next();
            if (detectEnemy(i)) {
                enemyQueue.add(i);
                Enemy spotted = enemyQueue.peek();
                if (spotted.getHealth() <= 0) {
                    removeEnemies.add(spotted);
                    enemyQueue.remove(spotted);
                    spotted = enemyQueue.peek();
                } else if (shoot_timer >= shoot_cooldown) {
                    bullets.add(new Bullet(this.getXPosition(), this.getYPosition()));
                    shoot_timer = 0;
                } else {
                    shoot_timer += dt;
                }
                for (Iterator<Bullet> iter = bullets.iterator(); iter.hasNext();) {
                    Bullet x = iter.next();
                    x.draw(batch);
                    x.update(dt, spotted);
                    if (x.hasHit()) {
                        iter.remove();
                        spotted.subtractHealth();
                        System.out.println("BULLET:" + i + "HAS HIT!");
                    }
                }
            }
            else{}
        }
        enemies.removeAll(removeEnemies);
    }

    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(this.getXPosition(), this.getYPosition(), e.getXPosition(), e.getYPosition()) <= enemy_detection_radius) {
            return true;
        }
        return false;
    }


}
