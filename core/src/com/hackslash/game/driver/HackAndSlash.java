package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class HackAndSlash extends ApplicationAdapter {

    ShapeRenderer sr;
    Player player;
    
    private OrthographicCamera cam;
    private Vector3 pos;

	private ShapeRenderer healthBar;
	private Color cl;
	private float barWidth = 500f;

	private float playerHealth = 5f;


    /**
     * Method called once when the application is created.
     */

    public void create() {
        sr = new ShapeRenderer();
        player = new Player();
        
        
		healthBar = new ShapeRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


		pos = new Vector3((Gdx.graphics.getWidth()/2f),(Gdx.graphics.getHeight()/2f), 0);
    }


    /**
     * Method called by the game loop from the application every time rendering should be performed. Game logic updates are usually also performed in this method.
     */
    public void render() {
        cam.update();
        
        player.update(Gdx.graphics.getDeltaTime());
        player.draw(sr);
        
        if(Gdx.input.isTouched())
		{
			playerHealth -= 1f;
			barWidth -= 100f;
			cam.unproject(pos);
		}

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(playerHealth >= 4)
		{
			cl = Color.GREEN;
		}
		else if(playerHealth >= 2)
		{
			cl = Color.YELLOW;
		}
		else
		{
			cl = Color.RED;
		}

		healthBar.begin(ShapeRenderer.ShapeType.Filled);
		healthBar.setColor(cl);
		healthBar.rect(0, 0, barWidth,50);
		healthBar.end();
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
        sr.dispose();
		healthBar.dispose();
    }




}
