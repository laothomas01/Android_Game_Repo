package com.hackslash.game.driver;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.hackslash.game.model.GameObject;
import jdk.nashorn.internal.ir.PropertyKey;
import com.badlogic.gdx.Input.Keys;

import java.io.File;
import java.io.IOException;
/**
 * REFERENCE LINKS:
 * <p>
 * getting a perpendicular vector to set the position of the offset perpendicular objects
 * https://gamedev.stackexchange.com/questions/149654/how-to-rotate-a-local-position-offset-based-on-a-direction-vector
 */

/**
 * App Manager:
 * -Handles game states
 * -Handles loading/saving game states
 * <p>
 * -Load and Save player data( dont care about spawned enemies for this game. keep it simple)
 * <p>
 * []@TODO: implement spawn points
 * []@TODO: implement following camera
 * []@TODO: implement auto-target enemy function
 * []@TODO: implement health bars
 * []@TODO: fix skill bugs
 * []@TODO: migrate skill components to a CSV file
 * []@TODO: create settings file
 */
class Graphics2D {
    Sprite sprite;
    Texture texture;
    Color color;
    SpriteBatch spriteBatch;
    private SpriteBatch fontSpriteBatch;
    private BitmapFont font;

    public Graphics2D() {
        font = new BitmapFont();
        fontSpriteBatch = new SpriteBatch();
        texture = new Texture("circle.png");
        sprite = new Sprite(texture);
        color = new Color();
        spriteBatch = new SpriteBatch();
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(String fileName) {
        this.texture = new Texture(fileName);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return this.color;
    }

    public SpriteBatch getSpriteBatch() {
        return this.spriteBatch;
    }

    public void drawSprite() {
        this.getSpriteBatch().begin();
        this.getSprite().draw(this.getSpriteBatch());
        this.getSpriteBatch().end();
    }

    public void drawFontSprite() {
        fontSpriteBatch.begin();

        font.draw(fontSpriteBatch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());

        fontSpriteBatch.end();
    }


}

//maintain all current physics calculation
class Physics2D {

    //adds a slight bounce to a colliding object with impulse
    float COLLISION_COEF;
    Vector2 position;
    Vector2 directionVector;
    Vector2 acceleration;
    Vector2 angularVelocity;
    Vector2 normalVector;
    Vector2 tempNormalVector;
    Vector2 tempNewDirectionVector;
    float radians;
    float moveSpeed;
    float angularSpeed;
    float width;
    float height;
    float mass;
    float distanceFrom;
    float impactDistance;

    boolean isCollided;


    public Physics2D() {


        COLLISION_COEF = 1.0f;
        tempNormalVector = new Vector2();
        position = new Vector2();
        directionVector = new Vector2();
        acceleration = new Vector2();
        angularVelocity = new Vector2();
        normalVector = new Vector2();
        tempNewDirectionVector = new Vector2();
        width = 0;
        height = 0;
        mass = 1.0f;
        radians = 0f;
        moveSpeed = 0f;
        angularSpeed = 0f;
        distanceFrom = 0f;
        impactDistance = 0f;
        isCollided = false;
    }

    public Vector2 getTempNewDirectionVector() {
        return tempNewDirectionVector;
    }

