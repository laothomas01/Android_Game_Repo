package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {


    Vector2 position;
<<<<<<< HEAD

=======
    Sprite sprite;
    Texture tex;
    boolean enemy_hit;
    boolean enemy_dead;
>>>>>>> working_on_bullet_shooting_branch

    /**
     * Basic Enemy constructor with basic attributes
     */
    public Enemy() {
        x = Gdx.graphics.getWidth() / 4;
        y = Gdx.graphics.getHeight() / 4;
        position = new Vector2(x, y);
<<<<<<< HEAD
        radius = 20;
        size = radius;
        speed = 100;
        damage = 1;
        health = 1;

    }

    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, float enemy_size, float enemy_health) {
        x = posX;
        y = posY;
        position = new Vector2(x, y);
        speed = enemy_speed;
        damage = enemy_damage;
        size = enemy_size;
        health = enemy_health;
=======

        size = 20;
        speed = 200;
        damage = 1;
        health = 1;
        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, size, size);
>>>>>>> working_on_bullet_shooting_branch
    }

    public Enemy(float posX, float posY, float enemy_speed, float enemy_damage, int enemy_size, float enemy_health) {
        x = posX;
        y = posY;
        position = new Vector2(x, y);
        speed = enemy_speed;
        damage = enemy_damage;
        size = enemy_size;
        health = enemy_health;

        tex = new Texture(Gdx.files.internal("circle.png"));
        sprite = new Sprite(tex, 0, 0, 20, 20);
        enemy_hit = false;
        enemy_dead = false;
    }

<<<<<<< HEAD
    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.circle(getXPosition(), getYPosition(), getEnemySize());
        sr.end();
=======
    public void update(float dt, Player player) {

        basic_enemy_AI(player, dt);
    }

    public void draw(Batch batch) {
        batch.begin();
        batch.setColor(Color.WHITE);
        sprite.setScale(getEnemySize(), getEnemySize());
        sprite.setPosition(getXPosition(), getYPosition());
        batch.draw(tex, getXPosition(), getYPosition(), (getEnemySize() * 2), (getEnemySize() * 2));
        batch.end();
>>>>>>> working_on_bullet_shooting_branch
    }

    public void basic_enemy_AI(Player player, float dt) {

        /**
         * two different ways to have the enemy or any object follow another object in the most basic form
         */
        Vector2 playerPos = player.getPlayerPosition();
        Vector2 enemy_direction = new Vector2();
        enemy_direction.x = (playerPos.x + 20) - (getEnemyPosition().x + 20);
        enemy_direction.y = (playerPos.y + 20) - (getEnemyPosition().y + 20);
        enemy_direction.nor();
        getEnemyPosition().x += enemy_direction.x * speed * dt;
        getEnemyPosition().y += enemy_direction.y * speed * dt;
<<<<<<< HEAD
=======
    }

    public boolean isHit() {
        return enemy_hit;
    }

    public boolean isDead() {
        return enemy_dead;
    }

    public void set_is_hit(boolean h) {
        enemy_hit = h;
    }

    public void set_is_dead(boolean d) {
        d = enemy_dead;
>>>>>>> working_on_bullet_shooting_branch
    }

    public float getXPosition() {
        return position.x;
    }

    public float getYPosition() {
        return position.y;
    }

    public Vector2 getEnemyPosition() {
        return position;
    }

<<<<<<< HEAD
    public void setEnemySize(float size) {
        this.size = size;
    }
=======
    public void setEnemySize(int size) {
        this.size = size;
    }

    public int getEnemySize() {
        return size;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTex() {
        return tex;
    }

    public float getHealth() {
        return health;
    }

    public void subtractHealth() {
        health -= 1;
    }

>>>>>>> working_on_bullet_shooting_branch

    public float getEnemySize() {
        return size;
    }
}
