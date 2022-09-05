package com.hackslash.GAME_VERSION1.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    public Enemy(float x_position, float y_position, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
        x = x_position;
        y = y_position;
        health = enemy_health;
        size = enemy_size;
        damage = enemy_damage;
        speed = enemy_speed;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(texture, 0, 0, 20, 20);
        sprite.setPosition(getPosition().x, getPosition().y);
        spriteBatch = new SpriteBatch();
        dx = 0;
        dy = 0;
        object = OBJECT_TYPE.ENEMY;
    }
}
