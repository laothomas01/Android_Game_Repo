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
    float speed;

    public TestObject() {

        speed = 500;
        size = 50;
        velocity = new Vector2();
        position = new Vector2(MathUtils.random(0, Gdx.graphics.getWidth()), MathUtils.random(0, Gdx.graphics.getHeight()));
        img = new Texture("square.png");
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

    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    public void setPosition(float x, float y) {
        this.position = new Vector2(x, y);
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

    // ------------------- MADE FOR TESTING AND UNDERSTANDING IMPULSE PHYSICS --------------
    ArrayList<TestObject> listOfObjects;
    //Creating variables for collision testing and experimenting
    Vector2 velocity;
    Vector2 normal;
    Vector2 temp;

    TestObject player;

    // --------------------------------------------------------------------------------------

    public void create() {

        player = new TestObject();
        player.getSprite().setColor(Color.GREEN);
        player.getSprite().setSize(75, 75);
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

        listOfObjects.get(0).getSprite().setColor(new Color(Color.BLUE));


    }


    float alpha = 0;

    public void render() {


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        IMPULSE_PHYSICS_COLLISION();


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

    public void IMPULSE_PHYSICS_COLLISION() {

        batch.begin();

        for (int i = 0; i < listOfObjects.size(); i++) {
            listOfObjects.get(i).draw(batch);
        }


        player.getSprite().draw(batch);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.velocity.x = 1;
            player.velocity.y = 1;
            player.speed = 1000;

        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.velocity.x = -1;

            player.speed = 1000;
            player.velocity.y = -1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.UP)) {

            player.speed = 1000;
            player.velocity.x = -1;
            player.velocity.y = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.velocity.x = 1;
            player.speed = 1000;
            player.velocity.y = -1;

        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.velocity.x = -1;
            player.speed = 1000;

        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.velocity.y = 1;
            player.speed = 1000;

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.velocity.y = -1;
            player.speed = 1000;

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.velocity.x = 1;
            player.speed = 1000;
        } else {
            player.speed = 0;
            player.velocity.x = 0;
            player.velocity.y = 0;
        }

        player.position.x = player.position.x + player.velocity.x * player.speed * deltaTime;
        player.position.y = player.position.y + player.velocity.y * player.speed * deltaTime;
        player.getSprite().setPosition(player.position.x, player.position.y);


        checkForCollision();
        listOfObjects.get(0).getSprite().setPosition
                (

                        listOfObjects.get(0).getTestObjectPosition().x + listOfObjects.get(0).getTestObjectVelocity().x,
                        listOfObjects.get(0).getTestObjectPosition().y + listOfObjects.get(0).getTestObjectVelocity().y

                );

//


    }

    /**
     * CALL THIS FUNCTION TO TEST ORBITTING AN OBJECT AROUND ANOTHER OBJECT
     * <p>
     * MAKE SURE TO COMMENT THIS FUNCTION OUT IF YOU ARE TESTING PLAYER INPUT CONTROLS WITH ANOTHER OBJECT!
     */

    public void checkForCollision() {
        //impulse collision physics
        normal.set(player.getTestObjectPosition()).sub(listOfObjects.get(0).getTestObjectPosition());
        float distance = normal.len();
        float impactDistance = (player.getSize() + listOfObjects.get(0).getSize()) / 1f;
        normal.nor();


        if (distance < impactDistance) {
            // here is where the impulse physics occursy -1
            temp.set(normal).scl((impactDistance - distance) / 2);
            player.getTestObjectPosition().add(temp);
            temp.set(normal).scl((impactDistance - distance) / 2);
            listOfObjects.get(0).getTestObjectPosition().sub(temp);

            velocity.set(player.getTestObjectVelocity()).sub(listOfObjects.get(0).getTestObjectVelocity());
            float impulse = (-(1 + COLLISION_COEFF) * normal.dot(velocity)) /
                    (normal.dot(normal) * (1 / 0.5f) + (1 / 0.5f));
            temp.set(normal).scl(impulse / 0.5f);
            player.getTestObjectVelocity().add(temp);
            temp.set(normal).scl(impulse / 0.5f);
            listOfObjects.get(0).getTestObjectVelocity().sub(temp);
        }

    }

    public void ORBIT_AROUND_OBJECT() {
        batch.setProjectionMatrix(followCam.combined);
        //draw sprites
        batch.begin();
        SpriteOfPositionToRotate.draw(batch);


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


