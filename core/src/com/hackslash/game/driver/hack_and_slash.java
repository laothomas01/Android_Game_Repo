package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.controller.BulletController;
import com.hackslash.game.controller.EnemyController;
import com.hackslash.game.controller.PlayerController;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.*;

import java.util.ArrayList;


/***
 *
 *
 *     float deltaTime;
 *     Vector2 rotatingObjectPosition;
 *     Sprite rotatingSprite;
 *     Texture rotatingSpriteImg; // spin object
 *     SpriteBatch batch;
 *
 *     public void create() {
 *         //spin object
 *         deltaTime = 0;
 *         rotatingSpriteImg = new Texture("square.png");
 *         rotatingSprite = new Sprite(rotatingSpriteImg); // player sprite
 *         rotatingObjectPosition = new Vector2(center.x + 100, center.y + 100);
 *         rotatingSprite.scale(1f);
 *         batch = new SpriteBatch();
 *     }
 *
 *     public void render() {
 *
 *
 *         deltaTime = Gdx.graphics.getDeltaTime();
 *
 *         Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
 *         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 *         rotatingSprite.setPosition(rotatingObjectPosition.x, rotatingObjectPosition.y);
 *         playerSprite.setPosition(playerPosition.x, playerPosition.y);
 *         batch.begin();
 *         rotatingSprite.draw(batch);
 *         batch.end();
 *
 *         rotatingObjectPosition.set(rotatingObjectPosition.rotateAroundDeg(playerPosition, 270 * deltaTime));
 *
 *         playerPosition.set(playerPosition.x + 100 * deltaTime, playerPosition.y + 100 * deltaTime);
 *
 *
 *     }
 *
 */


/*
 *
 * Having issues with the camera following the player.
 *
 * why?
 *
 * player has its own spritebatch
 *
 * healthbar has its own sprite batch
 *
 *
 * so how should i fix this problem?
 *
 * let's refactor how our view classes go about drawing textures. we will not batch.begin...batch.end the draw functions, but will do that in the render.
 *
 *
 *
 * */
public class hack_and_slash extends ApplicationAdapter {

    Vector2 rotatingObjectPosition;
    Sprite rotatingSprite;
    Texture rotatingSpriteImg; // spin object
    SpriteBatch rotatingBatch;


    String TAG1 = "BULLETS";
    String TAG2 = "BULLET";
    SpriteBatch healthBarBatch;
    OrthographicCamera followCam;
    PlayerView playerView;
    EnemyView enemyView;
    BulletView bulletView;
    UserInterfaceView ui_view;
    PlayerController player_controller;
    EnemyController enemy_controller;
    BulletController bullet_controller;
    Player player;
    Enemy e1;
    //    Enemy e2;
//    Enemy e3;
//    Enemy e4;
//    Enemy e5;
//    Enemy e6;
//    Enemy e7;
//    Enemy e8;
//    Enemy e9;
//    Enemy e10;
//    Enemy e11;
//    Enemy e12;
//    Enemy e13;
//    Enemy e14;
//    Enemy e15;
//    Enemy e16;
//    Enemy e17;
//    Enemy e18;
//    Enemy e19;
//    Enemy e20;
//    Enemy e21;
//    Enemy e22;
//    Enemy e23;
//    Enemy e24;
    ArrayList<Enemy> enemies;
    ArrayList<Enemy> enemiesToRemove;
    ArrayList<Bullet> bulletsToRemove;
    float deltaTime;
    float maxFindNextEnemyTimer;
    float currentFindNextEnemyTimer;

