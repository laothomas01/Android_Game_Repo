package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {

    public Enemy(float x_position, float y_position, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
//        x = x_position;
//        y = y_position;
//        current_health = enemy_health;
//        current_size = enemy_size;
//        current_damage = enemy_damage;
//        current_speed = enemy_speed;
//        position = new Vector2(x, y);
//        velocity = new Vector2(0, 0);
//        texture = new Texture(Gdx.files.internal("circle.png"));
//        sprite = new Sprite(texture, 0, 0, 20, 20);
//        sprite.setPosition(getPosition().x, getPosition().y);
//        spriteBatch = new SpriteBatch();
//        dx = 0;
//        dy = 0;
//        object = OBJECT_TYPE.ENEMY;
    }

    public Enemy() {

        //random positioning for test purposes
        x = MathUtils.random(0, 500);
        y = MathUtils.random(0, 500);

        //Enemy angle
        radians = 0;
        //Enemy direction
        dx = 0;
        dy = 0;
        size = 1f;
        speed = 50f;
        position = new Vector2(x, y);
        img = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(img);
        sprite.setScale(size, size);
        sprite.setPosition(position.x, position.y);
        sprite.setColor(Color.BLUE);
        batch = new SpriteBatch();
        object = OBJECT_TYPE.ENEMY;

    }

}
