package com.GameVersion2.game.Managers;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Entities.Entity;
import com.GameVersion2.game.Entities.ExpDrop;
import com.GameVersion2.game.Entities.Projectile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Global game manager for game entities
 * Would be better to have 1 instantiate function for ALLLLLL GAME ENTITIES/GAME OBJECTS >:(
 * <p>
 * AS you notice in this code, i have to come back and add more and more and morrrrrrre features to handle CRUF
 * of a collection of game objects.
 * <p>
 * How can i generalize this to make my life easier as well as future devs and future refactoring?????
 * Problem with design currently:
 * - no reusability
 * - having to go back and add more and more features
 * - the object manager is now a manager of other managers(WE DONT WANT THIS)
 */

public class GameObjectManager {

    private float spawnTimer = 0f;
    Array<Entity> enemies = new Array<>();
    static Array<Entity> projectiles = new Array<>();
    static Array<Entity> expDrops = new Array<>();

    //handle removal of all game objects

    Array<Entity> garbageCollection = new Array<>();

    private int randomLocation = 0;

    public GameObjectManager() {

    }


    public void setSpawnCoolDownTimer(float cd) {
        spawnTimer = cd;
    }

    public float getSpawnCoolDownTimer() {
        return spawnTimer;
    }

//    public float getMaxSpawnCoolDown() {
//        return maxSpawnCoolDown;
//    }


    public void spawnEnemies(float dt, int enemyType, int enemyCount, float maxSpawnCoolDown) {

        randomLocation = MathUtils.random(1, 4);
        if (spawnTimer < 0) {
            spawnTimer = maxSpawnCoolDown;
            Enemy e = new Enemy(0, 0, enemyType);
            /**
             * coordinates for center of viewport
             //             */
//            int X = AppManager.getLocalViewPortWidth() / 2;
//            int Y = AppManager.getLocalViewPortHeight() / 2;
//            int LeftX = X - 300;
//            int UpY = Y + 200;
//            int RightX = X + 300;
//            int DownY = Y - 200;`
            e.getPhysics().setPosition(0, 0);
            switch (randomLocation) {
                case 1:
                    e.getPhysics().setPosition(MathUtils.random(
                            //300(X coord of origin)
                            AppManager.getLocalViewPortWidth() / 2,
                            //450(X coord of origin offset by 150)
                            AppManager.getLocalViewPortWidth() / 2 + 300), MathUtils.random(
                            //400(Y coord of origin)
                            AppManager.getLocalViewPortHeight() / 2,
                            //500(Y coord of origin offset by 100)
                            AppManager.getLocalViewPortHeight() / 2 + 200));
                    break;
                case 2:
                    e.getPhysics().setPosition(MathUtils.random(AppManager.getLocalViewPortWidth() / 2 - 300, AppManager.getLocalViewPortWidth() / 2), MathUtils.random(AppManager.getLocalViewPortHeight() / 2, AppManager.getLocalViewPortHeight() / 2 + 200));
                    break;
                case 3:
                    e.getPhysics().setPosition(MathUtils.random(AppManager.getLocalViewPortWidth() / 2 - 300, AppManager.getLocalViewPortWidth() / 2), MathUtils.random(AppManager.getLocalViewPortHeight() / 2 - 200, AppManager.getLocalViewPortHeight() / 2));
                    break;
                case 4:
                    e.getPhysics().setPosition(MathUtils.random(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortWidth() / 2 + 300), MathUtils.random(AppManager.getLocalViewPortHeight() / 2 - 200, AppManager.getLocalViewPortHeight() / 2));
                    break;
                default:
                    break;
            }
            getEnemies().add(e);


        } else {
            spawnTimer -= dt;
        }

//        }


        //i want an enemy with a given position


//        addEnemies(new Enemy(new Vector2(spawner.getPhysics().getPosition()), 10, 10, 0));


//        for (Enemy e : getEnemies()) {
//            //if an enemy entity is outside the viewport, do not render the object
//            e.update(dt);
//            if (
//                //if position > (800,600)
//                    (e.getPhysics().getPosition().x > AppManager.getScreenWidth() && e.getPhysics().getPosition().y > AppManager.getScreenHeight()) ||
//                            //if position > (0,800)
//                            //if position.x < 0
//                            e.getPhysics().getPosition().y > AppManager.getScreenHeight() || e.getPhysics().getPosition().y < 0 ||
//                            //if position > (0,600)
//                            //if position.x < 0
//                            e.getPhysics().getPosition().x > AppManager.getScreenWidth() || e.getPhysics().getPosition().x < 0 ||
//                            //if position < (0,0)
//                            e.getPhysics().getPosition().x < 0 && e.getPhysics().getPosition().y < 0) {
//                /**
//                 * if enemy not in viewport, dont render
//                 */
//                e.getPhysics().moveTowards(target, dt);
//            } else {
//                e.getGraphics().drawSprite();
//                e.getPhysics().moveTowards(target, dt);
//            }
//
//        }

    }


    //when enemy dies, add exp object to array
    //update objects in array to provide rendering
//    public void spawnEXP() {
//        for ( e : getEnemies()) {
//            if (e.getState().equals("DEAD")) {
//                getExpDrops().add(new ExpDrop(e.getPhysics().getPosition()));
//            }
//        }
//    }

    public static Array<Entity> getExpDrops() {
        return expDrops;
    }

    //    public void spawnBullets(float dt) {
//        for (Entity p : projectiles) {
//            p.update(dt);
//        }
//    }

    public void setEnemyCollectionSize(int size) {
        this.enemies.setSize(size);
    }

    public Array<Entity> getEnemies() {
        return enemies;
    }

    public void addEnemies(Enemy e) {
        this.enemies.add(e);
    }

    public static Array<Entity> getProjectiles() {
        return projectiles;
    }

    public void addProjectiles(Projectile b) {
        projectiles.add(b);
    }

    public Array<Entity> getGarbageCollection() {
        return garbageCollection;
    }

    public String getProjectilesToString() {
        return projectiles.toString();
    }


}
