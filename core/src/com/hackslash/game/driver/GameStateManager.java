package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

/*
 *
 * CLASSES:
 *
 * -game object
 * -entity state manager
 * -skills( let's handle skill creation like Dota2's skill customization)
 * -skills manager
 * -game state manager
 * -physics2D(put this in later)
 *
 *
 * */


/*
 *
 * game states:
 *
 * //initialize phase
 * create
 *
 * //updates
 * render
 *
 *
 *
 * pause
 *
 * resume
 *
 * dispose
 *
 *
 *
 *
 * */


class Physics2D {

    float COLLISION_COEF;
    Vector2 normal;
    Vector2 temp;
    Vector2 newVelocity;
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    Vector2 angularVelocity;
    float radians;
    float speed;
    float rotateSpeed;
    float width;
    float height;
    float mass;
    float distance;
    float impactDistance;


    public Physics2D() {
        COLLISION_COEF = 1.0f;
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        angularVelocity = new Vector2(0, 0);
        width = 0;
        height = 0;
        mass = 1.0f;
        radians = 0f;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 pos) {
        this.position = pos;
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


}

class gameObject {
    Physics2D transform;
    Sprite sprite;
    Texture texture;

    Color color;

    public gameObject() {
        transform = new Physics2D();
        texture = new Texture("circle.png");
        sprite = new Sprite(texture);
        color = new Color();
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Physics2D getTransform() {
        return this.transform;
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

    public void update() {
        this.getSprite().setSize(this.getTransform().getWidth(), this.getTransform().getHeight());
        this.getSprite().setPosition(this.getTransform().getPosition().x, this.getTransform().getPosition().y);
        this.getSprite().setTexture(this.getTexture());
        this.getSprite().setColor(this.getColor());
    }


}

class Player extends gameObject {
    SkillManager skillManager;

    Player() {
        skillManager = new SkillManager();
        this.getTransform().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        this.setTexture("circle.png");
        this.getTransform().setSize(10f, 10f);
        this.setColor(Color.BLUE);
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public String toString() {
        return "    POSITION:   " + this.getTransform().getPosition() + "  TEXTURE: " + this.getTexture().toString() + "   SIZE:    " + this.getTransform().getWidth() + "," + this.getTransform().getHeight();
    }
}


/*
 *
 * A skill is an upgradable action that cna be performed by the player
 *
 */
class Skill {
    String name;
    float coolDown;
    String behavior;
    int level;

    public Skill() {

    }
}


/*
 * ACCESSES AND MODIFIED SKILLS
 *
 * */
class SkillManager {
    ArrayMap<Skill, String> skills;

    public SkillManager() {
        skills = new ArrayMap<>();
        skills.ordered = true;
    }


}


public class GameStateManager extends ApplicationAdapter {

    SpriteBatch gameObjectBatch;


//    //used to add a slight bounce to colliding objects
//    float COLLISION_COEF = 1.0f;
//    float deltaTime;
//    //-----------------------IMPULSE COLLISION VECTORS---------------
//    Vector2 normal;
//    Vector2 temp;
//    Vector2 newVelocity;
    //---------------------------------------------------------------


//    entityStateManager manager = new entityStateManager();


    //what is an entity state manager?

    /*
     *
     * entity state manager should handle what happens to an entity:
     *  -destroyed
     *  -stored
     *
     *
     * */


//    class entityStateManager {
//        Array<gameObject> collectionOfBullets;
//        Array<gameObject> collectionOfRemovedBullets;
//
//        public entityStateManager() {
//            collectionOfBullets = new Array<>();
//            collectionOfRemovedBullets = new Array<>();
//        }
//
//        public Array<gameObject> getCollectionOfBullets() {
//            return collectionOfBullets;
//        }
//
//        public Array<gameObject> getCollectionOfRemovedBullets() {
//            return collectionOfRemovedBullets;
//        }
//
//        public boolean removeBullets() {
//            return collectionOfBullets.removeAll(collectionOfRemovedBullets, false);
//        }
//
//        public void setCollectionSize(int size) {
//            collectionOfBullets.setSize(size);
//        }

//        public void manageBullets() {
//            if (!getCollectionOfBullets().isEmpty()) {
//
//            }
//        }

//        public void manageBullets() {
//            if (!manager.getCollectionOfBullets().isEmpty()) {
//                for (gameObject b : manager.getCollectionOfBullets()) {
//                    if (b.hasCollided(enemy)) {
//                        manager.getCollectionOfRemovedBullets().add(b);
//                    }
//                    System.out.println("LIFE SPAN:" + b.getLifeSpan());
//                    System.out.println("MAX LIFE SPAN:" + b.getMaxLifeSpan());
//
//                    if (b.lifeSpan <= 0) {
//                        b.setLifeSpan(b.getMaxLifeSpan());
//                        manager.getCollectionOfRemovedBullets().add(b);
//                    } else {
//                        b.lifeSpan -= deltaTime;
//                    }
//
//                    b.move(deltaTime);
//                    b.update();
//                }
//            }
//            manager.removeBullets();
//        }


//}


    /*
     *
     * Any in-game entity that can be destroyed,created or interacted with
     *   -exp drops
     *   -the player
     *   -enemies
     *   -bullets
     *
     * */
//    class gameObject {
//
//
//        //-------------------------------PHYSICS ATTRIBUTES----------------------
//        Vector2 position;
//        Vector2 velocity;
//        Vector2 acceleration;
//        Vector2 angularVelocity;
//
//        float rotateRadians;
//        float shootRadians;
//        float orbitDistance;
//        float health;
//        float damage;
//
//        float moveSpeed;
//
//
//        float width;
//        float height;
//        float rotationSpeed;
//
//        float mass;
//        //------------------------------------------------------------------------------
//
//
//        //----------------------------MISC ATTRIBUTES FOR GAME OBJECTS-------------------
//        float maxCoolDown;
//        float coolDown;
//
//        float skillMaxCoolDown;
//        float skillCoolDown;
//        float lifeSpan;
//        float maxLifeSpan;
//        float distance;
//        float impactDistance;
//        //----------------------------------------------------------
//
//        //-----------------------GAME GRAPHICS----------------------
//        Sprite sprite;
//        Texture texture;
//        SpriteBatch batch;
//
//        //------------------------------------------------------------
//
//        //---------------------GAME OBJECT CONSTRUCTOR----------------
//        public gameObject() {
//
//            position = new Vector2(0, 0);
//            velocity = new Vector2(0, 0);
//            acceleration = new Vector2(0, 0);
//            angularVelocity = new Vector2(0, 0);
//            rotateRadians = 0;
//            shootRadians = 0.261799f;
//            orbitDistance = 0;
//            health = 0;
//            damage = 0;
//            width = 0;
//            height = 0;
//            rotationSpeed = 0;
//            texture = new Texture("circle.png");
//            sprite = new Sprite(texture);
//            sprite.setPosition(position.x, position.y);
//            batch = new SpriteBatch();
//            mass = 1.0f;
//            moveSpeed = 0;
//            maxCoolDown = 0.25f;
//            coolDown = maxCoolDown;
//            maxLifeSpan = 0.0f;
//            lifeSpan = 0.0f;
//            skillMaxCoolDown = 2.0f;
//            skillCoolDown = skillMaxCoolDown;
//        }
//        //--------------------------------------------------------------

    //Update our game objects with new data every 60 frames


    //update what???
//        public void update() {
////            this.position.set(this.possition.x, this.position.y);
//            this.sprite.setPosition(this.position.x, this.position.y);
//            this.sprite.setTexture(this.texture);
//        }
//
//        public void setMaxLifeSpan(float max) {
//            this.maxLifeSpan = max;
//        }
//
//        public float getMaxLifeSpan() {
//            return this.maxLifeSpan;
//        }
//
//        public void setLifeSpan(float span) {
//            this.lifeSpan = span;
//        }
//
//        public float getLifeSpan() {
//            return this.lifeSpan;
//        }
//
//        //------------------PHYSICS FUNCTIONS-------------------------
//
//        public boolean hasCollided(gameObject object) {
//            normal.set(this.position).sub(object.position);
//            distance = normal.len();
//            impactDistance = (this.sprite.getWidth() + object.sprite.getWidth()) / 1.7f;
//            normal.nor();
//            //when we collided, we should check the type of object we are colliding with
//            if (distance < impactDistance) {
//                return true;
//            }
//            return false;
//        }
//
//        public void performImpulseCollision(gameObject object) {
//
//            if (hasCollided(object)) {
//                //let's give an object impulse during collision
//                //we use temp because the method would change the contents of normal!
//                temp.set(normal).scl((impactDistance - distance) / 2);
//                this.position.add(temp);
//                temp.set(normal).scl((impactDistance - distance) / 2);
//                object.position.sub(temp);
//
//                //Let's implement newton's law of impact
//                //convert the two velocities into a single reference frame
//                newVelocity.set(this.velocity.sub(object.velocity));
//
//                // Compute the impulse (see Essential Math for Game Programmers)
//                float impulse = (-(1 + COLLISION_COEF) * normal.dot(newVelocity)) / (normal.dot(normal) * (1 / this.mass + 1 / object.mass));
//                //Change velocity of two object using this impulse
//                temp.set(normal).scl(impulse / this.mass);
//                this.velocity.add(temp);
//                temp.set(normal).scl(impulse / object.mass);
//                object.velocity.sub(temp);
//            }
//
//        }
//
//        // calculate number of orbits per second(note: this calculation for rotation speed is not accurate)
//        public void increaseRotationAngle() {
//            if (this.rotateRadians < (MathUtils.PI * 2)) {
//                this.rotateRadians += rotationSpeed;
//            } else {
//                this.rotateRadians = 0;
//            }
//        }
//
//
//        //perform basic circular motion around the parameterized object
//        public void rotateAround(gameObject parent) {
//            this.increaseRotationAngle();
//            this.angularVelocity.set(MathUtils.cos(this.rotateRadians), MathUtils.sin(this.rotateRadians));
//            this.velocity.set(this.orbitDistance * this.angularVelocity.x, this.orbitDistance * this.angularVelocity.y);
//            this.position.set(parent.position.x + this.velocity.x, parent.position.y + this.velocity.y);
//        }
//
//        //follow a target
//        public void moveTowards(gameObject parent, float dt) {
//            this.rotateRadians = MathUtils.atan2(parent.position.y - this.position.y, parent.position.x - this.position.x);
//            this.velocity.set(MathUtils.cos(this.rotateRadians), MathUtils.sin(this.rotateRadians));
//            this.position.set(this.position.add(this.velocity.scl(this.moveSpeed * dt)));
//        }
//
//        public void move(float dt) {
//            this.position.add(this.velocity.x * this.moveSpeed * dt, this.velocity.y * this.moveSpeed * dt);
//        }
//
//
//        //----------------------------------------------------------------------
//
//
//        //--------------------------NON-PHYSICS-RELATED-FUNCTIONS--------------------------------
//
//
//        //this should handle all ranged attacks??????
//        public void shoot(gameObject object, float dt) {
//
//            SingleShot(object, dt);
//
////            constantVelocityShoot(object, dt);
//            //SKILL #2
////            TriShot(object, dt);
//
//
//            //SKILL #3
//
////            SurroundShot(object, dt);
//
//
//            //SKILL #4
//
//            //WORK IN PROGRESS
////            PitchForkShot(object, dt);
//
//
//            //handling cases for bullets
//            manager.manageBullets();
//
//        }

    // BASE SHOOT ATTACk
    /*
     *
     * How can we generalize the equations or components found in this function?
     * */
//        public void SingleShot(gameObject target, float dt) {
//
//            /*
//             *
//             * Generalized equations for shooting:
//             *
//             *  velocity <- radians
//             *
//             * offset <- velocity , offset distance , shooter's position
//             *
//             * equation: bullet final position = shooter's current position + (velocity * 50) * speed * deltaTime
//             *
//             * */
//
//            //find angle between current object and target
//            float radians = MathUtils.atan2(target.position.y - this.position.y, target.position.x - this.position.x);
//
//            //direction of the projectile
//            Vector2 vel = new Vector2(MathUtils.cos(radians), MathUtils.sin(radians));
//
//
//            //offset the projectile position from the calling object's position
//            Vector2 offsetPosition = new Vector2(
//
//
//                    //  X direction
//                    (vel.x *
//                            //offset distance
//                            50) +
//                            //current Xposition of calling object
//                            this.position.x,
//                    // Y direction
//                    (vel.y *
//                            //offset distance
//                            50) +
//                            //current Yposition of calling object
//                            this.position.y);
//
////            {
//
//
////                if (
////                    //limit to collection size.
////                    // why? we are rendering at 60 frames per bullet and using an arraylist for a datastructure
////                    //
////                        manager.getCollectionOfBullets().size < 1
////
////
////                )
//
//
//            {
//
//                gameObject bullet = new gameObject();
//                bullet.sprite.setColor(Color.GREEN);
//                bullet.velocity.set(vel);
//                bullet.position.set(offsetPosition);
//                bullet.moveSpeed = 500f;
//                bullet.setMaxLifeSpan(0.5f);
//                bullet.setLifeSpan(bullet.getMaxLifeSpan());
//                bullet.update();
//                manager.getCollectionOfBullets().add(bullet);
//            }
//
////            }
//        }

//        public void PitchForkShot(gameObject object, float dt) {
////            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
//            Vector2 vel1 = new Vector2(MathUtils.cos(1.5708f), MathUtils.sin(1.5708f));
//
//            Vector2 vel2 = new Vector2(MathUtils.cos(1.5708f), MathUtils.sin(1.5708f));
//
//            Vector2 vel3 = new Vector2(MathUtils.cos(1.5708f), MathUtils.sin(1.5708f));
////            Vector2 vel3 = new Vector2(MathUtils.cos(radians - 0.785398f), MathUtils.sin(radians - 0.785398f));
////
//            Vector2 offsetPosition1 = new Vector2((vel1.x * 50) + this.position.x, (vel1.y * 50) + this.position.y);
//            Vector2 offsetPosition2 = new Vector2((vel2.x * 50) + this.position.x - 40, (vel2.y * 50) + this.position.y);
//            Vector2 offsetPosition3 = new Vector2((vel3.x * 50) + this.position.x + 40, (vel3.y * 50) + this.position.y);
////
//            if (manager.getCollectionOfBullets().size < 3) {
//
////                //PITCH FORK SHOT
//                gameObject bullet1 = new gameObject();
//                bullet1.sprite.setColor(Color.GREEN);
//                bullet1.velocity.set(vel1);
//                bullet1.position.set(offsetPosition1);
//                bullet1.moveSpeed = 300f;
//                bullet1.update();
//                manager.getCollectionOfBullets().add(bullet1);
//
//                gameObject bullet2 = new gameObject();
//                bullet2.sprite.setColor(Color.GREEN);
//                bullet2.velocity.set(vel2);
//                bullet2.position.set(offsetPosition2);
//                bullet2.moveSpeed = 300f;
//                bullet2.update();
//                manager.getCollectionOfBullets().add(bullet2);
//
//
//                gameObject bullet3 = new gameObject();
//                bullet3.sprite.setColor(Color.GREEN);
//                bullet3.velocity.set(vel3);
//                bullet3.position.set(offsetPosition3);
//                bullet3.moveSpeed = 300f;
//                bullet3.update();
//                manager.getCollectionOfBullets().add(bullet3);
//            }
//
//        }


//        public void SurroundShot(gameObject object, float dt) {
//            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
//            Vector2 vel1 = new Vector2(MathUtils.cos(radians), MathUtils.sin((radians)));
//            Vector2 vel2 = new Vector2(MathUtils.cos(radians + 1.5708f), MathUtils.sin((radians + 1.5708f)));
//            Vector2 vel3 = new Vector2(MathUtils.cos(radians - 1.5708f), MathUtils.sin((radians - 1.5708f)));
//            Vector2 offsetPosition1 = new Vector2((vel1.x * 20) + this.position.x, (vel1.y * 20) + this.position.y);
//            Vector2 offsetPosition2 = new Vector2((vel2.x * 20) + this.position.x, (vel2.y * 20) + this.position.y);
//            Vector2 offsetPosition3 = new Vector2((vel3.x * 20) + this.position.x, (vel3.y * 20) + this.position.y);
//
//            if (manager.getCollectionOfBullets().size < 3) {
//                //SKILL #2
//                //Fan Shot
//                gameObject bullet1 = new gameObject();
//                bullet1.sprite.setColor(Color.GREEN);
//                bullet1.velocity.set(vel1);
//                bullet1.position.set(offsetPosition1);
//                bullet1.moveSpeed = 300f;
//                bullet1.update();
//                manager.getCollectionOfBullets().add(bullet1);
//
//                gameObject bullet2 = new gameObject();
//                System.out.println("MAX:" + bullet1.maxLifeSpan);
//                bullet2.sprite.setColor(Color.GREEN);
//                bullet2.velocity.set(vel2);
//                bullet2.position.set(offsetPosition2);
//                bullet2.moveSpeed = 300f;
//                bullet2.update();
//                manager.getCollectionOfBullets().add(bullet2);
//
//                gameObject bullet3 = new gameObject();
//                bullet3.sprite.setColor(Color.GREEN);
//                bullet3.velocity.set(vel3);
//                bullet3.position.set(offsetPosition3);
//                bullet3.moveSpeed = 300f;
//                bullet3.update();
//                manager.getCollectionOfBullets().add(bullet3);
//
//
////                }
//
//            }
//        }

    //BUG: bullet max life span is bugged! currently, the value is baked in
    // SKILL 2
//        public void TriShot(gameObject object, float dt) {
//
//
//            float radians = MathUtils.atan2(object.position.y - this.position.y, object.position.x - this.position.x);
//            Vector2 vel1 = new Vector2(MathUtils.cos(radians), MathUtils.sin((radians)));
//            Vector2 vel2 = new Vector2(MathUtils.cos(radians + 0.261799f), MathUtils.sin((radians + 0.261799f)));
//            Vector2 vel3 = new Vector2(MathUtils.cos(radians - 0.261799f), MathUtils.sin((radians - 0.261799f)));
//            Vector2 offsetPosition1 = new Vector2((vel1.x * 20) + this.position.x, (vel1.y * 20) + this.position.y);
//            Vector2 offsetPosition2 = new Vector2((vel2.x * 20) + this.position.x, (vel2.y * 20) + this.position.y);
//            Vector2 offsetPosition3 = new Vector2((vel3.x * 20) + this.position.x, (vel3.y * 20) + this.position.y);
////            if (coolDown <= 0) {
//            if (manager.getCollectionOfBullets().size < 3) {
//                //SKILL #2
//                //Fan Shot
//                gameObject bullet1 = new gameObject();
//                bullet1.sprite.setColor(Color.GREEN);
//                bullet1.velocity.set(vel1);
//                bullet1.position.set(offsetPosition1);
//                bullet1.moveSpeed = 300f;
//                bullet1.update();
//                manager.getCollectionOfBullets().add(bullet1);
//
//                gameObject bullet2 = new gameObject();
//                System.out.println("MAX:" + bullet1.maxLifeSpan);
//                bullet2.sprite.setColor(Color.GREEN);
//                bullet2.velocity.set(vel2);
//                bullet2.position.set(offsetPosition2);
//                bullet2.moveSpeed = 300f;
//                bullet2.update();
//                manager.getCollectionOfBullets().add(bullet2);
//
//                gameObject bullet3 = new gameObject();
//                bullet3.sprite.setColor(Color.GREEN);
//                bullet3.velocity.set(vel3);
//                bullet3.position.set(offsetPosition3);
//                bullet3.moveSpeed = 300f;
//                bullet3.update();
//                manager.getCollectionOfBullets().add(bullet3);
//
//
//            }
//
//        }
//
//        public void parallelShot() {
//
//        }

//    }


//    private SpriteBatch fontSpriteBatch;
//    private BitmapFont font;
//
//
//    private gameObject player;
//    private gameObject projectile;
//
//    private gameObject projectile2;
//
//    private gameObject enemy;


    //  Called when the Application is first created.
    //intialization game state
    float deltaTime;
    Player player;


    public void create() {
//        //initialize....
//        normal = new Vector2();
//        temp = new Vector2();
//        newVelocity = new Vector2();
//
//        font = new BitmapFont();
//        fontSpriteBatch = new SpriteBatch();
//        //-----------------------test player object-------------------------
//        player = new gameObject();
//        player.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//        player.texture = new Texture("square.png");
//        //------------------------------------------------------------------
//
//        //-----------------------testing first rotating projectile---------------
//        projectile = new gameObject();
//        //distance basically an offset from the object you are following
//        projectile.orbitDistance = 100;
//        projectile.position.set(player.position.x + projectile.orbitDistance, player.position.y + projectile.orbitDistance);
//        projectile.texture = new Texture("circle.png");
//        //orbit speed needs more accurate calculations
//        projectile.rotationSpeed = .03f;
//        projectile.sprite.setSize(projectile.sprite.getRegionWidth(), projectile.sprite.getRegionHeight());
//        //------------------------------------------------------------------------
//
//
//        //-----------------------testing second rotating projectile----------
//        projectile2 = new gameObject();
//        projectile2.orbitDistance = 50;
//        projectile2.position.set(projectile.position.x + projectile2.orbitDistance, projectile.position.y + projectile2.orbitDistance);
//        projectile2.texture = new Texture("circle.png");
//        projectile2.sprite.setColor(Color.BLUE);
//        projectile2.rotationSpeed = 0.07f;
//        projectile2.sprite.setSize(projectile2.sprite.getRegionWidth() / 2, projectile2.sprite.getRegionHeight() / 2);
//        //--------------------------------------------------------------------
//
//        //------------------------test enemy object-------------------------------
//        enemy = new gameObject();
//        enemy.position.set(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
//        enemy.texture = new Texture("circle.png");
//        enemy.sprite.setColor(Color.RED);
//        enemy.moveSpeed = 500;
//        //--------------------------------------------------------------------------------
        gameObjectBatch = new SpriteBatch();
        player = new Player();

    }

    //updating game state
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        System.out.println("PLAYER:" + player.toString());


        this.drawGameSprites();

        player.update();

//        //---------------------testing shooting mechanic----------------------------------
//
//        player.shoot(enemy, deltaTime);
//
//        //-----------------------------draw game objects-----------------------
//
//        player.batch.begin();
//        player.sprite.draw(player.batch);
//        projectile.sprite.draw(player.batch);
//        projectile2.sprite.draw(player.batch);
//        enemy.sprite.draw(player.batch);
//
//        for (gameObject b : manager.getCollectionOfBullets()) {
//            b.sprite.draw(player.batch);
//        }
//        player.batch.end();
//        //----------------------------------------------------------------------
//
//
//        //-----------------------------test object rotation functions------------------
//        projectile.rotateAround(player);
//        projectile2.rotateAround(projectile);
//        //------------------------------------------------------------------------------
//
//        fontSpriteBatch.begin();
//
//        //----------------------- DISPLAY TEXT ON SCREEN ---------------------------------
//
//        font.draw(fontSpriteBatch, "Upper left, FPS=" + Gdx.graphics.getFramesPerSecond(), 0, Gdx.graphics.getHeight());
//        font.draw(fontSpriteBatch, "Upper Fleft, ENEMY DISTANCE=" + enemy.position.dst(player.position), 0, Gdx.graphics.getHeight() - 50);
//        fontSpriteBatch.end();
//
//        // ---------------------------------------------------------------------------------
//
//
//        //check if player moves out of screen boundaries
//        if (player.position.x > Gdx.graphics.getWidth()) {
//            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
//        }
//        if (player.position.x < 0) {
//            player.position.set(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 2);
//        }
//
//
//        //TESTING FUNCTIONS
//        enemy.performImpulseCollision(player);
//        enemy.position.x += 1;
//
//
//        if (enemy.position.y > Gdx.graphics.getWidth()) {
//            enemy.position.set(Gdx.graphics.getWidth() / 16, Gdx.graphics.getHeight() / 16);
//        }
//
//
//        //update all objects
//        player.update();
//        projectile.update();
//        projectile2.update();
//        enemy.update();

    }

    @Override
    public void resize(int width, int height) {
    }


    /*
     *
     * Android Platform:
     *
     *
     * Called when the Application is paused.
     *
     * */
    public void pause() {
    }


    //called when the Application is resumed from a paused state.
    //when the application starts again
    @Override
    public void resume() {
    }


    /*
     * FUNCTIONS TO DRAW OBJECTS DURING RUN TIME
     *
     * */
    public SpriteBatch getGameObjectBatch() {
        return gameObjectBatch;
    }

    public void drawGameSprites() {
        getGameObjectBatch().begin();
        player.getSprite().draw(getGameObjectBatch());
        getGameObjectBatch().end();
    }


}





