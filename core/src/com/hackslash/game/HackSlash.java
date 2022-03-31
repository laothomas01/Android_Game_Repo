package com.hackslash.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

public class HackSlash extends ApplicationAdapter {

    ShapeRenderer shape;
    ArrayList<Enemy> enemies = new ArrayList<>();
    Random r = new Random();


    /**
     * main game loop
     * <p>
     * we should take all the current code and place it into a function
     */
    @Override
    public void create() {
        shape = new ShapeRenderer();
        for (int i = 0; i < 1; i++) {
            enemies.add(new Enemy(r.nextInt(Gdx.graphics.getWidth()),
                    r.nextInt(Gdx.graphics.getHeight()),
                    50, r.nextInt(15), r.nextInt(15)));
        }
    }


    @Override


    /**
    * renders the game at 60 frames per second
    */
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Enemy enemy : enemies) {
            /**
             * update the enemy's actions
             */
            enemy.update();

            enemy.draw(shape);
        }

        shape.end();
    }


}
