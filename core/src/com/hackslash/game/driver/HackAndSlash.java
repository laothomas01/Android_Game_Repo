package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
<<<<<<< HEAD
=======
import com.badlogic.gdx.graphics.Color;
>>>>>>> working_on_bullet_shooting_branch
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
<<<<<<< HEAD
=======
import com.badlogic.gdx.math.Vector2;
>>>>>>> working_on_bullet_shooting_branch
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
<<<<<<< HEAD
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
=======
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.model.PlayerHealthBar;
>>>>>>> working_on_bullet_shooting_branch
import com.hackslash.game.model.Spawner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class HackAndSlash extends ApplicationAdapter {

    ShapeRenderer sr;
    Player player;
<<<<<<< HEAD
    OrthographicCamera cam;
    float player_x_Move;
    float player_y_Move;

    /**
     * -------TOUCH PAD------
     */
    private Stage stage;
=======
    Enemy e;
    Enemy e0;
    OrthographicCamera cam;
    float player_x_Move;
    float player_y_Move;
    // Player's Health Bar
    private PlayerHealthBar playerHB;
    SpriteBatch batch;
    /**
     * -------TOUCH PAD------
     */
>>>>>>> working_on_bullet_shooting_branch
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    /**
<<<<<<< HEAD
     * -------------------------
     */

=======
     * -----------------------
     */

    static float wait_time = 1f;
    static float shootTimer = 0;


>>>>>>> working_on_bullet_shooting_branch
    float deltaTime;
    Random r = new Random();
    /**
     * --------------Initialize Spawners---------
     */
    ArrayList<Enemy> e1;
//    ArrayList<Enemy> e2;
//    ArrayList<Enemy> e3;
//    ArrayList<Enemy> e4;
//    ArrayList<Enemy> e5;
//    ArrayList<Enemy> e6;
//    ArrayList<Enemy> e7;
//    ArrayList<Enemy> e8;
//    Spawner quadrant1;
//    Spawner quadrant2;
//    Spawner quadrant3;
//    Spawner quadrant4;
//    Spawner Y_Intercept_Positive;
//    Spawner Y_Intercept_Negative;
//    Spawner X_Intercept_Positive;
//    Spawner X_Intercept_Negative;

//    ArrayList<ArrayList<Enemy>> allEnemies;

    ArrayList<Bullet> bullets;
    //
    Queue<Enemy> enemyQueue;

    /**
<<<<<<< HEAD
     * --------------Initialize Spawners---------
     */
    ArrayList<Enemy> e1;
    ArrayList<Enemy> e2;
    ArrayList<Enemy> e3;
    ArrayList<Enemy> e4;
    ArrayList<Enemy> e5;
    ArrayList<Enemy> e6;
    ArrayList<Enemy> e7;
    ArrayList<Enemy> e8;
    Spawner quadrant1;
    Spawner quadrant2;
    Spawner quadrant3;
    Spawner quadrant4;
    Spawner Y_Intercept_Positive;
    Spawner Y_Intercept_Negative;
    Spawner X_Intercept_Positive;
    Spawner X_Intercept_Negative;

    /**
=======
>>>>>>> working_on_bullet_shooting_branch
     * --------------------------------
     */

    public void create() {

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
<<<<<<< HEAD

        Gdx.input.setInputProcessor(stage);

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);

        e1 = new ArrayList<Enemy>();
        e2 = new ArrayList<Enemy>();
        e3 = new ArrayList<Enemy>();
        e4 = new ArrayList<Enemy>();
        e5 = new ArrayList<Enemy>();
        e6 = new ArrayList<Enemy>();
        e7 = new ArrayList<Enemy>();
        e8 = new ArrayList<Enemy>();
        quadrant1 = new Spawner(2000, 2000);
        quadrant2 = new Spawner(-2000, 2000);
        quadrant3 = new Spawner(-2000, -2000);
        quadrant4 = new Spawner(2000, -2000);

        Y_Intercept_Positive = new Spawner(0, 2000);
        Y_Intercept_Negative = new Spawner(0, -2000);
        X_Intercept_Positive = new Spawner(2000, 0);
        X_Intercept_Negative = new Spawner(-2000, 0);
=======
        /**
         * -----------------------------------------------------
         */
        Gdx.input.setInputProcessor(stage);
>>>>>>> working_on_bullet_shooting_branch

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);


        /**
         * INITIALIZE LISTS OF ENEMIES
         *
         * 4 QUADRANTS
         * 4 INTERCEPTS
         */
        e1 = new ArrayList<Enemy>();
        for (int i = 0; i < 10; i++) {
            e1.add(new Enemy((int) Math.floor(Math.random() * (200 - 10 + 1) + 10), (int) Math.floor(Math.random() * (200 - 10 + 1) + 10), (int) Math.floor(Math.random() * (200 - 10 + 1) + 10), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), 3));
        }