    public Vector2 getTempNormalVector() {
        return tempNormalVector;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float spd) {
        this.moveSpeed = spd;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    public void setDirectionVector(Vector2 headingVector) {
        this.directionVector = headingVector;
    }

    public void setDirectionVector(float x, float y) {
        setDirectionVector(new Vector2(x, y));
    }


    public void setPosition(float x, float y) {
        setPosition(new Vector2(x, y));
    }


    public void setWidth(float wdth) {
        this.width = wdth;
    }

    public void setHeight(float hght) {
        this.height = hght;
    }

    public void setSize(float wdth, float hght) {
        this.width = wdth;
        this.height = hght;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }


    public float getDistanceBetween() {
        return distanceFrom;
    }

    public void setDistanceBetween(float distance) {
        this.distanceFrom = distance;
    }

    public float getImpactDistance() {
        return this.impactDistance;
    }

    public void setImpactDistance(float impact) {
        this.impactDistance = impact;
    }

    public void setNormalVector(Vector2 normal) {
        this.normalVector = normal;
    }

    public Vector2 getNormalVector() {
        return this.normalVector;
    }

    public float getMass() {
        return this.mass;
    }

//    public boolean hasCollided(InGameObject obj) {
//        setNormalVector(this.getPosition().sub(obj.getPhysics().getPosition()));
//        this.setDistanceBetween(this.getNormalVector().len());
//        this.setImpactDistance(this.getWidth() + obj.getPhysics().getWidth());
//        this.getNormalVector().nor();
//        if (this.getDistanceBetween() < this.getImpactDistance()) {
//            return true;
//        }
//        return false;
//    }

    public void move(float dt) {
        this.getPosition().add(this.getDirectionVector().x * this.getMoveSpeed() * dt, this.getDirectionVector().y * this.getMoveSpeed() * dt);
    }


    //change object direction based on target position
    public void moveTowards(InGameObject target, float dt) {
        float radians = getAngleBetweenTwoVectors(target);
        this.setDirectionVector(MathUtils.cos(radians), MathUtils.sin(radians));
    }

    //get angle between target position and calling game object's position
    public float getAngleBetweenTwoVectors(InGameObject target) {
        //destination                       source

        return MathUtils.atan2(target.getPhysics().getPosition().y - this.getPosition().y, target.getPhysics().getPosition().x - this.getPosition().x);
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float rad) {
        this.radians = rad;
    }

    // calculate number of orbits per second(note: this calculation for rotation speed is not accurate)
    public void increaseRotationAngle() {
        if (this.getRadians() < (MathUtils.PI * 2)) {
            this.setRadians(this.getRadians() + .05f);
        } else {
            this.setRadians(0f);
        }
    }


    //perform basic circular motion around the parameterized object
//    public void rotateAround(hack_and_slash.InGameObject parent) {
//        this.increaseRotationAngle();
//        this.angularVelocity.set(MathUtils.cos(this.getRadians()), MathUtils.sin(this.getRadians()));
//        this.d(this.orbitDistance * this.angularVelocity.x, this.orbitDistance * this.angularVelocity.y);
//        this.position.set(parent.position.x + this.velocity.x, parent.position.y + this.velocity.y);
//    }

    /**
     * COLLISION PHYSICS!
     *
     * @return
     */
    public boolean checkIfCollided() {
        return isCollided;
    }

    public void setIsCollided(boolean collided) {
        this.isCollided = collided;
    }


    public boolean hasCollided(InGameObject target) {
        this.getNormalVector().set(this.getPosition()).sub(target.getPhysics().getPosition());
        this.setDistanceBetween(getNormalVector().len());
        this.setImpactDistance((this.getWidth() + target.getPhysics().getWidth()) / 1.8f);
        //if you have less than or no distance between an object's collision distance, you crashed
        if (this.getDistanceBetween() < this.getImpactDistance()) {
            this.setIsCollided(true);
            return true;
        }
        return false;

    }

    public void performImpulseCollision(InGameObject object) {

        if (hasCollided(object)) {


            //temporary normal vector to hold values used for changing normal vector
            getTempNormalVector().set(getNormalVector().scl(getImpactDistance() - getDistanceBetween() / 2));

            getPosition().add(getTempNormalVector());

            getTempNormalVector().set(getNormalVector().scl(getImpactDistance() - getDistanceBetween() / 2));


            object.getPhysics().getPosition().sub(getTempNormalVector());

            //setting direction of colliding objects based on impulse force
            getTempNewDirectionVector().set(getDirectionVector().sub(object.getPhysics().getDirectionVector()));

            //Newton's Law of Impact
            float impulse = (-(1 + COLLISION_COEF) * (getNormalVector().dot(getTempNewDirectionVector()))) / (getNormalVector().dot(getNormalVector()) * (1 / getMass() + 1 / object.getPhysics().getMass()));

            //Change velocity of colliding objects using impulse
            getTempNormalVector().set(getNormalVector()).scl(impulse / getMass());
            getDirectionVector().add(tempNormalVector);
            getTempNormalVector().set(getNormalVector()).scl(impulse / getMass());

            object.getPhysics().getDirectionVector().sub(getTempNormalVector());

        }
    }

    //---------------------------------------------NOTES------------------------------------------

    //think about in between events called Collided. check if i already hit something before and if i have not hit something, that means I CAN hit something.

    //this is a one hit situation

    //this is a one time event.

    //when the projectile hits something and a knockback is applied and i can only collide once and knock something back.

    //think about what happens during the previous and the next frame

    //think about how you are applying collision

    //one hit instance. hit one object dont hit another object.

    //understand game states.

    //----------------------------------------------------------------------------------------------


    //---------------------------------------------NOTES---------------------------------------------

    //graphics

    //-----------------------------------------------------------------------------------------------


}


/**
 * Handles CRUD operations on game objects
 */

class GameObjectManager {

