package com.GameVersion2.game.Managers;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Entities.Entity;
import com.GameVersion2.game.Entities.Projectile;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Handles CRUD operations on game objects
 */

public class GameObjectManager {
    private float maxSpawnCoolDown = 3f;
    private float spawnTimer = 0f;
    Array<Enemy> enemies = new Array<>();

    Array<Projectile> projectiles = new Array<>();
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

    public float getMaxSpawnCoolDown() {
        return maxSpawnCoolDown;
    }


    public void spawnEnemies(float dt, int enemyType, int enemyCount) {

        /**
         * @TODO need to regulate how fast spawning will be with a timer
         * @TODO fix spawn positions
         */
        randomLocation = MathUtils.random(1, 4);
        if (spawnTimer < 0) {
            spawnTimer = maxSpawnCoolDown;
            Enemy e = new Enemy(0, 0, enemyType);
            /**
             * coordinates for center of viewport
             */
            int X = AppManager.getLocalViewPortWidth() / 2;
            int Y = AppManager.getLocalViewPortHeight() / 2;
            int LeftX = X - 300;
            int UpY = Y + 200;
            int RightX = X + 300;
            int DownY = Y - 200;
            e.getPhysics().setPosition(X, Y);
            switch (randomLocation) {
                case 1:
                    e.getPhysics().setPosition(MathUtils.random(
                            //300(X coord of origin)
                            X,
                            //450(X coord of origin offset by 150)
                            RightX), MathUtils.random(
                            //400(Y coord of origin)
                            Y,
                            //500(Y coord of origin offset by 100)
                            UpY));
                    System.out.println(e.toString());
                    break;
                case 2:
                    e.getPhysics().setPosition(MathUtils.random(LeftX, X), MathUtils.random(Y, UpY));
                    break;
                case 3:
                    e.getPhysics().setPosition(MathUtils.random(LeftX, X), MathUtils.random(DownY, Y));
                    break;
                case 4:
                    e.getPhysics().setPosition(MathUtils.random(X, RightX), MathUtils.random(DownY, Y));
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

    public void spawnBullets(float dt) {
        for (Projectile p : projectiles) {
            p.update(dt);
        }
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

    public Array<Entity> getGarbageCollection() {
        return garbageCollection;
    }

    public String getProjectilesToString() {
        return projectiles.toString();
    }

    public void addInGameObjectsToRemove(Entity b) {
        garbageCollection.add(b);
    }


}
