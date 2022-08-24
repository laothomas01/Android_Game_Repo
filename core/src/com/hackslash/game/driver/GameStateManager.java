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
import com.badlogic.gdx.utils.ObjectMap;
import com.hackslash.game.model.GameObject;

//TODO: migrate all graphic related functions and variables to this class
//maintain all usages for graphics
class Graphics2D {
    Sprite sprite;
    Texture texture;
    Color color;

    public Graphics2D() {
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

    public void setTexture(String fileName) {
        this.texture = new Texture(fileName);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return this.color;
    }

    public void drawObject(SpriteBatch batch) {
        this.getSprite().draw(batch);
    }

}

//maintain all current physics calculation
class Physics2D {

    float COLLISION_COEF;
    Vector2 position;
    Vector2 directionVector;
    Vector2 acceleration;
    Vector2 angularVelocity;
    float radians;
    float moveSpeed;
    float angularSpeed;
    float width;
    float height;
    float mass;
    float distanceFrom;
    float impactDistance;

    boolean isCollided;

    Vector2 normalVector;

    public Physics2D() {


        COLLISION_COEF = 1.0f;
        position = new Vector2(0, 0);
        directionVector = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        angularVelocity = new Vector2(0, 0);
        normalVector = new Vector2();
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

    public float getSpeed() {
        return this.moveSpeed;
    }

    public void setSpeed(float spd) {
        this.moveSpeed = spd;
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

    public void move(float dt) {
        this.getPosition().add(this.getDirectionVector().scl(this.getMoveSpeed() * dt));
    }

    //TODO: fix the naming convention or parameter
    //returns an arc tangent of y/x in radians
    public float getAngleBetweenTwoVectors(gameObject target) {
        return MathUtils.atan2(this.getPosition().y - target.getPhysics().getPosition().y, this.getPosition().x - target.getPhysics().getPosition().x);
    }

    public boolean checkIsCollided() {
        return isCollided;
    }

    public void setIsCollided(boolean collided) {
        this.isCollided = collided;
    }

    //TODO: migrate more physics functions from other classes to physics2D

    public boolean hasCollided(gameObject target) {
        this.getNormalVector().set(this.getPosition()).sub(target.getPhysics().getPosition());
        this.setDistanceBetween(getNormalVector().len());
        this.setImpactDistance((this.getWidth() + target.getPhysics().getWidth()) / 1.7f);
        //if you have less than or no distance between an object's collision distance, you crashed
        if (this.getDistanceBetween() < this.getImpactDistance()) {
            this.setIsCollided(true);
            return true;
        }
        return false;

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

//    public boolean hasCollided(gameObject obj1, gameObject obj2) {
//        obj1.getPhysics().getNormal().set(obj1.getPhysics().getPosition()).sub(obj2.getPhysics().getPosition());
//        obj1.getPhysics().setDistanceBetween(obj1.getPhysics().getNormal().len());
//        obj1.getPhysics().setImpactDistance((obj1.getSprite().getWidth() + obj2.getSprite().getWidth()) / 1.7f);
//        if (obj1.getPhysics().getDistanceBetween() < obj1.physics.impactDistance) {
//            return true;
//        }
//        return false;
//
//    }

//    public void move(gameObject obj1, float dt) {
////        this.getPhysics().getPosition().add(this.getPhysics().directionVector());
//        obj1.getPhysics().getPosition().add(obj1.getPhysics().directionVector());
//    }


}


//-----------------------------------------------GAME OBJECTS---------------------------------------------------------------


//game objects will do all the more specific physics calculations:
/*
 * movement, collisions, shooting.
 *
 * why am i doing this?
 *
 * in Unity, you have an object which has a "component" attached to the objet. then you call the "get component" to get the component you need.
 *
 * here it is different and for the sake of readibility, i would like my code to say: player.move(whatever speed)
 *
 *
 */


/*

 PERFORMS CRUD OPERATIONS ON GAME OBJECTS

-loading bullets
-inventory systems
-removing objects

 */
class GameObjectManager {
    Array<Bullet> bullets;
    Array<Bullet> removeAllBullets;

    public GameObjectManager() {
        bullets = new Array<>();
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void addBullets(Bullet b) {
        bullets.add(b);
    }

    public Array<Bullet> getRemoveAllBullets() {
        return removeAllBullets;
    }

    public String getBulletsToString() {
        return bullets.toString();
    }

    public void addBulletsToRemove(Bullet b) {
        removeAllBullets.add(b);
    }


}


class gameObject {


    //give game objects a physics component
    Physics2D physics;
    Graphics2D graphics;


    public gameObject() {
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


    //handles all changes to the game objects

    /*
     *
     * changes to velocity
     * changes to position
     * changes to sprite attributes
     *
     * */
    public void update(float dt) {
        graphics.getSprite().setSize(this.getPhysics().getWidth(), this.getPhysics().getHeight());
        graphics.getSprite().setPosition(this.getPhysics().getPosition().x, this.getPhysics().getPosition().y);
        graphics.getSprite().setTexture(graphics.getTexture());
        graphics.getSprite().setColor(graphics.getColor());


//        this.getPhysics().move(dt);
    }


    //let's override this function because dfferent game objects will behave different when "shooting".
    public void shoot(gameObject target, float dt) {

    }

}


class Bullet extends gameObject {
    Bullet() {
        this.getPhysics().setPosition(100f, 0f);
//        graphics.setTexture()("circle.png");
//        this.getPhysics().setSize(10f, 10f);
//        graphics.setColor(Color.GREEN);
        this.getPhysics().setSpeed(200f);
        this.getPhysics().setDirectionVector(1, 1);
    }
}


//have an API to update attributes. toggle this, set that, get something.
class Player extends gameObject {
    GameObjectManager gameObjectManager;
    Array<Skill> skills;


    Player() {

        skills = new Array<>();
        gameObjectManager = new GameObjectManager();
        this.getPhysics().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        graphics.setTexture("square.png");
        this.getPhysics().setSize(10f, 10f);
        this.getPhysics().setDirectionVector(1, 1);
        this.getPhysics().setMoveSpeed(500);
        graphics.setColor(Color.BLUE);
    }

    //---------------------------------------------------------------------------------
//    public SkillManager getSkillManager() {
//        return skillManager;
//    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public String toString() {
        return "    POSITION:   " + this.getPhysics().getPosition() + " DIRECTION VECTOR " + this.getPhysics().getDirectionVector() + " MOVE SPEED " + this.getPhysics().getMoveSpeed();
    }


    //shoot will be a trigger for using the player skills
    @Override
    public void shoot(gameObject target, float dt) {


    }
    /*
     *
     *
     * Note: key notes to keep in mind: production of projectiles is contingent upon skills being off cooldown, collision, maybe lifespan.
     * -we do not want an overlap of sklls hapenning at the same time and inteference between the current and next skill
     * -skills should be variant to collision, lifespan or whatever state the current skill and its set of objects.
     * -production of projectiles should not be affected by the state of the cooldown
     *
     *
     *
     * -have a projectile cleanup
     * -have a projectile producer
     *
     *
     * */

    //    @Override
//    public void shoot(gameObject target, float dt) {
//        for (ObjectMap.Entry<Skill, String> skill : skillManager.getSkills()) {
//
////            float offsetDistance = 50f;
////            float projectileCount = skill.key.projectileCount;
////            float skillCoolDown = skill.key.coolDown;
////            float radians = MathUtils.atan2(this.getPhysics().getPosition().y - target.getPhysics().getPosition().y, this.getPhysics().getPosition().x - target.getPhysics().getPosition().x);
////            Vector2 bulletDir = new Vector2(MathUtils.cos(radians), MathUtils.sin(radians));
////            Vector2 offsetPosition = new Vector2(bulletDir.x * offsetDistance, bulletDir.y * offsetDistance);
////
////            if (skillCoolDown < 0) {
////                System.out.println("HELLO WORLD!");
////            } else {
////                System.out.println("COOLDOWN!" + skillCoolDown);
////                skillCoolDown -= dt;
////            }
//
////            if (skillCoolDown <= 0) {
////                if (getGameObjectManager().getBullets().size < projectileCount) {
////                    Bullet bullet = new Bullet();
////                    bullet.getPhysics().setDirectonVector(bulletDir);
////                    bullet.getPhysics().position.set(offsetPosition);
////                    bullet.update();
////                    this.getGameObjectManager().addBullets(bullet);
////                }
////            } else {
////                skillCoolDown -= dt;
////            }
//
//        }
////
//    }
}

class Enemy extends gameObject {
    public Enemy() {
        this.getPhysics().setPosition(1, 0);
        graphics.setTexture("square.png");
        this.getPhysics().setSize(10f, 10f);
        graphics.setColor(Color.RED);
    }

//    public String toString() {
////        return "    POSITION:   " + this.getPhysics().getPosition() + "  TEXTURE: " + graphics.getTexture()toString() + "   SIZE:    " + this.getPhysics().getWidth() + "," + this.getPhysics().getHeight();
//    }
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
    boolean onCoolDown;
    String skillName;
    float coolDown;
    int level;
    String attackType;
    float projectileCount;

    public Skill() {
        onCoolDown = false;
        skillName = "BasicShot";
        coolDown = 1.5f;
        level = 1;
        projectileCount = 1f;
        attackType = "Ranged";
    }

    public String toString() {
        return "    NAME:   " + skillName + "   COOL DOWN   " + coolDown + "    LEVEL   " + level + "   PROJECTILE COUNT    " + projectileCount;
    }

    public float getProjectileCount() {
        return projectileCount;
    }

    public String getName() {
        return skillName;
    }

    public void setProjectileCount(float count) {
        projectileCount = count;
    }
}



/*
 * ACCESSES AND MODIFY SKILLS
 *
 * */
//class SkillManager {
//    ArrayMap<Skill, String> skills;
//
//    public SkillManager() {
//        skills = new ArrayMap<>();
//        skills.ordered = true;
//    }
//
//    public ArrayMap<Skill, String> getSkills() {
//        return skills;
//    }
//
//    public void addSkill(Skill skill, String str) {
//        skills.put(skill, str);
//    }
//
//
//}


public class GameStateManager extends ApplicationAdapter {

//    SpriteBatch gameObjectBatch;


//    //used to add a slight bounce to colliding objects
//    float COLLISION_COEF = 1.0f;
//    float deltaTime;
//    //-----------------------IMPULSE COLLISION VECTORS---------------
//    Vector2 normal;
//    Vector2 temp;
//    Vector2 newDirectionVector;
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
//                newDirectionVector.set(this.velocity.sub(object.velocity));
//
//                // Compute the impulse (see Essential Math for Game Programmers)
//                float impulse = (-(1 + COLLISION_COEF) * normal.dot(newDirectionVector)) / (normal.dot(normal) * (1 / this.mass + 1 / object.mass));
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
    Enemy enemy;

    Bullet bullet;

    float tempCount = 2.0f;


    public void create() {
//        //initialize....
//        normal = new Vector2();
//        temp = new Vector2();
//        newDirectionVector = new Vector2();
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
//        gameObjectBatch = new SpriteBatch();
        player = new Player();

//        player.getSkillManager().addSkill(new Skill(), "Basic Shot");
        enemy = new Enemy();

    }

    //updating game state
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        this.drawGameSprites();

//        player.shoot(enemy, deltaTime);
        System.out.println(player.toString());
        player.update(deltaTime);
        enemy.update(deltaTime);


//        for (Bullet b : player.getGameObjectManager().getBullets()) {
//            b.getPhysics().move(b, deltaTime);
//            b.update();
//        }


//        System.out.println(player.getGameObjectManager().getBulletsToString());


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
//    public SpriteBatch getGameObjectBatch() {
//        return gameObjectBatch;
//    }

//    public void drawGameSprites() {
////        getGameObjectBatch().begin();
////
//////
//////        player.getSprite().draw(getGameObjectBatch());
//////        enemy.getSprite().draw(getGameObjectBatch());
////
////
////        getGameObjectBatch().end();
//
//
//    }


}