    //a chosen number range from 1->4(inclusively) will determine the spawn position of an enemy object
    Array<Enemy> enemies;
    Array<Projectile> projectiles;

    //handle removal of all game objects
    Array<InGameObject> garbageCollection;

    public GameObjectManager() {
        projectiles = new Array<>();
        enemies = new Array<>();
        garbageCollection = new Array<>();
    }

    public void setEnemyCollectionSize(int size) {
        this.enemies.setSize(size);
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemies(Enemy e) {
        this.enemies.add(e);
    }

    public Array<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectiles(Projectile b) {
        projectiles.add(b);
    }

    public Array<InGameObject> getGarbageCollection() {
        return garbageCollection;
    }

    public String getProjectilesToString() {
        return projectiles.toString();
    }

    public void addInGameObjectsToRemove(InGameObject b) {
        garbageCollection.add(b);
    }

//    public int getSpawnRandomizer() {
//        return this.enemySpawnRandomizer;
//    }


    public void spawnEnemies(float dt, InGameObject target) {
        int randomSpawner = MathUtils.random(1, 4);
        /**
         * @TODO need to regulate how fast spawning will be with a timer
         */

        //fix these spawn positions
        if (randomSpawner == 4) {
            addEnemies(new Enemy(AppManager.getScreenWidth() / 2, -AppManager.getScreenHeight() / 2, 10f, 10f, 100f));
        } else if (randomSpawner == 3) {
            addEnemies(new Enemy(-AppManager.getScreenWidth() / 2, -AppManager.getScreenHeight() / 2, 10f, 10f, 100f));
        } else if (randomSpawner == 2) {
            addEnemies(new Enemy(-AppManager.getScreenWidth() / 2, AppManager.getScreenHeight() / 2, 10f, 10f, 100f));
        } else {
            addEnemies(new Enemy(AppManager.getScreenWidth() / 2, AppManager.getScreenHeight() / 2, 10f, 10f, 100f));
        }

//        System.out.println(this.getEnemies().toString());

        for (Enemy e : getEnemies()) {

            //
            if (
                //if position > (800,600)
                    (e.getPhysics().getPosition().x > AppManager.getScreenWidth() && e.getPhysics().getPosition().y > AppManager.getScreenHeight())
                            ||
                            //if position > (0,800)
                            //if position.x < 0
                            e.getPhysics().getPosition().y > AppManager.getScreenHeight() || e.getPhysics().getPosition().y < 0
                            ||
                            //if position > (0,600)
                            //if position.x < 0
                            e.getPhysics().getPosition().x > AppManager.getScreenWidth() || e.getPhysics().getPosition().x < 0
                            ||
                            //if positon < (0,0)
                            e.getPhysics().getPosition().x < 0 && e.getPhysics().getPosition().y < 0
            ) {
                e.update(dt);
                e.getPhysics().moveTowards(target, dt);
            } else {
                e.update(dt);
                e.getGraphics().drawSprite();
                e.getPhysics().moveTowards(target, dt);
            }
//            if (e != null) {
            /**
             * if enemy not in camera view or game screen, dont render
             */
        }
//        }
//        }

    }

    public void spawnBullets(float dt) {
        for (Projectile p : projectiles) {

            p.update(dt);

        }
        //if projectile not in camera view, do not draw it and destroy the object

        //else, render the object
    }

}


class InGameObject {
    //physics2D manager
    Physics2D physics;

