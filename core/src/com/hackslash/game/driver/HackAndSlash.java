package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    float player_x_Move;
    float player_y_Move;
    // Player's Health Bar
    private PlayerHealthBar playerHB;
    SpriteBatch batch;
    Queue<Enemy> targets;
    OrthographicCamera cam;
    OrthographicCamera UIcam;
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

    float deltaTime;
    float spawnWait;
    float maxspawnWaitTime;
    float lerp;
    float rageTime;
    float rageTimer;
    /**
     * --------------Initialize Spawners---------
     */

    ArrayList<Enemy> enemies;
    ArrayList<Enemy> remove_enemies;

    Enemy e1;
    Enemy e2;
    Enemy e3;
    Enemy e4;
    Enemy e5;
    Enemy e6;
    Enemy e7;
    Enemy e8;
    Enemy e9;
    Enemy e1B;
    Enemy e2B;
    Enemy e3B;
    Enemy e4B;
    Enemy e5B;
    Enemy e6B;
    Enemy e7B;
    Enemy e8B;
    Enemy e9B;
    /**
     * --------------------------------
     */

    boolean GAME_PAUSED;

    public void create() {

        lerp = 0.1f;
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
        e1 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e2 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e3 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(7, 16), MathUtils.random(1, 5));
        e4 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 120), 1, MathUtils.random(6, 15), MathUtils.random(1, 5));
        e5 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(5, 14), MathUtils.random(1, 5));
        e6 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(4, 13), MathUtils.random(1, 5));
        e7 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(3, 12), MathUtils.random(1, 5));
        e8 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(2, 11), MathUtils.random(1, 5));
        e9 = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(5, 10), MathUtils.random(1, 5));

        e1B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e2B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(10, 20), MathUtils.random(1, 5));
        e3B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(7, 16), MathUtils.random(1, 5));
        e4B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 120), 1, MathUtils.random(6, 15), MathUtils.random(1, 5));
        e5B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(5, 14), MathUtils.random(1, 5));
        e6B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(4, 13), MathUtils.random(1, 5));
        e7B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(3, 12), MathUtils.random(1, 5));
        e8B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(2, 11), MathUtils.random(1, 5));
        e9B = new Enemy(MathUtils.random(0, 2500) + 20, MathUtils.random(0, 2500) + 20, MathUtils.random(50, 200), 1, MathUtils.random(5, 10), MathUtils.random(1, 5));


        enemies = new ArrayList();
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
        enemies.add(e8);
        enemies.add(e9);
        enemies.add(e1B);
        enemies.add(e2B);
        enemies.add(e3B);
        enemies.add(e4B);
        enemies.add(e5B);
        enemies.add(e6B);
        enemies.add(e7B);
        enemies.add(e8B);
        enemies.add(e9B);
        targets = new LinkedList<>();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        UIcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        UIcam.position.set(UIcam.viewportWidth / 2f, UIcam.viewportHeight / 2f, 0);
        UIcam.update();
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
            for (int i = 0; i < enemies.size(); i++) {
                if (b.intersect(enemies.get(i))) {
                    enemies.get(i).takeDamage(b.getDamage());
                }
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
        //draw health bar

        batch.setProjectionMatrix(UIcam.combined);
        playerHB.draw(batch);

//
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

        batch.setProjectionMatrix(cam.combined);
        player.draw(batch);


        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).shouldRemove()) {
                enemies.remove(i);
                i--;
            } else {
                player.update(deltaTime, batch, enemies.get(i));
                enemies.get(i).update(deltaTime, player);
                enemies.get(i).draw(batch);
            }
        }

        System.out.println(player.getBullets().size());


        check_Bullet_Enemy_Overlap(player.getBullets());
        check_Player_Enemy_Overlap(enemies);


        stage.act(deltaTime);
        stage.draw();


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
        Vector3 position = cam.position;
        position.x = cam.position.x + (player.getXPosition() * 1 - cam.position.x) * deltaTime;
        position.y = cam.position.y + (player.getYPosition() * 1 - cam.position.y) * deltaTime;
        cam.position.set(position);
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
        GAME_PAUSED = !GAME_PAUSED;
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


}