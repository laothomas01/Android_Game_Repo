package com.GameVersion2.game.Entities;

import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Player has the following:
 * 2D physics: speed, velocity, mass, movement, collision, shooting projectiles, handle upgrades
 * 2D Graphics: sprite, texture, sprite batch
 * Misc Attributes: health, level
 * User input
 */
public class Player extends Entity {
    GameObjectManager InGameObjectManager = new GameObjectManager();
    Array<Skill> skills;
    float shootAngle;

    Vector2 shootDirection;

    /**
     * Constructor should contain attributes relating to what a player object should consist of.
     */
    public Player() {

        shootDirection = new Vector2(0, 0);
        shootAngle = 0;
        //input handling
        Gdx.input.setInputProcessor(new GameInputProcessor());
        //player has a collection of skills;
        skills = new Array<>();
        /**
         * @TODO BUG: lacking exception handling. ex: parallel shoot will get data from.
         * @TODO improper projectile counts will cause data leakage into other skills.
         *
         */

        /**
         * Leveling up Basic Shoot Skill:
         *
         */
//        skills.add(new Skill("Basic Shoot", 0.5f, false, 1, 1, 0, 0));
//
//        /**
//         * Leveling up Parallel Shoot Skill
//         */
//        //this only takes care of even numbers of projectiles
//        skills.add(new Skill("Parallel Shoot", 2.0f, false, 4, 1, 0, 0));
//
//        /**
//         * Leveling up the fan shoot skill
//         */
//        //this only accounts for odd number of projectiles right now
//        skills.add(new Skill("Fan Shoot", 1.5f, false, 3, 1, 15, 0));


        this.getPhysics().setPosition(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);
        graphics.setTexture("square.png");
        this.getPhysics().setSpriteSize(10f, 10f);
        this.getPhysics().setMoveSpeed(100f);
        graphics.setColor(Color.BLUE);
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

    public void setShootDirection(float x, float y) {
        this.shootDirection = new Vector2(x, y);
    }

    public Vector2 getShootDirection() {
        return this.shootDirection;
    }

    //---------------------------------------------------------------------------------

    public GameObjectManager getGameObjectManager() {
        return InGameObjectManager;
    }


    private void handleKeyBoardInput() {

        if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W)))) {
            if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
                this.getPhysics().setDirectionVector(1, 1);

            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
                this.getPhysics().setDirectionVector(-1, 1);
            } else {
                this.getPhysics().setDirectionVector(0, 1);
            }
        } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S)))) {
            if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
                this.getPhysics().setDirectionVector(1, -1);
            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
                this.getPhysics().setDirectionVector(-1, -1);
            } else {
                this.getPhysics().setDirectionVector(0, -1);
            }
        } else if (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT) || GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A)) {
            this.getPhysics().setDirectionVector(-1, 0);
        } else if (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT) || GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D)) {
            this.getPhysics().setDirectionVector(1, 0);
        } else {
            this.getPhysics().setDirectionVector(0, 0);
        }

        GameInputProcessor.GameKeys.update();
    }

    /**
     * -Specifically made currently for ranged attacks
     * -pick 1 target, shoot at it, and let the projectile's other functionalities handle other interactions
     *
     * @param target
     * @param dt
     */
    public void shoot(Entity target, float dt) {

        //find the angle(in radians) between target and shooter
        this.getPhysics().setRadians(MathUtils.atan2(
                target.getPhysics().getPosition().y - this.getPhysics().getPosition().y,
                target.getPhysics().getPosition().x - this.getPhysics().getPosition().x
        ));

        //use to move bullet in direction of target
        this.setShootDirection(MathUtils.cos(this.getPhysics().getRadians()),
                MathUtils.sin(this.getPhysics().getRadians()));

        /**
         * Lets focus on de
         */


//        //angle(in radians) used to find the direction for shooting at target
//        float shootRadians = MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x);
//
//        //new shoot direction
//        Vector2 newHeadVector = new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians));
//
//
//        //will be used to scale offset perpendicular distances
//        //can be used to expand or shrink the distance between the projectiles and the center
//        float deltaMultiplier = 0.0f;
//
//        // (this needs to be initialized inside the skill class)
//        float perpendicularOffsetDistance = 10.0f;
//        Vector2 perpendicularVector = new Vector2(-newHeadVector.y * perpendicularOffsetDistance, newHeadVector.x * perpendicularOffsetDistance);
//
//
//        for (int i = 0; i < this.getSkills().size; i++) {
//            //if the cool down flag has been set and cool down timer has finished resetting
//            if (this.getSkill(i).hasFinishedCoolDown()) {
//
//                //reset the skill's cooldown timer , set the cooldown flag to false
//                this.getSkill(i).update(this.getSkill(i).getMaxCoolDownTimer(), false);
//
//
//                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------
//                if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getDeltaAngle() >= 1 && this.getSkill(i).getProjectileCount() % 2 == 1) {
//
//                    //delta angle converted to radians
//                    float offsetRadians = this.getSkill(i).getDeltaAngle() * MathUtils.PI / 180;
//
//                    //offset angle multiplier
//                    deltaMultiplier = 1;
//                    //general unchanged bullet used during production
//
//                    //create bullets based on number of projectiles
//                    for (int j = 0; j < this.getSkill(i).getProjectileCount(); j++) {
//                        Projectile bullet = new Projectile();
//                        //set bullet move direction
//                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
//                        bullet.getGraphics().setColor(Color.PINK);
//                        bullet.getPhysics().setMoveSpeed(250f);
//                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
//                        /**
//                         * If any object collides within the boundaries of the distance between the center and left or right bullet, kill the enemy.
//                         */
////                        //general unchanged bullet used during production
////                        Projectile bullet = new Projectile();
////                        //set bullet move direction
////                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
////                        bullet.getGraphics().setColor(Color.PINK);
////                        bullet.getPhysics().setMoveSpeed(100f);
////                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
//                        //if bullet index = odd
//                        if (j >= 1 && j % 2 == 1) {
//                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians + (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians + (offsetRadians * deltaMultiplier))));
//                        } else if (j >= 1 && j % 2 == 0) {
//
//
//                            //if bullet index = even
//                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians - (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians - (offsetRadians * deltaMultiplier))));
//                            //ever even bullet, once we set the current bullet direction vector, we increment the deltaMultiplier
//                            deltaMultiplier += 1;
//                        }
//
//                        this.getGameObjectManager().addProjectiles(bullet);
//                    }
//
//
//                }
//
//                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------
//
//
//                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------
////                else if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getProjectileCount() % 2 == 0) {
////                    //---------------Bug testing---------------
////                    System.out.println(this.getSkill(i).getProjectileCount());
////                    System.out.println("PARALLEL SHOOT!");
////                    //----------------------------------------
////                    //this will be used to shrink the gap distance between the bullets and their "center"
////                    deltaMultiplier = 0.5f;
////
////
////                    for (int j = 1; j <= this.getSkill(i).getProjectileCount(); j++) {
//////                        Projectile bullet = new Projectile();
//////                        bullet.getPhysics().setDirectionVector(newHeadVector);
////                        bullet.getGraphics().setColor(Color.RED);
////                        /*
////                         * EQUATION:
////                         *
////                         * symbols:
////                         * Pf = final position
////                         * Pc = current position
////                         *
////                         *
////                         *
////                         * */
////                        if (j % 2 == 1) {
////                            bullet.getPhysics().getPosition().add(perpendicularVector.x * deltaMultiplier, perpendicularVector.y * deltaMultiplier);
////                        } else {
////                            bullet.getPhysics().getPosition().add(-perpendicularVector.x * deltaMultiplier, -perpendicularVector.y * deltaMultiplier);
////                            deltaMultiplier += 1;
////                        }
////                        this.getGameObjectManager().addProjectiles(bullet);
////
////
////                    }
////
////
////                }
//                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------
//
//                else {
//                    Projectile bullet = new Projectile();
//                    bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
//                    bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
//                    this.getGameObjectManager().addProjectiles(bullet);
//                }
//
//            } else {
//                this.getSkill(i).update(this.getSkill(i).getCoolDown() - dt, true);
//            }
//
//        }

    }

    public void Update(float dt) {
        update(dt);
        this.getPhysics().move(dt);
        handleKeyBoardInput();
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
