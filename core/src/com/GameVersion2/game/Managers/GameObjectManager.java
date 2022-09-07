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
    private float maxSpawnCoolDown = 1f;
    private float spawnTimer = 0f;
    //has to be 0 index based
    Array<Enemy> enemies = new Array<>();
    Array<Projectile> projectiles = new Array<>();
    //handle removal of all game objects
    Array<Entity> garbageCollection = new Array<>();

    int waves = 1;

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


    public void spawnEnemies(float dt, int enemyType, int enemyCount, int offsetDistance) {

        /**
         * @TODO need to regulate how fast spawning will be with a timer
         * @TODO fix spawn positions
         */
        System.out.println(getEnemies().size);

//        if (getEnemies().size < enemyCount) {
        if (spawnTimer < 0) {
            spawnTimer = maxSpawnCoolDown;
            Enemy e = new Enemy(
                    MathUtils.random(AppManager.getLocalViewPortWidth() / 4, AppManager.getLocalViewPortWidth() / 2),
                    MathUtils.random(AppManager.getLocalViewPortHeight() / 4, AppManager.getLocalViewPortHeight() / 2),
                    -1);
            e.setType(0);
            System.out.println("TYPE:" + e.getType());
            System.out.println("WIDTH:" + e.getPhysics().getSpriteWidth());
//            Enemy e2 = new Enemy(AppManager.getLocalViewPortWidth() / 4, AppManager.getLocalViewPortHeight() / 4, 0);
//                switch (MathUtils.random(0, 3)) {
//                    case 0:
//            e.getPhysics().setPosition(
//                    MathUtils.random(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortWidth())
//                    , MathUtils.random(AppManager.getLocalViewPortHeight() / 2, AppManager.getLocalViewPortHeight())
//            );

//            e.getPhysics().setPosition(
//                    MathUtils.random(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortWidth())
//                    , MathUtils.random(AppManager.getLocalViewPortHeight() / 2, AppManager.getLocalViewPortHeight())
//            );
////                        (MathUtils.random((AppManager.getLocalViewPortHeight() / 2),
////                                AppManager.getLocalViewPortHeight() - 100) + offsetDistance), enemyType)
////                        Enemy e = new Enemy((
//
//                              ;
//            break;
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//
//        }
            getEnemies().add(e);

            //should be handled by a game timer. not here.
//        waves += 1;

        } else {
            spawnTimer -= dt;
        }
        System.out.println("WAVE:" + waves);

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