    //graphics2D manager
    Graphics2D graphics;


    public InGameObject() {
        //give your game objects a physics2D component
        physics = new Physics2D();
        graphics = new Graphics2D();
    }

    public Graphics2D getGraphics() {
        return this.graphics;
    }

    public Physics2D getPhysics() {
        return this.physics;
    }


    public void update(float dt) {
        graphics.getSprite().setSize(this.getPhysics().getWidth(), this.getPhysics().getHeight());
        graphics.getSprite().setPosition(this.getPhysics().getPosition().x, this.getPhysics().getPosition().y);
        graphics.getSprite().setTexture(graphics.getTexture());
        graphics.getSprite().setColor(graphics.getColor());
        this.getPhysics().move(dt);
    }


    public void shoot(InGameObject target, float dt) {

    }

}


class Projectile extends InGameObject {

    Projectile() {
        this.getPhysics().setSize(10f, 10f);
        this.getPhysics().setDirectionVector(1, 1);
        this.getPhysics().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.getPhysics().setMoveSpeed(250f);
        graphics.setColor(Color.GREEN);
    }

    Projectile(Vector2 newPosition, Vector2 newDirection, float newSpeed, float newWidth, float newHeight) {
        this.getPhysics().setPosition(newPosition);
        this.getPhysics().setDirectionVector(newDirection);
        this.getPhysics().setMoveSpeed(newSpeed);
        this.getPhysics().setSize(newWidth, newHeight);
        graphics.setColor(Color.GREEN);
    }


    public String toString() {
        return "    POSITION:   " + this.getPhysics().getPosition().toString() + "   HEADING VECTOR    " + this.getPhysics().getDirectionVector();
    }


}


class Enemy extends InGameObject {
    public Enemy() {
        this.getPhysics().setPosition(1, 0);
        this.getPhysics().setDirectionVector(1, 1);
        this.getPhysics().setSize(10f, 10f);
        graphics.setColor(Color.RED);
        this.getPhysics().setMoveSpeed(40f);
    }

    //AppManager.getScreenWidth()/2,AppManager.getScreenHeight()/2
    public Enemy(Vector2 position, float width, float height, float speed) {
        this.getPhysics().setPosition(position);
        this.getPhysics().setSize(width, height);
        this.getPhysics().setMoveSpeed(speed);
        graphics.setColor(Color.RED);
    }

    public Enemy(float x, float y, float width, float height, float speed) {
        this.getPhysics().setPosition(x, y);
        this.getPhysics().setSize(width, height);
        this.getPhysics().setMoveSpeed(speed);
        graphics.setColor(Color.RED);
    }


    public String toString() {
        return "ENEMY:\n" + "POSITION:" + this.getPhysics().getPosition() + "\nDIRECTION VECTOR: " + this.getPhysics().getDirectionVector() + "\nMOVE SPEED: " + this.getPhysics().getMoveSpeed();
    }

}


/*
 *
 * A skill is an upgradable action that cna be performed by the player
 *
 * // we will make this less universal for more specific types of skills later on
 *
 * // consider if we want to swap out skills for a different type of skill
 *
 * //consider that we need to persist the levels of the currently leveled up skill
 *
 * //we will have a gui or some kind of console interaction to check on how skills are created.
 *
 * //basically, based on the skill selected, its data will be parsed and put into a skill class.
 *
 * //we will use that data generated from a skill object to determine the number of objects the player can shoot.
 *
 *
 */
class Skill {

    float projectileCount;
    float coolDownTimer;
    float MaxCoolDownTimer;
    boolean isOnCoolDown;
    String skillName;
    int level;

    float deltaRadian;
    float deltaPosition;


