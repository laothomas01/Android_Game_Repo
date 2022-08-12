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
    Vector2 VelocityOfRotatingObject;
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


    float rotateObjectDx;
    float rotateObjectDy;

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

        projectilePositionDx = 0;
        projectilePositionDy = 0;
        VelocityOfRotatingObject = new Vector2(projectilePositionDx, projectilePositionDy);

        batch = new SpriteBatch();


        positionToRotateImg = new Texture("square.png");
        positionToRotateSprite = new Sprite(positionToRotateImg);
        positionToRotateAround = new Vector2(200, 300);
        positionToRotateSprite.setPosition(positionToRotateAround.x, positionToRotateAround.y);
        positionToRotateSprite.scale(1);
        positionToRotateDx = 0;
        positionToRotateDy = 0;

        projectileImg = new Texture("circle.png");
        projectileSprite = new Sprite(projectileImg);
        projectilePosition = new Vector2(300, 300);
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
        distance = MathUtils.round(distance);

        VelocityOfPositionToRotateAround.set(positionToRotateDx, positionToRotateDy);
        VelocityOfRotatingObject.set(MathUtils.cos(radians) * distance, MathUtils.sin(radians) * distance);

        positionToRotateAround.set(positionToRotateAround.x + VelocityOfPositionToRotateAround.x * 500 * deltaTime, positionToRotateAround.y + VelocityOfPositionToRotateAround.y * 500 * deltaTime);
        positionToRotateSprite.setPosition(positionToRotateAround.x, positionToRotateAround.y);

//        System.out.println(" DISTANCE: " + distance + " DEGREES: " + degrees + "----->" + " RADIANS:" + radians + " x: " + positionToRotateAround.x + " y: " + positionToRotateAround.y + "   COS    " + MathUtils.cos(radians) + "," + MathUtils.sin(radians) + "*" + distance + "=" + positionToRotateAround.x + MathUtils.cos(radians) * distance + "    SIN     " + positionToRotateAround.y + MathUtils.sin(radians) * distance);

//        System.out.println(positionToRotateAround.x + VelocityOfPositionToRotateAround.x * distance + ":" + positionToRotateAround.y + VelocityOfPositionToRotateAround.y * distance);

//        VelocityOfPositionToRotateAround.set(MathUtils.cos(radians), MathUtils.sin(radians));

        System.out.println("DISTANCE:" + distance);
        /**
         *
         *
         *
         * projectilePosition = positionRotateAroundPosition + directionOfPositionToRotateAround * how much to be away from the rotating position
         *
         * Am I using Polar Coordinates????
         *
         * We are using polar coordinates.
         *
         * We make known 2 components: the angle we are rotating at, the distance between the rotating object and the point being rotated around
         *
         *
         *
         *
         *
         *
         *
         */
        projectilePosition.set(
                //if no player movement, positionToRotateAround does not change, but the radians change.

                /**
                 *
                 *  convert distance and theta to X and Y cartesian coordinates
                 *
                 *          dx = distance * cos(theta)
                 *          dy = distance * sin(theta)
                 *
                 *
                 * */

                //player position           player direction
                positionToRotateAround.x + VelocityOfRotatingObject.x, positionToRotateAround.y + VelocityOfRotatingObject.y);


        System.out.println("PROJECTILE POSITION:" + projectilePosition.toString());
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);


    }


}
