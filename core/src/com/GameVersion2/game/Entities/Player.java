package com.GameVersion2.game.Entities;

import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Player has the following:
 * 2D physics: speed, velocity, mass, movement, collision, shooting projectiles, handle upgrades
 * 2D Graphics: sprite, texture, sprite batch
 * Misc Attributes: health, level
 * User input
 */
public class Player extends Entity {

    // ------------------------------------------ ITERATION 1 -----------------------------------------
    //list of skills used for killing enemies or supporting the player
    Array<Skill> skills;


    //-------------------------------------------------------------------------------------------------


    //-------------------------------------------ITERATION 2 ------------------------------------------

    Map<String, Skill> skillsMap;
    //-------------------------------------------------------------------------------------------------
    int level;
    int enemyDetectionRadius = 100;
    boolean hasLeveledUp;

    private int expReq;
    private int currentExp;
    Queue<Entity> seenEnemies;


    /**
     * Storing enemies seen by player
     */


    /**
     * Constructor should contain attributes relating to what a player object should consist of.
     */
    public Player() {
        expReq = 10;
        currentExp = 0;
        seenEnemies = new LinkedList<>();
        hasLeveledUp = false;
        level = 1;
        //player has a collection of skills;


        //ITERATION 1
        skills = new Array<>();
        //

        //ITERATION 2
        skillsMap = new HashMap<>();
        //
        /**
         * @TODO BUG: lacking exception handling. ex: parallel shoot will get data from.
         * @TODO improper projectile counts and IF ELSE affects other shoot conditions
         *
         */

        /**
         * Leveling up Basic Shoot Skill:
         *
         */
        Skill skill1 = new Skill("Basic Shoot", "", 1f, false, 1, 1, 0, 0);
        Skill skill2 = new Skill("Fan Shoot", "", 1.5f, false, 3, 1, 15, 0);
        skillsMap.put(skill1.getSkillName(), skill1);
        skillsMap.put(skill2.getSkillName(), skill2);

//        skills.add(new Skill("Parallel Shoot", 2.0f, false, 4, 1, 0, 0));
//        skills.add(new Skill("Fan Shoot", 1.5f, false, 3, 1, 15, 0));


        this.getPhysics().setPosition(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(10f, 10f);

        //100 pixel units per second
        this.getPhysics().setMoveSpeed(100f);
        graphics.setColor(Color.BLUE);
    }


    public boolean hasLeveledUp() {

        if (getCurrentExp() >= getExpReq()) {
            //set current exp back to 0
            //be careful of this. used to reset current exp so we can level up again.

            return true;
        }
        return false;
    }

    //------------------------- ITERATION 1 -----------------------------------------

    public boolean hasLeveled() {
        return this.hasLeveledUp;
    }

    public int getExpReq() {
        return expReq;
    }

    public void setLevel(int l) {
        this.level = l;
    }

    public int getLevel() {
        return level;
    }

    public Array<Skill> getSkills() {
        return skills;
    }

    //when a player selects an upgrade, add to player's skill collection
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public Skill getSkill(int i) {
        return skills.get(i);
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int exp) {
        this.currentExp = exp;
    }

    public boolean detectEntity(Entity target) {
        if (Vector2.dst(this.getPhysics().getPosition().x, this.getPhysics().getPosition().y, target.getPhysics().getPosition().x, target.getPhysics().getPosition().y) <= enemyDetectionRadius) {
            return true;
        }
        return false;
    }

    public Queue<Entity> getSeenEnemies() {
        return this.seenEnemies;
    }

    public void addSeenEnemy(Entity target) {
        //if the target has been detected and has not yet been recorded
        if (detectEntity(target)) {
            if (!seenEnemies.contains(target)) {
                seenEnemies.add(target);
            }
        }
    }


    //---------------------------------------------------------------------------------


    /**
     * -Specifically made currently for ranged attacks
     * -pick 1 target, shoot at it, and let the projectile's other functionalities handle other interactions
     */
    public String toString() {
        return "SPEED:" + this.getPhysics().getMoveSpeed() + "SIZE = " + "WIDTH:" + this.getPhysics().getSpriteWidth() + "HEIGHT:" + this.getPhysics().getSpriteHeight() + "GRAPHICS:" + this.getGraphics().getColor().toString();
    }


    public void shoot(Entity target, float dt, GameObjectManager projectiles) {

        this.getPhysics().setShootDirection(MathUtils.cos(MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x)), MathUtils.sin(MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x)));


