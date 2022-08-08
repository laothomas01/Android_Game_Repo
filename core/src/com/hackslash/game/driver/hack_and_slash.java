package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.*;
import com.hackslash.game.controller.BulletController;
import com.hackslash.game.controller.EnemyController;
import com.hackslash.game.controller.PlayerController;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.*;
import org.graalvm.compiler.loop.MathUtil;

import java.util.ArrayList;

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

    //    Sprite obj1;
//    Sprite obj2;
//    SpriteBatch batch;
//    Texture img1, img2;
//    float angleBetweenTwoVectors;
//    float dx;
//    float dy;
//    float x1;
//    float y1;
//    float x2;
//    float y2;
//    Vector2 velocity;
//
//    String TAG1 = "BULLETS";
//    String TAG2 = "BULLET";
//    SpriteBatch healthBarBatch;
//    OrthographicCamera followCam;
//    PlayerView playerView;
//    EnemyView enemyView;
//    BulletView bulletView;
//    UserInterfaceView ui_view;
//    PlayerController player_controller;
//    EnemyController enemy_controller;
//    BulletController bullet_controller;
//    Player player;
//    Enemy e1;
//    //    Enemy e2;
////    Enemy e3;
////    Enemy e4;
////    Enemy e5;
////    Enemy e6;
////    Enemy e7;
////    Enemy e8;
////    Enemy e9;
////    Enemy e10;
////    Enemy e11;
////    Enemy e12;
////    Enemy e13;
////    Enemy e14;
////    Enemy e15;
////    Enemy e16;
////    Enemy e17;
////    Enemy e18;
////    Enemy e19;
////    Enemy e20;
////    Enemy e21;
////    Enemy e22;
////    Enemy e23;
////    Enemy e24;
//    ArrayList<Enemy> enemies;
//    ArrayList<Enemy> enemiesToRemove;
//    ArrayList<Bullet> bulletsToRemove;
//    float deltaTime;
//    float maxFindNextEnemyTimer;
//    float currentFindNextEnemyTimer;
//    Vector2 center;
    float deltaTime;

    Vector2 center;
    Vector2 rotatingObjectPosition;
    Vector2 playerPosition;
    Sprite rotatingSprite;
    Sprite playerSprite;
    Texture rotatingSpriteImg; // spin object
    Texture playerSpriteImg; // moving object
    SpriteBatch batch;
    float val = 0;

    float mat2[][];
    float matRes[][];

    float matCoords[][];
    float currentX, currentY = 0;
    float newX, newY = 0;
    float tempX, tempY = 0;


    public void create() {
//        center = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
//        angleBetweenTwoVectors = 0;
//        dx = 0;
//        dy = 0;
//        velocity = new Vector2(dx, dy);
//        x1 = Gdx.graphics.getWidth() / 2;
//        y1 = Gdx.graphics.getHeight() / 2;
//
//        img1 = new Texture("circle.png");
//        img2 = new Texture("square.png");
////        obj1 = new Sprite(img1);
////        obj1.scale(1);
////        obj1.setPosition(x1, y1);
//        obj2 = new Sprite(img2);
//        obj2.scale(1);
//        obj2.setPosition((Gdx.graphics.getWidth() / 2) + 100, (Gdx.graphics.getHeight() / 2) + 100);
//        batch = new SpriteBatch();
//        maxFindNextEnemyTimer = 5f;
//        float currentFindNextEnemyTimer = maxFindNextEnemyTimer;
//        healthBarBatch = new SpriteBatch();
//        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        player = new Player();
//
//        e1 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
////        e2 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e3 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e4 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e5 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e6 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e7 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e8 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////
////        e9 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
////        e10 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e11 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e12 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e13 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 0.5f, 30, 10);
////        e14 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e15 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e16 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e16 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e17 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e18 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e19 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////
////        e20 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e21 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e22 = new Enemy(MathUtils.random(0, 1000) * -1, MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e23 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 1000), 100f, 1, 30, 1);
////        e24 = new Enemy(MathUtils.random(0, 1000), MathUtils.random(0, 100), 100f, 1, 30, 1);
//
//        enemies = new ArrayList<>();
//        enemies.add(e1);
////        enemies.add(e2);
////        enemies.add(e3);
////        enemies.add(e4);
////        enemies.add(e5);
////        enemies.add(e6);
////        enemies.add(e7);
////        enemies.add(e8);
////        enemies.add(e9);
////        enemies.add(e10);
////        enemies.add(e11);
////        enemies.add(e12);
////        enemies.add(e13);
////        enemies.add(e14);
////        enemies.add(e15);
////        enemies.add(e16);
////        enemies.add(e17);
////        enemies.add(e17);
////        enemies.add(e18);
////        enemies.add(e19);
////        enemies.add(e20);
////        enemies.add(e21);
////        enemies.add(e22);
////        enemies.add(e23);
////        enemies.add(e24);
//
//        enemyView = new EnemyView();
//        playerView = new PlayerView();
//        bulletView = new BulletView();
//        ui_view = new UserInterfaceView();
//        ui_view.init_touchpad();
//        ui_view.init_healthbar();
//        player_controller = new PlayerController(player, ui_view);
//        enemy_controller = new EnemyController();
//        bullet_controller = new BulletController();
//        bulletsToRemove = new ArrayList<>();
//        enemiesToRemove = new ArrayList<>();


        deltaTime = 0;
        //spin object
        rotatingSpriteImg = new Texture("square.png");
        rotatingSprite = new Sprite(rotatingSpriteImg);
        rotatingSprite.setPosition(Gdx.graphics.getWidth() / 2 + 100, Gdx.graphics.getHeight() / 2 + 100);

        //player object
        playerSpriteImg = new Texture("circle.png");
        playerSprite = new Sprite(playerSpriteImg);
        //point to rotate around
        playerSprite.setPosition(1, 1);

        batch = new SpriteBatch();

        currentX = (rotatingSprite.getX() - playerSprite.getX());
        currentY = (rotatingSprite.getY() - playerSprite.getY());

        tempX = currentX;
        tempY = currentY;
        mat2 = new float[][]{

                {MathUtils.cosDeg(45), -MathUtils.sinDeg(45), 0},
                {MathUtils.sinDeg(45), MathUtils.cosDeg(45), 0},
                {0, 0, 1}


        };

        matCoords = new float[][]{

                {rotatingSprite.getX()},
                {rotatingSprite.getY()},
                {1}
        };
        matRes = new float[][];

    }