    public Skill() {
        deltaPosition = 0;
        deltaRadian = 0;
        projectileCount = 0f;
        MaxCoolDownTimer = 0f;
        coolDownTimer = MaxCoolDownTimer;
        isOnCoolDown = false;
        skillName = "";
        level = 1;
    }

    public Skill(String name, float MaxCoolDownTimer, boolean isOnCoolDown, float prjctileCount, int lvl, float angle, float pos) {
        this.setSkillName(name);
        this.setMaxCoolDownTimer(MaxCoolDownTimer);
        this.setCoolDown(MaxCoolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
        this.setLevel(lvl);
        this.setProjectileCount(prjctileCount);
        this.setDeltaAngle(angle);
        this.setDeltaPosition(pos);
    }

    public String toString() {
        return "        SKILL NAME:     " + this.getSkillName() + "     COOLDOWN:       " + this.getCoolDown() + "      IS_ON_COOLDOWN      " + this.getIsOnCoolDown() + "      LEVEL       " + this.getLevel();
    }

    public void update(String name, float MaxCoolDownTimer, boolean isOnCoolDown, float projectileCount, int level) {
        this.setSkillName(name);
        this.setMaxCoolDownTimer(MaxCoolDownTimer);
        this.setCoolDown(MaxCoolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
        this.setLevel(level);
        this.setProjectileCount(projectileCount);
    }

    public void update(float coolDownTimer, boolean isOnCoolDown) {
        this.setCoolDown(coolDownTimer);
        this.setIsOnCoolDown(isOnCoolDown);
    }

    public float getDeltaPosition() {
        return deltaPosition;
    }

    public float getDeltaAngle() {
        return deltaRadian;
    }

    public void setDeltaAngle(float rad) {
        this.deltaRadian = rad;
    }

    public void setDeltaPosition(float pos) {
        this.deltaPosition = pos;
    }


    public boolean hasFinishedCoolDown() {
        if (this.getIsOnCoolDown() && this.getCoolDown() <= 0) {
            return true;
        }
        return false;
    }


    public float getProjectileCount() {
        return this.projectileCount;
    }

    public void setProjectileCount(float count) {
        this.projectileCount = count;
    }

    public String getSkillName() {
        return this.skillName;
    }

    public void setSkillName(String name) {
        this.skillName = name;
    }

    public void setMaxCoolDownTimer(float max) {
        this.MaxCoolDownTimer = max;
    }

    public float getMaxCoolDownTimer() {
        return this.MaxCoolDownTimer;
    }

    public float getCoolDown() {
        return coolDownTimer;
    }

    public void setCoolDown(float coolDownTimer) {
        this.coolDownTimer = coolDownTimer;
    }

    public boolean getIsOnCoolDown() {
        return isOnCoolDown;
    }

    public void setIsOnCoolDown(boolean onCoolDown) {
        isOnCoolDown = onCoolDown;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


}


/**
 * This will be for desktop platform
 */
//Handles when gameplay begins, pauses and ends
class GameInputProcessor extends InputAdapter {
    public boolean keyDown(int k) {
        if (k == Keys.UP) {
            GameKeys.setKey(GameKeys.UP, true);
        }
        if (k == Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, true);
        }
        if (k == Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, true);
        }
        if (k == Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, true);
        }
        if (k == Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, true);
        }
        if (k == Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESC, true);
        }

        if (k == Keys.W) {
            GameKeys.setKey(GameKeys.W, true);
        }
        if (k == Keys.A) {
            GameKeys.setKey(GameKeys.A, true);
        }
        if (k == Keys.S) {
            GameKeys.setKey(GameKeys.S, true);
        }
        if (k == Keys.D) {
            GameKeys.setKey(GameKeys.D, true);
        }

        return true;
    }

