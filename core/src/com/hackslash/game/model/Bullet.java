package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Bullet extends GameObject {

    static float wait_time = 1f;
    static float shootTimer = 0;
    Vector2 position;


    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        position = new Vector2(x, y);
        speed = 5f;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(Color.RED);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(getXPosition(), getYPosition(), 5, 15);
        sr.end();
    }

    public void update(float dt, Player player) {

        Vector2 playerPos = player.getPlayerPosition();
        position.x += playerPos.x * speed * dt;
        position.y += playerPos.y * speed * dt;

    }

    public static void shootBullets(ArrayList<Bullet> bullets, float dt, Player player, ShapeRenderer sr) {
        shootTimer += dt;
        if(shootTimer>=wait_time) {
            bullets.add(new Bullet(player.getXPosition(), player.getYPosition() ));

        }


        for (Bullet bullet : bullets) {
            bullet.draw(sr);
            bullet.update(dt, player);
        }
    }

    public float getXPosition() {
        return position.x;
    }

    public float getYPosition() {
        return position.y;
    }

}
