package com.GameVersion2.game.Util;

import com.GameVersion2.game.Entities.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * PHYSICS 2D SYSTEM
 * <p>
 * REFERENCE LINKS:
 * getting a perpendicular vector to set the position of the offset perpendicular objects
 * https://gamedev.stackexchange.com/questions/149654/how-to-rotate-a-local-position-offset-based-on-a-direction-vector
 */


public class Physics2D {

    //adds a slight bounce to a colliding object with impulse
    float COLLISION_COEF = 1.0f;
    Vector2 position;


    //direction of entity movement
    //velocity = direction Vector * speed * time
    Vector2 directionVector;


    //change over time in entity's direction vector
    Vector2 acceleration;

    Vector2 angularVelocity;

    Vector2 normalVector;

    //temp normal vector used to store new data for updating normal vector
    Vector2 tempNormalVector;

    //temp direction vector used to store new data for updating direction vector
    Vector2 tempNewDirectionVector;

    //value used for rotations
    float radians = 0f;
    float moveSpeed = 0f;
    float angularSpeed = 0f;
    float spriteWidth = 0;
    private float spriteHeight = 0;
    float mass = 1.0f;
    float distanceFrom = 0f;
    float impactDistance = 0f;


    //distance from object being rotated around
    float orbitDistance = 0f;

    boolean isCollided = false;


    public Physics2D() {

        position = new Vector2();
        directionVector = new Vector2();
        angularVelocity = new Vector2();
        acceleration = new Vector2();
        normalVector = new Vector2();

        tempNormalVector = new Vector2();

        tempNewDirectionVector = new Vector2();
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


    public void setSpriteWidth(float wdth) {
        this.spriteWidth = wdth;
    }

    public void setSpriteHeight(float hght) {
        this.spriteHeight = hght;
    }

    public void setSpriteSize(float wdth, float hght) {
        this.spriteWidth = wdth;
        this.spriteHeight = hght;
    }

    public float getSpriteWidth() {
        return this.spriteWidth;
    }

    public float getSpriteHeight() {
        return this.spriteHeight;
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


    public void move(float dt) {
        this.getPosition().add(this.getDirectionVector().x * this.getMoveSpeed() * dt, this.getDirectionVector().y * this.getMoveSpeed() * dt);
    }


    //change object direction based on target position
    public void moveTowards(Entity target, float dt) {
        float radians = getAngleBetweenTwoVectors(target);
        this.setDirectionVector(MathUtils.cos(radians), MathUtils.sin(radians));
    }

    //get angle between target position and calling game object's position
    public float getAngleBetweenTwoVectors(Entity target) {
        //destination                       source
        return MathUtils.atan2(target.getPhysics().getPosition().y - this.getPosition().y, target.getPhysics().getPosition().x - this.getPosition().x);
    }

    // ------------------------ ROTATIONS ----------------------
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

    public Vector2 getAngularVelocity() {
        return this.angularVelocity;
    }

    public void setOrbitDistance(float dist) {
        this.orbitDistance = dist;
    }

    public void rotateAround(Entity target) {
        this.increaseRotationAngle();
        getAngularVelocity().set(MathUtils.cos(this.radians), MathUtils.sin(this.radians));
        this.getDirectionVector().set(orbitDistance * getAngularVelocity().x, orbitDistance * getAngularVelocity().y);
        getPosition().set(target.getPhysics().getPosition().x + getDirectionVector().x, target.getPhysics().getPosition().y + getDirectionVector().y);

    }

    // ---------------------------------------------------------------

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


    public boolean hasCollided(Entity target) {
        this.setDistanceBetween(Vector2.dst2(this.getPosition().x, this.getPosition().y, target.getPhysics().getPosition().x, target.getPhysics().getPosition().y));
//        System.out.println("DISTANCE BETWEEN:" + this.getDistanceBetween());
        this.setImpactDistance((this.getSpriteWidth() + target.getPhysics().getSpriteWidth()) / 2f);

        if (this.getDistanceBetween() < this.getImpactDistance()) {
            return true;
        }
//        this.getNormalVector().dst2(this.getPosition().sub(target.getPhysics().getPosition()));
//        this.getNormalVector().set(this.getPosition()).sub(target.getPhysics().getPosition());
//
//        this.setDistanceBetween(getNormalVector().len());
//        //                            ( sprite width + target width )/
//        this.setImpactDistance((this.getSpriteWidth() + target.getPhysics().getSpriteWidth()) / 2f);
//        //if you have less than or no distance between an object's collision distance, you crashed
//        if (this.getDistanceBetween() < this.getImpactDistance()) {
//            this.setIsCollided(true);
//            return true;
//        }
        return false;

    }

    /*
    look at this later
     */
    public void performImpulseCollision(Entity target) {

        if (hasCollided(target)) {

            //temporary normal vector to hold values used for changing normal vector
            getTempNormalVector().set(getNormalVector().scl(getImpactDistance() - getDistanceBetween() / 2));

            getPosition().add(getTempNormalVector());

            getTempNormalVector().set(getNormalVector().scl(getImpactDistance() - getDistanceBetween() / 2));


//            object.getPhysics().getPosition().sub(getTempNormalVector());

            //setting direction of colliding objects based on impulse force
            getTempNewDirectionVector().set(getDirectionVector().sub(target.getPhysics().getDirectionVector()));

            //Newton's Law of Impact
            float impulse = (-(1 + COLLISION_COEF) * (getNormalVector().dot(getTempNewDirectionVector()))) / (getNormalVector().dot(getNormalVector()) * (1 / getMass() + 1 / 1));

            //Change velocity of colliding objects using impulse
            getTempNormalVector().set(getNormalVector()).scl(impulse / 1);
            getDirectionVector().add(tempNormalVector);
            getTempNormalVector().set(getNormalVector()).scl(impulse / 1);

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
