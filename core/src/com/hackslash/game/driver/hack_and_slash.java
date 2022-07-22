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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.hackslash.game.controller.Bullet_Controller;
import com.hackslash.game.controller.Enemy_Controller;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.Bullet_View;
import com.hackslash.game.view.Enemy_View;
import com.hackslash.game.view.Player_View;
import com.hackslash.game.view.UI_View;
import com.hackslash.game.view.Game_Object_View;
import com.hackslash.game.controller.Player_Controller;

import java.util.ArrayList;

public class hack_and_slash extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    OrthographicCamera cam;


    OrthographicCamera cam2;
    Sprite sprite;
    Sprite sprite2;
    float w, h; // viewport width and height.
    float radians;
    float dx;
    float dy;

    /*
     * Notes:
     *
     * cam position = (0,0)
     *
     *
     * */
    public void create() {

        dx = 0;
        dy = 0;
        radians = 0;
        batch = new SpriteBatch();
        img = new Texture("square.png");
        //the constructor does not take values for a sprite position. only texture regions, width, height
        sprite = new Sprite(img, 0, 0, 25, 25);
        sprite2 = new Sprite(img, 0, 0, 25, 25);

        sprite2.setPosition(0, 0);
        sprite.setPosition(100, 0);

        sprite.setColor(new Color(Color.GREEN));
        sprite2.setColor(new Color(Color.BLUE));
        //cam with viewports = to width and height of device screen
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //        w = cam.viewportWidth;
//        h = cam.viewportHeight;
//        cam.translate(cam.viewportWidth / 2, cam.viewportHeight / 2);
//        cam.position.set(sprite.getX(), sprite.getY(), 0);
//
//        cam.update();
//        sprite.setPosition((int) cam.position.x, (int) cam.position.y);

    }

    public void restartGame() {
    }


    //Note to self:
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        sprite.setPosition(sprite.getX() + 1, sprite.getY() + 1);
        cam.position.x = sprite.getX();
        cam.position.y = sprite.getY();
        cam.update();


        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        batch.begin();
        sprite2.draw(batch);

        batch.end();


//        moveToSprite(sprite2, sprite);
        sprite.setPosition(sprite.getX() + 1, sprite.getY());

//        sprite2.setPosition(sprite2.getX() + 4, sprite2.getY() + 1);
    }

    public void moveToSprite(Sprite s2, Sprite s1) {
        radians = MathUtils.atan2(s1.getY() - s2.getY(), s1.getX() - s2.getX());
        dx = MathUtils.cos(radians);
        dy = MathUtils.sin(radians);
        s2.setPosition(s2.getX() + 10 + dx * 100 * Gdx.graphics.getDeltaTime(), s2.getY() * dy * 20 * Gdx.graphics.getDeltaTime());
    }


    /**
     * This method is called every time the game screen is re-sized and the game is not in the paused state. It is also called once just after the create() method.
     * The parameters are the new width and height the screen has been resized to in pixels.
     */
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
    }

    /**
     * On Android this method is called when the Home button is pressed or an incoming call is received. On desktop this is called when the window is minimized and just before dispose() when exiting the application.
     * A good place to save the game state.
     */
    public void pause() {
//        GAME_PAUSED = !GAME_PAUSED;

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
//        enemyTex(enemies);
    }


    public void loadEnemies() {

    }

    public void Handle_Seen_Enemies() {
        //using a Queue to maintain the insertion order of my seen enemies

    }

    public void Handle_Unseen_Enemies() {

    }

    public void Player_Bullet_Enemy_Interaction(Enemy curr) {

    }


}
