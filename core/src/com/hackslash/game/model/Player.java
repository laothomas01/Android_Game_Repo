package com.hackslash.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {


    /**
     * We will only create 1 player so parameters for the players are not needed
     */
//    private Vector2 firePointPosition;
//    private Vector2 firePointVelocity;
//    float firePoint_dx = 0;
//    float firePoint_dy = 0;
    public Player() {


        x = 0;
        y = 0;

        position = new Vector2(x, y);
        //player direction
        dx = 0;
        dy = 0;

        speed = 100;
        //angle in radians
        radians = 0;
        size = 1f;
        img = new Texture("square.png");
        sprite = new Sprite(img);
        sprite.setColor(new Color(Color.GREEN));
        sprite.setPosition(position.x, position.y);
        sprite.setScale(size, size);
        batch = new SpriteBatch();
        object = OBJECT_TYPE.PLAYER;

        //divide by 2 but using right shifting
//        x = Gdx.graphics.getWidth() / 2f;
//        y = Gdx.graphics.getHeight() / 2f;

//        health = 25f;
//        size = 25;
//        speed = 500;
//        damage = 1;
//        current_health = health;
//        current_damage = damage;
//        current_speed = speed;
//        current_size = size;
//        texture = new Texture(Gdx.files.internal("square.png"));
//        sprite = new Sprite(texture);
//        sprite.setPosition(this.getPosition().x, this.getPosition().y);
//        spriteBatch = new SpriteBatch();
//        position = new Vector2(this.getX(), this.getY());
//        velocity = new Vector2(0, 0);
//        dx = 0;
//        dy = 0;
//        object = OBJECT_TYPE.PLAYER;

    }

//    public SpriteBatch getBatch() {
//        return batch;
//    }


//    float MAX_BULLETS = 1;
//    float playerHealth;
//    Sprite sprite;
//    Texture tex;
//    float fireTimer;
//    float fireTime;
//    boolean remove;
//    boolean fired;
//
//
//    float enemy_detection_radius = 1000f;
//    ArrayList<Bullet> bullets;
//
//    public Player() {
//
//        x = Gdx.graphics.getWidth() / 2;
//        y = Gdx.graphics.getHeight() / 2;
//        size = 25;
//        speed = 300f;
//        playerHealth = 1000f;
//
//        tex = new Texture(Gdx.files.internal("square.png"));
//        sprite = new Sprite(tex, 0, 0, 20, 20);
//        bullets = new ArrayList<Bullet>();
//        fireTimer = 0;
//        fireTime = 2f;
//    }
//
//    public Player(float posX, float posY, int player_size, float player_speed) {
//        x = posX;
//        y = posY;
//        size = player_size;
//        speed = player_speed;
//    }
//
//    public void update(float dt, Batch batch, Enemy e) {
//
//        if (detectEnemy(e)) {
//            if (!e.isHit()) {
//
//                if (fireTimer > fireTime) {
//                    fireTimer = 0;
//                    radians = MathUtils.atan2(e.getYPosition() - y, e.getXPosition() - x);
//                    loadBullets();
//
//                } else {
//                    fireTimer += dt;
//                    fireBullets(dt, batch);
//                }
//            }
//
//
//        }
//
//
//    }
//
//    public void loadBullets() {
//        if (bullets.size() == MAX_BULLETS) {
//            return;
//        }
//        bullets.add(new Bullet(x, y, radians));
//    }
//
//
//    public void fireBullets(float dt, Batch batch) {
//
//        if (!bullets.isEmpty()) {
//            bullets.get(0).draw(batch);
//            bullets.get(0).update(dt);
//            if (bullets.get(0).shouldRemove()) {
//                bullets.remove(0);
//            }
//        }
//
//    }
//
//
//    public void draw(Batch batch) {
//        batch.begin();
//        sprite.setPosition(x, y);
//        sprite.draw(batch);
//        batch.end();
//
//    }
//
//    public ArrayList<Bullet> getBullets() {
//        return bullets;
//    }
//
//    public float getXPosition() {
//        return x;
//    }
//
//
//    public float getYPosition() {
//        return y;
//    }
//
//    public float getPlayerSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }
//
//    public void setXPosition(float dx) {
//        x = dx;
//    }
//
//    public void setYPosition(float dy) {
//        y = dy;
//    }
//
//    public float getPlayerHealth() {
//        return playerHealth;
//
//    }
//
//    public Sprite getSprite() {
//        return sprite;
//    }
//
//    public boolean detectEnemy(Enemy e) {
//        if (Vector2.dst(this.getXPosition(), this.getYPosition(), e.getXPosition(), e.getYPosition()) < enemy_detection_radius) {
//            return true;
//        }
//        return false;
//
//    }
//
//    public boolean shouldRemove() {
//        return remove;
//    }
//
//    public void takeDamage(float h) {
//        playerHealth -= h;
//    }
//
//     public void dispose()
//    {
//        tex.dispose();
//    }


}
