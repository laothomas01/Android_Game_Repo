package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.model.PlayerHealthBar;
import com.hackslash.game.model.Spawner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class HackAndSlash extends ApplicationAdapter {
    private Stage stage;
    ShapeRenderer sr;
    Player player;
    //    OrthographicCamera cam;
    float player_x_Move;
    float player_y_Move;
    // Player's Health Bar
    private PlayerHealthBar playerHB;
    SpriteBatch batch;
    Queue<Enemy> targets;
    /**
     * -------TOUCH PAD------
     */
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    /**
     * -----------------------
     */

    float maxshootwaitTime;
    float shootTime;
    float deltaTime;
    float spawnWait;
    float maxspawnWaitTime;

    float rageTime;
    float rageTimer;
    /**
     * --------------Initialize Spawners---------
     */

//    ArrayList<Bullet> allBullets;
    ArrayList<Enemy> enemies;
//    ArrayList<Enemy> removeEnemies;
//    ArrayList<Bullet> removeBullets;

    Enemy e1;
    Enemy e2;
    Enemy e3;
    Enemy e4;
    Enemy e5;
    Enemy e6;
    Enemy e7;
    Enemy e8;
    Enemy e9;


    /**
     * --------------------------------
     */

    public void create() {

        rageTime = 10;
        rageTimer = 0;
        spawnWait = 0;
        maxspawnWaitTime = 3;
        sr = new ShapeRenderer();
        player = new Player();

        playerHB = new PlayerHealthBar(player);
        batch = new SpriteBatch();

        skin = new Skin();
        skin.add("touchBackground", new Texture("touchBackground.png"));
        skin.add("touchKnob", new Texture("touchKnob.png"));
        /**
         * --------Create a Stage and add TouchPad------------
         */
        touchBackground = skin.getDrawable("touchBackground");
        touchKnob = skin.getDrawable("touchKnob");

        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);


        stage = new Stage();
        stage.addActor(touchpad);
        /**
         * -----------------------------------------------------
         */
        Gdx.input.setInputProcessor(stage);

//        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);
//

//        allBullets = new ArrayList<>();
//        enemies = new ArrayList<>();
//        enemies.add(new Enemy(2000, 2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(2000, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, 2000, 100, 1, 10, 1));
//
//        enemies.add(new Enemy(2000, 0, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, 0, 100, 1, 10, 1));
//        enemies.add(new Enemy(0, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
//
//        enemies.add(new Enemy(2000, 2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(2000, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, 2000, 100, 1, 10, 1));
//
//        enemies.add(new Enemy(2000, 0, 100, 1, 10, 1));
//        enemies.add(new Enemy(-2000, 0, 100, 1, 10, 1));
//        enemies.add(new Enemy(0, -2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
//        enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
//        removeEnemies = new ArrayList<>();
//        removeBullets = new ArrayList<>();
//        enemyQueue = new LinkedList<>();
        /**
         * INITIALIZE LISTS OF ENEMIES
         *
         * 4 QUADRANTS
         *
         * 4 INTERCEPTS
         */
        /**
         * -------------------------------------------------
         */
        e1 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e2 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e3 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(7, 16), MathUtils.random(1, 5));
        e4 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 120), 1, MathUtils.random(6, 15), MathUtils.random(1, 5));
        e5 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(5, 14), MathUtils.random(1, 5));
        e6 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(4, 13), MathUtils.random(1, 5));
        e7 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(3, 12), MathUtils.random(1, 5));
        e8 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(2, 11), MathUtils.random(1, 5));
        e9 = new Enemy(MathUtils.random(0, 2000), MathUtils.random(0, 2000), MathUtils.random(50, 200), 1, MathUtils.random(5, 10), MathUtils.random(1, 5));

        enemies = new ArrayList<Enemy>();
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
        enemies.add(e8);
        enemies.add(e9);
        targets = new LinkedList<>();

    }

    /**
     * Method called to check that none of the enemies in the array list of enemies are
     * touching the player character. if none are touching- do nothing. if an enemy
     * is touching the player character, subtract health from the player.
     */
    public void check_Player_Enemy_Overlap(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            if (player.getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())) {
                playerHB.subtractHealth();
            }

        }
    }

    public void check_Bullet_Enemy_Overlap(ArrayList<Bullet> bullets) {
        for (Bullet b : bullets) {
            if (e1 != null) {
                if (b.intersect(e1)) {
                    e1.takeDamage(b.getDamage());
                }
            }
            if (e2 != null) {
                if (b.intersect(e2)) {
                    e2.takeDamage(b.getDamage());
                }
            }
            if (e3 != null) {
                if (b.intersect(e3)) {
                    e3.takeDamage(b.getDamage());
                }
            }
            if (e4 != null) {
                if (b.intersect(e4)) {
                    e4.takeDamage(b.getDamage());
                }
            }
            if (e5 != null) {
                if (b.intersect(e5)) {
                    e5.takeDamage(b.getDamage());
                }
            }
            if (e6 != null) {
                if (b.intersect(e6)) {
                    e6.takeDamage(b.getDamage());
                }
            }
            if (e7 != null) {
                if (b.intersect(e7)) {
                    e7.takeDamage(b.getDamage());
                }
            }
            if (e8 != null) {
                if (b.intersect(e8)) {
                    e8.takeDamage(b.getDamage());
                }
            }
            if (e9 != null) {
                if (b.intersect(e9)) {
                    e9.takeDamage(b.getDamage());
                }
            }
        }
    }


    /**
     * Method to find the distance between player and enemies using distance formula
     * if the distance is <= 300f then shoot bullets at the enemy
     *
     * @param enemyList this is an <arraylist> of enemy
     */
