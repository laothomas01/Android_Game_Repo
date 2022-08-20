package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.hackslash.game.controller.BulletController;
import com.hackslash.game.controller.EnemyController;
import com.hackslash.game.controller.PlayerController;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.GameObject;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.*;
import jdk.nashorn.internal.runtime.Debug;
import org.graalvm.compiler.loop.MathUtil;
import org.graalvm.compiler.replacements.Log;

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

public class hack_and_slash extends ApplicationAdapter {


    float COLLISION_COEF = 1.0f;
    float deltaTime;

    Vector2 normal;
    Vector2 temp;
    Vector2 newVelocity;
    Array<gameObject> collectionOfBullets;

    //BULLETS

    class gameObject {


        //-------------------------------PHYSICS ATTRIBUTES----------------------
        Vector2 position;
        Vector2 velocity;
        Vector2 acceleration;
        Vector2 angularVelocity;

        float radians;
        float orbitDistance;
        float health;
        float damage;

        float moveSpeed;


        float width;
        float height;
        float rotationSpeed;

        float mass;

        //------------------------------------------------------------------------------

        //----------------------------MISC ATTRIBUTES FOR GAME OBJECTS-------------------


        float maxCoolDown;
        float coolDown;


        //

        //-----------------------GAME GRAPHICS----------------------
        Sprite sprite;
        Texture texture;
        SpriteBatch batch;

        //------------------------------------------------------------

        //---------------------GAME OBJECT CONSTRUCTOR----------------
        public gameObject() {

            position = new Vector2(0, 0);
            velocity = new Vector2(0, 0);
            acceleration = new Vector2(0, 0);
            angularVelocity = new Vector2(0, 0);
            radians = 0;
            orbitDistance = 0;
            health = 0;
            damage = 0;
            width = 0;
            height = 0;
            rotationSpeed = 0;
            texture = new Texture("circle.png");
            sprite = new Sprite(texture);
            sprite.setPosition(position.x, position.y);
            batch = new SpriteBatch();
            mass = 1.0f;
            moveSpeed = 0;
            coolDown = 0f;
            maxCoolDown = 1f;
            coolDown = maxCoolDown;
//            collectionOfBullets = new Array<>();

        }
        //--------------------------------------------------------------

        //Update our game objects with new data every 60 frames
        public void update() {
            this.position.set(this.position.x, this.position.y);
            this.sprite.setPosition(this.position.x, this.position.y);
            this.sprite.setTexture(this.texture);
        }

        //------------------PHYSICS FUNCTIONS-------------------------
        public void checkCollision(gameObject object) {
            normal.set(this.position).sub(object.position);
            float distance = normal.len();
            float impactDistance = (this.sprite.getWidth() + object.sprite.getWidth()) / 1.7f;
            normal.nor();
            //when we collided, we should check the type of object we are colliding with
            if (distance < impactDistance) {
                //let's give an object impulse during collision
                //we use temp because the method would change the contents of normal!
                temp.set(normal).scl((impactDistance - distance) / 2);
                this.position.add(temp);
                temp.set(normal).scl((impactDistance - distance) / 2);
                object.position.sub(temp);

                //Let's implement newton's law of impact
                //convert the two velocities into a single reference frame
                newVelocity.set(this.velocity.sub(object.velocity));

                // Compute the impulse (see Essential Math for Game Programmers)
                float impulse = (-(1 + COLLISION_COEF) * normal.dot(newVelocity)) / (normal.dot(normal) * (1 / this.mass + 1 / object.mass));
                //Change velocity of two object using this impulse
                temp.set(normal).scl(impulse / this.mass);
                this.velocity.add(temp);
                temp.set(normal).scl(impulse / object.mass);
                object.velocity.sub(temp);
            }
        }

        // calculate number of orbits per second(note: this calculation for rotation speed is not accurate)
        public void increaseRotationAngle() {
            if (this.radians < (MathUtils.PI * 2)) {
                this.radians += rotationSpeed;
            } else {
                this.radians = 0;
            }
        }


