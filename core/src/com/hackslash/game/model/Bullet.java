package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Bullet extends GameObject {

    Vector2 position;
    Sprite sprite;
    Texture tex;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        position = new Vector2(x, y);
        speed = 5f;

        tex = new Texture(Gdx.files.internal("square.png"));
        sprite = new Sprite(tex, 0, 0, 5, 15);
    }

    public void draw(Batch batch) {
        batch.begin();
        sprite.setPosition(getXPosition(), getYPosition());
        sprite.setColor(Color.RED);
        sprite.draw(batch);
        batch.end();
    }

    public void update(float dt, float dx, float dy) {

        //Vector2 direction = player.getPlayerPosition();
        position.x += dx * speed * dt;
        position.y += dy * speed * dt;

    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public float getXPosition() {
        return position.x;
    }

    public float getYPosition() {
        return position.y;
    }

}