//        e2 = new ArrayList<Enemy>();
//        e3 = new ArrayList<Enemy>();
//        e4 = new ArrayList<Enemy>();
//        e5 = new ArrayList<Enemy>();
//        e6 = new ArrayList<Enemy>();
//        e7 = new ArrayList<Enemy>();
//        e8 = new ArrayList<Enemy>();
//        quadrant1 = new Spawner(2000, 2000);
//        quadrant2 = new Spawner(-2000, 2000);
//        quadrant3 = new Spawner(-2000, -2000);
//        quadrant4 = new Spawner(2000, -2000);
//
//        Y_Intercept_Positive = new Spawner(0, 2000);
//        Y_Intercept_Negative = new Spawner(0, -2000);
//        X_Intercept_Positive = new Spawner(2000, 0);
//        X_Intercept_Negative = new Spawner(-2000, 0);
        /**
         * -------------------------------------------------
         */
//        allEnemies = new ArrayList<ArrayList<Enemy>>();
//        allEnemies.add(e1);
//        allEnemies.add(e2);
//        allEnemies.add(e3);
//        allEnemies.add(e4);
//        allEnemies.add(e5);
//        allEnemies.add(e6);
//        allEnemies.add(e7);
//        allEnemies.add(e8);
//        e = new Enemy();
//        e0 = new Enemy();

        bullets = new ArrayList<>();
        enemyQueue = new LinkedList<Enemy>();
    }

    /**
     * Method called to check that none of the enemies in the array list of enemies are
     * touching the player character. if none are touching- do nothing. if an enemy
     * is touching the player character, subtract health from the player.
     *
     * @param enemyList this is an <arraylist> of enemy
     */
//    public void checkOverlap(ArrayList<Enemy> enemyList) {
//        for (Enemy e : enemyList) {
//            if (player.getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())) {
//                playerHB.subtractHealth();
//            } else {
//            }
//        }
//    }

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
<<<<<<< HEAD
         * make game's frame rate independent
         */
        float lerp = 0.1f;
=======
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
>>>>>>> working_on_bullet_shooting_branch
        deltaTime = Gdx.graphics.getDeltaTime();
        /**
         * Update Player Movement
         */
        player_x_Move = player.getXPosition() + touchpad.getKnobPercentX() * player.getPlayerSpeed() * deltaTime;
        player_y_Move = player.getYPosition() + touchpad.getKnobPercentY() * player.getPlayerSpeed() * deltaTime;
        player.setXPosition(player_x_Move);
        player.setYPosition(player_y_Move);
        sr.setProjectionMatrix(cam.combined);
<<<<<<< HEAD
        player.draw(sr);
=======
        player.draw(batch);
        player.shootBullets(e1, deltaTime, bullets, batch, enemyQueue);
        for (Enemy e : e1) {
            e.update(deltaTime, player);
            e.draw(batch);
        }

        Iterator<Enemy> iter1 = e1.iterator();
//        Iterator<Bullet> iter2 = bullets.iterator();
//        while (iter1.hasNext()) {
//            while (iter2.hasNext()) {
//                Enemy enemy = iter1.next();
//
//                Bullet bullet = iter2.next();
//
//                if (bullet.hasHit()) {
//                    if (enemy.isDead()) {
//                        iter1.remove();
//                        enemyQueue.remove(enemy);
//                        iter2.remove();
//                    } else {
//                        iter2.remove();
//                    }
//                }
//
//            }
//        }


