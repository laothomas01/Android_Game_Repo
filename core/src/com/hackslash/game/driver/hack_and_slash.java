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
import com.badlogic.gdx.scenes.scene2d.ui.List;
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

    OrthographicCamera followCam;
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
    Sprite SpriteOfPositionToRotate;


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

    ArrayList<Sprite> sprites;


    public void create() {
        sprites = new ArrayList<>();
        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        followCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        SpriteOfPositionToRotate = new Sprite(positionToRotateImg);
        positionToRotateAround = new Vector2(0, 0);
        SpriteOfPositionToRotate.setPosition(positionToRotateAround.x, positionToRotateAround.y);
        SpriteOfPositionToRotate.scale(1);
        positionToRotateDx = 0;
        positionToRotateDy = 0;

        projectileImg = new Texture("circle.png");
        projectileSprite = new Sprite(projectileImg);
        projectilePosition = new Vector2(positionToRotateAround.x + 100, positionToRotateAround.y + 100);
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);
        projectileSprite.scale(1);
        projectilePositionDx = 0;
        projectilePositionDy = 0;

        for (int i = 0; i < 100; i++) {
            sprites.add(new Sprite(new Texture("circle.png")));
        }
        for (Sprite s : sprites) {
            s.setPosition(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
            s.scale(MathUtils.random(0.2f, 1));
        }
    }


    float alpha = 0;

    public void render() {


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(followCam.combined);
        //draw sprites
        batch.begin();
        SpriteOfPositionToRotate.draw(batch);
        projectileSprite.draw(batch);

        //draw test sprites for collision detection
        for (Sprite s : sprites) {
            s.draw(batch);
        }
        batch.end();


        handleUserInput();


        // in radians, check when radians = 6.28 radians OR (360 degrees).
        if (radians < MathUtils.PI * 2) {
            radians += .05f;
        } else {
            radians = 0;
        }


        distance = projectilePosition.dst(positionToRotateAround);
        distance = MathUtils.round(distance);


        movePlayer();


        //calculate x and y direction of rotating object using polar coordinates
        //x = distance * cos(radians)
        //y = distance * sin(radians)
        VelocityOfRotatingObject.set(MathUtils.cos(radians) * distance, MathUtils.sin(radians) * distance);

        projectilePosition.set(
                //player position       +     rotating object direction = rotating object moving in direction of player
                positionToRotateAround.x + VelocityOfRotatingObject.x, positionToRotateAround.y + VelocityOfRotatingObject.y);


        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);


        //linear interpolate the following camera's position
        Vector3 position = followCam.position;
        // a + (b - a) * lerp
        // b = target
        // a = current camera position

        position.x = followCam.position.x + (positionToRotateAround.x * 1f - followCam.position.x) * 0.5f;
        position.y = followCam.position.y + (positionToRotateAround.y * 1f - followCam.position.y) * 0.5f;


        followCam.position.set(position);
        followCam.update();
    }

    public void handleUserInput() {
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
    }

    public void movePlayer() {
        VelocityOfPositionToRotateAround.set(positionToRotateDx, positionToRotateDy);
        positionToRotateAround.set(positionToRotateAround.x + VelocityOfPositionToRotateAround.x * 500 * deltaTime, positionToRotateAround.y + VelocityOfPositionToRotateAround.y * 500 * deltaTime);
        SpriteOfPositionToRotate.setPosition(positionToRotateAround.x, positionToRotateAround.y);


    }


}