        //perform basic circular motion around the parameterized object
        public void rotateAround(gameObject parent) {
            this.increaseRotationAngle();
            this.angularVelocity.set(MathUtils.cos(this.radians), MathUtils.sin(this.radians));
            this.velocity.set(this.orbitDistance * this.angularVelocity.x, this.orbitDistance * this.angularVelocity.y);
            this.position.set(parent.position.x + this.velocity.x, parent.position.y + this.velocity.y);

        }

        //follow a target
        public void moveTowards(gameObject parent, float dt) {
            this.radians = MathUtils.atan2(parent.position.y - this.position.y, parent.position.x - this.position.x);
            this.velocity.set(MathUtils.cos(this.radians), MathUtils.sin(this.radians));
            this.position.set(this.position.add(this.velocity.scl(this.moveSpeed * dt)));
        }

        public void move(float dt) {
            this.position.set(this.position.add(this.velocity.scl(this.moveSpeed * dt)));
        }

        //----------------------------------------------------------------------


        //--------------------------NON-PHYSICS-RELATED-FUNCTIONS--------------------------------


        public void shoot(gameObject object, float dt) {
            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
            System.out.println("RADIANS:" + radians);
            Vector2 newVelocity = new Vector2(MathUtils.cos(radians), MathUtils.sin(radians));
            if (coolDown <= 0) {
                if (collectionOfBullets.size < 1) {
                    gameObject bullet = new gameObject();
                    System.out.println("POSITION:" + this.position);
                    bullet.position.set(this.position);
                    System.out.println("BULLET POSITION:" + bullet.position);
                    bullet.sprite.setColor(Color.GREEN);
                    System.out.println("VELOCITY:" + newVelocity);
                    bullet.velocity.set(newVelocity);
                    System.out.println("BULLET VELOCITY:" + bullet.velocity.toString());
                    bullet.moveSpeed = 200f;
                    collectionOfBullets.add(bullet);
                }
            } else {
                coolDown -= dt;
            }
        }
//        public void shoot(gameObject object, float dt) {
//
//            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
//            Vector2 newVelocity = new Vector2(MathUtils.cos(radians), MathUtils.sin(radians));
//
//            if (coolDown <= 0) {
//
//                //do not do nested if's. change this later
//                if (this.collectionOfBullets.size < 1) {
//
////                    gameObject bullet1 = new gameObject();
////                    bullet1.sprite.setSize(10, 10);
////                    bullet1.sprite.setColor(Color.RED);
////                    bullet1.texture = bulletImg;
////                    bullet1.position = this.position;
////                    bullet1.velocity = newVelocity;
////                    bullet1.moveSpeed = 200f;
////                    this.collectionOfBullets.add(bullet1);
//                }
//                coolDown = maxCoolDown;
//            } else {
//                coolDown -= dt;
//            }
//        }


        //-----------------------------------------------------------


    }


    private SpriteBatch fontSpriteBatch;
    private BitmapFont font;


    private gameObject player;
    private gameObject projectile;

    private gameObject projectile2;

    private gameObject enemy;


