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

/**
 * SUMMARY
 * ________
 * THIS IS A BRANCH I WILL USE TO TEST NEW FEATURES AND WILL INTEGRATE MY WORK BACK INTO THE MAIN BRANCH
 * <p>
 * CONSIDER THIS A PLAYGROUND OF SORTS
 * [x] following camera
 * [x] orbiting sprite around another sprite
 * [x] testing out linear interpolation
 * [] bouncy sprite collision
 */
class TestObject {
    Vector2 position;
    Vector2 velocity;
    float size;
    Texture img;
    Sprite sprite;

    public TestObject() {

        size = 50;
        position = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
        velocity = new Vector2();
        img = new Texture("circle.png");
        sprite = new Sprite(img);
        sprite.setPosition(position.x, position.y);
        sprite.setSize(size, size);
    }

    public void draw(SpriteBatch batch) {
        getSprite().draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getTestObjectVelocity() {
        return velocity;
    }

    public Vector2 getTestObjectPosition() {
        return position;
    }

    public float getSize() {
        return size;
    }

}

public class hack_and_slash extends ApplicationAdapter {

    /**
     * Impulse for giving collisions a slight bounce
     */
    public static final float COLLISION_COEFF = 0.1f;
    //camera following player during gameplay
    OrthographicCamera followCam;
    float deltaTime = 0;

    Vector2 positionToRotateAround;

    //orbitting object around a position
    Vector2 projectilePosition;

    float positionToRotateDx;
    float positionToRotateDy;

    Vector2 VelocityOfPositionToRotateAround;
    Vector2 VelocityOfRotatingObject;

    Vector2 testSpriteVelocity;


    //direction of orbiting object
    float projectilePositionDx;
    float projectilePositionDy;


    Sprite projectileSprite;
    Sprite SpriteOfPositionToRotate;


    Texture positionToRotateImg;
    Texture projectileImg;

    SpriteBatch batch;

    float radius;
    float radians;


    float distance;
    float x;
    float y;

    //initialize testing sprites
    ArrayList<TestObject> listOfObjects;
    //Creating variables for collision testing and experimenting
    Vector2 velocity;
    Vector2 normal;
    Vector2 temp;


    public void create() {

        listOfObjects = new ArrayList<>();
        velocity = new Vector2();
        normal = new Vector2();
        temp = new Vector2();

        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

//        followCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        x = 0;
        y = 0;
        distance = 0;
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
        SpriteOfPositionToRotate.setSize(50, 50);
//        SpriteOfPositionToRotate.scale(1);
        positionToRotateDx = 0;
        positionToRotateDy = 0;

        projectileImg = new Texture("circle.png");
        projectileSprite = new Sprite(projectileImg);
        projectilePosition = new Vector2(positionToRotateAround.x + 100, positionToRotateAround.y + 100);
        projectileSprite.setPosition(projectilePosition.x, projectilePosition.y);
//        projectileSprite.scale(1);
        projectileSprite.setSize(50, 50);
        projectilePositionDx = 0;
        projectilePositionDy = 0;

        for (int i = 0; i < 20; i++) {
            listOfObjects.add(new TestObject());
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

        for (int i = 0; i < listOfObjects.size(); i++) {
            listOfObjects.get(i).draw(batch);
        }

        projectileSprite.draw(batch);

        //draw test sprites for collision detection

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

        /**
         * Check for Collisions
         *
         * */

//        for (int i = 0; i < listOfObjects.size(); i++) {
        //what is a normal vector?
        normal.set(listOfObjects.get(0).getTestObjectPosition().sub(positionToRotateAround));
        //length of the normal vector
        float dist = normal.len();
        float impactDistance = (50 + listOfObjects.get(0).getSize()) / 2;
        normal.nor();

        if (dist < impactDistance) {

            temp.set(normal).scl((impactDistance - dist) / 2);
            listOfObjects.get(0).getTestObjectPosition().add(temp);
            temp.set(normal).scl((impactDistance - dist) / 2);
            listOfObjects.get(0).getTestObjectPosition().sub(temp);

            //Newton's Law of Impact
            // Convert the two velocities into a single reference frame
            velocity.set(listOfObjects.get(0).getTestObjectVelocity().sub(positionToRotateAround));

            //Compute Impulse
            float impulse = (-(1 + COLLISION_COEFF) * normal.dot(velocity)) /
                    (normal.dot(normal) * (1 / 1.0f + 1 / 1.0f));

            // Change velocity of the two objects using this impulse
            temp.set(normal).scl(impulse / 1.0f);
            listOfObjects.get(0).getTestObjectVelocity().add(temp);
            temp.set(normal).scl(impulse / 1.0f);
            listOfObjects.get(0).getTestObjectVelocity().sub(temp);

//            }
        }

    }


    //testing purposes
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

//    public void checkForCollision(TestObject obj, Vector2 pos, Vector2 vel) {
//        normal.set(obj.getTestObjectPosition().sub(pos));
//        float distance = normal.len();
//        float impactDist = (obj.getSize() + 50) / 3f;
//        normal.nor();
//
//        if (distance < impactDist) {
//            temp.set(normal).scl((impactDist - distance) / 2);
//            obj.getTestObjectPosition().add(temp);
//            temp.set(normal).scl((impactDist - distance) / 2);
//            float impulse = (-(1 + COLLISION_COEFF) * normal.dot(velocity)) / (normal.dot(normal) * (1 / 1.0f + 1 / 1.0f));
//
//            temp.set(normal).scl(impulse/ 1.0f);
//            obj.getTestObjectVelocity().add(temp);
//            temp.set(normal).scl(impulse/1.0f);
//
//        }
//
//
//    }


}


