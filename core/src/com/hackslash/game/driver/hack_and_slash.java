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
    //BULLETS
    gameStateManager manager = new gameStateManager();

    class gameStateManager {
        Array<gameObject> collectionOfBullets;
        Array<gameObject> collectionOfRemovedBullets;

        public gameStateManager() {
            collectionOfBullets = new Array<>();
            collectionOfRemovedBullets = new Array<>();
        }

        public Array<gameObject> getCollectionOfBullets() {
            return collectionOfBullets;
        }

        public Array<gameObject> getCollectionOfRemovedBullets() {
            return collectionOfRemovedBullets;
        }

        public boolean removeBullets() {
            return collectionOfBullets.removeAll(collectionOfRemovedBullets, false);
        }


    }

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

        float lifeSpan;
        float maxLifeSpan;

        float distance;
        float impactDistance;

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
            lifeSpan = 0;
            maxLifeSpan = 2.0f;
            lifeSpan = maxLifeSpan;

        }
        //--------------------------------------------------------------

        //Update our game objects with new data every 60 frames
        public void update() {
//            this.position.set(this.possition.x, this.position.y);
            this.sprite.setPosition(this.position.x, this.position.y);
            this.sprite.setTexture(this.texture);
        }

        //------------------PHYSICS FUNCTIONS-------------------------

        public boolean hasCollided(gameObject object) {
            normal.set(this.position).sub(object.position);
            distance = normal.len();
            impactDistance = (this.sprite.getWidth() + object.sprite.getWidth()) / 1.7f;
            normal.nor();
            //when we collided, we should check the type of object we are colliding with
            if (distance < impactDistance) {
                return true;
            }
            return false;
        }

        public void performImpulseCollision(gameObject object) {

            if (hasCollided(object)) {
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
            this.position.add(this.velocity);
        }

        //----------------------------------------------------------------------


        //--------------------------NON-PHYSICS-RELATED-FUNCTIONS--------------------------------


        public void shoot(gameObject object, float dt) {




//            System.out.println("OLD:" + this.position);
//            Vector2 normal = new Vector2();
//            normal.set(object.position.x - this.position.x, object.position.y - this.position.y);
//            System.out.println("NORMAL:" + normal);
////            float len = normal.len();
////            System.out.println("NORMAL:" + normal.nor());
//            Vector2 unit = new Vector2();
//            unit.set(MathUtils.round(normal.nor().x), MathUtils.round(normal.nor().y));
//            System.out.println("UNIT:" + unit);
//            Vector2 offset = new Vector2();
//            offset.set(25 * unit.x, 25 * unit.y);
//            Vector2 newPosition = new Vector2();
//            newPosition.set(offset.add(this.position));
//            System.out.println("NEW:" + newPosition);
            if (coolDown <= 0) {
                if (manager.getCollectionOfBullets().size < 1) {
                    gameObject bullet = new gameObject();
                    bullet.sprite.setColor(Color.GREEN);
//                    bullet.velocity.set(unit);
//                    bullet.position.set(newPosition);
//                    bullet.update();
//                    manager.getCollectionOfBullets().add(bullet);
                }
            } else {
                coolDown -= dt;
            }

//            Vector2 newVelocity2 = new Vector2(MathUtils.cos(radians - 0.785398f), MathUtils.sin(radians - 0.785398f));
//            Vector2 newVelocity3 = new Vector2(MathUtils.cos(radians + 0.785398f), MathUtils.sin(radians + 0.785398f));
//
//            if (coolDown <= 0) {
//                if (manager.getCollectionOfBullets().size < 3) {
//                    gameObject bullet = new gameObject();
//                    bullet.sprite.setColor(Color.GREEN);
//////                    bullet.position.set(player.position);
////                    bullet.velocity.set(newVelocity);
////
////                    bullet.update();
////                    bullet.moveSpeed = 70f;z
////                    manager.getCollectionOfBullets().add(bullet);
//                    //timer
////                    skill1()
////                    //timer
////                    skill2()
////                    //timer
////                    skill()
//
////                    gameObject bullet2 = new gameObject();
////                    bullet2.sprite.setColor(Color.GREEN);
////                    bullet2.position.set(player.position);
////                    bullet2.velocity.set(newVelocity2);
////                    bullet2.update();
////                    manager.getCollectionOfBullets().add(bullet2);
////
////                    gameObject bullet3 = new gameObject();
////                    bullet3.sprite.setColor(Color.GREEN);
////                    bullet3.position.set(player.position);
////                    bullet3.velocity.set(newVelocity3);
////                    bullet3.update();
////                    manager.getCollectionOfBullets().add(bullet3);
//
//
//                }
//            } else {
//                coolDown -= dt;
//            }
        }

        //-----------------------------------------------------------


    }


    private SpriteBatch fontSpriteBatch;
    private BitmapFont font;


    private gameObject player;
    private gameObject projectile;

    private gameObject projectile2;

    private gameObject enemy;


    public void create() {

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

        for (gameObject b : manager.getCollectionOfBullets()) {
            b.sprite.draw(player.batch);
        }
        player.batch.end();


        //----------------------------------------------------------------------


        //-----------------------------test object rotation functions------------------
        projectile.rotateAround(player);
        projectile2.rotateAround(projectile);
        //------------------------------------------------------------------------------

        fontSpriteBatch.begin();

        //----------------------- DISPLAY TEXT ON SCREEN ---------------------------------

        font.draw(fontSpriteBatch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
        font.draw(fontSpriteBatch, "Upper Fleft, ENEMY DISTANCE=" + enemy.position.dst(player.position), 0, Gdx.graphics.getHeight() - 50);
        fontSpriteBatch.end();

        // ---------------------------------------------------------------------------------


        //check if player moves out of screen boundaries
        if (player.position.x > Gdx.graphics.getWidth()) {
            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        }
        if (player.position.x < 0) {
            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
        }

//        System.out.println("IS EMPTY!:" + collectionOfBullets.isEmpty());


        //TESTING FUNCTIONS
        enemy.performImpulseCollision(player);
//        enemy.moveTowards(player, deltaTime);


        //update all objects
        player.update();
        projectile.update();
        projectile2.update();
        enemy.update();

//
        for (gameObject b : manager.getCollectionOfBullets()) {
            if (b.hasCollided(enemy)) {
                manager.getCollectionOfRemovedBullets().add(b);
            }

            b.move(deltaTime);
            b.update();
        }
        manager.removeBullets();


    }
}