//    int i = 0;
//    float radians = 0;
//    float newX = 0;
//    float nextX = ;
//    float newY = 0;
//    float nextY = 0;

    public void render() {


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        playerSprite.draw(batch);
        rotatingSprite.draw(batch);
        batch.end();
        rotatingSprite.setPosition(0, 0);

//
//        rotatingSprite.setPosition(
//
//                (rotatingSprite.getX() - Gdx.graphics.getWidth()) * MathUtils.cosDeg(90) - (rotatingSprite.getY() - Gdx.graphics.getHeight()) * MathUtils.sinDeg(90) * deltaTime,
//                (rotatingSprite.getX() - Gdx.graphics.getWidth()) * MathUtils.sinDeg(90) + (rotatingSprite.getY() - Gdx.graphics.getHeight()) * MathUtils.cosDeg(90) * deltaTime
//
//        );


//        newX = -currentY + playerSprite.getX();
//        newY = currentX + playerSprite.getY();
//
//        rotatingSprite.setPosition(newX, newY);


//        currentX = newX + playerSprite.getX();
//        currentY = newY + playerSprite.getY();
////        rotatingSprite.setPosition(newX, newY);
//
//        currentX = rotatingSprite.getX() - playerSprite.getX();
//        currentY = rotatingSprite.getY() - playerSprite.getY();


//        currX = rotatingSprite.getX() - playerSprite.getX();
//        currY = rotatingSprite.getY() - playerSprite.getY();
//
//
//        System.out.println(" CURR: " + currX + " : " + currY);
//
//        newX = -currY;
//        newY = currX;
//
//        newX = newX + playerSprite.getX();
//        newY = newY + playerSprite.getY();
//
//        System.out.println(" new X " + newX);
//        System.out.println(" new Y " + newY);
        //we will go 90 degrees counter clock wise rotation


    }

}
