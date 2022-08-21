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


    //used to add a slight bounce to colliding objects
    float COLLISION_COEF = 1.0f;
    float deltaTime;

    //-----------------------IMPULSE COLLISION VECTORS---------------
    Vector2 normal;
    Vector2 temp;
    Vector2 newVelocity;
    //---------------------------------------------------------------

    //BULLETS
//    float radians2 = 0.0174533f;
//    float radians3 = 0.0174533f;
//    float radians4 = 0.0174533f;


    gameStateManager manager = new gameStateManager();


    /*
     *
     * Class for handling game events:
     * What is a game state?
     * -collision?
     * -detection?
     * -removing objects?
     * -adding objects?
     * -loading output, saving input?
     * */
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

        public void manageBullets() {
            for (gameObject b : manager.getCollectionOfBullets()) {
                if (b.hasCollided(enemy)) {
                    manager.getCollectionOfRemovedBullets().add(b);
                }
                System.out.println("LIFESPAN:" + b.lifeSpan);
                if (b.lifeSpan <= 0) {
                    b.lifeSpan = b.maxLifeSpan;
                    manager.getCollectionOfRemovedBullets().add(b);
                } else {
                    b.lifeSpan -= deltaTime;
                }

                b.move(deltaTime);
                b.update();
            }
            manager.removeBullets();
        }


    }


    /*
     *
     * Any in-game entity that can be destroyed,created or interacted with
     *   -exp drops
     *   -the player
     *   -enemies
     *   -bullets
     *
     * */
    class gameObject {


        //-------------------------------PHYSICS ATTRIBUTES----------------------
        Vector2 position;
        Vector2 velocity;
        Vector2 acceleration;
        Vector2 angularVelocity;

        float rotateRadians;
        float shootRadians;
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

        float skillMaxCoolDown;
        float skillCoolDown;

        float lifeSpan;
        float maxLifeSpan;

        float distance;
        float impactDistance;

        //----------------------------------------------------------

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
            rotateRadians = 0;
            shootRadians = 0.261799f;
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
            maxCoolDown = 0.25f;
            coolDown = maxCoolDown;
            maxLifeSpan = 1f;
            skillMaxCoolDown = 2.0f;
            skillCoolDown = skillMaxCoolDown;
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
            if (this.rotateRadians < (MathUtils.PI * 2)) {
                this.rotateRadians += rotationSpeed;
            } else {
                this.rotateRadians = 0;
            }
        }


        //perform basic circular motion around the parameterized object
        public void rotateAround(gameObject parent) {
            this.increaseRotationAngle();
            this.angularVelocity.set(MathUtils.cos(this.rotateRadians), MathUtils.sin(this.rotateRadians));
            this.velocity.set(this.orbitDistance * this.angularVelocity.x, this.orbitDistance * this.angularVelocity.y);
            this.position.set(parent.position.x + this.velocity.x, parent.position.y + this.velocity.y);
        }

        //follow a target
        public void moveTowards(gameObject parent, float dt) {
            this.rotateRadians = MathUtils.atan2(parent.position.y - this.position.y, parent.position.x - this.position.x);
            this.velocity.set(MathUtils.cos(this.rotateRadians), MathUtils.sin(this.rotateRadians));
            this.position.set(this.position.add(this.velocity.scl(this.moveSpeed * dt)));
        }

        public void move(float dt) {
            this.position.add(this.velocity.x * this.moveSpeed * dt, this.velocity.y * this.moveSpeed * dt);
        }

        //----------------------------------------------------------------------


        //--------------------------NON-PHYSICS-RELATED-FUNCTIONS--------------------------------


        //this should handle all ranged attacks??????
        public void shoot(gameObject object, float dt) {

//            constantVelocityShoot(object, dt);

            constantVelocityShoot(object, dt);
            //SKILL #2
            fanShot(object, dt);
            System.out.println("SHOOT RADIANS:" + shootRadians);


            //handling cases for bullets
            manager.manageBullets();

        }

        public void constantVelocityShoot(gameObject object, float dt) {
            //shooting a bullet with linear motion but offset from player's position
            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
            Vector2 vel = new Vector2(MathUtils.cos(radians), MathUtils.sin(radians));
            Vector2 offsetPosition = new Vector2((vel.x * 50) + this.position.x, (vel.y * 50) + this.position.y);


            if (coolDown <= 0) {
                if (manager.getCollectionOfBullets().size < 1) {
                    //SKILL #1
                    //Instantiate a bullet with constant velocity
                    gameObject bullet = new gameObject();
                    bullet.sprite.setColor(Color.GREEN);
                    bullet.velocity.set(vel);
                    bullet.position.set(offsetPosition);
                    bullet.moveSpeed = 500f;
                    bullet.update();
                    manager.getCollectionOfBullets().add(bullet);
                }

            } else {
                coolDown -= dt;
            }
        }

        public void fanShot(gameObject object, float dt) {

            if (this.shootRadians < (MathUtils.PI * 2)) {
                this.shootRadians += 0.0174533f;
            } else {
                this.shootRadians = 0.261799f;
            }

//            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
            Vector2 vel1 = new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians));
            Vector2 vel2 = new Vector2(MathUtils.cos(shootRadians * 2), MathUtils.sin(shootRadians * 2));
            Vector2 vel3 = new Vector2(MathUtils.cos(shootRadians * 3), MathUtils.sin(shootRadians * 3));
            Vector2 offsetPosition1 = new Vector2((vel1.x * 20) + this.position.x, (vel1.y * 20) + this.position.y);
            Vector2 offsetPosition2 = new Vector2((vel2.x * 20) + this.position.x, (vel2.y * 20) + this.position.y);
            Vector2 offsetPosition3 = new Vector2((vel3.x * 20) + this.position.x, (vel3.y * 20) + this.position.y);
//            if (coolDown <= 0) {
            if (manager.getCollectionOfBullets().size < 3) {
                //SKILL #2
                //Fan Shot
                gameObject bullet1 = new gameObject();
                bullet1.maxLifeSpan = 1.5f;
                bullet1.sprite.setColor(Color.GREEN);
                bullet1.velocity.set(vel1);
                bullet1.position.set(offsetPosition1);
                bullet1.moveSpeed = 300f;
                bullet1.update();
                manager.getCollectionOfBullets().add(bullet1);

                gameObject bullet2 = new gameObject();
                bullet2.maxLifeSpan = 1.5f;
                bullet2.sprite.setColor(Color.GREEN);
                bullet2.velocity.set(vel2);
                bullet2.position.set(offsetPosition2);
                bullet2.moveSpeed = 300f;
                bullet2.update();
                manager.getCollectionOfBullets().add(bullet2);

                gameObject bullet3 = new gameObject();
                bullet3.maxLifeSpan = 1.5f;
                bullet3.sprite.setColor(Color.GREEN);
                bullet3.velocity.set(vel3);
                bullet3.position.set(offsetPosition3);
                bullet3.moveSpeed = 300f;
                bullet3.update();
                manager.getCollectionOfBullets().add(bullet3);


//                }

            }
//                else {
//                coolDown -= dt;
//            }

        }

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


    }

    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //---------------------testing shooting mechanic----------------------------------

        player.shoot(enemy, deltaTime);

        //-----------------------------draw game objects-----------------------

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


        //TESTING FUNCTIONS
        enemy.performImpulseCollision(player);


        if (enemy.position.y > Gdx.graphics.getWidth()) {
            enemy.position.set(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
        }


        //update all objects
        player.update();
        projectile.update();
        projectile2.update();
        enemy.update();


    }
}