//        player.shootBullets(e1, deltaTime, bullets, batch, enemyQueue);
//


//        player.shootBullets(e, deltaTime, bullets, batch);
//        player.shootBullets(e0, deltaTime, bullets, batch);


//        e.draw(batch);
//        e.update(deltaTime, player);
//        e0.draw(batch);
//        e0.update(deltaTime, player);


//
////        findDistance(e1);
////        findDistance(e2);
////        findDistance(e3);
////        findDistance(e4);
////        findDistance(e5);
////        findDistance(e6);
////        findDistance(e7);
////        findDistance(e8);
//
//        //After bullet collides with enemy, remove enemy and remove bullet
//        ArrayList<Bullet> bulletsRemove = new ArrayList<Bullet>();
//        ArrayList<Enemy> enemyRemove = new ArrayList<Enemy>();
//        for (int j = bullets.size() - 1; j >= 0; j--) {
//            for(ArrayList<Enemy> enemyList : allEnemies) {
//                for(int i = enemyList.size() - 1; i >= 0; i--) {
//
//                    if(bullets.get(j).getSprite().getBoundingRectangle().overlaps(enemyList.get(i).getSprite().getBoundingRectangle())) {
//                        bulletsRemove.add(bullets.get(j));
//                        enemyRemove.add(enemyList.get(i));
//                    }
//                    enemyList.removeAll(enemyRemove);
//                }
//
//            }
//            bullets.removeAll(bulletsRemove);
//        }

>>>>>>> working_on_bullet_shooting_branch

        stage.act(deltaTime);
        stage.draw();
        /**
         * Spawn Positions
         */
<<<<<<< HEAD
        quadrant1.spawnEnemies(e1, deltaTime, player, sr);
        quadrant2.spawnEnemies(e2, deltaTime, player, sr);
        quadrant3.spawnEnemies(e3, deltaTime, player, sr);
        quadrant4.spawnEnemies(e4, deltaTime, player, sr);

        Y_Intercept_Positive.spawnEnemies(e5, deltaTime, player, sr);
        Y_Intercept_Negative.spawnEnemies(e6, deltaTime, player, sr);
        X_Intercept_Positive.spawnEnemies(e7, deltaTime, player, sr);
        X_Intercept_Negative.spawnEnemies(e8, deltaTime, player, sr);
=======
//        quadrant1.spawnEnemies(e1, deltaTime, player, batch);
//        quadrant2.spawnEnemies(e2, deltaTime, player, batch);
//        quadrant3.spawnEnemies(e3, deltaTime, player, batch);
//        quadrant4.spawnEnemies(e4, deltaTime, player, batch);
//
//        Y_Intercept_Positive.spawnEnemies(e5, deltaTime, player, batch);
//        Y_Intercept_Negative.spawnEnemies(e6, deltaTime, player, batch);
//        X_Intercept_Positive.spawnEnemies(e7, deltaTime, player, batch);
//        X_Intercept_Negative.spawnEnemies(e8, deltaTime, player, batch);
>>>>>>> working_on_bullet_shooting_branch

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
<<<<<<< HEAD
        Vector3 position = cam.position;
        position.x = cam.position.x + (player.getXPosition() * 1 - cam.position.x) * deltaTime;
        position.y = cam.position.y + (player.getYPosition() * 1 - cam.position.y) * deltaTime;
        cam.position.set(position);
        cam.update();
=======
        float lerp = 0.1f;

        cam.position.x += (player.getPlayerPosition().x + 1 - cam.position.x + 1) * lerp * 0.5f;
        cam.position.y += (player.getPlayerPosition().y + 1 - cam.position.y + 1) * lerp * 0.5f;
        cam.update();


>>>>>>> working_on_bullet_shooting_branch
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
<<<<<<< HEAD

    }
=======
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
>>>>>>> working_on_bullet_shooting_branch


}
