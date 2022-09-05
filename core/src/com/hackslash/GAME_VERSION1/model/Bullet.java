package com.hackslash.GAME_VERSION1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject {


    float lifeSpan;
    float maxLifeSpan;

    public Bullet(float x_pos, float y_pos, int s, float x_dir, float y_dir, Vector2 v) {
        x = x_pos;
        y = y_pos;
        size = s;
//        radians = r;
        speed = 500f;
        damage = 1f;
        texture = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(texture, 0, 0, (int) size, (int) size);
        position = new Vector2(x, y);
        velocity = v;
        dx = x_dir;
        dy = y_dir;
        maxLifeSpan = 5;
        lifeSpan = maxLifeSpan;
        object = OBJECT_TYPE.BULLET;
        spriteBatch = new SpriteBatch();

    }

    public String toString() {
        return "BULLET:" + this.hashCode() + "POSITION:" + this.getPosition().toString() + "VELOCITY:" + this.getVelocity().toString();
    }

    public float getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(float span) {
        lifeSpan = span;
    }
}