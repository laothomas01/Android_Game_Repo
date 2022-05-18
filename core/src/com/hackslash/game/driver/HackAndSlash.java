package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.model.PlayerHealthBar;
import com.hackslash.game.model.Spawner;

import org.graalvm.compiler.loop.MathUtil;

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
    OrthographicCamera cam;
    OrthographicCamera UIcam;
    float MAX_ENEMIES;
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
    float spawnTimer;
    float spawnTime;
    float lerp;
    float rageTime;
    float rageTimer;
    float currentGameTime;
    float MAXIMUM_ENEMY_SIZE;

    /**
     * --------------Initialize Spawners---------
     */
    ArrayList<Enemy> enemies;

    /**
     * --------------------------------
     */

    enum Screen {
        MENU, MAIN_GAME, GAME_OVER;
    }

    Screen currentScreen = Screen.MENU;
    BitmapFont font;
    boolean GAME_PAUSED;

    public void create() {
        MAXIMUM_ENEMY_SIZE = 500;
        lerp = 0.1f;
        rageTime = 10;
        rageTimer = 0;
        spawnTime = 3f;
        spawnTimer = 0;
        currentGameTime = 0;
        MAX_ENEMIES = 1000;
        sr = new ShapeRenderer();
        player = new Player();
        playerHB = new PlayerHealthBar(player);
        batch = new SpriteBatch();
        font = new BitmapFont();

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
        /**
         * -----------------------------------------------------
         */
        Gdx.input.setInputProcessor(stage);


        enemies = new ArrayList();

        /**
         * Use one camera to follow the player
         * -set the camera's projection before drawing the object you want the camera to focus on
         */
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        /**
         * Use another camera to focus on the UI
         * -set the camera's projection before drawing the object you want the camera to focus on
         */
        UIcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        UIcam.position.set(UIcam.viewportWidth / 2f, UIcam.viewportHeight / 2f, 0);
        UIcam.update();
    }

    public void restartGame() {
        player = new Player();
        playerHB = new PlayerHealthBar(player);

        enemies = new ArrayList();

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
         ** Screen Clearing every frame
         */
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (currentScreen == Screen.MENU) {
            batch.begin();
            batch.draw(new Texture("logo.png"), Gdx.graphics.getWidth() * .10f, Gdx.graphics.getHeight() * .5f, 900, 210);
            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = font;
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.font.getData().setScale(4.0f);
            final TextButton playBtn = new TextButton("Play", textButtonStyle);
            playBtn.setPosition((Gdx.graphics.getWidth() / 2 - playBtn.getWidth() / 2), (Gdx.graphics.getHeight() / 2 - playBtn.getHeight() / 2));
            stage.addActor(playBtn);
            playBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stage.clear();
                    currentScreen = Screen.MAIN_GAME;

                }
            });

            batch.end();
        } else if (currentScreen == Screen.MAIN_GAME) {

            stage.addActor(touchpad);
            batch.begin();
            /**
             * draw the health bar
             */
            batch.setProjectionMatrix(UIcam.combined);
            batch.end();
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

            batch.setProjectionMatrix(cam.combined);
            player.draw(batch);


            if (spawnTimer > spawnTime) {
                spawnTimer = 0;
                loadEnemies();
            } else {
                spawnTimer += deltaTime;
            }

            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).shouldRemove()) {
                    enemies.remove(i);
                    i--;
                } else {
                    player.update(deltaTime, batch, enemies.get(i));
                    enemies.get(i).update(deltaTime, player, batch);
                }

            }
            check_Bullet_Enemy_Overlap(player.getBullets());
            check_Player_Enemy_Overlap(enemies);

            if (playerHB.getCurrentHealth() <= 0) {
                stage.clear();
                currentScreen = Screen.GAME_OVER;
            }
            
            /**
             ** Set cam Position
             */
            Vector3 position = cam.position;
            position.x = cam.position.x + (player.getXPosition() * 1 - cam.position.x) * deltaTime;
            position.y = cam.position.y + (player.getYPosition() * 1 - cam.position.y) * deltaTime;
            cam.position.set(position);
            cam.update();

        } else if (currentScreen == Screen.GAME_OVER) {
            batch.begin();
            batch.setProjectionMatrix(UIcam.combined);
            batch.draw(new Texture("gameover.png"), Gdx.graphics.getWidth() * .10f, Gdx.graphics.getHeight() * .5f, 900, 210);

            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = font;
            textButtonStyle.fontColor = Color.WHITE;
            textButtonStyle.font.getData().setScale(4.0f);
            TextButton againBtn = new TextButton("Try Again", textButtonStyle);
            againBtn.setPosition((Gdx.graphics.getWidth() / 2 - againBtn.getWidth() / 2), (Gdx.graphics.getHeight() / 2 - againBtn.getHeight() / 2));
            stage.addActor(againBtn);
            againBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    stage.clear();
                    currentScreen = Screen.MENU;
                    restartGame();
                }
            });
            batch.end();

        }
        stage.act(deltaTime);
        stage.draw();



        /**
         * lerp: linear interpolation:
         *  -smooth camera motion that is not too rigid.
         *  -want to decrease jittery movement of the camera
         * camera_position  + (target_position - camera_position) * lerp
         *
         */

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
        sr.dispose();
        stage.dispose();
        skin.dispose();
        batch.dispose();
        player.dispose();
        playerHB.dispose();
        enemyTex(enemies);
    }


    public void loadEnemies() {
        /**
         * CREATE NEW ENEMIES
         *
         * 4 QUADRANTS
         *
         * 4 INTERCEPTS
         *
         */
        /**
         * -------------------------------------------------
         */

        if (enemies.size() > MAXIMUM_ENEMY_SIZE) {
            return;
        } else {
            enemies.add(new Enemy(2000, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(-2000, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(-2000, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(2000, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(0, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(0, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(2000, 0, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
            enemies.add(new Enemy(-2000, 0, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));

        }

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