    public void create() {
        collectionOfBullets = new Array<>();
        //initialize....
        normal = new Vector2();
        temp = new Vector2();
        newVelocity = new Vector2();

        font = new BitmapFont();
        fontSpriteBatch = new SpriteBatch();

        //-----------------------test player object-------------------------
        player = new gameObject();
        player.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        player.texture = new Texture("square.png");
        //------------------------------------------------------------------

        //-----------------------testing first rotating projectile---------------
        projectile = new gameObject();
        //distance basically an offset from the object you are following
        projectile.orbitDistance = 100;
        projectile.position.set(player.position.x + projectile.orbitDistance, player.position.y + projectile.orbitDistance);
        projectile.texture = new Texture("circle.png");
        //orbit speed needs more accurate calculations
        projectile.rotationSpeed = .03f;
        projectile.sprite.setSize(projectile.sprite.getRegionWidth(), projectile.sprite.getRegionHeight());
        //------------------------------------------------------------------------


        //-----------------------testing second rotating projectile----------
        projectile2 = new gameObject();
        projectile2.orbitDistance = 50;
        projectile2.position.set(projectile.position.x + projectile2.orbitDistance, projectile.position.y + projectile2.orbitDistance);
        projectile2.texture = new Texture("circle.png");
        projectile2.sprite.setColor(Color.BLUE);
        projectile2.rotationSpeed = 0.07f;
        projectile2.sprite.setSize(projectile2.sprite.getRegionWidth() / 2, projectile2.sprite.getRegionHeight() / 2);
        //--------------------------------------------------------------------

        //------------------------test enemy object-------------------------------
        enemy = new gameObject();
        enemy.position.set(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
        enemy.texture = new Texture("circle.png");
        enemy.sprite.setColor(Color.RED);
        enemy.moveSpeed = 500;
        //--------------------------------------------------------------------------------

        //---------------------testing shooting mechanic----------------------------------
//        bulletImg = new Texture("circle.png");

        collectionOfBullets = new Array<>();

    }

    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //-----------------------------draw game objects-----------------------

        player.shoot(enemy, deltaTime);


        player.batch.begin();
        player.sprite.draw(player.batch);
        projectile.sprite.draw(player.batch);
        projectile2.sprite.draw(player.batch);
        enemy.sprite.draw(player.batch);
        if (!collectionOfBullets.isEmpty()) {
            collectionOfBullets.get(0).sprite.draw(player.batch);
        }


//        System.out.println("BULLETS:" + player.collectionOfBullets);
//        for (gameObject b : player.collectionOfBullets) {
//            b.sprite.draw(player.batch);
//        }


        //----------------------------------------------------------------------

        //-----------------------------test object rotation functions------------------
        projectile.rotateAround(player);
        projectile2.rotateAround(projectile);
        //------------------------------------------------------------------------------

        fontSpriteBatch.begin();

        //----------------------- DISPLAY TEXT ON SCREEN ---------------------------------

        font.draw(fontSpriteBatch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
        font.draw(fontSpriteBatch, "Upper left, ENEMY DISTANCE=" + enemy.position.dst(player.position), 0, Gdx.graphics.getHeight() - 50);
//        font.draw(fontSpriteBatch, "Upper left, RADIANS=" + projectile.radians, 0, Gdx.graphics.getHeight() - 100);
//        font.draw(fontSpriteBatch, "Upper left, ANGULAR VELOCITY=" + "(" + MathUtils.cos(projectile.radians) + "        " + MathUtils.sin(projectile.radians) + ")", 0, Gdx.graphics.getHeight() - 150);
//        font.draw(fontSpriteBatch, "Upper left, POSITION=" + "(" + MathUtils.round(projectile.position.x) + "    ,   " + MathUtils.round(projectile.position.y) + ")", 0, Gdx.graphics.getHeight() - 200);
        fontSpriteBatch.end();

        // ---------------------------------------------------------------------------------


        //check if player moves out of screen boundaries
        if (player.position.x > Gdx.graphics.getWidth()) {
            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        }
        if (player.position.x < 0) {
            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        }


        //TESTING FUNCTIONS
//        enemy.checkCollision(player);
//        enemy.moveTowards(player, deltaTime);


        //TESTING PLAYER SHOOT FUNCTION
//        player.batch.begin();
//        player.collectionOfBullets.get(0).sprite.draw(player.batch);
//        player.batch.end();

//        for (gameObject b : player.collectionOfBullets) {
//            b.position.set(b.position.add(, 1));
//        }


        //update all objects
        player.update();
        projectile.update();
        projectile2.update();
        enemy.update();
        for (gameObject b : collectionOfBullets) {
            b.update();
        }


        player.batch.end();

//        //update every bullet in the player
//        for (gameObject b : player.collectionOfBullets) {
//            b.position.add(1, 1);
//            b.update();
//        }


    }
}