        // ----------------------------------- ITERATION 2 -----------------------------------

        for (Map.Entry<String, Skill> set : skillsMap.entrySet()) {
            System.out.println(set);
        }

        //------------------------------------------------------------------------------------

        // -----------------------------ITERATION 1------------------------------------

        //---------------use to move bullet in direction of target


        //----------------------------------------------------------------------

//        /**
//         * Iterate through each skill and update their values
//         */
//        for (int i = 0; i < this.getSkills().size; i++) {
//            //update increase skill's cooldown timer
//            this.getSkill(i).update(this.getSkill(i).getCoolDownTimer() + dt, true);
//
//            // isOnCoolDown = true, coolDownTimer > maxCoolDown
//            // we must set the flag of isOnCoolDown = false, cooldown timer - max cooldown time = 0, so time resets
//            if (this.getSkill(i).hasFinishedCoolDown()) {
//                this.getSkill(i).update(this.getSkill(i).getCoolDownTimer() - this.getSkill(i).getmaxCoolDownTime(), false);
//
//                /**
//                 * Use the other constructor for projectile objects
//                 */
//
//
////                Projectile bullet = new Projectile();
////
////                //set bullet's constant direction of movement
//////                bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(getPhysics().getRadians()), MathUtils.sin(getPhysics().getRadians())));
////                bullet.getPhysics().setMovementDirection(this.getPhysics().getShootDirection());
////                //XY-offset the position when spawning
////                bullet.getPhysics().setPosition(new Vector2((
////                        bullet.getPhysics().getMovementDirection().x * 2) + this.getPhysics().getPosition().x,
////                        (bullet.getPhysics().getMovementDirection().y * 2) + this.getPhysics().getPosition().y));
////
//                /**
//                 * N number of bullets for the given skill being created and shot
//                 */
//                for (int j = 0; j < this.getSkill(i).getProjectileCount(); j++) {
//                    Projectile bullet = new Projectile();
//                    bullet.getPhysics().setMoveSpeed(150);
//                    //set bullet's constant direction of movement
////                bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(getPhysics().getRadians()), MathUtils.sin(getPhysics().getRadians())));
//                    bullet.getPhysics().setMovementDirection(this.getPhysics().getShootDirection());
//                    //XY-offset the position when spawning
//                    bullet.getPhysics().setPosition(new Vector2(
//                            (bullet.getPhysics().getShootDirection().x * 2) + this.getPhysics().getPosition().x,
//
//                            (bullet.getPhysics().getShootDirection().y * 2) + this.getPhysics().getPosition().y)
//
//                    );
//                    projectiles.addProjectiles(bullet);
//
//                }
//
//            }
//
//        }
//
////        //angle(in radians) used to find the direction for shooting at target
////        float shootRadians = MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x);
////
////        //new shoot direction
////        Vector2 newHeadVector = new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians));
////
////
////        //will be used to scale offset perpendicular distances
////        //can be used to expand or shrink the distance between the projectiles and the center
////        float deltaMultiplier = 0.0f;
////
////        // (this needs to be initialized inside the skill class)
////        float perpendicularOffsetDistance = 10.0f;
////        Vector2 perpendicularVector = new Vector2(-newHeadVector.y * perpendicularOffsetDistance, newHeadVector.x * perpendicularOffsetDistance);
////
////
////        for (int i = 0; i < this.getSkills().size; i++) {
////            //if the cool down flag has been set and cool down timer has finished resetting
////            if (this.getSkill(i).hasFinishedCoolDown()) {
////
////                //reset the skill's cooldown timer , set the cooldown flag to false
////                this.getSkill(i).update(this.getSkill(i).getMaxCoolDownTimer(), false);
////
////
////                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------
////                if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getDeltaAngle() >= 1 && this.getSkill(i).getProjectileCount() % 2 == 1) {
////
////                    //delta angle converted to radians
////                    float offsetRadians = this.getSkill(i).getDeltaAngle() * MathUtils.PI / 180;
////
////                    //offset angle multiplier
////                    deltaMultiplier = 1;
////                    //general unchanged bullet used during production
////
////                    //create bullets based on number of projectiles
////                    for (int j = 0; j < this.getSkill(i).getProjectileCount(); j++) {
////                        Projectile bullet = new Projectile();
////                        //set bullet move direction
////                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
////                        bullet.getGraphics().setColor(Color.PINK);
////                        bullet.getPhysics().setMoveSpeed(250f);
////                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
////                        /**
////                         * If any object collides within the boundaries of the distance between the center and left or right bullet, kill the enemy.
////                         */
//////                        //general unchanged bullet used during production
//////                        Projectile bullet = new Projectile();
//////                        //set bullet move direction
//////                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
//////                        bullet.getGraphics().setColor(Color.PINK);
//////                        bullet.getPhysics().setMoveSpeed(100f);
//////                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
////                        //if bullet index = odd
////                        if (j >= 1 && j % 2 == 1) {
////                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians + (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians + (offsetRadians * deltaMultiplier))));
////                        } else if (j >= 1 && j % 2 == 0) {
////
////
////                            //if bullet index = even
////                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians - (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians - (offsetRadians * deltaMultiplier))));
////                            //ever even bullet, once we set the current bullet direction vector, we increment the deltaMultiplier
////                            deltaMultiplier += 1;
////                        }
////
////                        this.getGameObjectManager().addProjectiles(bullet);
////                    }
////
////
////                }
////
////                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------
////
////
////                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------
//////                else if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getProjectileCount() % 2 == 0) {
//////                    //---------------Bug testing---------------
//////                    System.out.println(this.getSkill(i).getProjectileCount());
//////                    System.out.println("PARALLEL SHOOT!");
//////                    //----------------------------------------
//////                    //this will be used to shrink the gap distance between the bullets and their "center"
//////                    deltaMultiplier = 0.5f;
//////
//////
//////                    for (int j = 1; j <= this.getSkill(i).getProjectileCount(); j++) {
////////                        Projectile bullet = new Projectile();
////////                        bullet.getPhysics().setDirectionVector(newHeadVector);
//////                        bullet.getGraphics().setColor(Color.RED);
//////                        /*
//////                         * EQUATION:
//////                         *
//////                         * symbols:
//////                         * Pf = final position
//////                         * Pc = current position
//////                         *
//////                         *
//////                         *
//////                         * */
//////                        if (j % 2 == 1) {
//////                            bullet.getPhysics().getPosition().add(perpendicularVector.x * deltaMultiplier, perpendicularVector.y * deltaMultiplier);
//////                        } else {
//////                            bullet.getPhysics().getPosition().add(-perpendicularVector.x * deltaMultiplier, -perpendicularVector.y * deltaMultiplier);
//////                            deltaMultiplier += 1;
//////                        }
//////                        this.getGameObjectManager().addProjectiles(bullet);
//////
//////
//////                    }
//////
//////
//////                }
////                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------
////
////                else {
////                    Projectile bullet = new Projectile();
////                    bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
////                    bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
////                    this.getGameObjectManager().addProjectiles(bullet);
////                }
////
////            } else {
////                this.getSkill(i).update(this.getSkill(i).getCoolDown() - dt, true);
////            }
////
        //-------------------------------------------------------------------------

        //--------------------------------------------------------------------------------


    }


    public void Update(float dt) {
        update(dt);
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

}