    public boolean keyUp(int k) {
        if (k == Keys.UP) {
            GameKeys.setKey(GameKeys.UP, false);
        }
        if (k == Keys.LEFT) {
            GameKeys.setKey(GameKeys.LEFT, false);
        }
        if (k == Keys.DOWN) {
            GameKeys.setKey(GameKeys.DOWN, false);
        }
        if (k == Keys.RIGHT) {
            GameKeys.setKey(GameKeys.RIGHT, false);
        }
        if (k == Keys.ENTER) {
            GameKeys.setKey(GameKeys.ENTER, false);
        }
        if (k == Keys.ESCAPE) {
            GameKeys.setKey(GameKeys.ESC, false);
        }
        if (k == Keys.W) {
            GameKeys.setKey(GameKeys.W, false);
        }
        if (k == Keys.A) {
            GameKeys.setKey(GameKeys.A, false);
        }
        if (k == Keys.S) {
            GameKeys.setKey(GameKeys.S, false);
        }
        if (k == Keys.D) {
            GameKeys.setKey(GameKeys.D, false);
        }

        return true;
    }
}

/**
 * This will be for desktop platform
 */
class GameKeys {
    //if false, key is released. if true, key is pressed
    private static boolean[] keys;
    //previous state of keys
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 10;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int ESC = 4;
    public static final int ENTER = 5;
    public static final int W = 6;
    public static final int A = 7;
    public static final int S = 8;
    public static final int D = 9;


    static {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public static boolean isDown(int k) {
        return keys[k];
    }

    public static boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}

/**
 * CRUD operations on the state of the game
 * <p>
 * -initializations, closing the app, running the app
 */
public class GameStateManager extends ApplicationAdapter {


    float deltaTime;
    Player player;
    Graphics2D settings;
    GameObjectManager objectSpawner;

    //    Enemy enemy;
    //    enum InputKeys { FORWARD,BACKWARD,LEFT,RIGHT,FORWARD_RIGHT,FORWARD_LEFT,BACKWARD_RIGHT,BACKWARD_LEFT}
    public void create() {

        //This will get your preferences from storage

////If the preference key is empty, create it by putting a value into it
//        if (!prefs.contains("key")) prefs.putInteger("key", 1337);
//
////Get value from a preference key "key" (must not be empty)
//        int val = prefs.getInteger("key");
////Do something with your value and put it back to the preference
//        val += 1;
//        prefs.putInteger("key", val);
//
////This will finally save the changes to storage
//        prefs.flush();

        //-------------------------------------------------
        //---------------------------------------------------
        settings = new Graphics2D();
        player = new Player();
//        enemy = new Enemy();
//        enemy.getPhysics().setDirectionVector(0, 1);

        //Input Manager
        Gdx.input.setInputProcessor(new GameInputProcessor());
        objectSpawner = new GameObjectManager();

//        String fileName = "helloworld.txt";
////        boolean testFileExists = Gdx.files.local(fileName).exists();
////
////        if (testFileExists) {
////            //load data into game objects
////            FileHandle fh = new FileHandle(fileName);
////            String[] enemy_coords = fh.readString().split("[(,)]");
////
////            enemy.getPhysics().setPosition(Float.parseFloat(enemy_coords[1]), Float.parseFloat(enemy_coords[2]));
////
////            if (enemy.getPhysics().getPosition().x == Float.parseFloat(enemy_coords[1]) && enemy.getPhysics().getPosition().y == Float.parseFloat(enemy_coords[2])) {
////                System.out.println("SUCCESS!");
////            } else {
////                System.out.println("FAILED ASSERTION!");
////            }
////        } else {
////            System.out.println("-------------------FILE DOES NOT EXIST!----------------");
////        }


    }

