package com.GameVersion2.game.Managers;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Entities.Entity;
import com.GameVersion2.game.Entities.Projectile;
import com.badlogic.gdx.utils.Array;

/**
 * Handles CRUD operations on game objects
 */

public class GameObjectManager {
    float deltaPosition = 0;
    private float maxSpawnCoolDown = 1.2f;
    private float spawnCoolDownTimer = 0f;
    //has to be 0 index based
    private float enemyWave = 1f;
    Array<Enemy> enemies = new Array<>();
    Array<Projectile> projectiles = new Array<>();
    //handle removal of all game objects
    Array<Entity> garbageCollection = new Array<>();

    public GameObjectManager() {

    }

    public void setSpawnCoolDownTimer(float cd) {
        spawnCoolDownTimer = cd;
    }

    public float getSpawnCoolDownTimer() {
        return spawnCoolDownTimer;
    }

    public float getMaxSpawnCoolDown() {
        return maxSpawnCoolDown;
    }


    public void spawnEnemies(float dt, int enemyType, int enemyCount, int offsetMultiplier) {

        /**
         * @TODO need to regulate how fast spawning will be with a timer
         * @TODO fix spawn positions
         */


        if (getSpawnCoolDownTimer() <= 0) {
            setSpawnCoolDownTimer(getMaxSpawnCoolDown());
            for (int i = 0; i < enemyCount; i++) {

            }
        } else {
            setSpawnCoolDownTimer(getMaxSpawnCoolDown() - dt);
        }


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
