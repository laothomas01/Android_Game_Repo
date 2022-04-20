package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.ShortArray;
import com.hackslash.game.driver.HackAndSlash;

public class Player extends GameObject {

    int playerHealth;


    float maxSpeed;


    Vector2 player_position;
    Vector2 center;
    FloatArray vertices;

    /**
     * Filling in the polygon
     */
    Texture texture;
    PolygonSprite polySprite;
    PolygonSpriteBatch polyBatch;

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
        speed = 300;

        playerHealth = 100;

        maxSpeed = 300;


    }

    public void update(float dt) {


    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(player_position.x, player_position.y, 20, 20);
        sr.end();
//        sr.begin(ShapeRenderer.ShapeType.Line);
//        sr.setColor(0, 1, 0, 1);
//        setShape();
//        sr.polygon(vertices.toArray());
//
//
//        sr.end();
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

    public int getPlayerHealth(){
        return playerHealth;


    public void handleInputs(float dt) {
        

    }


}
