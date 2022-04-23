package com.hackslash.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject {


    Vector2 player_position;


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


    }

    public Player(float posX, float posY, int player_size, float player_speed) {
        x = posX;
        y = posY;
        size = player_size;
        speed = player_speed;
    }


    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(player_position.x, player_position.y, 20, 20);
        sr.end();
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


}
