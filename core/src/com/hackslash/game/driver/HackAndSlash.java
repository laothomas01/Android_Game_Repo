package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import java.util.LinkedList;
import java.util.Queue;

public class HackAndSlash extends ApplicationAdapter {
    private Stage stage;
    ShapeRenderer sr;
    Player player;
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
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    /**
     * -----------------------
     */

    static float wait_time = 1f;
    static float shootTimer = 0;

    float deltaTime;

    /**
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

    ArrayList<ArrayList<Enemy>> allEnemies;

    ArrayList<Bullet> bullets;

    Queue<Enemy> enemyQueue;

    /**
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
        /**
         * -----------------------------------------------------
         */
        Gdx.input.setInputProcessor(stage);

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);


        /**
         * INITIALIZE LISTS OF ENEMIES
         *
         * 4 QUADRANTS
         * 4 INTERCEPTS
         */
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
        /**
         * -------------------------------------------------
         */
        allEnemies = new ArrayList<ArrayList<Enemy>>();
        allEnemies.add(e1);
        allEnemies.add(e2);
        allEnemies.add(e3);
        allEnemies.add(e4);
        allEnemies.add(e5);
        allEnemies.add(e6);
        allEnemies.add(e7);
        allEnemies.add(e8);

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
    public void checkOverlap(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            if (player.getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())) {
                playerHB.subtractHealth();
            } else {
            }
        }
    }

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
        checkOverlap(e1);
        checkOverlap(e2);
        checkOverlap(e3);
        checkOverlap(e4);
        checkOverlap(e5);
        checkOverlap(e6);
        checkOverlap(e7);
        checkOverlap(e8);

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
        sr.setProjectionMatrix(cam.combined);
        player.draw(batch);

        player.shootBullets(e1, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e2, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e3, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e4, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e5, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e6, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e7, deltaTime, bullets, batch, enemyQueue);
        player.shootBullets(e8, deltaTime, bullets, batch, enemyQueue);

        stage.act(deltaTime);
        stage.draw();
        /**
         * Spawn Positions
         */
        quadrant1.spawnEnemies(e1, deltaTime, player, batch);
        quadrant2.spawnEnemies(e2, deltaTime, player, batch);
        quadrant3.spawnEnemies(e3, deltaTime, player, batch);
        quadrant4.spawnEnemies(e4, deltaTime, player, batch);
        Y_Intercept_Positive.spawnEnemies(e5, deltaTime, player, batch);
        Y_Intercept_Negative.spawnEnemies(e6, deltaTime, player, batch);
        X_Intercept_Positive.spawnEnemies(e7, deltaTime, player, batch);
        X_Intercept_Negative.spawnEnemies(e8, deltaTime, player, batch);

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
        float lerp = 0.1f;

        cam.position.x += (player.getPlayerPosition().x + 1 - cam.position.x + 1) * lerp * 0.5f;
        cam.position.y += (player.getPlayerPosition().y + 1 - cam.position.y + 1) * lerp * 0.5f;
        cam.update();


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
        sr.dispose();
        stage.dispose();
        skin.dispose();
        batch.dispose();
        player.dispose();
        playerHB.dispose();
        enemyTex(e1);
        enemyTex(e2);
        enemyTex(e3);
        enemyTex(e4);
        enemyTex(e5);
        enemyTex(e6);
        enemyTex(e7);
        enemyTex(e8);
    }

    /**
     * method used to cycle through enemy arraylist to dispose of textures
     *
     * @param enemyList an arraylist of enemy
     */
    public void enemyTex(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            e.getTex().dispose();
        }
    }


}
