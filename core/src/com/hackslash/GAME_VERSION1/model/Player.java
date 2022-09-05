package com.hackslash.GAME_VERSION1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {
    public Player() {
        x = 0;
        y = 0;
        maxHealth = 100f;
        size = 25;
        speed = 500;
        damage = 1;
        health = maxHealth;
        texture = new Texture(Gdx.files.internal("square.png"));
        sprite = new Sprite(texture, 0, 0, (int) size, (int) size);
        spriteBatch = new SpriteBatch();
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        dx = 0;
        dy = 0;
        radians = 0;
        object = OBJECT_TYPE.PLAYER;
    }
}