    public void create() {


        maxFindNextEnemyTimer = 5f;
        float currentFindNextEnemyTimer = maxFindNextEnemyTimer;
        healthBarBatch = new SpriteBatch();
        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player();

        e1 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
//        e2 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e3 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e4 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e5 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e6 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e7 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e8 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//
//        e9 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
//        e10 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e11 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e12 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e13 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
//        e14 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e15 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e16 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e16 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e17 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e18 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e19 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//
//        e20 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e21 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e22 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e23 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
//        e24 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 100), 100f, 1, 30, 1);

        enemies = new ArrayList<>();
        enemies.add(e1);
//        enemies.add(e2);
//        enemies.add(e3);
//        enemies.add(e4);
//        enemies.add(e5);
//        enemies.add(e6);
//        enemies.add(e7);
//        enemies.add(e8);
//        enemies.add(e9);
//        enemies.add(e10);
//        enemies.add(e11);
//        enemies.add(e12);
//        enemies.add(e13);
//        enemies.add(e14);
//        enemies.add(e15);
//        enemies.add(e16);
//        enemies.add(e17);
//        enemies.add(e17);
//        enemies.add(e18);
//        enemies.add(e19);
//        enemies.add(e20);
//        enemies.add(e21);
//        enemies.add(e22);
//        enemies.add(e23);
//        enemies.add(e24);

        enemyView = new EnemyView();
        playerView = new PlayerView();
        bulletView = new BulletView();
        ui_view = new UserInterfaceView();
        ui_view.init_touchpad();
        ui_view.init_healthbar();
        player_controller = new PlayerController(player, ui_view);
        enemy_controller = new EnemyController();
        bullet_controller = new BulletController();
        bulletsToRemove = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();


        rotatingSpriteImg = new Texture("circle.png");
        rotatingBatch = new SpriteBatch();
        rotatingSprite = new Sprite(rotatingSpriteImg);
        rotatingObjectPosition = new Vector2(player.getPosition().x + 100, player.getPosition().y + 100);

    }

    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ui_view.update_touchpad();

        ui_view.updatePlayerHealthBar(healthBarBatch, player, followCam);
        player.getSpriteBatch().setProjectionMatrix(followCam.combined);

//        followCam.position.x = player.getPosition().x;
//        followCam.position.y = player.getPosition().y;
        playerView.draw_player(player.getSpriteBatch(), player);
        player_controller.move(deltaTime);
        player.getSpriteBatch().begin();
        rotatingSprite.draw(player.getSpriteBatch());
        player.getSpriteBatch().end();
        rotatingBatch.setProjectionMatrix(followCam.combined);
        rotatingSprite.setPosition(rotatingObjectPosition.x, rotatingObjectPosition.y);
        rotatingObjectPosition.set(rotatingObjectPosition.rotateAroundDeg(player.getPosition(), 90 * deltaTime));


        for (Enemy e : enemies) {
            e.getSpriteBatch().setProjectionMatrix(followCam.combined);
            enemyView.draw_enemy(e.getSpriteBatch(), e);
            enemy_controller.moveToPlayer(deltaTime, e, player);
            if (player_controller.detectEnemy(e)) {
                player_controller.storeSeenEnemy(e);
            }
            if (player.hasCollided(player, e)) {
                player.takeDamage(e);
            }
//            if (player_controller.hasCollided(player, e)) {
//                player.takeDamage(e);
//            }
        }


        Enemy currentSeenEnemy = player_controller.get_Seen_Enemies().peek();
        /**
         * -Enemy is default colored LIME
         *
         * -If you see an enemy, it's lit red
         * -If you are out of range for an enemy, it's lit blue
         * -Being out of range for 5 seconds means getting rid of the currently seen enemy and deciding to attack the next enemy in the queue
         */

        if (currentSeenEnemy != null) {
            if (player_controller.detectEnemy(currentSeenEnemy)) {
                currentSeenEnemy.getSpriteBatch().setColor(Color.RED);
                player_controller.shoot(deltaTime, currentSeenEnemy);
            } else {

                currentSeenEnemy.getSpriteBatch().setColor(Color.BLUE);

                if (currentFindNextEnemyTimer <= 0) {
                    currentSeenEnemy.getSpriteBatch().setColor(Color.LIME);
                    player_controller.get_Seen_Enemies().remove(currentSeenEnemy);

                    currentFindNextEnemyTimer = maxFindNextEnemyTimer;
                } else {
                    currentFindNextEnemyTimer -= deltaTime;
                }

            }
        }
        if (player_controller.get_Seen_Enemies().isEmpty()) {
            return;
        }


        for (Bullet b : player_controller.getBullets()) {
            b.getSpriteBatch().setProjectionMatrix(followCam.combined);

            //bullet collision
//            if (bullet_controller.hasCollided(b, currentSeenEnemy)) {
//                b.setSpeed(0f);
//                bulletsToRemove.add(b);
//            }

            bullet_controller.move(deltaTime, b);
            bulletView.draw_bullets(b.getSpriteBatch(), b);
        }


        for (Bullet b : player_controller.getBullets()) {
            if (b.getLifeSpan() <= 0) {
                bulletsToRemove.add(b);
            } else {
                b.setLifeSpan(b.getLifeSpan() - deltaTime);
            }
        }


        enemies.removeAll(enemiesToRemove);
        player_controller.getBullets().removeAll(bulletsToRemove);


//        followCam.update();
    }

}