//    public void findDistance(ArrayList<Enemy> enemyList) {
//        float distance;
//        for (Enemy e : enemyList) {
//            distance = (float) Math.sqrt(Math.pow(e.getXPosition() - player.getXPosition(), 2) + Math.pow(e.getYPosition() - player.getYPosition(), 2));
//            if (distance <= 300f) {
//                //System.out.println("enemy distance:" + distance);
//                shootBullets(e);
//
//            }
//        }
//
//
//    }

//    public void shootBullets(Enemy e) {
//        shootTimer += deltaTime;
//        if (shootTimer >= wait_time) {
//            bullets.add(new Bullet(player.getXPosition(), player.getYPosition()));
//
//        }
//
//        for (Bullet bullet : bullets) {
//            bullet.draw(batch);
//            bullet.update(deltaTime, e.getXPosition(), e.getYPosition());
//        }
//
//    }

    /**
     * Method called by the game loop from the application every time rendering should be performed. Game logic updates are usually also performed in this method.
     */

    public void render() {

        /**
         * Screen Clearing every frame
         */
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /**
         * CHECK IF ENEMIES ARE TOUCHING THE PLAYER
         */
//        checkOverlap(e1);
//        checkOverlap(e2);
//        checkOverlap(e3);
//        checkOverlap(e4);
//        checkOverlap(e5);
//        checkOverlap(e6);
//        checkOverlap(e7);
//        checkOverlap(e8);

        //draw health bar
        sr.setProjectionMatrix(batch.getProjectionMatrix());
        playerHB.draw(batch);

        /**
         * make game's frame rate independent
         */
        deltaTime = Gdx.graphics.getDeltaTime();
        /**
         * Update Player Movement
         */
        player_x_Move = player.getXPosition() + touchpad.getKnobPercentX() * player.getPlayerSpeed() * deltaTime;
        player_y_Move = player.getYPosition() + touchpad.getKnobPercentY() * player.getPlayerSpeed() * deltaTime;
        player.setXPosition(player_x_Move);
        player.setYPosition(player_y_Move);

//        sr.setProjectionMatrix(cam.combined);
        player.draw(batch);
        player.update(deltaTime, batch, e1);
        player.update(deltaTime, batch, e2);
        player.update(deltaTime, batch, e4);
        player.update(deltaTime, batch, e4);
        player.update(deltaTime, batch, e5);
        player.update(deltaTime, batch, e6);
        player.update(deltaTime, batch, e7);
        player.update(deltaTime, batch, e8);
        player.update(deltaTime, batch, e9);

        if (player.shouldRemove()) {
            player = null;
        }


//        for (int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).draw(batch);
//            enemies.get(i).update(deltaTime, player);
//        }
        if (e1 != null) {
            e1.draw(batch);
            e1.update(deltaTime, player);
            if (e1.shouldRemove()) {
                e1 = null;
            }
        }
        if (e2 != null) {
            e2.draw(batch);
            e2.update(deltaTime, player);
            if (e2.shouldRemove()) {
                e2 = null;
            }
        }
        if (e3 != null) {
            e3.draw(batch);
            e3.update(deltaTime, player);
            if (e3.shouldRemove()) {
                e3 = null;
            }
        }
        if (e4 != null) {
            e4.draw(batch);
            e4.update(deltaTime, player);
            if (e4.shouldRemove()) {
                e4 = null;
            }
        }
        if (e5 != null) {
            e5.draw(batch);
            e5.update(deltaTime, player);
            if (e5.shouldRemove()) {
                e5 = null;
            }
        }
        if (e6 != null) {
            e6.draw(batch);
            e6.update(deltaTime, player);
            if (e6.shouldRemove()) {
                e6 = null;
            }
        }
        if (e7 != null) {
            e7.draw(batch);
            e7.update(deltaTime, player);
            if (e7.shouldRemove()) {
                e7 = null;
            }
        }
        if (e8 != null) {
            e8.draw(batch);
            e8.update(deltaTime, player);
            if (e8.shouldRemove()) {
                e8 = null;
            }
        }
        if (e9 != null) {
            e9.draw(batch);
            e9.update(deltaTime, player);
            if (e9.shouldRemove()) {
                e9 = null;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (rageTimer > rageTime) {
                rageTimer = 0;
                enemies.get(i).setSpeed(5);
                enemies.get(i).setSize(5);
                enemies.get(i).getSprite().setColor(1, 0, 1, 1);
            } else {
                rageTimer += deltaTime;
            }
        }


        check_Bullet_Enemy_Overlap(player.getBullets());
        check_Player_Enemy_Overlap(enemies);


//
//
//        if (spawnWait >= maxspawnWaitTime) {
//            spawnWait = 0;
//            enemies.add(new Enemy(2000, 2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(2000, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, 2000, 100, 1, 10, 1));
////
////            enemies.add(new Enemy(2000, 0, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, 0, 100, 1, 10, 1));
////            enemies.add(new Enemy(0, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
////
////            enemies.add(new Enemy(2000, 2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(2000, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, 2000, 100, 1, 10, 1));
////
////            enemies.add(new Enemy(2000, 0, 100, 1, 10, 1));
////            enemies.add(new Enemy(-2000, 0, 100, 1, 10, 1));
////            enemies.add(new Enemy(0, -2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
////            enemies.add(new Enemy(0, 2000, 100, 1, 10, 1));
//        } else {
//            spawnWait += deltaTime;
//        }
////        System.out.println(enemies.size());
//
////        player.loadBullets();
//        for (int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).update(deltaTime, player);
//            enemies.get(i).draw(batch);
//        }


        stage.act(deltaTime);
        stage.draw();
        /**
         * Spawn Positions
         */


//
//        Y_Intercept_Positive.spawnEnemies(e5, deltaTime, player, batch);
//        Y_Intercept_Negative.spawnEnemies(e6, deltaTime, player, batch);
//        X_Intercept_Positive.spawnEnemies(e7, deltaTime, player, batch);
//        X_Intercept_Negative.spawnEnemies(e8, deltaTime, player, batch);

/**
 * Set cam Position
 */
        /**
         * lerp: linear interpolation:
         *  -smooth camera motion that is not too rigid.
         *  -want to decrease jittery movement of the camera
         * camera_position  + (target_position - camera_position) * lerp
         *
         */
//        float lerp = 0.1f;
//
//        cam.position.x += (player.getPlayerPosition().x + 1 - cam.position.x + 1) * lerp * 0.5f;
//        cam.position.y += (player.getPlayerPosition().y + 1 - cam.position.y + 1) * lerp * 0.5f;
//        cam.update();


    }


    /**
     * This method is called every time the game screen is re-sized and the game is not in the paused state. It is also called once just after the create() method.
     * The parameters are the new width and height the screen has been resized to in pixels.
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * On Android this method is called when the Home button is pressed or an incoming call is received. On desktop this is called when the window is minimized and just before dispose() when exiting the application.
     * A good place to save the game state.
     */
    public void pause() {
    }

    /**
     * This method is called on Android, when the application resumes from a paused state, and on desktop when unminimized.
     */
    public void resume() {
    }

    /**
     * Called when the application is destroyed. It is preceded by a call to pause().
     */
    public void dispose() {
//        sr.dispose();
//        stage.dispose();
//        skin.dispose();
//        batch.dispose();
//        player.dispose();
//        playerHB.dispose();
//        enemyTex(e1);
//        enemyTex(e2);
//        enemyTex(e3);
//        enemyTex(e4);
//        enemyTex(e5);
//        enemyTex(e6);
//        enemyTex(e7);
//        enemyTex(e8);
    }

    /**
     * method used to cycle through enemy arraylist to dispose of textures
     *
     * @param enemyList an arraylist of enemy
     */
//    public void enemyTex(ArrayList<Enemy> enemyList) {
//        for (Enemy e : enemyList) {
//            e.getTex().dispose();
//        }
//    }


}