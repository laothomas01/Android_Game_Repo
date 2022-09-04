package com.hackslash.game.driver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    GameObjectManager InGameObjectManager;
    Array<Skill> skills;



    Player() {
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
        skills.add(new Skill("Basic Shoot", 0.5f, false, 1, 1, 0, 0));

        /**
         * Leveling up Parallel Shoot Skill
         */
        //this only takes care of even numbers of projectiles
        skills.add(new Skill("Parallel Shoot", 2.0f, false, 4, 1, 0, 0));

        /**
         * Leveling up the fan shoot skill
         */
        //this only accounts for odd number of projectiles right now
        skills.add(new Skill("Fan Shoot", 1.5f, false, 3, 1, 15, 0));



        InGameObjectManager = new GameObjectManager();
        this.getPhysics().setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        graphics.setTexture("square.png");
        this.getPhysics().setSize(10f, 10f);
        this.getPhysics().setMoveSpeed(100f);
        graphics.setColor(Color.BLUE);
    }

    public Array<Skill> getSkills() {
        return skills;
    }


    //we will use this when players collect skill
    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public Skill getSkill(int i) {
        return skills.get(i);
    }

    //---------------------------------------------------------------------------------

    public GameObjectManager getGameObjectManager() {
        return InGameObjectManager;
    }

    public String toString() {
        return "PLAYER:\n" + "POSITION:" + this.getPhysics().getPosition() + "DIRECTION:" + this.getPhysics().getDirectionVector().toString() +
                "CAN MOVE:" + this.ableToMove();
    }


    public void shoot(Entity target, float dt) {

        //angle(in radians) used to find the direction for shooting at target
        float shootRadians = MathUtils.atan2(target.getPhysics().getPosition().y - this.getPhysics().getPosition().y, target.getPhysics().getPosition().x - this.getPhysics().getPosition().x);

        //new shoot direction
        Vector2 newHeadVector = new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians));


        //will be used to scale offset perpendicular distances
        //can be used to expand or shrink the distance between the projectiles and the center
        float deltaMultiplier = 0.0f;

        // (this needs to be initialized inside the skill class)
        float perpendicularOffsetDistance = 10.0f;
        Vector2 perpendicularVector = new Vector2(-newHeadVector.y * perpendicularOffsetDistance, newHeadVector.x * perpendicularOffsetDistance);


        for (int i = 0; i < this.getSkills().size; i++) {
            //if the cool down flag has been set and cool down timer has finished resetting
            if (this.getSkill(i).hasFinishedCoolDown()) {

                //reset the skill's cooldown timer , set the cooldown flag to false
                this.getSkill(i).update(this.getSkill(i).getMaxCoolDownTimer(), false);


                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------
                if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getDeltaAngle() >= 1 && this.getSkill(i).getProjectileCount() % 2 == 1) {

                    //delta angle converted to radians
                    float offsetRadians = this.getSkill(i).getDeltaAngle() * MathUtils.PI / 180;

                    //offset angle multiplier
                    deltaMultiplier = 1;
                    //general unchanged bullet used during production

                    //create bullets based on number of projectiles
                    for (int j = 0; j < this.getSkill(i).getProjectileCount(); j++) {
                        Projectile bullet = new Projectile();
                        //set bullet move direction
                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
                        bullet.getGraphics().setColor(Color.PINK);
                        bullet.getPhysics().setMoveSpeed(250f);
                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
                        System.out.println("FAN SHOOT!");
                        /**
                         * If any object collides within the boundaries of the distance between the center and left or right bullet, kill the enemy.
                         */
//                        //general unchanged bullet used during production
//                        Projectile bullet = new Projectile();
//                        //set bullet move direction
//                        bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
//                        bullet.getGraphics().setColor(Color.PINK);
//                        bullet.getPhysics().setMoveSpeed(100f);
//                        bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
                        //if bullet index = odd
                        if (j >= 1 && j % 2 == 1) {
                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians + (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians + (offsetRadians * deltaMultiplier))));
                        } else if (j >= 1 && j % 2 == 0) {


                            //if bullet index = even
                            bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians - (offsetRadians * deltaMultiplier)), MathUtils.sin(shootRadians - (offsetRadians * deltaMultiplier))));
                            //ever even bullet, once we set the current bullet direction vector, we increment the deltaMultiplier
                            deltaMultiplier += 1;
                        }

                        this.getGameObjectManager().addProjectiles(bullet);
                    }


                }

                //----------------------------------Odd Numbered Fan Shot Production Rule----------------------------------


                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------
//                else if (this.getSkill(i).getProjectileCount() > 1 && this.getSkill(i).getProjectileCount() % 2 == 0) {
//                    //---------------Bug testing---------------
//                    System.out.println(this.getSkill(i).getProjectileCount());
//                    System.out.println("PARALLEL SHOOT!");
//                    //----------------------------------------
//                    //this will be used to shrink the gap distance between the bullets and their "center"
//                    deltaMultiplier = 0.5f;
//
//
//                    for (int j = 1; j <= this.getSkill(i).getProjectileCount(); j++) {
////                        Projectile bullet = new Projectile();
////                        bullet.getPhysics().setDirectionVector(newHeadVector);
//                        bullet.getGraphics().setColor(Color.RED);
//                        /*
//                         * EQUATION:
//                         *
//                         * symbols:
//                         * Pf = final position
//                         * Pc = current position
//                         *
//                         *
//                         *
//                         * */
//                        if (j % 2 == 1) {
//                            bullet.getPhysics().getPosition().add(perpendicularVector.x * deltaMultiplier, perpendicularVector.y * deltaMultiplier);
//                        } else {
//                            bullet.getPhysics().getPosition().add(-perpendicularVector.x * deltaMultiplier, -perpendicularVector.y * deltaMultiplier);
//                            deltaMultiplier += 1;
//                        }
//                        this.getGameObjectManager().addProjectiles(bullet);
//
//
//                    }
//
//
//                }
                //----------------------------------Even Numbered Parallel Shoot Production Rule----------------------------------

                else {
                    Projectile bullet = new Projectile();
                    bullet.getPhysics().setDirectionVector(new Vector2(MathUtils.cos(shootRadians), MathUtils.sin(shootRadians)));
                    bullet.getPhysics().setPosition(new Vector2((bullet.getPhysics().getDirectionVector().x * 50) + this.getPhysics().getPosition().x, (bullet.getPhysics().getDirectionVector().y * 50) + this.getPhysics().getPosition().y));
                    this.getGameObjectManager().addProjectiles(bullet);
                }

            } else {
                this.getSkill(i).update(this.getSkill(i).getCoolDown() - dt, true);
            }

        }

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
