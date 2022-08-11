package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.hackslash.game.controller.BulletController;
import com.hackslash.game.controller.EnemyController;
import com.hackslash.game.controller.PlayerController;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.*;
import org.graalvm.compiler.loop.MathUtil;

import java.util.ArrayList;

public class hack_and_slash extends ApplicationAdapter {

    float deltaTime = 0;
    Vector2 positionToRotateAround;

    Vector2 projectilePosition;

    float positionToRotateDx;
    float positionToRotateDy;

    Vector2 VelocityOfPositionToRotateAround;
    float projectilePositionDx;
    float projectilePositionDy;


    Sprite projectileSprite;
    Sprite positionToRotateSprite;


    Texture positionToRotateImg;
    Texture projectileImg;

    SpriteBatch batch;

    float radius;
    float radians;

    float arcLength;

    float degrees;

    float distance;
    float x;
    float y;

    public void create() {
        x = 0;
        y = 0;
        distance = 0;
        degrees = 45;
        radians = degrees * (MathUtils.PI / 180);
        arcLength = 0;
        positionToRotateDx = 0;
        positionToRotateDy = 0;

        VelocityOfPositionToRotateAround = new Vector2(positionToRotateDx, positionToRotateDy);

        batch = new SpriteBatch();


        positionToRotateImg = new Texture("square.png");
        positionToRotateSprite = new Sprite(positionToRotateImg);
        positionToRotateAround = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        positionToRotateSprite.setPosition(positionToRotateAround.x, positionToRotateAround.y);
        positionToRotateSprite.scale(1);
        positionToRotateDx = 0;
        positionToRotateDy = 0;

        projectileImg = new Texture("circle.png");
        projectileSprite = new Sprite(projectileImg);
        projectilePosition = new Vector2(Gdx.graphics.getWidth() / 2 + 100, Gdx.graphics.getHeight() / 2 + 100);
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);
        projectileSprite.scale(1);
        projectilePositionDx = 0;
        projectilePositionDy = 0;
    }


    float alpha = 0;

    public void render() {


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw sprites
        batch.begin();
        positionToRotateSprite.draw(batch);
        projectileSprite.draw(batch);
        batch.end();

//        radius = positionToRotateAround.dst(projectilePosition);
//        arcLength = radians * radius;


        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            positionToRotateDx = 1;
            positionToRotateDy = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            positionToRotateDx = -1;
            positionToRotateDy = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {
            positionToRotateDx = -1;
            positionToRotateDy = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            positionToRotateDx = 1;
            positionToRotateDy = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            positionToRotateDx = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            positionToRotateDy = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            positionToRotateDy = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            positionToRotateDx = 1;
        } else {
            positionToRotateDx = 0;
            positionToRotateDy = 0;
        }

        if (radians < MathUtils.PI * 2) {
            radians += .05f;
        } else {
            radians = 0;
        }

        distance = projectilePosition.dst(positionToRotateAround);


        VelocityOfPositionToRotateAround.set(positionToRotateDx, positionToRotateDy);
        positionToRotateAround.set(positionToRotateAround.x + VelocityOfPositionToRotateAround.x * 500 * deltaTime, positionToRotateAround.y + VelocityOfPositionToRotateAround.y * 500 * deltaTime);


        positionToRotateSprite.setPosition(positionToRotateAround.x, positionToRotateAround.y);

        System.out.println(" DISTANCE: " + distance + " DEGREES: " + degrees + "----->" + " RADIANS:" + radians + " x: " + x + " y: " + y + "   COS    " + MathUtils.cos(radians) * distance + "    SIN     " + MathUtils.sin(radians) * distance);
        projectilePosition.set(positionToRotateAround.x + MathUtils.cos(radians) * distance, positionToRotateAround.y + MathUtils.sin(radians) * distance);
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);


    }


}
