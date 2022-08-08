package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Affine2;
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

    Affine2 af;
    Vector2 rotatingObjectPosition;
    Sprite rotatingSprite;
    Texture rotatingSpriteImg; // spin object
    SpriteBatch rotatingBatch;


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
    ArrayList<Enemy> enemies;
    ArrayList<Enemy> enemiesToRemove;
    ArrayList<Bullet> bulletsToRemove;
    float deltaTime;
    float maxFindNextEnemyTimer;
    float currentFindNextEnemyTimer;

    public void create() {

        af = new Affine2();
        maxFindNextEnemyTimer = 5f;
        float currentFindNextEnemyTimer = maxFindNextEnemyTimer;
        healthBarBatch = new SpriteBatch();
        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player();

        e1 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);

        enemies = new ArrayList<>();
        enemies.add(e1);

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
        rotatingSprite.setPosition(player.getPosition().x + 100, player.getPosition().y + 100);

    }

    float i = 0;

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
        rotatingBatch.begin();
        rotatingSprite.draw(rotatingBatch);
        rotatingBatch.end();
        rotatingSprite = new Sprite(rotatingSpriteImg);

//        rotatingSprite.setPosition(player.getPosition().x + 100, player.getPosition().y + 100);
//
//        rotatingSprite.setOriginBasedPosition(player.getPosition().x, player.getPosition().y);

//        rotatingSprite.setRotation( i+= 10);
//        rotatingSprite.setRotation(i++);


        rotatingBatch.setProjectionMatrix(followCam.combined);

        for (Enemy e : enemies) {
            e.getSpriteBatch().setProjectionMatrix(followCam.combined);
            enemyView.draw_enemy(e.getSpriteBatch(), e);
            enemy_controller.moveToPlayer(deltaTime, e, player);
            if (player_controller.detectEnemy(e)) {
                player_controller.storeSeenEnemy(e);
            }
//            if (player.hasCollided(player, e)) {
//                player.takeDamage(e);
//            }
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
