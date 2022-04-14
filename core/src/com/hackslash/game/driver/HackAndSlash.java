package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hackslash.game.model.Camera;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;

import java.util.ArrayList;
import java.util.Random;

public class HackAndSlash extends ApplicationAdapter {

    ShapeRenderer sr;
    Player player;
    Enemy enemy;
    float xMove;
    float yMove;

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

    /**
     * Orthographic camera
     */
    OrthographicCamera cam;


    float deltaTime;

    SpriteBatch batch;


    /**
     * Method called once when the application is created.
     * <p>
     * like a Start() function in Unity
     */

    public void create() {
//        batch = new SpriteBatch();

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
        enemy = new Enemy();

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);


    }


    /**
     * Method called by the game loop from the application every time rendering should be performed. Game logic updates are usually also performed in this method.
     */
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /**
         * make game's frame rate independent
         */
        deltaTime = Gdx.graphics.getDeltaTime();
        /**
         * Update Player Movement
         */
        xMove = player.getXPosition() + touchpad.getKnobPercentX() * player.getPlayerSpeed() * deltaTime;
        yMove = player.getYPosition() + touchpad.getKnobPercentY() * player.getPlayerSpeed() * deltaTime;
        player.setXPosition(xMove);
        player.setYPosition(yMove);
        /**
         * draw objects
         */
        player.draw(sr);


//        enemy.draw(sr);
        enemy.update(deltaTime, player);


        stage.act(deltaTime);
        stage.draw();

        cam.position.set(player.getXPosition(), player.getYPosition(), 0);
        sr.setProjectionMatrix(cam.combined);

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


////    public static int WIDTH;
////    public static int HEIGHT;
////    private Vector3 touchPos;
////    public static OrthographicCamera cam;
////    /**
////     * using this class because i dont have sprites right now
////     */
////    private Rectangle square;
////    /**
////     * used for drawing 2D images we load.
////     * <p>
////     * SpriteBatch batch
////     */
////    ShapeRenderer shape;
////
////
////    /**
////     * temporary speed;
////     */
////
////    float maxSpeed;
////
////    /**
////     * used to load all our files
////     */
//    @Override
//    public void create() {
////        shape = new ShapeRenderer();
////        WIDTH = Gdx.graphics.getWidth();
////        HEIGHT = Gdx.graphics.getHeight();
////
////
////        /**
////         * load sprites here is you have any\
////         *  ex:
////         *     dropImage = new Texture(Gdx.files.internal("droplet.png"));
////         *       bucketImage = new Texture(Gdx.files.internal("bucket.png"));
////         */
////        /**
////         * load sound effects or music here is you have any
////         */
////
////        cam = new OrthographicCamera();
////        cam.setToOrtho(false, WIDTH, HEIGHT);
////
////        /**
////         * these values are measured in pixels.
////         */
////        square = new Rectangle();
////        square.x = 800 / 2 - 64 / 2;
////        square.y = 20;
////        square.width = 64;
////        square.height = 64;
////
////
////        touchPos = new Vector3();
//
//    }
//
//
//    @Override
//
//
//    /**
//     * renders the game at 60 frames per second
//     * basically the game loop
//     */
//    public void render() {
////        /**
////         * ScreenUtils.clear(r, g, b, a)
////         */
////        ScreenUtils.clear(0, 0, 0.2f, 1);
////
////        /**
////         * update camera once per frame
////         */
////        cam.update();
////        shape.setProjectionMatrix(cam.combined);
////        /**
////         * shape.begin() -> shape.end()
////         *  -records all drawing commands between begin and end.
////         *  -end submits all drawing requests we made at once.
////         */
////        shape.begin(ShapeRenderer.ShapeType.Filled);
////        shape.rect(square.x, square.y, square.width, square.height);
////        shape.end();
////
////
////        handle_controls();
//
//    }
//
//    public void handle_controls() {
//        /**
//         * temporary speed
//         */
//
////        if (Gdx.input.isTouched()) {
////
////            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
////            cam.unproject(touchPos);
////            square.x = touchPos.x - 64 / 2;
////            square.y = touchPos.y - 64 / 2;
////        }
////
////        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) square.x -= 200 * Gdx.graphics.getDeltaTime();
////        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) square.x += 200 * Gdx.graphics.getDeltaTime();
////
////        if (square.x < 0) {
////            square.x = 0;
////        }
////        if (square.x > 800 - 64) {
////            square.x = 800 - 64;
////        }
//
//
//    }


}
