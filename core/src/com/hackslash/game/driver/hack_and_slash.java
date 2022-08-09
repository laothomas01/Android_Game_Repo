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

    float angleV;
    float angleA;
    float angle;

    public void create() {
        angle = 0;
        angleA = 0.01f;
        angleV = 0.1f;
        radius = 0;
        radians = 0;
        arcLength = 0;
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


    public void render() {


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw sprites
        batch.begin();
        positionToRotateSprite.draw(batch);
        projectileSprite.draw(batch);
        batch.end();

        radians = MathUtils.atan2(projectilePosition.y - positionToRotateAround.y, projectilePosition.x - positionToRotateAround.x);
        radius = positionToRotateAround.dst(projectilePosition);
        arcLength = radians * radius;
        System.out.println("    RADIANS: " + radians + "    RADIUS:  " + radius + " ARC LENGTH: " + arcLength);


        projectilePosition.set(projectilePosition.rotateAroundDeg(positionToRotateAround, 90 * deltaTime));
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);
        //between some interval of time,  we are adding more angular acceleration until 60 frames are up.


    }

}
