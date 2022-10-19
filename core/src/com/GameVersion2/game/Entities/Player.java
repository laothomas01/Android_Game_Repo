package com.GameVersion2.game.Entities;

import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.beans.PropertyEditor;
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


    Map<String, Skill> skillsMap;
    int level;
    int enemyDetectionRadius = 1000;
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


        skillsMap = new HashMap<>();
        /**
         * @TODO BUG: lacking exception handling. ex: parallel shoot will get data from.
         * @TODO improper projectile counts and IF ELSE affects other shoot conditions
         *
         */

        /**
         * Leveling up Basic Shoot Skill:
         *
         */
        /**
         * We can use these parameters *
         */
        /**
         * BASIC SHOOT:
         * //these upgrades will have a delta angle OR a delta position but never both.
         *  - FAN SHOOT: has a delta angle
         *  - PARALLEL SHOOT: has a delta position
         */
        //will be a branching upgrade from basic shoot
        Skill skill1 = new Skill("Basic Shoot", "", 1f, false, 1, 1, 0, 0);
//        skillsMap.put(skill1.getSkillName(), skill1);
        skillsMap.put(skill1.getSkillName(), skill1);

//        skills.add(new Skill("Parallel Shoot", 2.0f, false, 4, 1, 0, 0));
//        skills.add(new Skill("Fan Shoot", 1.5f, false, 3, 1, 15, 0));


        this.getPhysics().setPosition(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(10f, 10f);
        //100 pixel units per second
        this.getPhysics().setMoveSpeed(100f);
        graphics.setColor(Color.BLUE);
    }

    public Map<String, Skill> getSkillsMap() {
        return skillsMap;
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

//    public Array<Skill> getSkills() {
//        return skills;
//    }
//
//    //when a player selects an upgrade, add to player's skill collection
//    public void addSkill(Skill skill) {
//        skills.add(skill);
//    }
//
//    public Skill getSkill(int i) {
//        return skills.get(i);
//    }

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


    //if an enemy is seen by the player, add enemy to a Queue
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

    //what does shoot do?

    /**
     * -initialize shoot angles
     * -set shoot direction based on shoot angles
     * -initialize offset shoot angle multiplier
     * -iterate through a list of skills
     * -check if a skill has finished its cooldown
     * -updates skill cooldown
     * -check the skill's parameters and create a shoot style from its values: basic shoot, forked shoot, parallel shoot
     */

    public void shoot(Entity target, float dt, GameObjectManager projectiles) {
        //angle between position vectors
        float shootRadians = MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x);
        //create the heading vector for player's shoot direction
        this.getPhysics().setShootDirection(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians));

        // ----------------------------------- ITERATION 2 -----------------------------------
        //loop through set of player skills
        for (Map.Entry<String, Skill> set : skillsMap.entrySet()) {
            //get the current skill
            Skill skill = set.getValue();

            //convert the delta angle to radians because we calculate in radians
            //use these radians to offset the projectile's angle from the currently found radians.
            //note: the form of our shooting will be uniformed!
            float deltaRadians = skill.getDeltaAngle() * MathUtils.PI / 180;

            //update cooldown
            skill.update(skill.getCoolDownTimer() + dt, true);

            //@TODO check for conditions regarding fan shot
            //if finished cooling down
            if (skill.hasFinishedCoolDown()) {

                //applied to every projectile after the 1st projectile
                //used to offset delta angles of every i+2'th and i+3'rd projectile
                //increment multiplier after every i+3'rd projectile
                //reset the angle multiplier after cool down finishes
                //this also handles conditions of when the projectile count is increased or decreased
                //used to widen or condense the spread of bullets
                float deltaAngleMultiplier = 1;
                System.out.println("MULTIPLIER:" + deltaAngleMultiplier);

                //update current skill with a zeroed cool down timer
                skill.update(skill.getCoolDownTimer() - skill.getmaxCoolDownTime(), false);

                // --------------------------------------------------FANNED SHOT----------------------------------------
                if (skill.getProjectileCount() > 1 && skill.getDeltaAngle() >= 1) {
                    //we will set these
                    if (skill.getProjectileCount() % 2 == 1) {
                        //each index will be a new configuration for the current bullet
                        //we add a bullet for each index
                        //0th index for odd
                        for (int i = 0; i < skill.getProjectileCount(); i++) {
                            Projectile p = new Projectile();
                            p.getPhysics().setMovementDirection(this.getPhysics().getShootDirection());
                            p.getGraphics().setColor(Color.PINK);
                            p.getPhysics().setMoveSpeed(200);
                            if (i >= 1) {
                                if (i % 2 == 1) {
                                    p.getPhysics().setMovementDirection(new Vector2(MathUtils.cos(shootRadians + (deltaRadians * deltaAngleMultiplier)), MathUtils.sin(shootRadians + (deltaRadians * deltaAngleMultiplier))));
                                } else {
                                    p.getPhysics().setMovementDirection(new Vector2(MathUtils.cos(shootRadians - (deltaRadians * deltaAngleMultiplier)), MathUtils.sin(shootRadians - (deltaRadians * deltaAngleMultiplier))));
                                    /**     Projectile creation cycle: (by indices) 0 1 2
                                     *       0 = center projectile. no modifications to the movement direction angle
                                     *      --------------------we will check if these indices are odd or even---------------
                                     *      n(index) |   m(multiplier)      |   deltaAngle  |   n % 2   |        f(n)            |
                                     *        1    |       1              |      15       |     1      |        m_f = m_o       |
                                     *        2    |       1             |      15       |      0     |        m_f = m_o + 1   |
                                     *      ----------------------------------------------------------------------------------
                                     *
                                     */
                                    deltaAngleMultiplier += 1;
                                }
                            }
                            p.getPhysics().setPosition(p.getPhysics().getMovementDirection().x + this.getPhysics().getPosition().x, p.getPhysics().getMovementDirection().y + this.getPhysics().getPosition().y);
                            projectiles.addProjectiles(p);
                        }
                    } else {
                        for (int i = 0; i < skill.getProjectileCount(); i++) {
                            Projectile p = new Projectile();
                            p.getPhysics().setMovementDirection(this.getPhysics().getShootDirection());
                            p.getGraphics().setColor(Color.YELLOW);
                            p.getPhysics().setMoveSpeed(200);
                            //we ignore the center bullet
                            if (i % 2 == 1) {
                                p.getPhysics().setMovementDirection(new Vector2(MathUtils.cos(shootRadians + (deltaRadians * deltaAngleMultiplier)), MathUtils.sin(shootRadians + (deltaRadians * deltaAngleMultiplier))));
                            } else {
                                p.getPhysics().setMovementDirection(new Vector2(MathUtils.cos(shootRadians - (deltaRadians * deltaAngleMultiplier)), MathUtils.sin(shootRadians - (deltaRadians * deltaAngleMultiplier))));
                                deltaAngleMultiplier += 1f;
                            }
                            p.getPhysics().setPosition(p.getPhysics().getMovementDirection().x + this.getPhysics().getPosition().x, p.getPhysics().getMovementDirection().y + this.getPhysics().getPosition().y);
                            projectiles.addProjectiles(p);
                        }

                    }

                }
                //------------------------------------------------------------------------------------------------------

                //--------------------------------------------------PARALLEL SHOT---------------------------------------
                //Delta Angle = 0
                //Delta Position >= 1
                //------------------------------------------------------------------------------------------------------

                //---------------------------------------------------BASIC SHOT-----------------------------------------
                else {
                    Projectile p = new Projectile();
                    p.getPhysics().setMovementDirection(this.getPhysics().getShootDirection());
                    p.getGraphics().setColor(Color.CYAN);
                    p.getPhysics().setMoveSpeed(200);
                    p.getPhysics().setPosition(p.getPhysics().getMovementDirection().x + this.getPhysics().getPosition().x, p.getPhysics().getMovementDirection().y + this.getPhysics().getPosition().y);
                    projectiles.addProjectiles(p);
                }
                //------------------------------------------------------------------------------------------------------
//
//                else {
//                    Projectile p = new Projectile();
//                    p.getPhysics().setMovementDirection(new Vector2(MathUtils.cos(radians), MathUtils.sin(radians)));
//                    p.getPhysics().setMoveSpeed(150f);
//                    p.getPhysics().setPosition(new Vector2((p.getPhysics().getMovementDirection().x) + this.getPhysics().getPosition().x, (p.getPhysics().getMovementDirection().y) + this.getPhysics().getPosition().y));
//                    projectiles.addProjectiles(p);
//                // --------------------------------------------------PARALLEL SHOT--------------------------------------

                //
//                }

                // -----------------------------ITERATION 1------------------------------------

                //---------------use to move bullet in direction of target---------------------


                //-----------------------------------------------------------------------------

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
//                 * N number of bullets for the given skill being created and shot
//                 */
//                for (int j = 0; j < this.getSkill(i).getProjectileCount(); j++)
//                {
//                    Projectile bullet = new Projectile();
//                    bullet.getPhysics().setMoveSpeed(150);
//                    //set bullet's constant direction of movement
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
                //-----------------------------------------------------------------------------


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


//        public void Update ( float dt){
//            update(dt);
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
}