    public void handleKeyBoardInput() {

        if ((GameKeys.isDown(GameKeys.UP) || (GameKeys.isDown(GameKeys.W)))) {
            if ((GameKeys.isDown(GameKeys.UP) && GameKeys.isDown(GameKeys.RIGHT)) || (GameKeys.isDown(GameKeys.W) && GameKeys.isDown(GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, 1);

            } else if ((GameKeys.isDown(GameKeys.UP) && GameKeys.isDown(GameKeys.LEFT)) || (GameKeys.isDown(GameKeys.W) && GameKeys.isDown(GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, 1);
            } else {
                player.getPhysics().setDirectionVector(0, 1);
            }
        } else if ((GameKeys.isDown(GameKeys.DOWN) || (GameKeys.isDown(GameKeys.S)))) {
            if ((GameKeys.isDown(GameKeys.DOWN) && GameKeys.isDown(GameKeys.RIGHT)) || (GameKeys.isDown(GameKeys.S) && GameKeys.isDown(GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, -1);
            } else if ((GameKeys.isDown(GameKeys.DOWN) && GameKeys.isDown(GameKeys.LEFT)) || (GameKeys.isDown(GameKeys.S) && GameKeys.isDown(GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, -1);
            } else {
                player.getPhysics().setDirectionVector(0, -1);
            }
        } else if (GameKeys.isDown(GameKeys.LEFT) || GameKeys.isDown(GameKeys.A)) {
            player.getPhysics().setDirectionVector(-1, 0);
        } else if (GameKeys.isDown(GameKeys.RIGHT) || GameKeys.isDown(GameKeys.D)) {
            player.getPhysics().setDirectionVector(1, 0);
        } else {
            player.getPhysics().setDirectionVector(0, 0);
        }

        GameKeys.update();
    }

    public void render() {

        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //test game keys
        handleKeyBoardInput();
        //TESTING SKILL MANAGER
//        player.shoot(enemy, deltaTime);

        settings.drawFontSprite();
        player.getGraphics().drawSprite();
//        enemy.getGraphics().drawSprite();
        player.update(deltaTime);
        objectSpawner.spawnEnemies(deltaTime, player);
//        if (!objectSpawner.getEnemies().isEmpty()) {
//            for (Enemy e : objectSpawner.getEnemies()) {
//                e.update(deltaTime);
//            }
//        }

//        enemy.update(deltaTime);

        // integrate by propagating time
//        for (Projectile p : player.getGameObjectManager().getProjectiles()) {
//            p.update(deltaTime);
//            //if projectile in camera view: draw
//            //if not in camera view, do not draw
//            p.getGraphics().drawSprite();
//        }


        //----------------------------------------------------------------------------------------
        /*
         *
         * WRITING AUTOMATED TESTS!
         *
         * [X] GOOD?
         * COLLISION CHECKING
         *
         * BOUNDARY CHECKING
         * */


//        for (Projectile p : player.getGameObjectManager().getProjectiles()) {
//            if (p.getPhysics().hasCollided(enemy) || p.getPhysics().getPosition().x < 0 || p.getPhysics().getPosition().y > Gdx.graphics.getHeight()) {
//                player.getGameObjectManager().addInGameObjectsToRemove(p);
//            }
//
//        }
//
//        if (enemy.getPhysics().getPosition().y > Gdx.graphics.getHeight()) {
//            enemy.getPhysics().setPosition(0, 0);
//        }

//        player.getGameObjectManager().addInGameObjectsToRemove();

    }

    @Override
    public void resize(int width, int height) {

    }


    public void pause() {

        //pause when player levels up and has to choose an upgrade

        //pause when player swaps the application window but has not closed the project

        //called first when dispose() is called
    }


    //called when the Application is resumed from a paused state.
    //transition from pause() -> resume()
    @Override
    public void resume() {


    }

    public void dispose() {

        /**
         * WIll BE WRITING DATA TO FILE HERE
         */

//        prefs.putString("PlayerPosition:", player.getPhysics().getPosition().toString());
//        prefs.flush();
//        for (Projectile p : player.getGameObjectManager().getProjectiles()) {

        //update preferences
//        prefs.flush();

        //note: pause() will be called when dispose exits. take advantage of this.
        //file created during exit time of game
//        File test = new File("helloworld.txt");
//        try {
//            if (test.createNewFile()) {
//                System.out.println("File Created: " + test.getName());
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occured!");
//            throw new RuntimeException(e);
//        }
//        FileHandle testhandle = new FileHandle(test);
//        System.out.println(enemy.getPhysics().getPosition().toString());
//        testhandle.writeString(enemy.getPhysics().getPosition().toString(), false);


    }


}





