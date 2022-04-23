package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.model.Spawner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class HackAndSlash extends ApplicationAdapter {

    ShapeRenderer sr;
    Player player;
    OrthographicCamera cam;
    float player_x_Move;
    float player_y_Move;

    /**
     * -------TOUCH PAD------
     */
    private Stage stage;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    /**
     * -------------------------
     */

    float deltaTime;

    /**
     * Initialize Spawners
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

    public void create() {

        sr = new ShapeRenderer();
        player = new Player();

        skin = new Skin();
        skin.add("touchBackground", new Texture("touchBackground.png"));
        skin.add("touchKnob", new Texture("touchKnob.png"));

        touchBackground = skin.getDrawable("touchBackground");
        touchKnob = skin.getDrawable("touchKnob");

        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create TouchPad with the style
        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);

        //Create a Stage and add TouchPad
        stage = new Stage();
        stage.addActor(touchpad);

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
        player.draw(sr);

        stage.act(deltaTime);
        stage.draw();
        /**
         * Spawn Positions
         */
        quadrant1.spawnEnemies(e1, deltaTime, player, sr);
        quadrant2.spawnEnemies(e2, deltaTime, player, sr);
        quadrant3.spawnEnemies(e3, deltaTime, player, sr);
        quadrant4.spawnEnemies(e4, deltaTime, player, sr);

        Y_Intercept_Positive.spawnEnemies(e5, deltaTime, player, sr);
        Y_Intercept_Negative.spawnEnemies(e6, deltaTime, player, sr);
        X_Intercept_Positive.spawnEnemies(e7, deltaTime, player, sr);
        X_Intercept_Negative.spawnEnemies(e8, deltaTime, player, sr);

/**
 * Set cam Position
 */
        cam.position.x = player.getXPosition();
        cam.position.y = player.getYPosition();
        cam.update();
    }


    /**
     * This method is called every time the game screen is re-sized and the game is not in the paused state. It is also called once just after the create() method.
     * The parameters are the new width and height the screen has been resized to in pixels.
     */
    public void resize(int width, int height) {
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

    }


}
